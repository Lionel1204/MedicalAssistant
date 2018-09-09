package com.gma.medicalassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gma.medicalassistant.R;

public class CheckResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result);
    }

    public void onClickUploadBtn(View view) {
        String info = getResources().getString(R.string.check_result_upload_feedback);
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }
}
