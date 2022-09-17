package com.exam.exspring.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.exspring.member.MemberVo;

@Controller
@RequestMapping("/bbs/") //"/bbs/list.do" 부분의 /bbs/가 공통, 생략가능.
//이 컨트롤러 내부의 @RequestMapping 메서드들의 공통경로를 클래스에 설정 가능
public class BbsController {
	@Autowired
	private BbsService bbsService; 
	
	@RequestMapping(value = "list.do", method = RequestMethod.GET) //get 방식은 스킵할수없다.
	//목록보여주는 메서드, Get방식으로 요청이 오면 실행한다.
	/* == @GetMapping(value="/bbs/list.do") 스프링 4.3이상부터 사용가능*/
	public String list(SearchInfo info, Map<String,Object> map){
		List<BbsVo> list = bbsService.selectBbsList(info);			
		map.put("bbsList", list);
		return "bbs/bbsList";// /WEB-INF/views/bbs/bbsList.jsp 스프링(servlet-context.xml)에 등록되어있기때문에 생략가능
		
	}

	 @GetMapping(value="add.do") //스프링 4.3이상부터 사용가능
	/* @RequestMapping(value = "add.do", method = RequestMethod.GET) */
	public String addform()  {
		return "bbs/bbsAdd";
		}
	
	@PostMapping(value="add.do") //스프링 4.3이상부터 사용가능
	/* @RequestMapping(value = "add.do", method = RequestMethod.POST) */
	public String add(BbsVo vo, HttpSession session, @SessionAttribute("loginUser") MemberVo memVo) {
//		MemberVo vo = new MemberVo();                 loginUser로 저장된 데이터를 memVo에 넣어줘라.
//        vo.setMemId(req.getParameter("memId"));
//        vo.setMemPass(req.getParameter("memPass"));
//        vo.setMemName(req.getParameter("memName"));
//        vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint"))); 이 작업을 스프링이 해준다.
//		MemberVo memVo = (MemberVo)session.getAttribute("loginUser");
		vo.setBbsWriter(memVo.getMemId());
		
        int num = bbsService.insertBbs(vo);
		return "redirect:/bbs/list.do";  
			//뷰이름에 redirect : 접두어를 사용하여 (포워드가 아닌)리다이렉트임을 표시 + 표시하지 않으면 포워딩을 진행해버림
	}
	
     	 @GetMapping(value="edit.do") //스프링 4.3이상부터 사용가능
		/* @RequestMapping(value = "edit.do", method = RequestMethod.GET) */
		public String editForm(int bbsNo, Map<String, Object> map) {	
//			String memId = req.getParameter("memId"); 파라미터값과 변수명이 같아야한다.
			BbsVo vo = bbsService.selectBbs(bbsNo);
			map.put("bbsVo", vo);
			return "bbs/bbsEdit"; 
		}
		
		 @PostMapping(value="edit.do") //스프링 4.3이상부터 사용가능
		/* @RequestMapping(value = "edit.do", method = RequestMethod.POST) */
		public String edit(BbsVo vo) {
//			HttpSession session = req.getSession();
//			MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
//			if (!mvo.getMemId().equals(req.getParameter("memId"))) { 
//			throw new RuntimeException("로그인한 사용자와 다른 회원 정보는 변경할 수 없습니다.");
//}
//			MemberVo vo = new MemberVo();
//		    vo.setMemId( req.getParameter("memId"));
//			vo.setMemPass( req.getParameter("memPass"));
//			vo.setMemName( req.getParameter("memName"));
//			vo.setMemPoint( Integer.parseInt(req.getParameter("memPoint") ));
//			int num = memberDao.updateMember(vo);  파라미터값과 객체의 이름이 같으면 스프링이 알아서 이 작업들을해준다.
			
			int num = bbsService.updateBbs(vo);
			return "redirect:/bbs/list.do";
	}
	
		 @GetMapping(value="del.do") //스프링 4.3이상부터 사용가능
		/* @RequestMapping(value = "del.do", method = RequestMethod.GET) */
		
		public String del(int bbsNo) {
			int num = bbsService.delBbs( bbsNo );
        	return "redirect:/bbs/list.do";
		}
		
}
//POJO : 옛날부터 사용하던 기능을 이용해서 애플리케이션을 만듦. 특정 라이브러리에 종속적이지 않은 기능을 사용



