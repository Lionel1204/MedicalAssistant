package com.gma.medicalassistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gma.medicalassistant.R;
import com.gma.medicalassistant.model.PlanItem;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.PlanItemViewHolder> implements View.OnClickListener {

    private List<PlanItem> list;//存放数据
    private Context context;
    private PlanItemClickListener mItemClickListener;

    public ItemAdapter(List<PlanItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PlanItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_plan_other, parent, false);
        PlanItemViewHolder holder = new PlanItemViewHolder(view);
        return holder;
    }

    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
    // 在这里对获取对象进行操作
    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
    //position是点击位置
    @Override
    public void onBindViewHolder(PlanItemViewHolder holder, final int position) {
        //设置textView显示内容为list里的对应项
        holder.textView.setText(list.get(position).getItemText());
        if (list.get(position).getPurchaseState()) {
            holder.purchaseBtn.setText(R.string.plan_purchase_again);
        }

        //子项的点击事件监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击子项" + position, Toast.LENGTH_SHORT).show();
            }
        });

        if (mItemClickListener != null) {
            holder.purchaseBtn.setTag(position);
            holder.purchaseBtn.setOnClickListener(this);
            holder.payBtn.setTag(position);
            holder.payBtn.setOnClickListener(this);
            holder.cancelPayBtn.setTag(position);
            holder.cancelPayBtn.setOnClickListener(this);
        }
    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int position = (int) v.getTag();
            mItemClickListener.onItemClick(v, position);
        }
    }

    public void setOnItemClickListener(PlanItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface PlanItemClickListener {
        void onItemClick(View view,int position);
    }

    //这里定义的是子项的类，不要在这里直接对获取对象进行操作
    public class PlanItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private Button purchaseBtn;
        private Button payBtn;
        private Button cancelPayBtn;

        public PlanItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.plan_other_item_title_textview);
            purchaseBtn = itemView.findViewById(R.id.btn_plan_item_purchase);
            payBtn = itemView.findViewById(R.id.btn_plan_item_pay);
            cancelPayBtn = itemView.findViewById(R.id.btn_plan_item_cancel);
        }
    }

    /*之下的方法都是为了方便操作，并不是必须的*/

    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, PlanItem pi) {
        if (position < list.size() && position >= 0) {
            list.add(position, pi);
            notifyItemInserted(position);
            return true;
        }
        return false;
    }

    //去除指定位置的子项
    public boolean removeItem(int position) {
        if (position < list.size() && position >= 0) {
            list.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }

    //清空显示数据
    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }
}