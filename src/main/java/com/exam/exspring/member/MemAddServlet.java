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


/*@WebServlet("/member/add.do")*/
public class MemAddServlet extends HttpServlet {		
	MemberDao memberDao = MemberDaoBatis.getInstance();	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/jsp/member/MemAddForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
        MemberVo vo = new MemberVo();
        vo.setMemId(req.getParameter("memId"));
        vo.setMemPass(req.getParameter("memPass"));
        vo.setMemName(req.getParameter("memName"));
        vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint")));
        int num = memberDao.insertMember(vo);

        resp.sendRedirect(req.getContextPath() + "/member/list.do");


	}

}




