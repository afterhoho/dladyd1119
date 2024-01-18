<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<h1>사측연산</h1>
	<%
	// 스크립틀릿(scriptlet) --> JAVA coding
	// parameter -> num1 , num2
	// 사칙연산
	request.setCharacterEncoding("utf-8");
	int num1 = Integer.parseInt( request.getParameter("num1"));
	int num2 = Integer.parseInt( request.getParameter("num2"));
	
	
	int add = num1 + num2;
	int min = num1 - num2;
	int mul = num1 * num2;
	int div = num1 / num2;
	

	%>
	<!-- Expression(표현식) -->
   
   덧셈 : <%=add %>
   덧셈 : <%=min %>
   덧셈 : <%=mul %>
   덧셈 : <%=div %>
  <%--  덧셈 : <%=num1 + num2 %><p>
   뺄셈 : <%=num1 - num2 %><p>
   곱셈 : <%=num1 * num2 %><p>
   나눗셈 : <%=num1 / num2 %><p> --%>
	
</body>
</html>