package com.technext.blogger.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.technext.blogger.model.Blog;

@Database(entities = {Blog.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class BLOG_DATABASE extends RoomDatabase {
    public abstract BlogDao blogDao();

    private static BLOG_DATABASE INSTANCE;

    public static BLOG_DATABASE getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BLOG_DATABASE.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BLOG_DATABASE.class, "blog_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}