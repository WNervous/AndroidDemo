package com.meevii.patterndemo.clone;

public class Client {
    /**
     *
     *
     *
     * 一般而言，Java语言中的clone()方法满足：
     (1) 对任何对象x，都有x.clone() != x，即克隆对象与原型对象不是同一个对象；
     (2) 对任何对象x，都有x.clone().getClass() == x.getClass()，即克隆对象与原型对象的类型一样；
     (3) 如果对象x的equals()方法定义恰当，那么x.clone().equals(x)应该成立。
     为了获取对象的一份拷贝，我们可以直接利用Object类的clone()方法，具体步骤如下：
     (1) 在派生类中覆盖基类的clone()方法，并声明为public；
     (2) 在派生类的clone()方法中，调用super.clone()；
     (3)派生类需实现Cloneable接口。
     此时，Object类相当于抽象原型类，所有实现了Cloneable接口的类相当于具体原型类。
     *
     *
     *
     *
     * @return
     */
    public static Prototype clones() {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        concretePrototype.setName("Zhang 3");
        concretePrototype.clones();
        return concretePrototype;
    }

    /**
     * 通过Java 的clone 方法实现
     *
     * @return
     */
    public static Prototype javaClones() {
        Prototype concretePrototype = new ConcretePrototype1();
        Prototype obj2 = concretePrototype.clones();
        return obj2;
    }
}
