
    @Override
    public void read{{varName2}}(ModelReference req, StreamObserver<{{returnType}}> responseObserver) {

        FmiSimulation fmu = fmus.get(req.getFmuId());
        {{primitive1}} read = fmu.read("{{varName1}}").as{{primitive2}}();
        {{returnType}} reply = {{returnType}}.newBuilder().setValue(read).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }