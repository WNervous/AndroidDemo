package com.meevii.architecturedemo.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Database(entities = Word.class, version = 1)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase INSTANCE;
    private static String TABLE_NAME = "word_database";

    public static WordDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class, TABLE_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull final SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    Observable.create(new ObservableOnSubscribe<Void>() {
                        @Override
                        public void subscribe(ObservableEmitter<Void> emitter) {
                            Word word1 = new Word();
                            word1.setWord("Hello");
                            INSTANCE.wordDao().insert(word1);
                            Word word2 = new Word();
                            word2.setWord("World");
                            INSTANCE.wordDao().insert(word2);
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                }
            };

    public abstract WordDao wordDao();
}
