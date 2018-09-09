package com.gma.medicalassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView recyclerView = findViewById(R.id.plan_other_recyclerview);//获取对象
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器，这里选择用竖直的列表
        List<String> list = new ArrayList<>();

        String plan1 = getResources().getString(R.string.plan_type_1);
        for (int i = 0; i < 2; i++) {
            list.add(String.format(plan1, i));
        }

        String plan2 = getResources().getString(R.string.plan_type_2);
        for (int i = 0; i < 2; i++) {
            list.add(String.format(plan2, i));
        }

        ItemAdapter itemAdapter=new ItemAdapter(list, this);//添加适配器，这里适配器刚刚装入了数据
        recyclerView.setAdapter(itemAdapter);
    }
}
