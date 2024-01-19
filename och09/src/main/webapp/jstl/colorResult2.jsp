<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--   {"red","orange","yellow","green","blue","navy","violet"} -->
	<c:forEach var="cnt" begin="1" end="7">
		<c:forEach var="col" items="${color }">
		<font color="${col }" size="${cnt }"> 야호 </font>	
		</c:forEach>
		<br>
		
	</c:forEach>
	

</body>
</html>