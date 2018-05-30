package com.example.yun.togethertogether;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by SS20171206001 on 2018-05-29.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public FragmentAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                //   return new MyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }


}