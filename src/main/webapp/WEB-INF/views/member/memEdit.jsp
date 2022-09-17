<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/WEB-INF/views/comm/menu.jsp" /> 
<%-- <jsp:include page="/WEB-INF/jsp/menu.jsp" /> --%>
	<h1>회원정보변경</h1>
     
     
<%-- 	 <form action='<%=request.getContextPath() %>/member/edit.do' method='post'>
	<c:choose>
	    <c:when test="${sessionScope.loginUser.memId eq memVo.memId}">	 
		아이디 : <input type='text' name='memId' value="${memVo.memId}" readonly="readonly"/><br> 
		비밀번호 : <input type='password' name='memPass' value="${memVo.memPass}" /><br> 
		이름 : <input type='text' name='memName' value="${memVo.memName}" /><br> 
		포인트 : <input type='text' name='memPoint' value="${memVo.memPoint}" /><br>
		<input type='submit' />
		</c:when>
		<c:otherwise>
		아이디 : <input type='text' name='memId' value="${memVo.memId}" readonly="readonly"/><br> 
		비밀번호 : <input type='password' name='memPass' value="${memVo.memPass}" /><br> 
		이름 : <input type='text' name='memName' value="${memVo.memName}" readonly="readonly" /><br> 
		포인트 : <input type='text' name='memPoint' value="${memVo.memPoint}" readonly="readonly"/><br>
		
		</c:otherwise>
    </c:choose> --%>
    <a href='${pageContext.request.contextPath}/member/list.do'><input type="button" value="목록"></a>
	<!-- </form>  -->
	<form action='<%=request.getContextPath() %>/member/edit.do' method='post'>
		아이디 : <input type='text' name='memId' value="${memVo.memId}" /><br> <!-- readonly="readonly" -->
		<%-- 비밀번호 : <input type='password' name='memPass' value="${memVo.memPass}" /><br>  --%>
		이름 : <input type='text' name='memName' value="${memVo.memName}" 
		<c:if test= "${memVo.memId==loginUser.memId}"></c:if>/><br> 
		포인트 : <input type='text' name='memPoint' value="${memVo.memPoint}" />
		<%-- <c:if test= "${memVo.memId==loginUser.memId}">readonly="readonly"</c:if>/><br> --%>
		<input type='submit' />
		<%-- <c:if test= "${memVo.memId==loginUser.memId}"> --%>
		
		<%-- </c:if>  --%>
		</form>

</body>
</html>

