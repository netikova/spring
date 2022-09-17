<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/comm/menu.jsp" /> 
  <h1>게시글변경</h1>     
     

        <form action='<%=request.getContextPath() %>/bbs/edit.do' method='post'>
        <table>
            <tbody>
                <tr><th>글번호</th><td><input type='text' name='bbsNo' value="${bbsVo.bbsNo}" readonly="readonly"/></td></tr>
                <tr><th>제목</th><td><input type='text' name='bbsTitle' value="${bbsVo.bbsTitle}" /></td></tr>
                <tr><th>내용</th><td><textarea name='bbsContent' rows="20" cols="20">${bbsVo.bbsContent}</textarea></td></tr>   
                                            <!-- textarea타입은 값을 속성에 넣지 않고 내용으로 넣는다. -->                                   
                <tr><th>작성자</th><td><c:out value="${bbsVo.bbsWriter}" /></td></tr>
                <tr><th>작성일</th>
                   <td>
                      <fmt:formatDate value="${bbsVo.bbsRegDate}" pattern="yyyy년MM월dd일 HH시mm분ss초"/>
                   </td>
                </tr>
            </tbody>
        </table>
        
			<%-- 글번호 : <input type='text' name='bbsNo' value="${bbsVo.bbsNo}" readonly="readonly"/><br> <!-- 글번호를 보여주기위해 그냥 text를 쓴다. 숨기려면 hidden -->
			제목 : <input type='text' name='bbsTitle' value="${bbsVo.bbsTitle}" /><br> 
			내용 : <input type='text' name='bbsContent' value="${bbsVo.bbsContent}" /><br>
			작성자 : <c:out value="${bbsVo.bbsWriter}" /><br> 
			작성일 : ${bbsVo.bbsRegDate}<br> 	 --%>	
		<input type='submit' value="저장" />			
		<a href = '${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}'><button type="button">삭제</button></a><br>		
		</form>
		
		</hr>
		<form id="replyForm">
		   <input type = "hidden" name="repBbsNo" value="${bbsVo.bbsNo}" /> <!-- 게시글 번호를 부모속성으로 -->
		   <textarea name="repContent" rows="5" cols="30"></textarea>
		   <input type="button" value="저장" id="saveBtn" />
		</form>
		
		<hr />
		<div id="replyDiv">
		
		
		</div>
		<script type="text/javascript">
		    function refreshReplyList(){
		    	$.ajax({
					  url: "${pageContext.request.contextPath}/reply/list.do", //요청주소
					  method: "GET", //요청방식			
					  data: {
						    repBbsNo : $('[name="repBbsNo"]').val()				
						  }, //파라미터		  
					   dataType: "json" //요청의 결과(서버의 응답)으로 받을 데이터의 형식			
					}).done(function( msg ) { //요청에 대한 응답을 성공적으로 받았을때 실행할 함수			
					   console.log(msg); // List<ReplyVo>
					   $('#replyDiv').empty();
					   for (var i = 0; i < msg.length; i++) {
						var vo = msg[i];
						console.log(vo.repNo, vo.repContent, vo.repWriter, vo.repRegDate);
//						$('#replyDiv').append('<div>' + vo.repContent + "</div>");
//						$('#replyDiv').append( $('<div>').text(vo.repContent) );
						
						$('<div>').text(vo.repWriter).appendTo( '#replyDiv' ); //<div>vo.repContent</div>
						$('<div>').text(vo.repRegDate).appendTo( '#replyDiv' ); //<div>vo.repContent</div>
						$('<div>').text(vo.repContent).appendTo( '#replyDiv' ); //<div>vo.repContent</div>
						
						if( vo.repWriter === '${loginUser.memId}'){
							$('<button>').attr('data-no', vo.repNo).addClass('delBtn').text('삭제2').appendTo('#replyDiv'); //<button>삭제</button>
							
						}
						
						$('<hr>').appendTo( '#replyDiv' ); //<hr></hr>
					
						
					}
					   
			 
					}).fail(function( jqXHR, textStatus ) { //요청이 실패한 경우 실행할 함수
				      alert( "Request failed: " + textStatus );
			        });
		    }
		    
		    $('#replyDiv').on('click', '.delBtn', function(){
		    	alert('삭제!');			    	
		    	$.ajax({
					  url: "${pageContext.request.contextPath}/reply/del.do", //요청주소
					  method: "GET", //요청방식			
					  data: {
						 repNo : $(this).attr('data-no')    /* this의 data-no속성값 클릭된 삭제버튼(==클릭이벤트가 발생한 엘리먼트)의 data-no 속성값 */
						  }, //파라미터		  
					   dataType: "json" //요청의 결과(서버의 응답)으로 받을 데이터의 형식			
					}).done(function( msg ) { //요청에 대한 응답을 성공적으로 받았을때 실행할 함수			
					   console.log(msg); // List<ReplyVo> {no:1}
					   alert(msg.no + '개의 댓글이 삭제되었습니다.');
					   refreshReplyList();	
					   
					}).fail(function( jqXHR, textStatus ) { //요청이 실패한 경우 실행할 함수
				      alert( "Request failed: " + textStatus );
			        });
		    });
		
		    refreshReplyList();	
		 

		   $('#saveBtn').on('click', function() {
			$.ajax({
				  url: "${pageContext.request.contextPath}/reply/add.do", //요청주소
				  method: "POST", //요청방식
				//  data : 'repBbsNo=부모글번호&repContent=댓글내용'
				  data: { repBbsNo : $('[name="repBbsNo"]').val()
					  , repContent: $('[name="repContent"]').val() }, //파라미터
			    //  data:  $('#replyForm').serialize(), //폼 내부의 입력요소들을 파라미터 문자열로 생성
				  dataType: "json" //요청의 결과(서버의 응답)으로 받을 데이터의 형식
				  ,success: function(msg){} //성공시 실행할 함수
				  ,error: function(jqXHR, textStatus) {} //실패시 실행할 함수
				  ,complete: function(){} //성공하든 실패하든 항상 실행할 함수
				}).done(function( msg ) { //요청에 대한 응답을 성공적으로 받았을때 실행할 함수
				 //서버로부터 받은 응답이 인자로 전달된다
			     //alert( msg );  //'{"no" : 1}'
				 // var data = JSON.parse(msg);  //'{"no" : 1}' -> {"no" : 1 } 메세지를 받아서 JSON형식으로 파싱
				 alert(msg.no + '개의 댓글을 저장했습니다.'); //1
				   $('[name="repContent"]').val(''); 
				   refreshReplyList();				 
				 
				}).fail(function( jqXHR, textStatus ) { //요청이 실패한 경우 실행할 함수
			      alert( "Request failed: " + textStatus );
				}).always(function(){  //성공하든 실패하든 요청 처리가 끝나면 항상 실행할 함수
					console.log("complete");					
				});			
		    });
		
		</script>

</body>
</html>

