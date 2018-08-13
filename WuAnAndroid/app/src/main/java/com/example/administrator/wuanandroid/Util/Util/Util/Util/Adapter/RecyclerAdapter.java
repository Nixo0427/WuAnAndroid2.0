package com.example.administrator.wuanandroid.Util.Util.Util.Util.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wuanandroid.Util.Util.Util.Util.Bean.Units;
import com.example.administrator.wuanandroid.R;

import java.util.List;

/**
 * @class name: RecyclerAdapter
 * @anthor ：卫士
 * @time :Create on 2018/8/13  16:06
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<Units> units;
    static  class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout l;
        private TextView name_txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            l=(LinearLayout)itemView.findViewById(R.id.l_view);
            name_txt=(TextView)itemView.findViewById(R.id.name_txt);
        }
    }
    public RecyclerAdapter(List<Units> units){
        this.units=units;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_units,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        //注册监听
        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Units unit=units.get(i);
        viewHolder.name_txt.setText(unit.getName());
    }

    @Override
    public int getItemCount() {
        return units.size();
    }
}
