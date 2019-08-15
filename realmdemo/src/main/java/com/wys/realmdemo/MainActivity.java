package com.wys.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wys.realmdemo.bean.Dog;
import com.wys.realmdemo.bean.Person;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private int index = 1;
    Realm defaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultInstance = Realm.getDefaultInstance();
    }

    public void getData(View view) {
        defaultInstance = Realm.getDefaultInstance();
        RealmResults<Dog> puppies = defaultInstance.where(Dog.class).findAll();
        Log.d("MainActivity", "size:" + puppies.size());
        puppies.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Dog>>() {
            @Override
            public void onChange(RealmResults<Dog> dogs, OrderedCollectionChangeSet changeSet) {
                Log.d("MainActivity", "onChange:"+dogs.size());
                changeSet.getInsertions();
            }
        });
    }

    public void setData(View view) {
        index++;
        Dog dog = new Dog();
        dog.setAge(18);
        dog.setName("jerry");
        defaultInstance = Realm.getDefaultInstance();
        defaultInstance.beginTransaction();
        Dog managedDog = defaultInstance.copyToRealm(dog);
        Person person = defaultInstance.createObject(Person.class);
        person.getDogs().add(managedDog);
        defaultInstance.commitTransaction();

    }
}
