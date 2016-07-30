package com.hanbit.user.weekendapp_20160723.member;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hb on 2016-07-23.
 */
public class MemberServiceImpl implements MemberService{
    Context context;
    MemberDAO dao;
    GuestDAO guestDAO;
    MemberBean session;
    public MemberServiceImpl(Context context) {
        dao = new MemberDAO(context);
        guestDAO = new GuestDAO(context);
        session = new MemberBean();
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
        guestDAO.add(bean);
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


        if(member.getId().equals("fail") ){
            loginOK = false;
        }else{
            loginOK = true;
            session.setId(id);
        }

        return loginOK;
    }

    @Override
    public int count() {
        return dao.count();

    }

    @Override
    public ArrayList<MemberBean> list() {
        return dao.list();
    }

    public ArrayList<MemberBean> guestlist() {
        return guestDAO.list();
    }

    @Override
    public ArrayList<MemberBean> findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public MemberBean findByID(String id) {

        if(dao.findById(id) ==null){
            Log.d("DAO 에서 넘어온 값이 : ","NULL");
        }else{
            Log.d("DAO 에서 넘어온 값이 : ","NOT NULL");
        }

        return dao.findById(id);
    }

    @Override
    public void update(MemberBean bean) {
        dao.update(bean);

    }

    @Override
    public void delete(String id) {
        Log.d("delete id :: ", id);
        dao.delete(id);
    }
}
