package com.dahai.dateselectdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class DayDateSelectAdapter extends RecyclerView.Adapter<DayDateSelectAdapter.Holder> {

    private Context context;
    private List<DayBean> list;
    private onDateSelectListener listener;
//    private int day=new Date().getDay();
//    private int month=new Date().getMonth();

    private String month=new SimpleDateFormat("M").format(new Date());
    private String day=new SimpleDateFormat("d").format(new Date());

    private List<MonthBean> monthBeanList;

    public DayDateSelectAdapter(Context context, List<DayBean> list) {
        this.context = context;
        this.list = list;
    }

    public DayDateSelectAdapter(Context context, List<DayBean> list,List<MonthBean> monthBeanList) {
        this.context = context;
        this.list = list;
        this.monthBeanList=monthBeanList;
    }

    public interface onDateSelectListener{
        void onSelect(int pos);
    }

    public void setListener(onDateSelectListener listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dateselect_day, parent, false);
        return new Holder(view);
    }

    private boolean isRuZhu=false;
    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final DayBean entity = list.get(position);
        if (entity.getDay()==0) {
            holder.tv_day.setText("");
        } else {
            if (monthBeanList!=null) {
                Log.i("LLLL", entity.getDay() + "----" + day + "-----" + month + "----"+monthBeanList.get(0).getMonth());
            }
            if (monthBeanList!=null&&
                    monthBeanList.size()>0&&
                    monthBeanList.get(0).getMonth()==Integer.valueOf(month)&&
                    entity.getDay()==Integer.valueOf(day)){

                holder.tv_day.setText("今天");
            }else {
                holder.tv_day.setText(entity.getDay() + "");
            }
                holder.tv_jr.setText(entity.getJr());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.onSelect(holder.getAdapterPosition());
                        }

                    }
                });
                if (entity.isSelectRuZhu()){
                    holder.tv_select1.setText("入住");
                    holder.tv_select1.setVisibility(View.VISIBLE);
                    holder.tv_select2.setVisibility(View.GONE);
                }else if (entity.isSelectLiKai()){
                    holder.tv_select2.setText("离开");
                    holder.tv_select2.setVisibility(View.VISIBLE);
                    holder.tv_select1.setVisibility(View.GONE);
                }

                if (entity.isSelect()) {
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_them));
                    holder.tv_day.setTextColor(ContextCompat.getColor(context, R.color.text_color_black));
                } else if (entity.isMiddle()) {
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_them_alpha));
                    holder.tv_day.setTextColor(ContextCompat.getColor(context, R.color.text_color_black));
                } else {
                    holder.itemView.setBackgroundColor(Color.WHITE);
                    holder.tv_day.setTextColor(ContextCompat.getColor(context, R.color.black));
                }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView tv_day,tv_jr,tv_select1,tv_select2;

        Holder(View itemView) {
            super(itemView);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            tv_jr = (TextView) itemView.findViewById(R.id.tv_jr);
            tv_select1=itemView.findViewById(R.id.tv_select1);
            tv_select2=itemView.findViewById(R.id.tv_select2);
        }
    }
}
