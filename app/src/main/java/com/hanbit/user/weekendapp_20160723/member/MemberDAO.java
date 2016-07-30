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
    // id, pw, name, email, phone, photo, addr
    public static final String ID = "id";
    public static final String PW = "pw";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String PHOTO = "photo";
        public static final String ADDR = "addr";
        public static final String TABLE_NAME = "member";

        public MemberDAO(Context context) {
//        context.getApplicationContext();

        helper = new dbOpenHelper(context);
        //super(context, "sangjinj02.sqlite", null, 1);
        this.context = context;
        helper = new dbOpenHelper(context);

    }
//
//    public void onCreate(SQLiteDatabase db) {
//        System.out.println("######################### onCreate ##################################");
//        db.execSQL("CREATE TABLE if not exist member (id text primary key, pw text, name text, email text, phone text, photo text, addr text);");
//
//        // 최초 디비가 생성 될때 초기 데이터를 입력 한다.
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj01', '1111', '정상진01', 'sangjinj7@gmail.com','010-6426-3539','--','addr01' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj02', '1111', '정상진02', 'sangjinj7@gmail.com','010-6426-3539','--','addr02' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj03', '1111', '정상진03', 'sangjinj7@gmail.com','010-6426-3539','--','addr03' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj04', '1111', '정상진04', 'sangjinj7@gmail.com','010-6426-3539','--','addr04' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj05', '1111', '정상진05', 'sangjinj7@gmail.com','010-6426-3539','--','addr05' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj06', '1111', '정상진06', 'sangjinj7@gmail.com','010-6426-3539','--','addr06' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj07', '1111', '정상진07', 'sangjinj7@gmail.com','010-6426-3539','--','addr07' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj08', '1111', '정상진08', 'sangjinj7@gmail.com','010-6426-3539','--','addr08' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj09', '1111', '정상진09', 'sangjinj7@gmail.com','010-6426-3539','--','addr09' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj10', '1111', '정상진10', 'sangjinj7@gmail.com','010-6426-3539','--','addr10' );");
//        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj11', '1111', '정상진01', 'sangjinj7@gmail.com','010-6426-3539','--','addr11' );");
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exist " + TABLE_NAME);
//        this.onCreate(db);
//    }

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
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
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
        String sql  = "select * from "+TABLE_NAME+" where "+ID+" ='"+id+"';";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        MemberBean temp = new MemberBean();
        if(cursor.moveToNext()){
            temp.setId(cursor.getString(cursor.getColumnIndex(ID)));
            temp.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            temp.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            temp.setPw(cursor.getString(cursor.getColumnIndex(PW)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            temp.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
            temp.setAddr(cursor.getString(cursor.getColumnIndex(ADDR)));
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