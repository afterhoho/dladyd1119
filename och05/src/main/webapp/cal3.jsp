<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>연산결과 오류 시 Java Script 이용한 에러 처리</h2>
   <%
      try {
         
         int num1 = Integer.parseInt(request.getParameter("num1"));
         int num2 = Integer.parseInt(request.getParameter("num2"));
         
         out.println(num1 + "+" + num2 + "=" + (num1+num2) + "<p>");
         out.println(num1 + "-" + num2 + "=" + (num1-num2) + "<p>");
         out.println(num1 + "*" + num2 + "=" + (num1*num2) + "<p>");
         out.println(num1 + "/" + num2 + "=" + (num1/num2) + "<p>");
         
      } catch (NumberFormatException e) {
      
   %>
   
   <script>
      alert("그게 숫자냐?");
      history.go(-1);      // 이전 페이지로 되돌아가라
   </script>
   
   <% } catch (ArithmeticException e) { %>
   
      <script>
         alert("0으로 나눌 수 없음");
         history.back();      // 이전 페이지로 되돌아가라
      </script>
      
   <% } catch (Exception e) {
         out.println(e.getMessage()); %>
         
      <script>
         alert("암튼 에러");
         location.href="num2.jsp";
      </script>
      
   <% } %>

</body>
</html>