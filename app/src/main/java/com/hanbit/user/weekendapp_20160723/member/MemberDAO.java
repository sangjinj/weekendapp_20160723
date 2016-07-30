package com.hanbit.user.weekendapp_20160723.member;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hanbit.user.weekendapp_20160723.dbhelper.dbOpenHelper;

import java.util.ArrayList;

/**
 * Created by hb on 2016-07-23.
 */
public class MemberDAO{
    Context context;
    dbOpenHelper helper;;
    public static final String ID = "id";
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PHOTO = "photo";
    public static final String ADDR = "addr";
    public static final String TABLE_NAME = "member";
    public MemberDAO(Context context) {
        helper = new dbOpenHelper(context);
        this.context = context;

    }


    public void insert(MemberBean bean){

        String sql = "insert into member(id, pw, name, email, phone, photo, addr) values ('"
                +bean.getId()+"', '"
                +bean.getPw()+"', '"
                +bean.getName()+"', '"
                +bean.getEmail()+"','"
                +bean.getPhone()+"','"
                +bean.getPhoto()+"','"
                +bean.getAddr()
                +"' );";
        System.out.println(""+ helper.getDatabaseName());
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    public MemberBean login(MemberBean param){
        MemberBean member = new MemberBean();
        SQLiteDatabase db = helper.getReadableDatabase();
        Log.d("DB진입 전 ID : ",param.getId());
        Cursor cursor = db.rawQuery("select * from member " +
                "where id = '"+param.getId()+"' and pw = '"+param.getPw()+"';",null);
        if(cursor.moveToNext()){
            member.setId(cursor.getString(1));
            member.setPw(cursor.getString(2));
        }else{
            member.setId("fail");
        }
        Log.d("DB결과 ID : ",member.getId());
        //db.execSQL(sql);
        db.close();
        return member;
    }
    public int count(){
        String sql = "select count(*) as count from member;";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        int count = 0;
        if(cursor.moveToNext()){
            count = cursor.getInt(cursor.getColumnIndex("count"));
        }
        return count;
    }
    public ArrayList<MemberBean> list(){
        String sql  = "select * from member;";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<MemberBean> tempArray = new ArrayList<MemberBean>();


        while (cursor.moveToNext()){
            MemberBean temp  = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setName(cursor.getString(1));
            temp.setEmail(cursor.getString(2));
            temp.setPw(cursor.getString(3));
            temp.setPhone(cursor.getString(4));
            temp.setPhoto(cursor.getString(5));
            temp.setAddr(cursor.getString(6));
            tempArray.add(temp);
        }
        return tempArray;
    }

    public ArrayList<MemberBean> findByName(String name){
        String sql  = "select " +
                String.format("%s,%s,%s,%s,%s,%s,%s",ID,NAME,EMAIL,PW,PHONE,PHOTO,ADDR) +
                " from member where "+NAME+"= '"+name+"';";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<MemberBean> tempArray = new ArrayList<MemberBean>();
        if(cursor != null){
            Log.d("DAO LIST 조회결과 : " ,"SUCCESS");
            cursor.moveToFirst();
        }
        do{
            MemberBean temp  = new MemberBean();
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
            tempArray.add(temp);
        }while (cursor.moveToNext());
        return tempArray;
    }

    public MemberBean findById(String id){
        String sql  = "select " +
                String.format("%s,%s,%s,%s,%s,%s,%s",ID,NAME,EMAIL,PW,PHONE,PHOTO,ADDR) +
                " from "+TABLE_NAME+" where "+ID+" ='"+id+"';";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        MemberBean temp = new MemberBean();
        if(cursor.moveToNext()){
            temp.setId(cursor.getString(0));
            temp.setName(cursor.getString(1));
            temp.setEmail(cursor.getString(2));
            temp.setPw(cursor.getString(3));
            temp.setPhone(cursor.getString(4));
            temp.setPhoto(cursor.getString(5));
            temp.setAddr(cursor.getString(6));
        }else{
            temp.setId("none");
        }

        return temp;
    }

    public MemberBean update(MemberBean memberBean){
        String sql  = "update "+TABLE_NAME+" set " +
                    PW+"="+memberBean.getPw()+"'" +
                    ", "+EMAIL+"='"+memberBean.getEmail()+"'" +
                    ", "+PHOTO+" = '"+memberBean.getPhoto()+"'" +
                    ", "+ADDR+" = '"+memberBean.getAddr()+"'" +
                    " where "+ID+" = '"+memberBean.getId()+"';";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        return null;
    }
    public int delete(String id){
        String sql = "delete from "+TABLE_NAME+" where "+ID+"='"+id+"';";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        return 0;
    }
}