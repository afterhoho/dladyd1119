<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="membercheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 삭제(회원탈퇴) 프로세스</title>
</head>
<body>

<%

   String passwd = request.getParameter("passwd");

   MemberDao md = MemberDao.getInstance();
   
   System.out.println("DeletePro id : " + id);
   
   // 과제 4. delete() 완성
   int result = md.delete(id, passwd);
   
   if (result == 1) {
      
      session.invalidate();
      
%>
   <script type="text/javascript">
      alert("탈퇴 완료.");
      location.href = "main.jsp";
   </script>
   
<%} else if (result == 0) { %>

   <script type="text/javascript">
      alert("암호 틀림. 다시 입력하세요.");
      history.go(-1);
   </script>

<%} else { %>

   <script type="text/javascript">
      alert("ID 틀림");
      history.go(-1);
   </script>
   
<%} %>

</body>
</html>