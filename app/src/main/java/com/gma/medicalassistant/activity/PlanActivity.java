package com.gma.medicalassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.adapter.ItemAdapter;
import com.gma.medicalassistant.model.PlanItem;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity implements ItemAdapter.PlanItemClickListener {

    private String TAG = "PlanActivity";

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
        List<PlanItem> list = new ArrayList<PlanItem>();

        String plan1 = getResources().getString(R.string.plan_type_1);
        for (int i = 0; i < 1; i++) {
            PlanItem pi = new PlanItem(String.format(plan1, i), true);
            list.add(pi);
        }

        String plan2 = getResources().getString(R.string.plan_type_2);
        for (int i = 0; i < 2; i++) {
            PlanItem pi = new PlanItem(String.format(plan2, i), false);
            list.add(pi);
        }

        ItemAdapter itemAdapter = new ItemAdapter(list, this);//添加适配器，这里适配器刚刚装入了数据
        itemAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "Item Id: " + view.getId() + " position: " + position);
        clickButton(view, position);
    }

    private void clickButton(View view, int position) {
        View itemView = (View) view.getParent();
        Button purchaseBtn = itemView.findViewById(R.id.btn_plan_item_purchase);
        Button payBtn = itemView.findViewById(R.id.btn_plan_item_pay);
        Button cancelBtn = itemView.findViewById(R.id.btn_plan_item_cancel);

        String toastInfo = getResources().getString(R.string.plan_call_pay_platform);

        switch (view.getId()) {
            case R.id.btn_plan_item_pay:
                payBtn.setVisibility(View.GONE);
                cancelBtn.setVisibility(View.GONE);
                purchaseBtn.setVisibility(View.VISIBLE);
                Toast.makeText(this, toastInfo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_plan_item_purchase:
                payBtn.setVisibility(View.VISIBLE);
                cancelBtn.setVisibility(View.VISIBLE);
                purchaseBtn.setVisibility(View.GONE);
                break;
            case R.id.btn_plan_item_cancel:
                payBtn.setVisibility(View.GONE);
                cancelBtn.setVisibility(View.GONE);
                purchaseBtn.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
