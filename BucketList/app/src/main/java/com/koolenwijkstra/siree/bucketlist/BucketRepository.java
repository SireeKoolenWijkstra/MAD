package com.koolenwijkstra.siree.bucketlist;

import android.app.Application;
import android.app.usage.NetworkStats;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;

import java.util.List;

public class BucketRepository {
    private BucketDao mBucketDao;
    private LiveData<List<Item>> mAllItems;

    public BucketRepository(Application application){
        AppDatabase db = AppDatabase.getsInstance(application);
        mBucketDao = db.bucketDao();
        mAllItems = mBucketDao.getAllItems();
    }

    public LiveData<List<Item>> getAllItems(){
        return mAllItems;
    }

    public void insert (Item item){
        new insertAsyncTask(mBucketDao).execute(item);
    }

    public void update (Item item){
        new updateAsyncTask(mBucketDao).execute(item);
    }


    private static class insertAsyncTask extends AsyncTask<Item, Void, Void> {

        private BucketDao mAsyncTaskDao;

        public insertAsyncTask(BucketDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params){
            mAsyncTaskDao.insertItems(params[0]);
            return null;
        }
    }

    private class updateAsyncTask extends AsyncTask<Item, Void, Void> {
        private BucketDao mAsyncTaskDao;

        public updateAsyncTask(BucketDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params){
            mAsyncTaskDao.updateItems(params[0]);
            return null;
        }
    }
}
