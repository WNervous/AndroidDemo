package com.meevii.patterndemo.chain;

public class ConcreteHandler extends Handler {

    @Override
    public void handlerRequest(String request) {
        if (true) {
            //
        } else {
            this.successor.handlerRequest(request);
        }
    }
}
