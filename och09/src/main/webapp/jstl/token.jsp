<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <!-- c:set var : 변수 선언 -->
   <!-- c:set value : 선언한 변수 초기화 -->
   <c:set var="animals" value="토끼, 고양이 * 강아지, 여우"></c:set>
   
   <h2>사자의 생일</h2>
   
   <!-- delims : delimiters (구분자) : 구분할 기준점 -->
   <c:forTokens var="an" items="${animals }" delims="*">
      ${an }<br>
   </c:forTokens>

</body>
</html>