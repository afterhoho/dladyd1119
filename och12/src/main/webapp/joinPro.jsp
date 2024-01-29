<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="member2" class="och12.Member2"></jsp:useBean>
	<jsp:setProperty property="*" name="member2" />
	<%
	MemberDao md = MemberDao.getInstance();
	int result = md.insert(member2);
	if (result > 0) {
	%>
	<script type="text/javascript">
		alert("회원가입 완료");
		location.href = "loginForm.jsp";
	</script>
	<%
	} else {
	%>
	<script type="text/javascript">
		alert("회원가입 실패");
		location.href = "joinForm.jsp";
	</script>
	<%
	}
	%>

</body>
</html>