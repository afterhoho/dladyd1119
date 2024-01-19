<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이중 forTokens 써보자</title>
</head>
<body>

   <!-- 변수 선언 -->
   
   <c:set var="color" value="red,orange,yellow,green,blue,navy,vioet"/>
   <c:set var="num" value="1-2-3-4-5-6-7"/>

   <!-- c:forTokens 쓰기 위해 원소를 구별할수 있는 일정한 구분자 필요 -->
   
   <c:forTokens var="size" items="${num }" delims="-">

      <c:forTokens var="col" items="${color }" delims=",">

         <font size="${size }" color="${col }">야호!</font>

      </c:forTokens>
      
      <br>
      
   </c:forTokens>

</body>
</html>