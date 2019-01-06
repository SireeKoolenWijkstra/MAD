package com.koolenwijkstra.siree.retrofit;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {User.class}, version = 1)
public abstract class RetroFitRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private final static String NAME_DATABASE = "retroFit_db";

    //static instance

    private static RetroFitRoomDatabase sInstance;

    //create a singleton
    public static RetroFitRoomDatabase getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, RetroFitRoomDatabase.class, NAME_DATABASE)
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return sInstance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(sInstance).execute();
        }
    };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mUserDao;
        User user = new User(33, 74, 169);

        PopulateDbAsync(RetroFitRoomDatabase db) {
            mUserDao = db.userDao();
        }


        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Void... voids) {
            mUserDao.deleteAll();
            mUserDao.insert(user);
            return null;
        }
    }
}
