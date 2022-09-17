package com.exam.exspring.member;

import java.util.List;

public class MemberDaoImpl implements MemberDao {

	@Override
	public List<MemberVo> selectMemberList() {
		// TODO Auto-generated method stub
		return "com.exam.exspring.member.MemberDao.selectMemberList";
	}

	@Override
	public int insertMember(MemberVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVo selectMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVo selectLoginMember(MemberVo vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
