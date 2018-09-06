package com.meevii.roomlibrarydemo.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = Repo.class, version = 3)
public abstract class RepoDatabase extends RoomDatabase {

    private static final String DB_NAME = "repoDatabase.db";
    private static volatile RepoDatabase instance;

    static synchronized RepoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RepoDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RepoDatabase.class,
                DB_NAME)
                .addMigrations(migration1_2,migration2_3)
                .build();
    }

    public abstract RepoDao getRepoDao();

    private static Migration migration1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Repo"
                    + " ADD COLUMN age INTEGER NOT NULL DEFAULT 10");
            //        alter table 表名 add 列明 bit default 0 with values 需要加上后面的 with values

        }
    };

    private static Migration migration2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Repo"
                    + " ADD COLUMN isNew INTEGER NOT NULL DEFAULT 0");
            //        alter table 表名 add 列明 bit default 0 with values 需要加上后面的 with values

        }
    };
}
