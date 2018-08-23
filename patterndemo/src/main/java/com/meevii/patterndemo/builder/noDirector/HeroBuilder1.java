package com.meevii.patterndemo.builder.noDirector;


public class HeroBuilder1 extends ActorBuilderNoPara {

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
}
