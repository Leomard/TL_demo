package com.jzone.tl_demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 28537 on 2017/7/29.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragList;
    private List<String> titleList;
    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragList,List<String> titleList) {
        super(fm);
        this.fragList=fragList;
        this.titleList=titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
