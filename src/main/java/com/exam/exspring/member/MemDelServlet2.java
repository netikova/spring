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


/*@WebServlet("/member/del.do")*/
public class MemDelServlet2 extends HttpServlet {		
	MemberDao memberDao = MemberDaoBatis.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getParameter("abc").equals("def");
		
		
		req.setCharacterEncoding("UTF-8");       
        String memId = req.getParameter("memId");        
        int num = memberDao.delMember(memId);
        
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
		
		out.println( num + "명의 회원이 삭제되었습니다.");
		
		
		out.println("</body>");
		out.println("</html>");	
*/	


	}

}




