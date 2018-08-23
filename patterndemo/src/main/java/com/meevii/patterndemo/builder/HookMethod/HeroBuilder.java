package com.meevii.patterndemo.builder.HookMethod;


public class HeroBuilder extends ActorBuilder {

    @Override
    void buildType() {
        actor.setType("Hero");
    }

    @Override
    void buildSex() {
        actor.setSex("Man");
    }

    @Override
    void buildFace() {
        actor.setFace("Handsome");
    }

    @Override
    void buildCostume() {
        actor.setCostume("Beauty");
    }

    @Override
    void buildHair() {
        actor.setHairstyle("black");
    }

    @Override
    public boolean isBareheaded() {
        return true;
    }
}
