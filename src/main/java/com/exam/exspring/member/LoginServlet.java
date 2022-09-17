package com.exam.exspring.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/member/login.do") //실행될 주소
public class LoginServlet extends HttpServlet {		
	MemberDao memberDao = MemberDaoBatis.getInstance();
	
	//서블릿의 service() 메서드 : 요청방식에 상관없이 실행되는 메서드
	//서블릿의 doXxx() 메서드 : 요청방식이 xxx인 경우에 실행되는 메서드
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/jsp/member/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        MemberVo vo = new MemberVo();
        vo.setMemId(req.getParameter("memId"));
        vo.setMemPass(req.getParameter("memPass"));
        MemberVo memVo = memberDao.selectLoginMember(vo);
        //맞는 Id와 Pass를 입력할 경우 memVo에 값이 들어가기 때문에 null값인지 확인하면 된다.
        if(memVo == null) { //로그인이 실패한 경우
           resp.sendRedirect(req.getContextPath() + "/member/login.do"); //다시 로그인 페이지로 이동
        }else { //로그인이 성공한 경우
           HttpSession session = req.getSession(); //현재 요청(을 보낸 사용자)가 속한 세션객체 가져오기
           session.setAttribute("loginUser", memVo); //로그인 성공한 사용자 정보를 세션에 "loginUser"라는 이름으로 저장 
           resp.sendRedirect(req.getContextPath() + "/member/list.do"); //회원목록 페이지로 이동
        }
		
        //resp.sendRedirect("이동할 사이트 주소"); 명령을 사용하여,
        // 웹브라우저에게 특정 사이트로 이동하라는 명령을 담은 응답을 전송
//       resp.sendRedirect("http://localhost/member/list.do");
        
/*
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");		
		PrintWriter out = resp.getWriter();		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Insert title here</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>회원추가</h1>");
		
		out.println( num + "명의 회원이 추가되었습니다.");
		
		
		out.println("</body>");
		out.println("</html>");		
*/		

	}

}




