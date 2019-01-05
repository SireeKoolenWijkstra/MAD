package com.koolenwijkstra.siree.retrofit;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UserFragmentAdapter extends FragmentPagerAdapter {

    UserViewModel userViewModel;

    private Context mContext;

    public UserFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i ==0){
            return new UserFragment();
        }else{
            return new DagFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
