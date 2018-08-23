package com.meevii.patterndemo.builder.actor;

public class DevilBuilder extends ActorBuilder {
    @Override
    void buildType() {
        actor.setType("Devil");
    }

    @Override
    void buildSex() {
        actor.setType("不男不女");
    }

    @Override
    void buildFace() {
        actor.setType("恶心的");
    }

    @Override
    void buildCostume() {
        actor.setCostume("奇怪的");
    }

    @Override
    void buildHair() {
        actor.setHairstyle("黄色");
    }
}
