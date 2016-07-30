package com.hanbit.user.weekendapp_20160723.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.user.weekendapp_20160723.R;

public class LoginActivity extends Activity implements View.OnClickListener{
    EditText etID,etPW;
    TextView textResult;
    Button btLogin;
    MemberService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etID = (EditText) findViewById(R.id.etID);
        etPW = (EditText) findViewById(R.id.etPW);
        btLogin = (Button) findViewById(R.id.btLogin);
        service = new MemberServiceImpl(this.getApplicationContext());
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id = etID.getText().toString();
        String pw = etPW.getText().toString();
        MemberBean member = new MemberBean();
        member.setId(id);
        member.setPw(pw);
        if(service.login(member)){
            Toast.makeText(LoginActivity.this,"로그인성공",Toast.LENGTH_SHORT).show();
            this.startActivity(new Intent(this, MemberListActivity.class));
        }else{
            Toast.makeText(LoginActivity.this,"로그인실패",Toast.LENGTH_SHORT).show();
        }
    }
}