package com.exam.exspring.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//과제 (1)번부터 구현 
//(1)변경하려는 회원아이디가 로그인한 회원의 아이디와 다르다면 변경되지 않도록 구현 (보안은 자바에서 한다.)
//(2)회원정보변경 화면에서도, 화면의 회원아이디가 로그인한 회원의 아이디와 다르다면,
//이름, 포인트 값 변경이 블가능하고 submit 버튼도 출력되지 않도록 구현

/*@WebServlet("/member/edit.do")*/
public class MemEditServlet2 extends HttpServlet {		
	MemberDao memberDao = MemberDaoBatis.getInstance();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId"); 
			
		MemberVo vo = memberDao.selectMember(memId);
		
		req.setAttribute("memVo", vo); //요청객체에 저장후 jsp에 이동한다.
		
		req.getRequestDispatcher("/WEB-INF/jsp/member/MemEdit.jsp").forward(req, resp);
	}
	
/*	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MemberVo memVo = (MemberVo)session.getAttribute("loginUser");
        MemberVo vo = new MemberVo();
        if(!req.getParameter("memId").equals(memVo.getMemId())) {
        	resp.sendRedirect(req.getContextPath()+"/member/list.do");
        	return;
        }*/
        
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		if(!mvo.getMemId().equals(req.getParameter("memId"))) {
			//로그인회원아이디와 변경할 회원아이디가 다르면
			throw new RuntimeException("로그인한 사용자와 다른 회원 정보는 변경 불가");
			//return;
		}
	
	    MemberVo vo = new MemberVo();
        vo.setMemId(req.getParameter("memId"));
//        vo.setMemPass(req.getParameter("memPass"));
        vo.setMemName(req.getParameter("memName"));
        vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint")));
        int num = memberDao.updateMember(vo);
		
        //resp.sendRedirect("이동할 사이트 주소"); 명령을 사용하여,
        // 웹브라우저에게 특정 사이트로 이동하라는 명령을 담은 응답을 전송
//       resp.sendRedirect("http://localhost/member/list.do");
        resp.sendRedirect(req.getContextPath() + "/member/list.do");
	
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




