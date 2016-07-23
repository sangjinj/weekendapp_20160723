package com.hanbit.user.weekendapp_20160723.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1027 on 2016-07-23.
 */
public class dbOpenHelper extends SQLiteOpenHelper {

    Context context;

    public dbOpenHelper(Context context) {

        //2번째 인자는 만들어지는 sqlite파일 이름이고 4번째 인자는 개발자가 만든 sqlite3버전이다.
        super(context, "sangjinj02.sqlite", null, 1);
        this.context = context;
    }
    //최초에 기존에 없었던 db가 새롭게 만들어질때 1번 호출
    public void onCreate(SQLiteDatabase db) {
        System.out.println("######################### onCreate ##################################");
        db.execSQL("CREATE TABLE member (id text primary key, pw text, name text, email text, phone text, photo text, addr text);");

        // 최초 디비가 생성 될때 초기 데이터를 입력 한다.
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj01', '1111', '정상진01', 'sangjinj7@gmail.com','010-6426-3539','--','addr01' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj02', '1111', '정상진02', 'sangjinj7@gmail.com','010-6426-3539','--','addr02' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj03', '1111', '정상진03', 'sangjinj7@gmail.com','010-6426-3539','--','addr03' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj04', '1111', '정상진04', 'sangjinj7@gmail.com','010-6426-3539','--','addr04' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj05', '1111', '정상진05', 'sangjinj7@gmail.com','010-6426-3539','--','addr05' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj06', '1111', '정상진06', 'sangjinj7@gmail.com','010-6426-3539','--','addr06' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj07', '1111', '정상진07', 'sangjinj7@gmail.com','010-6426-3539','--','addr07' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj08', '1111', '정상진08', 'sangjinj7@gmail.com','010-6426-3539','--','addr08' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj09', '1111', '정상진09', 'sangjinj7@gmail.com','010-6426-3539','--','addr09' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj10', '1111', '정상진10', 'sangjinj7@gmail.com','010-6426-3539','--','addr10' );");
        db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('sangjinj11', '1111', '정상진01', 'sangjinj7@gmail.com','010-6426-3539','--','addr11' );");

    }

    //이미 배포했던 db에 변경이 있을경우 호출된다.
    //주로 버전의 변경이 있을때 호출
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("######################### onUpgrade ##################################");
        //db.execSQL("CREATE TABLE member (id text primary key, pw text, name text, email text, phone text, photo text, addr text);");
    }

}