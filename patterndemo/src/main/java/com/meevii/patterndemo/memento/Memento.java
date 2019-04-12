package com.meevii.patterndemo.memento;

public class Memento {
    private String state;

    public Memento(Originator o) {
        state = o.getState();
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
