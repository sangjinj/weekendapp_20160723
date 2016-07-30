package com.hanbit.user.weekendapp_20160723.member;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hanbit.user.weekendapp_20160723.dbhelper.dbOpenHelper;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-07-30.
 */
    public class GuestDAO {
    Context context;
    dbOpenHelper dbhelper;
    public GuestDAO(Context context) {
        dbhelper = new dbOpenHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        this.context = context;


    }
    public void add(MemberBean guest){
        String sql  = "insert into guest(name, phone)"+
                String.format("values('%s','%s');", guest.getName(), guest.getPhone());
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.execSQL(sql);
    }
    public ArrayList<MemberBean> list(){
        ArrayList<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id as id, name, phone from guest;",null);

        do{
            MemberBean memberBean = new MemberBean();
            memberBean.setId(String.valueOf(cursor.getInt(0)));
            memberBean.setName(cursor.getString(1));
            memberBean.setPhone(cursor.getString(2));
            list.add(memberBean);
        }while(cursor.moveToNext());

        db.close();
        return list;
    }


}
