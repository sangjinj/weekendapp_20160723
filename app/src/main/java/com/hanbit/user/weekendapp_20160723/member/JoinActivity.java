package com.hanbit.user.weekendapp_20160723.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hanbit.user.weekendapp.MainActivity;
import com.hanbit.user.weekendapp.R;

public class JoinActivity extends Activity implements View.OnClickListener  {
    EditText etID,etPW,etName,etEmail,etPhone, etPhoto, etAddr;
    Button btJoin,btHome;
    TextView tvResult;
    MemberBean bean = new MemberBean();
    MemberService service = new MemberServiceImpl(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        etID = (EditText) findViewById(R.id.etID);
        etPW = (EditText) findViewById(R.id.etPW);
        etName = (EditText) findViewById(R.id.etName);

        etPhone = (EditText) findViewById(R.id.etPhone);
        etPhoto = (EditText) findViewById(R.id.etPhoto);
        etAddr = (EditText) findViewById(R.id.etAddr);


        etEmail = (EditText) findViewById(R.id.etEmail);
        btJoin = (Button) findViewById(R.id.btJoin);
        btHome = (Button) findViewById(R.id.btHome);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btJoin.setOnClickListener(this);
        btHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String id = etID.getText().toString();
        String pw = etPW.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        String phone = etPhone.getText().toString();
        String photo = etPhoto.getText().toString();
        String addr = etAddr.getText().toString();


        bean.setId(id);
        bean.setPw(pw);
        bean.setName(name);
        bean.setEmail(email);
        bean.setPhone(phone);
        bean.setPhoto(photo);
        bean.setAddr(addr);


        switch (v.getId()){
            case R.id.btJoin:
                service.join(bean);
                this.startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btHome:
                this.startActivity(new Intent(this,MainActivity.class));
                break;
            default : break;
        }
    }
}