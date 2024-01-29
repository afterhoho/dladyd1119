<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session (MVC 1 Model)</title>

<style type="text/css">
   table    {background-color: pink;}
   tr:hover {background-color: green;}
</style>

</head>
<body>

   <h2>회원가입</h2>
   
   <form action="agree.jsp">
   
      <table border="1">
      
         <tr>
            <td>ID</td>
            <td><input type="text"          name="id"             required="required"></td>
         </tr>
         
         <tr>
            <td>PW</td>
            <td><input type="password"    name="password"    required="required"></td>
         </tr>
         
         <tr>
            <td>Name</td>
            <td><input type="text"          name="name"       required="required"></td>
         </tr>
         
         <tr>
            <td><input type="submit" value="OK"></td>
            <td><input type="reset" value="Cancle"></td>
         </tr>
      
      </table>
   
   </form>

</body>
</html>