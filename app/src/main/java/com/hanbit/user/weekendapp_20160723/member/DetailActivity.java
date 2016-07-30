package com.hanbit.user.weekendapp_20160723.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hanbit.user.weekendapp_20160723.R;

public class DetailActivity extends Activity implements View.OnClickListener{
    MemberService memberService;
    MemberBean memberBean;
    TextView tvID, tvPW, tvName, tvEmail,tvPhone,tvPhoto,tvAddr;

    Button btPhone, btMap, btSms, btList;
    Phone phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        memberService = new MemberServiceImpl(this.getApplicationContext());
        phone = new Phone(this,this);
        Intent intent = this.getIntent();
        String id = intent.getExtras().getString("id");
        memberBean = memberService.findByID(id);

        tvID = (TextView) findViewById(R.id.tvID);
        tvPW = (TextView) findViewById(R.id.tvPW);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvPhoto = (TextView) findViewById(R.id.tvPhoto);
        tvAddr = (TextView) findViewById(R.id.tvAddr);

        tvID.setText(memberBean.getId());
        tvPW.setText(memberBean.getPw());
        tvName.setText(memberBean.getName());
        tvEmail.setText(memberBean.getEmail());
        tvPhone.setText(memberBean.getPhone());
        tvPhoto.setText(memberBean.getPhoto());
        tvAddr.setText(memberBean.getAddr());


        btPhone = (Button) findViewById(R.id.btPhone);
        btMap = (Button) findViewById(R.id.btMap);
        btSms = (Button) findViewById(R.id.btSms);
        btList = (Button) findViewById(R.id.btList);

        btPhone.setOnClickListener(this);
        btMap.setOnClickListener(this);
        btSms.setOnClickListener(this);
        btList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btPhone:
                phone.directCall(memberBean.getPhone());
                break;
            case R.id.btMap:
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                memberBean.setAddr("37.559768,126.94230800000003");
                intent.putExtra("pos",memberBean.getAddr());
                //,126.94230800000003
                startActivity(intent);
                break;
            case R.id.btSms:
                break;
            case R.id.btList:
                startActivity(new Intent(this,MemberListActivity.class));
                break;



        }

    }
}
