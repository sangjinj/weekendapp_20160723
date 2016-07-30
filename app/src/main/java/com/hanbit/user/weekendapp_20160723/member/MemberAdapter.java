package com.hanbit.user.weekendapp_20160723.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanbit.user.weekendapp_20160723.R;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-07-30.
 */
public class MemberAdapter extends BaseAdapter {
    ArrayList<MemberBean> list;
    LayoutInflater inflater;
    private Context mContext;

    private int[] photos={
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.eclair,
            R.drawable.froyo,
            R.drawable.gingerbread,
            R.drawable.honeycomb,
            R.drawable.icecream,
            R.drawable.jellybean,
            R.drawable.kitkat,
            R.drawable.lollipop
    };
    public MemberAdapter(Context context, ArrayList<MemberBean> list){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;

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
        if(v == null){
            v = inflater.inflate(R.layout.member_list,null);
            holder = new ViewHolder();
            holder.ivPhoto = (ImageView) v.findViewById(R.id.iv_photo);
            holder.tvName = (TextView) v.findViewById(R.id.tv_name);
            holder.tvPhone = (TextView) v.findViewById(R.id.tv_phone);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }
    //holder.ivPhoto




        return v;
    }
    static class ViewHolder{
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPhone;
    }

}
