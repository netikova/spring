<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.js"></script>
</head>
<body>
<%-- <%@ include file="" %> --%>
<%-- <c:import url=""></c:import> --%>
<%-- <jsp:include page="/WEB-INF/jsp/menu.jsp" /> --%>

<!-- Tiles ,SiteMesh 일일이 jsp에 include를 하지 않아도 적용할 수 있는 라이브러리 -->

		
<h1>게시글목록</h1>
<a href='${pageContext.request.contextPath}/bbs/add.do'>게시글작성</a><br>
<a href='<c:url value="/bbs/add.do"/>'><button type="button">새글쓰기</button></a><br>

<form action="${pageContext.request.contextPath}/bbs/list.do" >
    <select name="searchType" >
        <option value="title" >제목</option>         
        <option value="content" >내용</option>        
        <option value="total" >제목+내용</option>        
    </select>
    <script type="text/javascript">
       if('${searchInfo.searchType}'){
    	   /* document.querySelector('[name="searchType"]').value = '${searchInfo.searchType}'; */        
    	   $('[name="searchType"]').val( '${searchInfo.searchType}');       }
       
    </script>
	<input type="text" name="searchWord" value="${searchInfo.searchWord}" placeholder="검색어를 입력하세요" />
	<input type="submit" value="검색"/>
</form>

<table border="1">
    <thead>
         <tr><th>글번호</th>
             <th>글제목</th>
             <th>작성자</th>
             <th>작성일</th>
         </tr>
    </thead>
    <tbody>
        <c:forEach var="vo" items="${bbsList}"> 
	        <tr>
		      <td>  ${vo.bbsNo} </td>  <!-- 태그라이버리를 쓴다고 위에 선언 -->	<!-- bbsTitle을 클릭하면 실행 --><!-- GetMapping 값과 같아야 한다. -->
			  <td> : <a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}"><c:out value= "${vo.bbsTitle}"/></a> </td>	   
			  <td> : <c:out value= "${vo.bbsWriter}"/> </td>
			  <td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/></td>                                 <!-- 들어가는 파라미터 값이랑 같아야 한다. -->  
			</tr>	
        </c:forEach> 
    </tbody>

</table>	  


		
</body>
</html>
	
	
    