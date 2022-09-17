<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  model에 저장한 값 : ${modelVal} </P>
<P>  modelMap에 저장한 값 : ${modelMapVal} </P>
<P>  map에 저장한 값 : ${mapVal} </P>

<p> @ModelAttribute로 저장한 값 : ${mv.myId}, ${mv.myName}</p> 
<p> @ModelAttribute로 생략한 값 : ${myVo.myId}, ${myVo.myName}</p> 
</body>
</html>
