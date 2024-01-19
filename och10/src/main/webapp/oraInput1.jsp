<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

   // 파라미터 받아오기
   String deptno = request.getParameter("deptno");
   String dname = request.getParameter("dname");
   String loc = request.getParameter("loc");
   
   // DB 연동
   String driver = "oracle.jdbc.driver.OracleDriver";
//   String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
   String url = "jdbc:oracle:thin:@localhost:1521:xe";
//   String url = "jdbc:oracle:thin:@172.30.1.37:1521:xe";      // 내 IP
   
   // 쿼리문 작성
   String sql = String.format("Insert into dept values (%s, '%s', '%s')", deptno, dname, loc);
   
   // 콘솔에 찍어보자
   System.out.println("sql : " + sql);
   
   // 드라이버 로드
   Class.forName(driver);
   
   // DB 연동
   Connection conn = DriverManager.getConnection(url, "scott", "tiger");
   
   // Statement 객체 생성
   // 보안 상관 없으면 createStatement()
   Statement stmt = conn.createStatement();
   
   // executeUpdate()는 int로 받음
   int result = stmt.executeUpdate(sql);
   
   // 작업 결과 화면에 뿌리기
   if (result > 0)    out.println("입력 성공!");
   else            out.println("입력 실패ㅠ");
   
   // 닫기
   stmt.close();
   conn.close();
   
%>
</body>
</html>