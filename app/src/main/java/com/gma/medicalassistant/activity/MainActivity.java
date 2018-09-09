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
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.adapter.ViewPagerAdapter;
import com.gma.medicalassistant.utils.MedConst;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        TodayFragment.OnTodayFragmentInteractionListener,
        SignupFragment.OnSignupFragmentInteractionListener,
        CareActFragment.OnCareActFragmentInteractionListener,
        MineFragment.OnMineFragmentInteractionListener {

    private TextView mTextMessage;
    private boolean mLogged = false;
    private String TAG = "MainActivity";
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private BottomNavigationView navigation;
    private List<Fragment> list;
    private long startTime = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0, false);
                    return true;
                case R.id.navigation_signup:
                    if (allowOpen())
                        viewPager.setCurrentItem(1, false);
                    else {
                        String info = getResources().getString(R.string.main_repeat);
                        Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.navigation_activity:
                    viewPager.setCurrentItem(2, false);
                    return true;
                case R.id.navigation_myprofile:
                    viewPager.setCurrentItem(3, false);
                    return true;
            }
            return false;
        }
    };

    private boolean allowOpen() {
        long curTime = (new Date()).getTime();//本次单击的时间

        if(curTime - startTime > MedConst.MIN_INTERVAL_SWITCH_NAVIGATION){
            startTime = curTime;
            return true;
        } else {
            return false;
        }
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener
            = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.i(TAG, String.format("Page %d selected", position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private ViewPager.OnTouchListener mOnTouchListener
            = new ViewPager.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
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
        viewPager.setOnTouchListener(mOnTouchListener);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLogged = true;
        if (!mLogged) {
            Intent loginIntent = new Intent(MedConst.INTENT_ACTION_LOGIN);
            loginIntent.putExtra("key", "test");
            int requestCode = MedConst.LOGIN_REQUEST_CODE;
            startActivityForResult(loginIntent, requestCode);
        }
        startTime = (new Date()).getTime();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MedConst.LOGIN_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    String result = data.getExtras().getString("result");
                    mLogged = result.equals("OK");
                    Log.i(TAG, result);
                }
                break;
            case MedConst.CALL_DOCTOR_REQUEST_CODE:
                Log.i(TAG, MedConst.INTENT_ACTION_CALL_DOCTOR);
                break;
            case MedConst.CHECK_REQUEST_CODE:
                Log.i(TAG, MedConst.INTENT_ACTION_CHECK_RESULT);
                break;
            case MedConst.HEART_RATE_REQUEST_CODE:
                Log.i(TAG, MedConst.INTENT_ACTION_HEART_RATE);
                break;
            case MedConst.MEASUREMENT_REQUEST_CODE:
                Log.i(TAG, MedConst.INTENT_ACTION_MEASUREMENT);
                break;
            case MedConst.PLAN_REQUEST_CODE:
                Log.i(TAG, MedConst.INTENT_ACTION_PLAN);
                break;
            default:
                break;

        }
    }

    @Override
    public void onSignupFragmentInteraction(String s){
        Log.d(TAG, s);
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMineFragmentInteraction(String s){
        Log.d(TAG, s);
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCareActFragmentInteraction(String s){
        Log.d(TAG, s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTodayFragmentInteraction(String it, int code) {
        Log.d(TAG, "Today Frag " + it);
        Intent intent = new Intent(it);
        //intent.putExtra("key", "test");

        startActivityForResult(intent, code);
        //Toast.makeText(this, it, Toast.LENGTH_SHORT).show();
    }
}
