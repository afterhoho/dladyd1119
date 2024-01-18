<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
   <%
   String[] col = { "red", "orange", "yellow", "green", "blue", "navy", "violet", "black" };

   // n은 8미만 숫자
   int n = (int) (Math.random() * 8);

   // color1.jsp gugu1.jsp response.jsp
   String pgm = request.getParameter("pgm") + ".jsp";

   // 1. fOrm 없이 이동 Page를 지정하는 객체 선언
   RequestDispatcher rd = request.getRequestDispatcher(pgm);
   //form 대신 페이지 이동으로 쓸수 있음 
   if (pgm.equals("color1.jsp")) {
      // 변수선언
      request.setAttribute("color", col[n]);

      // rd = request.getRequestDispatcher ("color1.jsp"); 
   } else if (pgm.equals("gugu1.jsp")) {

      // 2단 9단
      request.setAttribute("num", n + 2);

      // rd = request.getRequestDispatcher ("gugu1.jsp"); 
   } else if (pgm.equals("response.jsp")) {
      // 예비
   }

   // 2. 이동 Page를 지정하는 객체 선언을 실제적으로 이동
   rd.forward(request, response);
   %>
</body>
</html>