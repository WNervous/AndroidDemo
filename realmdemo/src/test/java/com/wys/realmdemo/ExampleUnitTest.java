package com.wys.realmdemo;

import com.wys.realmdemo.bean.Dog;

import org.junit.Test;

import io.realm.Realm;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addData() {
        Dog dog = new Dog();
        dog.setAge(18);
        dog.setName("jerry");
        Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.beginTransaction();
        defaultInstance.copyFromRealm(dog);
    }
}