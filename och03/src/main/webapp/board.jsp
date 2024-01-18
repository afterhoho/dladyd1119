<%@page import="java.io.FileWriter"%>
<%@page import="java.util.Date"%>
<%@page import="jakarta.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% //스크립 트릿
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	//out 도 내장객체이기 때문에 선언 안해도 됨 서블릿 안만들어도 jsp로 사용하면 더좋음
	
	Date date = new Date();
	long fileName = date.getTime();
	// getRealPath --> tomcat metadata file 저장
	String real = application.getRealPath("/WEB-INF/out/"+fileName+".txt");
	System.out.println(real);
	// file 저장 할 수 있는 객체 direcive 지시자
	FileWriter fw =new FileWriter(real);
	// file 내용
	String msg = "제목 : " + title + "\r\n";
	msg += "작성자 : "+writer+"\r\n";
	msg += "내용 : "+content+"\r\n";
	fw.write(msg);
	fw.close();
	
	
	// --------화면 출력-------------
	out.println("제목1 : " +title+"<p>");
	out.println("작성자1 : "+writer+"<p>");
	out.println("내용 : " +content+"<p>");


%>
<!-- 익스프레션 대신 위에 선언은 되어 있어야함  -->
제목2  :<%=title %><p>
작성자2 :<%=writer %><p>
내용2  :<%=content %><p>
</body>
</html>