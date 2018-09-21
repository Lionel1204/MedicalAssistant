package com.gma.medicalassistant.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gma.medicalassistant.R;

public class CallDoctorActivity extends AppCompatActivity {

    private String TAG = "CallDoctorActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_doctor);
    }
}
