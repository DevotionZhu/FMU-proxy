# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: service.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
import definitions_pb2 as definitions__pb2


DESCRIPTOR = _descriptor.FileDescriptor(
  name='service.proto',
  package='no.mechatronics.sfi.fmu_proxy.grpc',
  syntax='proto3',
  serialized_pb=_b('\n\rservice.proto\x12\"no.mechatronics.sfi.fmu_proxy.grpc\x1a\x1bgoogle/protobuf/empty.proto\x1a\x11\x64\x65\x66initions.proto2\xbc\x1a\n\nFmuService\x12]\n\x14\x43reateInstanceFromCS\x12\x16.google.protobuf.Empty\x1a-.no.mechatronics.sfi.fmu_proxy.grpc.UIntProto\x12z\n\x14\x43reateInstanceFromME\x12\x33.no.mechatronics.sfi.fmu_proxy.grpc.IntegratorProto\x1a-.no.mechatronics.sfi.fmu_proxy.grpc.UIntProto\x12O\n\x07GetGuid\x12\x16.google.protobuf.Empty\x1a,.no.mechatronics.sfi.fmu_proxy.grpc.StrProto\x12T\n\x0cGetModelName\x12\x16.google.protobuf.Empty\x1a,.no.mechatronics.sfi.fmu_proxy.grpc.StrProto\x12^\n\x16GetModelDescriptionXml\x12\x16.google.protobuf.Empty\x1a,.no.mechatronics.sfi.fmu_proxy.grpc.StrProto\x12\x64\n\x11GetModelStructure\x12\x16.google.protobuf.Empty\x1a\x37.no.mechatronics.sfi.fmu_proxy.grpc.ModelStructureProto\x12\x66\n\x11GetModelVariables\x12\x16.google.protobuf.Empty\x1a\x37.no.mechatronics.sfi.fmu_proxy.grpc.ScalarVariableProto0\x01\x12n\n\x0eGetCurrentTime\x12-.no.mechatronics.sfi.fmu_proxy.grpc.UIntProto\x1a-.no.mechatronics.sfi.fmu_proxy.grpc.RealProto\x12l\n\x0cIsTerminated\x12-.no.mechatronics.sfi.fmu_proxy.grpc.UIntProto\x1a-.no.mechatronics.sfi.fmu_proxy.grpc.BoolProto\x12k\n\x04Init\x12\x34.no.mechatronics.sfi.fmu_proxy.grpc.InitRequestProto\x1a-.no.mechatronics.sfi.fmu_proxy.grpc.BoolProto\x12m\n\x04Step\x12\x34.no.mechatronics.sfi.fmu_proxy.grpc.StepRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12i\n\tTerminate\x12-.no.mechatronics.sfi.fmu_proxy.grpc.UIntProto\x1a-.no.mechatronics.sfi.fmu_proxy.grpc.BoolProto\x12g\n\x05Reset\x12-.no.mechatronics.sfi.fmu_proxy.grpc.UIntProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12u\n\x0bReadInteger\x12\x34.no.mechatronics.sfi.fmu_proxy.grpc.ReadRequestProto\x1a\x30.no.mechatronics.sfi.fmu_proxy.grpc.IntReadProto\x12s\n\x08ReadReal\x12\x34.no.mechatronics.sfi.fmu_proxy.grpc.ReadRequestProto\x1a\x31.no.mechatronics.sfi.fmu_proxy.grpc.RealReadProto\x12t\n\nReadString\x12\x34.no.mechatronics.sfi.fmu_proxy.grpc.ReadRequestProto\x1a\x30.no.mechatronics.sfi.fmu_proxy.grpc.StrReadProto\x12v\n\x0bReadBoolean\x12\x34.no.mechatronics.sfi.fmu_proxy.grpc.ReadRequestProto\x1a\x31.no.mechatronics.sfi.fmu_proxy.grpc.BoolReadProto\x12\x81\x01\n\x0f\x42ulkReadInteger\x12\x38.no.mechatronics.sfi.fmu_proxy.grpc.BulkReadRequestProto\x1a\x34.no.mechatronics.sfi.fmu_proxy.grpc.IntListReadProto\x12\x7f\n\x0c\x42ulkReadReal\x12\x38.no.mechatronics.sfi.fmu_proxy.grpc.BulkReadRequestProto\x1a\x35.no.mechatronics.sfi.fmu_proxy.grpc.RealListReadProto\x12\x80\x01\n\x0e\x42ulkReadString\x12\x38.no.mechatronics.sfi.fmu_proxy.grpc.BulkReadRequestProto\x1a\x34.no.mechatronics.sfi.fmu_proxy.grpc.StrListReadProto\x12\x82\x01\n\x0f\x42ulkReadBoolean\x12\x38.no.mechatronics.sfi.fmu_proxy.grpc.BulkReadRequestProto\x1a\x35.no.mechatronics.sfi.fmu_proxy.grpc.BoolListReadProto\x12}\n\x0cWriteInteger\x12<.no.mechatronics.sfi.fmu_proxy.grpc.WriteIntegerRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12w\n\tWriteReal\x12\x39.no.mechatronics.sfi.fmu_proxy.grpc.WriteRealRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12{\n\x0bWriteString\x12;.no.mechatronics.sfi.fmu_proxy.grpc.WriteStringRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12}\n\x0cWriteBoolean\x12<.no.mechatronics.sfi.fmu_proxy.grpc.WriteBooleanRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12\x85\x01\n\x10\x42ulkWriteInteger\x12@.no.mechatronics.sfi.fmu_proxy.grpc.BulkWriteIntegerRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12\x7f\n\rBulkWriteReal\x12=.no.mechatronics.sfi.fmu_proxy.grpc.BulkWriteRealRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12\x83\x01\n\x0f\x42ulkWriteString\x12?.no.mechatronics.sfi.fmu_proxy.grpc.BulkWriteStringRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProto\x12\x85\x01\n\x10\x42ulkWriteBoolean\x12@.no.mechatronics.sfi.fmu_proxy.grpc.BulkWriteBooleanRequestProto\x1a/.no.mechatronics.sfi.fmu_proxy.grpc.StatusProtob\x06proto3')
  ,
  dependencies=[google_dot_protobuf_dot_empty__pb2.DESCRIPTOR,definitions__pb2.DESCRIPTOR,])



_sym_db.RegisterFileDescriptor(DESCRIPTOR)



_FMUSERVICE = _descriptor.ServiceDescriptor(
  name='FmuService',
  full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService',
  file=DESCRIPTOR,
  index=0,
  options=None,
  serialized_start=102,
  serialized_end=3490,
  methods=[
  _descriptor.MethodDescriptor(
    name='CreateInstanceFromCS',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.CreateInstanceFromCS',
    index=0,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=definitions__pb2._UINTPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='CreateInstanceFromME',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.CreateInstanceFromME',
    index=1,
    containing_service=None,
    input_type=definitions__pb2._INTEGRATORPROTO,
    output_type=definitions__pb2._UINTPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='GetGuid',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.GetGuid',
    index=2,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=definitions__pb2._STRPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='GetModelName',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.GetModelName',
    index=3,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=definitions__pb2._STRPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='GetModelDescriptionXml',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.GetModelDescriptionXml',
    index=4,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=definitions__pb2._STRPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='GetModelStructure',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.GetModelStructure',
    index=5,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=definitions__pb2._MODELSTRUCTUREPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='GetModelVariables',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.GetModelVariables',
    index=6,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=definitions__pb2._SCALARVARIABLEPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='GetCurrentTime',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.GetCurrentTime',
    index=7,
    containing_service=None,
    input_type=definitions__pb2._UINTPROTO,
    output_type=definitions__pb2._REALPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='IsTerminated',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.IsTerminated',
    index=8,
    containing_service=None,
    input_type=definitions__pb2._UINTPROTO,
    output_type=definitions__pb2._BOOLPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='Init',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.Init',
    index=9,
    containing_service=None,
    input_type=definitions__pb2._INITREQUESTPROTO,
    output_type=definitions__pb2._BOOLPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='Step',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.Step',
    index=10,
    containing_service=None,
    input_type=definitions__pb2._STEPREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='Terminate',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.Terminate',
    index=11,
    containing_service=None,
    input_type=definitions__pb2._UINTPROTO,
    output_type=definitions__pb2._BOOLPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='Reset',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.Reset',
    index=12,
    containing_service=None,
    input_type=definitions__pb2._UINTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='ReadInteger',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.ReadInteger',
    index=13,
    containing_service=None,
    input_type=definitions__pb2._READREQUESTPROTO,
    output_type=definitions__pb2._INTREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='ReadReal',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.ReadReal',
    index=14,
    containing_service=None,
    input_type=definitions__pb2._READREQUESTPROTO,
    output_type=definitions__pb2._REALREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='ReadString',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.ReadString',
    index=15,
    containing_service=None,
    input_type=definitions__pb2._READREQUESTPROTO,
    output_type=definitions__pb2._STRREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='ReadBoolean',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.ReadBoolean',
    index=16,
    containing_service=None,
    input_type=definitions__pb2._READREQUESTPROTO,
    output_type=definitions__pb2._BOOLREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkReadInteger',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkReadInteger',
    index=17,
    containing_service=None,
    input_type=definitions__pb2._BULKREADREQUESTPROTO,
    output_type=definitions__pb2._INTLISTREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkReadReal',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkReadReal',
    index=18,
    containing_service=None,
    input_type=definitions__pb2._BULKREADREQUESTPROTO,
    output_type=definitions__pb2._REALLISTREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkReadString',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkReadString',
    index=19,
    containing_service=None,
    input_type=definitions__pb2._BULKREADREQUESTPROTO,
    output_type=definitions__pb2._STRLISTREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkReadBoolean',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkReadBoolean',
    index=20,
    containing_service=None,
    input_type=definitions__pb2._BULKREADREQUESTPROTO,
    output_type=definitions__pb2._BOOLLISTREADPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='WriteInteger',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.WriteInteger',
    index=21,
    containing_service=None,
    input_type=definitions__pb2._WRITEINTEGERREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='WriteReal',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.WriteReal',
    index=22,
    containing_service=None,
    input_type=definitions__pb2._WRITEREALREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='WriteString',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.WriteString',
    index=23,
    containing_service=None,
    input_type=definitions__pb2._WRITESTRINGREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='WriteBoolean',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.WriteBoolean',
    index=24,
    containing_service=None,
    input_type=definitions__pb2._WRITEBOOLEANREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkWriteInteger',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkWriteInteger',
    index=25,
    containing_service=None,
    input_type=definitions__pb2._BULKWRITEINTEGERREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkWriteReal',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkWriteReal',
    index=26,
    containing_service=None,
    input_type=definitions__pb2._BULKWRITEREALREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkWriteString',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkWriteString',
    index=27,
    containing_service=None,
    input_type=definitions__pb2._BULKWRITESTRINGREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='BulkWriteBoolean',
    full_name='no.mechatronics.sfi.fmu_proxy.grpc.FmuService.BulkWriteBoolean',
    index=28,
    containing_service=None,
    input_type=definitions__pb2._BULKWRITEBOOLEANREQUESTPROTO,
    output_type=definitions__pb2._STATUSPROTO,
    options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_FMUSERVICE)

DESCRIPTOR.services_by_name['FmuService'] = _FMUSERVICE

# @@protoc_insertion_point(module_scope)
