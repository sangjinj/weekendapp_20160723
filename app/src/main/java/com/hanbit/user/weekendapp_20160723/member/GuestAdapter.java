package com.hanbit.user.weekendapp_20160723.member;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hanbit.user.weekendapp_20160723.R;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-07-30.
 */
public class GuestAdapter extends BaseAdapter {
    ArrayList<MemberBean> list;
    LayoutInflater inflater;

    public GuestAdapter(Context context, ArrayList<MemberBean> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup g) {
        ViewHolder holder;
        if(v==null){
            v = inflater.inflate(R.layout.member_list,null);
            holder = new ViewHolder();
            holder.tvName = (TextView) v.findViewById(R.id.tv_name);
            holder.tvPhone = (TextView) v.findViewById(R.id.tv_phone);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }
        Log.d("어답터에서 체크한 이름["+i+"]",list.get(i).getName());
        holder.tvName.setText(list.get(i).getName());
        holder.tvPhone.setText(list.get(i).getPhone());
        return v;
    }
    // inner class
    static class ViewHolder{
        TextView tvName;
        TextView tvPhone;
    }
}