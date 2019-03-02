/*
 * The MIT License
 *
 * Copyright 2017-2018 Norwegian University of Technology
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

#ifndef FMU_PROXY_GRPCCLIENT_HPP
#define FMU_PROXY_GRPCCLIENT_HPP

#include <memory>
#include <string>

#include <grpcpp/grpcpp.h>

#include "RemoteFmuSlave.hpp"
#include "../common/service.grpc.pb.h"


namespace fmuproxy::grpc::client {

    class RemoteGrpcFmu {

    private:

        const std::string fmuId_;
        std::shared_ptr<FmuService::Stub> stub_;
        std::shared_ptr<const fmi4cpp::fmi2::ModelDescriptionBase> modelDescription_;

    public:

        RemoteGrpcFmu(const std::string &fmuId, std::shared_ptr<FmuService::Stub> stub);

        std::shared_ptr<const fmi4cpp::fmi2::ModelDescriptionBase> &getModelDescription();

        std::unique_ptr<RemoteFmuSlave> newInstance();

    };

    class GrpcClient {

    private:

        std::shared_ptr<FmuService::Stub> stub_;


    public:
        GrpcClient(const std::string &host, unsigned int port);

        RemoteGrpcFmu fromUrl(const std::string &url);

        RemoteGrpcFmu fromFile(const std::string &file);

        RemoteGrpcFmu fromGuid(const std::string &guid);

    };


}

#endif //FMU_PROXY_GRPCCLIENT_HPP
