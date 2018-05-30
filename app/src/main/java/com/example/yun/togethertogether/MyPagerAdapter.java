package com.example.yun.togethertogether;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by SS20171206001 on 2018-05-29.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mData;
    public MyPagerAdapter(FragmentManager fm){
        super(fm);

        mData=new ArrayList<>();
        mData.add(new FragmentHome());
        mData.add(new FragmentChat());
        mData.add(new FragmentMore());
    }
    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Home".toUpperCase();
            case 1:
                return "Chat";
            case 2:
                return "Post";
        }
        return null;

    }
}