package com.meevii.roomlibrarydemo.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RepoDao {

    @Query("SELECT * FROM Repo")
    List<Repo> getAllRepos();

    @Insert(onConflict = REPLACE)
    void insert(Repo... repos);

    @Update
    void update(Repo... repos);

    @Delete
    void delete(Repo... repos);

    @Query("SELECT*FROM REPO WHERE id = :id")
    Repo qurery(int id);
}
