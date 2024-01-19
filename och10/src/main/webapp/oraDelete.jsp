<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
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
	<h2>스크릿틀릿 + Statement 부서 삭제</h2>
	<% 
	String deptno = request.getParameter("deptno");
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@172.30.1.37:1521:xe";
	String sql = "select * from dept where deptno="+deptno;
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url , "scott", "tiger");
	Statement stmt = conn.createStatement();
	ResultSetMetaData rs = stmt.executeUpdate();
	%>

</body>
</html>