

    @Override
    public void write{{varName2}} ({{dataType}}Write req, StreamObserver<Status> responseObserver) {

        FmiSimulation fmu = fmus.get(req.getFmuId());
        statusReply(fmu.write("{{varName1}}").with(req.getValue()), responseObserver);

        }