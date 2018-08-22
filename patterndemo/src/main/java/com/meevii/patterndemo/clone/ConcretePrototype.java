package com.meevii.patterndemo.clone;

public class ConcretePrototype implements Prototype {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Prototype clones() {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        concretePrototype.setName(this.name);
        return concretePrototype;
    }
}
