/*
 * The MIT License
 *
 * Copyright 2017-2018. Norwegian University of Technology
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING  FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package no.mechatronics.sfi.fmu_proxy.grpc.services

import com.google.protobuf.Empty
import io.grpc.BindableService
import io.grpc.stub.StreamObserver
import no.mechatronics.sfi.fmi4j.common.FmiStatus
import no.mechatronics.sfi.fmi4j.fmu.FmuFile
import no.mechatronics.sfi.fmi4j.modeldescription.SimpleModelDescription
import no.mechatronics.sfi.fmu_proxy.fmu.Fmus
import no.mechatronics.sfi.fmu_proxy.grpc.FmuServiceGrpc
import no.mechatronics.sfi.fmu_proxy.grpc.GrpcFmuServer
import no.mechatronics.sfi.fmu_proxy.grpc.Proto
import org.apache.commons.math3.ode.FirstOrderIntegrator
import org.apache.commons.math3.ode.nonstiff.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface GrpcFmuService : BindableService {

    fun statusReply(status: FmiStatus, responseObserver: StreamObserver<Proto.Status>) {
        Proto.Status.newBuilder()
                .setCode(status.protoType())
                .build().also {
                    responseObserver.onNext(it)
                    responseObserver.onCompleted()
                }
    }

}


class GrpcFmuServiceImpl(
        private val fmuFile: FmuFile
): FmuServiceGrpc.FmuServiceImplBase(), GrpcFmuService {

    private val modelDescription: SimpleModelDescription
            = fmuFile.modelDescription

    override fun supportsModelExchange(request: Proto.UInt, responseObserver: StreamObserver<Proto.Bool>) {
        responseObserver.onNext(modelDescription.supportsModelExchange.protoType())
        responseObserver.onCompleted()
    }

    override fun supportsCoSimulation(request: Proto.UInt, responseObserver: StreamObserver<Proto.Bool>) {
        responseObserver.onNext(modelDescription.supportsCoSimulation.protoType())
        responseObserver.onCompleted()
    }

    override fun getModelDescription(request: Empty, responseObserver: StreamObserver<Proto.ModelDescription>) {
        responseObserver.onNext(modelDescription.protoType())
        responseObserver.onCompleted()
    }

    override fun createInstanceFromCS(req: Empty, responseObserver: StreamObserver<Proto.UInt>) {

        Fmus.put(fmuFile.asCoSimulationFmu().newInstance()).also { id ->
            Proto.UInt.newBuilder().setValue(id).build().also {
                responseObserver.onNext(it)
                responseObserver.onCompleted()
            }
        }

    }

    override fun createInstanceFromME(req: Proto.Integrator, responseObserver: StreamObserver<Proto.UInt>) {

        fun selectDefaultIntegrator(): FirstOrderIntegrator {
            val stepSize = fmuFile.modelDescription.defaultExperiment?.stepSize ?: 1E-3
            LOG.warn("No integrator specified.. Defaulting to Euler with $stepSize stepSize")
            return EulerIntegrator(stepSize)
        }

        val integrator = when (req.integratorsCase) {
            Proto.Integrator.IntegratorsCase.GILL -> GillIntegrator(req.gill.stepSize)
            Proto.Integrator.IntegratorsCase.EULER -> EulerIntegrator(req.euler.stepSize)
            Proto.Integrator.IntegratorsCase.MID_POINT -> MidpointIntegrator(req.midPoint.stepSize)
            Proto.Integrator.IntegratorsCase.RUNGE_KUTTA -> ClassicalRungeKuttaIntegrator(req.rungeKutta.stepSize)
            Proto.Integrator.IntegratorsCase.ADAMS_BASHFORTH -> req.adamsBashforth.let { AdamsBashforthIntegrator(it.nSteps, it.minStep, it.maxStep, it.scalAbsoluteTolerance, it.scalRelativeTolerance) }
            Proto.Integrator.IntegratorsCase.DORMAND_PRINCE54 -> req.dormandPrince54.let { DormandPrince54Integrator(it.minStep, it.maxStep, it.scalAbsoluteTolerance, it.scalRelativeTolerance) }
            else -> selectDefaultIntegrator()
        }

        Fmus.put(fmuFile.asModelExchangeFmu().newInstance(integrator)).also { id ->
            Proto.UInt.newBuilder().setValue(id).build().also {
                responseObserver.onNext(it)
                responseObserver.onCompleted()
            }
        }

    }

    override fun canGetAndSetFMUstate(req: Proto.UInt, responseObserver: StreamObserver<Proto.Bool>) {
        Fmus.get(req.value)?.apply {
            responseObserver.onNext(modelDescription.canGetAndSetFMUstate.protoType())
        }
        responseObserver.onCompleted()
    }


    override fun getModelDescriptionXml(request: Empty, responseObserver: StreamObserver<Proto.Str>) {

        Proto.Str.newBuilder().setValue(fmuFile.modelDescriptionXml).build().also {
            responseObserver.onNext(it)
            responseObserver.onCompleted()
        }

    }

    override fun getCurrentTime(req: Proto.UInt, responseObserver: StreamObserver<Proto.Real>) {

        Fmus.get(req.value)?.apply {
            responseObserver.onNext(currentTime.protoType())
        }
        responseObserver.onCompleted()

    }

    override fun isTerminated(req: Proto.UInt, responseObserver: StreamObserver<Proto.Bool>) {

        Fmus.get(req.value)?.apply {
            responseObserver.onNext(isTerminated.protoType())
        }
        responseObserver.onCompleted()

    }

    override fun readInteger(req: Proto.ReadRequest, responseObserver: StreamObserver<Proto.IntRead>) {

        Fmus.get(req.fmuId)?.apply {
            val valueReference = req.valueReference
            val read = variableAccessor.readInteger(valueReference)
            responseObserver.onNext(Proto.IntRead.newBuilder()
                    .setValue(read.value)
                    .setStatus(read.status.protoType())
                    .build())
        }
        responseObserver.onCompleted()

    }

    override fun bulkReadInteger(req: Proto.BulkReadRequest, responseObserver: StreamObserver<Proto.IntListRead>) {

        Fmus.get(req.fmuId)?.apply {
            val builder = Proto.IntListRead.newBuilder()
            val read = variableAccessor.readInteger(req.valueReferencesList.toIntArray())
            builder.status = read.status.protoType()
            for (value in read.value) {
                builder.addValues(value)
            }
            responseObserver.onNext(builder.build())
        }
        responseObserver.onCompleted()

    }

    override fun readReal(req: Proto.ReadRequest, responseObserver: StreamObserver<Proto.RealRead>) {

        Fmus.get(req.fmuId)?.apply {
            val valueReference = req.valueReference
            val read = variableAccessor.readReal(valueReference)
            responseObserver.onNext(Proto.RealRead.newBuilder()
                    .setValue(read.value)
                    .setStatus(read.status.protoType())
                    .build())
        }

        responseObserver.onCompleted()

    }

    override fun bulkReadReal(req: Proto.BulkReadRequest, responseObserver: StreamObserver<Proto.RealListRead>) {

        Fmus.get(req.fmuId)?.apply {
            val builder = Proto.RealListRead.newBuilder()
            val read = variableAccessor.readReal(req.valueReferencesList.toIntArray())
            builder.status = read.status.protoType()
            for (value in read.value) {
                builder.addValues(value)
            }
            responseObserver.onNext(builder.build())
        }
        responseObserver.onCompleted()

    }

    override fun readString(req: Proto.ReadRequest, responseObserver: StreamObserver<Proto.StrRead>) {

        Fmus.get(req.fmuId)?.apply {
            val read = variableAccessor.readString(req.valueReference)
            responseObserver.onNext(Proto.StrRead.newBuilder()
                    .setValue(read.value)
                    .setStatus(read.status.protoType())
                    .build())
        }

        responseObserver.onCompleted()

    }

    override fun bulkReadString(req: Proto.BulkReadRequest, responseObserver: StreamObserver<Proto.StrListRead>) {

        Fmus.get(req.fmuId)?.apply {
            val builder = Proto.StrListRead.newBuilder()
            val read = variableAccessor.readString(req.valueReferencesList.toIntArray())
            builder.status = read.status.protoType()
            for (value in read.value) {
                builder.addValues(value)
            }
            responseObserver.onNext(builder.build())
        }
        responseObserver.onCompleted()

    }

    override fun readBoolean(req: Proto.ReadRequest, responseObserver: StreamObserver<Proto.BoolRead>) {

        Fmus.get(req.fmuId)?.apply {
            val read = variableAccessor.readBoolean(req.valueReference)
            responseObserver.onNext(Proto.BoolRead.newBuilder()
                    .setValue(read.value)
                    .setStatus(read.status.protoType())
                    .build())
        }

        responseObserver.onCompleted()

    }

    override fun bulkReadBoolean(req: Proto.BulkReadRequest, responseObserver: StreamObserver<Proto.BoolListRead>) {

        Fmus.get(req.fmuId)?.apply {
            val builder = Proto.BoolListRead.newBuilder()
            val read = variableAccessor.readBoolean(req.valueReferencesList.toIntArray())
            builder.status = read.status.protoType()
            for (value in read.value) {
                builder.addValues(value)
            }
            responseObserver.onNext(builder.build())
        }
        responseObserver.onCompleted()

    }

    override fun writeInteger(req: Proto.WriteIntRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeInteger(req.valueReference, req.value)
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)

    }

    override fun bulkWriteInteger(req: Proto.BulkWriteIntRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeInteger(req.valueReferencesList.toIntArray(), req.valuesList.toIntArray())
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)

    }

    override fun writeReal(req: Proto.WriteRealRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeReal(req.valueReference, req.value)
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)


    }

    override fun bulkWriteReal(req: Proto.BulkWriteRealRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeReal(req.valueReferencesList.toIntArray(), req.valuesList.toDoubleArray())
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)

    }

    override fun writeString(req: Proto.WriteStrRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeString(req.valueReference, req.value)
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)


    }

    override fun bulkWriteString(req: Proto.BulkWriteStrRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeString(req.valueReferencesList.toIntArray(), req.valuesList.toTypedArray())
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)

    }

    override fun writeBoolean(req: Proto.WriteBoolRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeBoolean(req.valueReference, req.value)
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)


    }

    override fun bulkWriteBoolean(req: Proto.BulkWriteBoolRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            val status = variableAccessor.writeBoolean(req.valueReferencesList.toIntArray(), req.valuesList.toBooleanArray())
            statusReply(status, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)

    }

    override fun init(req: Proto.InitRequest, responseObserver: StreamObserver<Proto.Bool>) {

        var init = false
        Fmus.get(req.fmuId)?.apply {
            val start = req.start
            val stop = req.stop
            val hasStart = start > 0
            val hasStop = stop > 0 && stop > start
            init = if (hasStart && hasStop) {
                init(start, stop)
            } else if (hasStart && hasStop) {
                init(start)
            } else {
                init()
            }
        }

        Proto.Bool.newBuilder().setValue(init).build().also {
            responseObserver.onNext(it)
            responseObserver.onCompleted()
        }

    }

    override fun step(req: Proto.StepRequest, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.fmuId)?.apply {
            doStep(req.stepSize)
            statusReply(lastStatus, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)

    }

    override fun terminate(req: Proto.UInt, responseObserver: StreamObserver<Proto.Bool>) {

        var success = false
        val fmuId = req.value
        Fmus.remove(fmuId)?.apply {
            success = terminate()
            LOG.debug("Terminated fmu with success: $success")
        }
        Proto.Bool.newBuilder().setValue(success).build().also {
            responseObserver.onNext(it)
            responseObserver.onCompleted()
        }

    }

    override fun reset(req: Proto.UInt, responseObserver: StreamObserver<Proto.Status>) {

        Fmus.get(req.value)?.apply {
            reset()
            statusReply(lastStatus, responseObserver)
        } ?: statusReply(FmiStatus.Error, responseObserver)

    }

    private companion object {

        val LOG: Logger = LoggerFactory.getLogger(GrpcFmuServer::class.java)
    }

}

