<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Form은 param 받음</h2>
	첫번째 동물 : ${param.an1 }<br>
	두번째 동물 : ${param.an2 }<p>
	같은  동물 : ${param.an1==param.an2 }<br>

</body>
</html>