package com.koolenwijkstra.siree.retrofit;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

public class RetroFitRepository {

    private UserDao userDao;
    private LiveData<User> mUser;

    public RetroFitRepository(Application application) {
        RetroFitRoomDatabase db = RetroFitRoomDatabase.getsInstance(application);
        userDao = db.userDao();
        mUser = userDao.getUser();
    }

    public LiveData<User> getUser(){
        return mUser;
    }


    public void update (User user){
        new updateAsyncTask(userDao).execute(user);
    }

    private class updateAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao mAsyncTaskDao;

        public updateAsyncTask(UserDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params){
            mAsyncTaskDao.updateUser(params[0]);
            return null;
        }
    }
}

