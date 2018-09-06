package com.gma.medicalassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private int LOGIN_REQUEST_CODE = 10001;
    private boolean mLogged = false;
    private String TAG = "MainActivity";
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private BottomNavigationView navigation;
    private List<Fragment> list;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_signup:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_activity:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_myprofile:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener
            = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, "onPageSelected");

            //navigation.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.vp);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        list = new ArrayList<>();
        list.add(TodayFragment.newInstance("Today", "test1"));
        list.add(SignupFragment.newInstance("Signup", "test2"));
        list.add(CareActFragment.newInstance("Care Activity", "test3"));
        list.add(MineFragment.newInstance("Mine", "test4"));
        viewPagerAdapter.setList(list);
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLogged = true;
        if (!mLogged) {
            Intent loginIntent = new Intent("ACTION_LOGIN");
            loginIntent.putExtra("key", "test");
            int requestCode = LOGIN_REQUEST_CODE;
            startActivityForResult(loginIntent, requestCode);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10001) {
            if (resultCode == RESULT_OK) {
                String result = data.getExtras().getString("result");
                mLogged = result.equals("OK");
                Log.i(TAG, result);
            }
        }
    }
}
