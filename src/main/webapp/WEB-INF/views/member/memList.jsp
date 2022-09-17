<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.exam.exspring.member.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.exam.exspring.member.MemberDaoJdbc"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
</head>
<body>
<%-- <%@ include file="" %> --%>
<%-- <c:import url=""></c:import> --%>
<jsp:include page="/WEB-INF/views/comm/menu.jsp" /> 

<!-- Tiles ,SiteMesh 일일이 jsp에 include를 하지 않아도 적용할 수 있는 라이브러리 -->

		
<h1>회원목록</h1>
<a href='${pageContext.request.contextPath}/member/add.do'>회원추가</a><br>
    회원아이디 : 회원이름 : 회원포인트<br>	
	  
<c:forEach var="vo" items="${memList}">  <!-- 태그라이버리를 쓴다고 위에 선언 -->	
	<a href="${pageContext.request.contextPath}/member/edit.do?memId=${vo.memId}"><c:out value= "${vo.memId}"/></a>	   
	  : <c:out value= "${vo.memName}"/> : "${vo.memPoint}"
	<a href = '${pageContext.request.contextPath}/member/del.do?memId=${vo.memId}'>삭제</a><br>	
</c:forEach> 

		
</body>
</html>
	
	
    