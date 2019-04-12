package com.meevii.patterndemo.memento;

public class Originator {
    private String state;

    public Memento createMemento() {
        return new Memento(this);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    // 根据备忘录对象恢复原发器状态
    public void restoreMemento(Memento m) {
        state = m.getState();
    }


}
