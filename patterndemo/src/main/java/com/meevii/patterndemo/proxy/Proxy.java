package com.meevii.patterndemo.proxy;

public class Proxy extends Subject {
    RealSubject realSubject = new RealSubject();

    private void preRequest() {

    }


    @Override
    void request() {
        preRequest();
        realSubject.request();
        postRequest();
    }

    private void postRequest() {

    }
}
