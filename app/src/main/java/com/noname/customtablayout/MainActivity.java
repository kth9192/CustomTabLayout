package com.noname.customtablayout;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.noname.customtablayout.databinding.ActivityMainBinding;

import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();

    private ActivityMainBinding activityMainBinding;
    private TabPagerAdapter mPageAdapter;

    private String[] mPlanetTitles;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activityMainBinding.toolbar.setTitleTextColor(getResources().getColor(R.color.defaultWhite, null));
        }
        setSupportActionBar(activityMainBinding.toolbar);

        mPageAdapter = new TabPagerAdapter(getSupportFragmentManager());
        activityMainBinding.tabViewPager.setAdapter(mPageAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        activityMainBinding.tabLayout.setupWithViewPager(activityMainBinding.tabViewPager);
        activityMainBinding.tabLayout.addOnTabSelectedListener(tabSelectedListener);
        TabLayout.Tab tab = activityMainBinding.tabLayout.getTabAt(0);
        if (tab != null) {
            tab.select();
        }

        activityMainBinding.tabViewPager.addOnPageChangeListener(tabPageChangeListener);
        activityMainBinding.drawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle = new ActionBarDrawerToggle(this, activityMainBinding.drawerLayout, activityMainBinding.toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };

        activityMainBinding.drawerView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();
            Log.d(TAG, "선택 메뉴 " + id);

            switch (id){
                case  R.id.nav_test1 :
                    getSupportActionBar().setTitle("샘플1");
                    selectItem(R.id.nav_test1);
                    break;
                case R.id.nav_test2:
                    getSupportActionBar().setTitle("샘플2");
                    selectItem(R.id.nav_test2);
                    break;
                case R.id.nav_test3 :
                    getSupportActionBar().setTitle("샘플3");
                    selectItem(R.id.nav_test3);
                    break;
                default:
                    break;
            }

            activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        if (savedInstanceState == null){
            getSupportActionBar().setTitle("메모 하기");
            selectItem(R.id.nav_test1);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void selectItem(int id) {

        Fragment fragment = null;
        String tmpTag = null;

        switch (id){
            case R.id.nav_test1 :
//                fragment = new 1Fragment();
//                tmpTag = 1Fragment.class.getName();
                break;
            case R.id.nav_test2 :
//                fragment = new 2Fragment();
//                tmpTag = 2Fragment.class.getName();
                break;
            case R.id.nav_test3 :
//                fragment = new 3Fragment();
//                tmpTag = 3Fragment.class.getName();
                break;
            default:
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment, tmpTag)
                    .commit();
        }
    }

//    public void addTabIcon(){
//        activityMainBinding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_date_range_white_24dp);
//        activityMainBinding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_alarm_white_24dp);
//        activityMainBinding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_event_note_white_24dp);
//        activityMainBinding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_info_white_24dp);
//    }

    TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener(){

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            LinearLayout tabLayout1 = (LinearLayout)((ViewGroup) activityMainBinding.tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
            TextView tabTextView = (TextView) tabLayout1.getChildAt(1);
            tabTextView.setTypeface(null, Typeface.BOLD);
            tabTextView.setTextSize(20);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            LinearLayout tabLayout1 = (LinearLayout)((ViewGroup) activityMainBinding.tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
            TextView tabTextView = (TextView) tabLayout1.getChildAt(1);
            tabTextView.setTypeface(null, Typeface.NORMAL);
            tabTextView.setTextSize(16);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            Log.d(TAG, "탭위치" + tab.getPosition());
            LinearLayout tabLayout1 = (LinearLayout)((ViewGroup) activityMainBinding.tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
            TextView tabTextView = (TextView) tabLayout1.getChildAt(1);
            tabTextView.setTypeface(null, Typeface.BOLD);
        }
    };

    ViewPager.OnPageChangeListener tabPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            activityMainBinding.tabLayout.getTabAt(position).select();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
