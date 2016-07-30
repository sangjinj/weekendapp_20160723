package com.hanbit.user.weekendapp_20160723.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hanbit.user.weekendapp_20160723.R;

public class AddActivity extends Activity implements View.OnClickListener{
    EditText etNmae,etPhone;
    Button btAdd;
    MemberService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        service = new MemberServiceImpl(this.getApplicationContext());
        etNmae = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);

        btAdd = (Button) findViewById(R.id.btAdd);

        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = etNmae.getText().toString();
        String phone = etPhone.getText().toString();
        MemberBean guest = new MemberBean();
        guest.setName(name);
        guest.setPhone(phone);
        service.add(guest);
        startActivity(new Intent(this,GuestListActivity.class));
    }
}
