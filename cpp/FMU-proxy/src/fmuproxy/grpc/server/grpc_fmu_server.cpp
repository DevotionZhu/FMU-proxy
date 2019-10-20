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

#include <iostream>
#include <grpcpp/grpcpp.h>
#include <fmuproxy/grpc/server/grpc_fmu_server.hpp>

using grpc::Server;
using grpc::ServerBuilder;

using namespace std;
using namespace fmuproxy::grpc::server;

grpc_fmu_server::grpc_fmu_server(unordered_map<string, std::shared_ptr<fmi4cpp::fmi2::cs_fmu>> &fmus, const unsigned int port)
        : port_(port), service_(make_shared<fmu_service_impl>(fmus)) {}

void grpc_fmu_server::wait() {
    server_->Wait();
}

void grpc_fmu_server::start() {

    cout << "gRPC server listening to connections on port: " << port_ << endl;
    ServerBuilder builder;
    builder.AddListeningPort("localhost:" + to_string(port_), ::grpc::InsecureServerCredentials());
    builder.RegisterService(service_.get());
    server_ = move(builder.BuildAndStart());
    thread_ = make_unique<thread>(&grpc_fmu_server::wait, this);
}

void grpc_fmu_server::stop() {
    server_->Shutdown();
    thread_->join();
    cout << "gRPC server stopped.." << endl;
}
