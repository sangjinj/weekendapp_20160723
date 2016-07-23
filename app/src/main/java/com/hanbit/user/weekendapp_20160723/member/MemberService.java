package com.hanbit.user.weekendapp_20160723.member;

import java.util.List;

/**
 * Created by hb on 2016-07-23.
 */
public interface MemberService {
    /*Create : DB에 정보를 생성,입력*/
    public void join(MemberBean bean);
    public void add(MemberBean bean);
    /*Read : DB정보를 조회*/
    public boolean login(MemberBean bean);
    public int count();
    public List<MemberBean> list();
    public List<MemberBean> findByName(String name);
    public MemberBean findByID(String id);
    /*update : DB정보를 수정*/
    public void update(MemberBean bean);
    /*delete : DB정보를 삭제*/
    public void delete(String id);

}