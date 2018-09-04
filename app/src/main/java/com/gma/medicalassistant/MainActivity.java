package com.gma.medicalassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private int LOGIN_REQUEST_CODE = 10001;
    private boolean mLogged = false;
    private String TAG = "MainActivity";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_activity:
                    mTextMessage.setText(R.string.title_activity);
                    return true;
                case R.id.navigation_signup:
                    mTextMessage.setText(R.string.title_signup);
                    return true;
                case R.id.navigation_myprofile:
                    mTextMessage.setText(R.string.title_myprofile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
