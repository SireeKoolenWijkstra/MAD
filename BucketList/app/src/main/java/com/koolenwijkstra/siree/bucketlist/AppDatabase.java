package com.koolenwijkstra.siree.bucketlist;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

@Database(entities = {Item.class}, version = 2)

public abstract class AppDatabase extends RoomDatabase {

    public abstract BucketDao bucketDao();

    private final static String NAME_DATABASE = "bucketList_db";

    //static instance

    private static AppDatabase sInstance;

    //create a singleton
    public static AppDatabase getsInstance(Context context){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE)
                    .fallbackToDestructiveMigration()
                    //.addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return sInstance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(sInstance).execute();
        }
    };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final BucketDao mBucketDao;
        Item[] items = {new Item("Skydiven", "Uit een vliegtuig springen", false),
                new Item("nieuw huis kopen", "Aan het strand op de Malediven", false),
                new Item("Studie afmaken", "AAAARG", false)};

        PopulateDbAsync(AppDatabase db){
            mBucketDao = db.bucketDao();
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
            // Start the app with a clean database every time.
            mBucketDao.deleteAll();
            for (int i = 0; i<= items.length -1; i++){
                Item item = items[i];
                mBucketDao.insertItems(item);
            }
            return null;
        }
    }
}
