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
#include <fmuproxy/fmi/Fmu.hpp>
#include <fmuproxy/heartbeat/Heartbeat.hpp>
#include <fmuproxy/thrift/server/ThriftServer.hpp>
#include "boost/program_options.hpp"
#include "RemoteAddress.hpp"

using namespace std;
using namespace fmuproxy::fmi;
using namespace fmuproxy::heartbeat;
using namespace fmuproxy::thrift::server;

namespace {

    const int SUCCESS = 0;
    const int COMMANDLINE_ERROR = 1;
    const int UNHANDLED_ERROR = 2;

    void wait_for_input() {
        do {
            cout << '\n' << "Press a key to continue...\n";
        } while (cin.get() != '\n');
        cout << "Done." << endl;
    }

    int run_application(string &fmu_path, const unsigned int thrift_port, shared_ptr<RemoteAddress> remote) {

        bool has_remote = remote != nullptr;

        Fmu fmu(fmu_path);

        ThriftServer server(fmu, thrift_port);
        server.start();

        unique_ptr<Heartbeat> beat = nullptr;
        if (has_remote) {
            beat = make_unique<Heartbeat>(remote->host, remote->port, fmu.get_model_description_xml());
            beat->start();
        }

        wait_for_input();
        server.stop();

        if (has_remote) {
            beat->stop();
        }

        return 0;

    }

}


int main(int argc, char** argv) {

    try {

        namespace po = boost::program_options;

        po::options_description desc("Options");
        desc.add_options()
                ("help,h", "Print this help message and quits.")
                ("fmu,f", po::value<string>()->required(), "Path to FMU")
                ("remote,r", po::value<string>(), "IP address of the remote tracking server")
                ("thrift_port,t", po::value<unsigned int>()->required(), "Specify the network port to be used by the Thrift server");

        po::variables_map vm;
        try {

            po::store(po::parse_command_line(argc, argv, desc), vm);

            if ( vm.count("help") ) {
                cout << "FMU-proxy" << endl << desc << endl;
                return SUCCESS;
            }

            po::notify(vm);

        } catch(po::error& e) {
            std::cerr << "ERROR: " << e.what() << std::endl << std::endl;
            std::cerr << desc << std::endl;
            return COMMANDLINE_ERROR;
        }

        string fmu_path = vm["fmu"].as<string>();
        unsigned int thrift_port = vm["thrift_port"].as<unsigned int>();

        shared_ptr<RemoteAddress> remote = nullptr;
        if (vm.count("remote")) {
            string str = vm["remote"].as<string>();
            auto parse = RemoteAddress::parse (str);
            remote = shared_ptr<RemoteAddress>(&parse);
        }

        return run_application(fmu_path, thrift_port, remote);


    } catch(std::exception& e) {
        std::cerr << "Unhandled Exception reached the top of main: " << e.what() << ", application will now exit" << std::endl;
        return UNHANDLED_ERROR;
    }

}