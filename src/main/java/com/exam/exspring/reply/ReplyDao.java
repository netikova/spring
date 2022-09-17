package com.exam.exspring.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper  //객체를 생성해서 스프링에 자동으로 등록
public interface ReplyDao {

	int insertReply(ReplyVo vo);

	List<ReplyVo> selectReplyList(int repBbsNo);

	int deleteReply(ReplyVo vo);

}
