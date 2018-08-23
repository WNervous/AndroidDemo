package com.meevii.patterndemo.builder.actor;

public class ActorDirector {

    public Actor create(ActorBuilder actorBuilder) {
        actorBuilder.buildType();
        actorBuilder.buildCostume();
        actorBuilder.buildFace();
        actorBuilder.buildHair();
        actorBuilder.buildSex();
        return actorBuilder.builder();
    }
}
