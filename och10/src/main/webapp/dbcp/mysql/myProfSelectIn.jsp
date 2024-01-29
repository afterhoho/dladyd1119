<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="och10.Professor"%>
<%@page import="java.util.ArrayList"%>
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
   <%
   ArrayList<Professor> al = new ArrayList<Professor>();
   Context ctx = new InitialContext();
   DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
   Connection conn = ds.getConnection();
   String sql = "select profno, name from professor";
   Statement stmt = conn.createStatement();
   ResultSet rs = stmt.executeQuery(sql);
   
   while(rs.next()){
      Professor professor = new Professor();
      int profno = rs.getInt(1);
      String name = rs.getString(2);
      professor.setProfno(profno);
      professor.setName(name);
      al.add(professor);
      request.setAttribute("al", al);
      rs.close();
      stmt.close();
      conn.close();
   }
   stmt.close();
   conn.close();
   
   %>
   <h2>보고싶은 교수 번호를 선택하세요</h2>
   <form action="mySelect.jsp">
      <select name="pno">
         <c:forEach var="professor" items="${al }">
            <option value="${professor }">${professor.profno }
               ${professor.name }</option>
         </c:forEach>
      </select>
      <p>
         <input type="submit" value="선택 완료">
   </form>
</body>
</html>