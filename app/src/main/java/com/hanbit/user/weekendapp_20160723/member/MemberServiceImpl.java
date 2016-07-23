package com.hanbit.user.weekendapp_20160723.member;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by hb on 2016-07-23.
 */
public class MemberServiceImpl implements MemberService{
    Context context;
    MemberDAO dao;


    public MemberServiceImpl(Context context) {
        dao = new MemberDAO(context);
    }

    @Override
    public void join(MemberBean bean) {
        String id = bean.getId();
        String pw = bean.getPw();
        String name = bean.getName();
        String email = bean.getEmail();
        String phone = bean.getPhone();
        String photo = bean.getPhoto();
        String addr = bean.getAddr();
        Log.d("넘어온 ID : ",id);
        Log.d("넘어온 PW : ",pw);
        Log.d("넘어온 이름 : ",name);
        Log.d("넘어온 이메일 : ",email);
        Log.d("넘어온 폰 : ",phone);
        Log.d("넘어온 사진 : ",photo);
        Log.d("넘어온 주소 : ",addr);
        dao.insert(bean);
    }

    @Override
    public void add(MemberBean bean) {
        dao.insert(bean);
    }

    @Override
    public boolean login(MemberBean bean) {
        boolean loginOK = false;
        String id = bean.getId();
        String pw = bean.getPw();
        Log.d("넘어온 ID : ",id);
        Log.d("넘어온 PW : ",pw);
        MemberBean member = dao.login(bean);
        //Log.d("DAO 에서 반환한 ID ","");


        if(member != null){
            loginOK = true;
        }else{
            loginOK = false;
        }

        return loginOK;
    }

    @Override
    public int count() {
        return dao.count();

    }

    @Override
    public List<MemberBean> list() {
        return dao.list();
    }

    @Override
    public List<MemberBean> findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public MemberBean findByID(String id) {

        return dao.findById(id);
    }

    @Override
    public void update(MemberBean bean) {
        dao.update(bean);

    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }
}
