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

#include <fmuproxy/fmi/FmuInstance.hpp>

using namespace std;
using namespace fmuproxy::fmi;

const double RELATIVE_TOLERANCE = 1E-4;

template <typename A, typename B>
inline void checkSize(vector<A> v1, vector<B> v2) {
    if (v1.size() != v2.size()) {
        throw runtime_error("Vector sizes not equal!");
    }
}


FmuInstance::FmuInstance(fmi2_import_t *instance, ModelDescription &md)
        : instance(instance), modelDescription(md) {}

double FmuInstance::getCurrentTime() const {
    return current_time;
}

bool FmuInstance::isTerminated() const {
    return terminated;
}

ModelDescription &FmuInstance::getModelDescription() const {
    return modelDescription;
}

void FmuInstance::init() {
    return init(0.0);
}

void FmuInstance::init(double start) {
    return init(start, 0.0);
}

void FmuInstance::init(double start, double stop) {

    fmi2_boolean_t stop_time_defined = start < stop;
    fmi2_status_t status = fmi2_import_setup_experiment(instance, fmi2_true,
                                                        RELATIVE_TOLERANCE, start, stop_time_defined, stop);
    if(status != fmi2_status_ok) {
        throw runtime_error("fmi2_import_setup_experiment failed");
    }

    status = fmi2_import_enter_initialization_mode(instance);
    if(status != fmi2_status_ok) {
        throw runtime_error("fmi2_import_enter_initialization_mode failed");
    }

    status = fmi2_import_exit_initialization_mode(instance);
    if(status != fmi2_status_ok) {
        throw runtime_error("fmi2_import_exit_initialization_mode failed");
    }

}

fmi2_status_t FmuInstance::step(double step_size) {
    fmi2_status_t status = fmi2_import_do_step(
            instance, current_time, step_size, fmi2_true);
    if (status == fmi2_status_ok) {
        current_time += step_size;
    }
    return status;
}

fmi2_status_t FmuInstance::reset() {
    return fmi2_import_reset(instance);
}

fmi2_status_t FmuInstance::terminate() {
    if (!terminated) {
        terminated = true;
        return fmi2_import_terminate(instance);
    }
    return fmi2_status_ok;
}


fmi2_status_t FmuInstance::readInteger(const fmi2_value_reference_t vr, fmi2_integer_t &ref) {
    return fmi2_import_get_integer(instance, &vr, 1, &ref);
}

fmi2_status_t FmuInstance::readInteger(const vector<fmi2_value_reference_t> &vr, vector<fmi2_integer_t> &ref) {
    if (vr.size() != ref.size()) {
        throw runtime_error("vr.size() != ref.size()!");
    }
    return fmi2_import_get_integer(instance, vr.data(), vr.size(), ref.data());
}


fmi2_status_t FmuInstance::readReal(const fmi2_value_reference_t vr, fmi2_real_t &ref) {
    return fmi2_import_get_real(instance, &vr, 1, &ref);
}

fmi2_status_t FmuInstance::readReal(const vector<fmi2_value_reference_t> &vr, vector<fmi2_real_t> &ref) {
    checkSize(vr, ref);
    return fmi2_import_get_real(instance, vr.data(), vr.size(), ref.data());
}

fmi2_status_t FmuInstance::readString(const fmi2_value_reference_t vr, fmi2_string_t &ref) {
    return fmi2_import_get_string(instance, &vr, 1, &ref);
}

fmi2_status_t FmuInstance::readString(const std::vector<fmi2_value_reference_t> &vr, std::vector<fmi2_string_t> &ref) {
    checkSize(vr, ref);
    return fmi2_import_get_string(instance, vr.data(), vr.size(), ref.data());
}

fmi2_status_t FmuInstance::readBoolean(const fmi2_value_reference_t vr, fmi2_boolean_t &ref) {
    return fmi2_import_get_boolean(instance, &vr, 1, &ref);
}

fmi2_status_t FmuInstance::readBoolean(const std::vector<fmi2_value_reference_t> &vr, std::vector<fmi2_boolean_t> &ref) {
    checkSize(vr, ref);
    return fmi2_import_get_boolean(instance, vr.data(), vr.size(), ref.data());
}

fmi2_status_t FmuInstance::writeInteger(const fmi2_value_reference_t vr, const fmi2_integer_t value) {
    return fmi2_import_set_integer(instance, &vr, 1, &value);
}

fmi2_status_t FmuInstance::writeInteger(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_integer_t> &value) {
    checkSize(vr, value);
    return fmi2_import_set_integer(instance, vr.data(), vr.size(), value.data());
}

fmi2_status_t FmuInstance::writeReal(const fmi2_value_reference_t vr, const fmi2_real_t value) {
    return fmi2_import_set_real(instance, &vr, 1, &value);
}

fmi2_status_t FmuInstance::writeReal(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_real_t> &value) {
    checkSize(vr, value);
    return fmi2_import_set_real(instance, vr.data(), vr.size(), value.data());
}

fmi2_status_t FmuInstance::writeString(const fmi2_value_reference_t vr, const fmi2_string_t value) {
    return fmi2_import_set_string(instance, &vr, 1, &value);
}

fmi2_status_t FmuInstance::writeString(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_string_t> &value) {
    checkSize(vr, value);
    return fmi2_import_set_string(instance, vr.data(), vr.size(), value.data());
}

fmi2_status_t FmuInstance::writeBoolean(const fmi2_value_reference_t vr, const fmi2_boolean_t value) {
    return fmi2_import_set_boolean(instance, &vr, 1, &value);
}

fmi2_status_t FmuInstance::writeBoolean(const std::vector<fmi2_value_reference_t> &vr, const std::vector<fmi2_boolean_t> &value) {
    checkSize(vr, value);
    return fmi2_import_set_boolean(instance, vr.data(), vr.size(), value.data());
}


FmuInstance::~FmuInstance()  {

    std::cout << "FmuInstance destructor called" << std::endl;

    terminate();
    fmi2_import_destroy_dllfmu(instance);
    fmi2_import_free(instance);

}











