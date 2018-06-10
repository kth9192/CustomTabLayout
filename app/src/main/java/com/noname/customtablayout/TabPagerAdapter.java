package com.noname.customtablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private static String TAG = TabPagerAdapter.class.getName();

    private Map<Integer, Fragment> mFragmentMap = new HashMap<>();

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "탭1";
            case 1:
                return "탭2";
            case 2:
                return "탭3";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        mFragmentMap.put(position, createdFragment);
        Log.d(TAG, "프래그먼트 초기화 위치" + position);
        return createdFragment;
    }

    public Fragment getRegisteredFragment(int position) {
        return mFragmentMap.get(position);
    }
}
