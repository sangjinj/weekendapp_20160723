package com.hanbit.user.weekendapp_20160723;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hanbit.user.weekendapp_20160723.dbhelper.dbOpenHelper;
import com.hanbit.user.weekendapp_20160723.member.CountActivity;
import com.hanbit.user.weekendapp_20160723.member.FindActivity;
import com.hanbit.user.weekendapp_20160723.member.JoinActivity;
import com.hanbit.user.weekendapp_20160723.member.LoginActivity;
import com.hanbit.user.weekendapp_20160723.member.MemberDAO;
import com.hanbit.user.weekendapp_20160723.member.MemberListActivity;
import com.hanbit.user.weekendapp_20160723.member.MemberService;
import com.hanbit.user.weekendapp_20160723.member.MemberServiceImpl;
import com.hanbit.user.weekendapp_20160723.member.UpdateActivity;

public class MainActivity extends Activity implements View.OnClickListener{
    Button btAdd,btDelete,btUpdate,btFind,btList,btCount,btLogin,btJoin;

    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new MemberServiceImpl(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        btJoin = (Button) findViewById(R.id.btJoin);
        btAdd = (Button) findViewById(R.id.btAdd);
        btDelete = (Button) findViewById(R.id.btDelete);
        btUpdate = (Button) findViewById(R.id.btUpdate);
        btFind = (Button) findViewById(R.id.btFind);
        btList = (Button) findViewById(R.id.btList);
        btCount = (Button) findViewById(R.id.btCount);
        btLogin = (Button) findViewById(R.id.btLogin);

        btJoin.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btFind.setOnClickListener(this);
        btList.setOnClickListener(this);
        btCount.setOnClickListener(this);
        btLogin.setOnClickListener(this);

        // 이건 초기에 넣고 읽어 드리는 테스트 입니다.

        dbOpenHelper helper = new dbOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //db.execSQL("insert into member(name, pwd) values ('sangjinj01', 'pwd01');");
        //db.execSQL("insert into member(id, pw, name, email, phone, photo, addr) values ('abcd1234', '1111', '정상진01', 'sangjinj7@gmail.com','010-6426-3539','--','addr01' );");
        System.out.println("############################################################################## ::::::" + db.getPath());
        Cursor rs = db.rawQuery("select * from member;", null);
        //Cursor rs = db.rawQuery("select count(*) as memCnt from member;", null);

        while(rs.moveToNext()){
            System.out.println("name ::::::::::::::::: "+rs.getString(0));
            System.out.println("pw ::::::::::::::::: "+rs.getString(1));
        }

    }

    @Override
    public void onClick(View view) {
        MemberDAO memberDAO = new MemberDAO(this);
        memberDAO.list();

        switch (view.getId()){
            case R.id.btJoin :
                Toast.makeText(MainActivity.this,"btJoin",Toast.LENGTH_LONG).show();
                this.startActivity(new Intent(this, JoinActivity.class));
                break;
            case R.id.btAdd :
                Toast.makeText(MainActivity.this,"btAdd",Toast.LENGTH_LONG).show();
                this.startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btDelete :
                Toast.makeText(MainActivity.this,"btDelete",Toast.LENGTH_LONG).show();
                this.startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btUpdate :
                Toast.makeText(MainActivity.this,"btUpdate",Toast.LENGTH_LONG).show();
                this.startActivity(new Intent(this, UpdateActivity.class));
                break;
            case R.id.btFind :
                Toast.makeText(MainActivity.this,"btFind",Toast.LENGTH_LONG).show();
                this.startActivity(new Intent(this, FindActivity.class));
                break;
            case R.id.btList :
                Toast.makeText(MainActivity.this,"btList",Toast.LENGTH_LONG).show();
                this.startActivity(new Intent(this, MemberListActivity.class));
                break;
            case R.id.btCount :
                Toast.makeText(MainActivity.this,"btCount",Toast.LENGTH_LONG).show();
                this.startActivity(new Intent(this, CountActivity.class));
                break;
            case R.id.btLogin :
                System.out.println("--------------------------");
                Toast.makeText(MainActivity.this,"btLogin",Toast.LENGTH_SHORT).show();
                this.startActivity(new Intent(this, LoginActivity.class));
                break;


        }
    }
}
