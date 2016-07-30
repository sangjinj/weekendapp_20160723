package com.hanbit.user.weekendapp_20160723.member;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hanbit.user.weekendapp_20160723.R;

public class UpdateActivity extends Activity implements View.OnClickListener{
    TextView tvID;
    EditText etPw,etEmail,etPhoto,etAddr;
    Button btUpdate,btList;
    Context context;
    MemberService service;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        service = new MemberServiceImpl(this.getApplicationContext());
        tvID = (TextView) findViewById(R.id.tvID);
        etPw = (EditText) findViewById(R.id.etPW);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhoto = (EditText) findViewById(R.id.etPhoto);
        etAddr = (EditText) findViewById(R.id.etAddr);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btList = (Button) findViewById(R.id.btList);

        btUpdate.setOnClickListener(this);
        btList.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btUpdate:
                MemberBean temp = service.findByID(tvID.getText().toString());

                temp.setId(tvID.getText().toString());
                temp.setPw(etPw.getText().toString());
                temp.setEmail(etEmail.getText().toString());
                temp.setPhoto(etPhoto.getText().toString());
                temp.setAddr(etAddr.getText().toString());
                service.update(temp);
                this.startActivity(new Intent(this, MemberListActivity.class));
                break;
            case R.id.btList:
                this.startActivity(new Intent(this, MemberListActivity.class));
                break;
        }
    }
}
