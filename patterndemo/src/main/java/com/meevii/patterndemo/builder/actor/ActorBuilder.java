package com.meevii.patterndemo.builder.actor;

public abstract class ActorBuilder {

    Actor actor = new Actor();

    abstract void buildType();

    abstract void buildSex();

    abstract void buildFace();

    abstract void buildCostume();

    abstract void buildHair();

    public Actor builder() {
        return actor;
    }
}
