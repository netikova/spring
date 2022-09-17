package com.exam.exspring.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/member/addform.do")
public class MemAddFormServlet extends HttpServlet {		
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/jsp/member/MemAddForm.jsp").forward(req, resp);
	}

// "http://localhost:8000/exweb/member/list.do"로 요청을 보내면,
// 웹브라우저에 회원목록이 출력되도록 구현
/*		req.setCharacterEncoding("UTF-8"); //POST방식으로 전송되는 한글 파라미터 인코딩
		String aval = req.getParameter("a"); //파라미터의 값이 1개일때	
		
		String[] bvals = req.getParameterValues("b"); //파라미터의 값이 여러개일때				
*/
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");		
//		PrintWriter out = resp.getWriter();		
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<meta charset='UTF-8'>");
//		out.println("<title>Insert title here</title>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<h1>회원추가</h1>");
//		
//		out.println("<form action='" + req.getContextPath() + "/member/add.do' method='post'>");
//		out.println("아이디 : <input type='text' name='memId' /><br>");
//		out.println("비밀번호 : <input type='password' name='memPass' /><br>");
//		out.println("이름 : <input type='text' name='memName' /><br>");
//		out.println("포인트 : <input type='text' name='memPoint' /><br>");
//		out.println("<input type='submit'/>");
//		out.println("</form>");
//		
//		out.println("</body>");
//		out.println("</html>");		

	}






