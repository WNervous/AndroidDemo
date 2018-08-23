package com.meevii.patterndemo.builder.noDirector;

import com.meevii.patterndemo.builder.actor.Actor;

/**
 * 1.省略Director
 * 在有些情况下，为了简化系统结构，可以将Director和抽象建造者Builder进行合并，
 * 在Builder中提供逐步构建复杂产品对象的construct()方法。由于Builder类通常为抽象类，
 * 因此可以将construct()方法定义为静态(static)方法。
 * 如果将游戏角色设计中的指挥者类ActorController省略，ActorBuilder类的代码修改如下：
 */
public abstract class ActorBuilderNoPara {

    Actor actor = new Actor();

    abstract void buildType();

    abstract void buildSex();

    abstract void buildFace();

    abstract void buildCostume();

    abstract void buildHair();

    public Actor builder() {
        this.buildType();
        this.buildCostume();
        this.buildFace();
        this.buildHair();
        this.buildSex();
        return actor;
    }

}
