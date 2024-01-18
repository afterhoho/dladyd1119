<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%

	// parameter 받고 num만큼 누적
	int num =Integer.parseInt(request.getParameter("num"));
	int sum = 0;
	for(int i=0;i<= num;i++){
		sum+=i;
		}

%>
</head>
<body>
<h2>1부터 <%=num %>까지 합</h2>
<%=sum %>

</body>
</html>