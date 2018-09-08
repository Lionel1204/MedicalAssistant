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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.adapter.ViewPagerAdapter;
import com.gma.medicalassistant.utils.MedConst;

import java.util.ArrayList;
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
            Log.i(TAG, String.format("Page %d selected", position));
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

    @Override
    public void onSignupFragmentInteraction(String s){
        Log.d(TAG, s);
        String info = getResources().getString(R.string.signup_success);
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
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
    public void onTodayFragmentInteraction(String s) {
        Log.d(TAG, "Today Frag " + s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
