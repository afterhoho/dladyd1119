<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
   // parameter 받기 (body에 속성으로 넣어줄거라서 body 위에서)
   String color = request.getParameter("color");
%>

<body bgcolor="<%=color%>">
   <h1>Expression 이용한 색깔 변경</h1>
</body>

</html>