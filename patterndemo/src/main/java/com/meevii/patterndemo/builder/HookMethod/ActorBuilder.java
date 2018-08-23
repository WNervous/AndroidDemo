package com.meevii.patterndemo.builder.HookMethod;

import com.meevii.patterndemo.builder.actor.Actor;

public abstract class ActorBuilder {

    Actor actor = new Actor();

    abstract void buildType();

    abstract void buildSex();

    abstract void buildFace();

    abstract void buildCostume();

    abstract void buildHair();

    //钩子方法
    public boolean isBareheaded() {
        return false;
    }

    public Actor builder() {
        return actor;
    }
}
