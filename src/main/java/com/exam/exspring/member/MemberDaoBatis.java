package com.exam.exspring.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
public class MemberDaoBatis implements MemberDao{
	@Autowired //스프링으로부터 sessionfactory를 자동 주입해서 실행
//	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;  //SqlSessionTemplate session
	
	@Override
	public List<MemberVo> selectMemberList() {				
		return session.selectList("com.exam.exspring.member.MemberDao.selectMemberList");//여러명 조회	
	}
	
	@Override
	public MemberVo selectMember(String memId) {					
		return session.selectOne("com.exam.exspring.member.MemberDao.selectMember", memId);//한명 조회
	}

	@Override
	public int insertMember(MemberVo vo) {			
		return session.insert("com.exam.exspring.member.MemberDao.insertMember", vo);//여러명 조회	
	}

	@Override
	public int delMember(String memId) {				
		return session.delete("com.exam.exspring.member.MemberDao.delMember", memId);//여러명 조회
	}

	@Override
	public int updateMember(MemberVo vo) {			
		return session.update("com.exam.exspring.member.MemberDao.updateMember", vo);//여러명 조회			
	}

	@Override
	public MemberVo selectLoginMember(MemberVo vo) {			
		return session.selectOne("com.exam.exspring.member.MemberDao.selectLoginMember", vo);		
	}
}
