package com.hanbit.user.weekendapp_20160723.member;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hanbit.user.weekendapp_20160723.R;

import java.util.ArrayList;

public class MemberListActivity extends Activity {
    ListView lv_memberList;
    MemberService service;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        lv_memberList = (ListView) findViewById(R.id.lv_memberlist);
        service = new MemberServiceImpl(this.getApplicationContext());
        ArrayList<MemberBean> list = service.list();
        lv_memberList.setAdapter(new MemberAdapter(this,list));
        lv_memberList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        public String id;

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                Object o = lv_memberList.getItemAtPosition(i);
                MemberBean memberBean = (MemberBean) o;
                Toast.makeText(MemberListActivity.this,"선택한 이름"+ memberBean.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MemberListActivity.this, DetailActivity.class);
                this.id = memberBean.getId();
                intent.putExtra("id",memberBean.getId());
                startActivity(intent);
            }
        });
        lv_memberList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long l) {
                new AlertDialog.Builder(MemberListActivity.this)
                        .setTitle("삭제 OK ?")
                .setMessage("정말로 삭제하시겠습니까?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // 삭제
                                service.delete(id);
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            // 취소..
                    }
                })
                        .show();
                return true;
            }
        });
    }
}
