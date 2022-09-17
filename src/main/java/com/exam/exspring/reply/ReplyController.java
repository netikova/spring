package com.exam.exspring.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.exspring.member.MemberVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;	
	
	@RequestMapping(path = "/reply/list.do", method=RequestMethod.GET)
	@ResponseBody
	public List<ReplyVo> list(int repBbsNo) {
		List<ReplyVo> list = replyService.selectReplyList(repBbsNo);
		return list;
	}
	
	
    
	@RequestMapping(path = "/reply/add.do", method=RequestMethod.POST) //요청이 왔을때 이부분이 실행이 되려면 
	@ResponseBody //이 메서드의 반환값을 그대로 응답으로 전송
	public Map<String, Object> add(ReplyVo vo, HttpSession session) { //받을 변수가 많을때 이 변수들을 다 포함하고 있는 ReplyVo를 실행하면 한번에 받을 수 있다.
		MemberVo memVo = (MemberVo)session.getAttribute("loginUser");
		vo.setRepWriter(memVo.getMemId());
		
		//데이터베이스 댓글 추가(insert)
		int num = replyService.insertReply(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", num);
		
		return map;
//		String jsonStr = ""; 
//		
//		ObjectMapper mapper = new ObjectMapper(); //JAVA객채 <-> JSON문자열 변환 담당
//		try {			
//			jsonStr = mapper.writeValueAsString(map); //JAVA객체 -> JSON문자열 반환
//		} catch (JsonProcessingException e) {			
//			e.printStackTrace();
//		} 
//		
//		 return jsonStr; //"{ \"no\" : " + num+ "}";
		/*return num + "개의 댓글 저장 성공";*/
	}
	@RequestMapping(path = "/reply/del.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> del(ReplyVo vo, HttpSession session) {
		MemberVo memVo = (MemberVo) session.getAttribute("loginUser");
		vo.setRepWriter(memVo.getMemId());		
		
		int num = replyService.deleteReply(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", num);
		
		return map;
	}
}
//회원정보 1명
// Java
//class MemberVo{
//	String memId = "a001";
//	int memPoint = 10;	
//}

//Javascript
//var m {
//	memId: "001",
//	memPoint: 10
//}
//JSON
//자바스크립트 객체 표현을 그대로 사용
//차이점
//문자열 표현에 쌍따옴표만 사용 가능(작은 따옴표 불가)
//객체의 속성이름을 문자열로 표현

//XML
//"<member>
//   <memId>a001</memId>
//   <memPoint>10</memPoint>
// </member>"