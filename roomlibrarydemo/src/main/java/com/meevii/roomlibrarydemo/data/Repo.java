package com.meevii.roomlibrarydemo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Repo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String url;

    public int age;
    public boolean isNew;

    public Repo() {

        age = 1;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Repo(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        age = 0;
    }

    public int getAge() {
        return age = 0;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", age=" + age +
                ", isNew=" + isNew +
                '}';
    }
}
