package com.meevii.patterndemo.builder.HookMethod;

import com.meevii.patterndemo.builder.actor.Actor;

public class ActorDirector {

    public Actor create(ActorBuilder actorBuilder) {
        actorBuilder.buildType();
        actorBuilder.buildCostume();
        actorBuilder.buildFace();
        if (!actorBuilder.isBareheaded()) {
            actorBuilder.buildHair();
        }
        actorBuilder.buildSex();
        return actorBuilder.builder();
    }
}
