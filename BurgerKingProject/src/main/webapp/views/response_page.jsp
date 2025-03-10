<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.burgerking.model.dto.UserDTO"%>
<%
/* 	// 자바 코드 사용 가능!
	String message = (String)request.getAttribute("message"); 
	// 다형성이 적용됨 - Object타입으로 뽑히다 보니 형변환 필요 String 자식객체로 사용 가능!
	
	// UserDTO의 user필드에 필요한 값이 저장되어있음
	UserDTO user = (UserDTO)request.getAttribute("user"); // UserDTO 값이 뽑힘
	
	String userId = user.getUserId();
	String userName = user.getUserName(); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답페이지</title>
</head>
<body>
	
	
	<!--  
		"사용자가 입력한 userName"님!!
		
		회원가입에 성공하셨습니다~~~
		
		가입한 아이디 : userId
	-->
<%-- 	<h1><%= userName %>님!</h1> --%>
	<h1>${ user.userName }님!</h1>
	<p>
		<!-- request.getAttribute("message") -->
<%-- 		<%= message %> <br/> --%>
		${message}<br/>
		회원가입에 성공하셨습니다~~ <br/><br/>
		
<%-- 		가입에 성공한 아이디 : <%= userId %> --%>
		가입에 성공한 아이디 : ${ user.userId } <br/>
	</p>
	
	
</body>
</html>