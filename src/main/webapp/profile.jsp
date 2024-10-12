<%@ page import="in.sp.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4.Profile Page</title>
</head>
<body>
       <%
          User user = (User) session.getAttribute("session_user");
       %>
       
       <h1>Welcome</h1>
       <h2>name : <%=user.getName() %></h2>
       <h2>email : <%=user.getEmail() %></h2>
       <h2>city : <%=user.getCity() %></h2>
       <a href="logout">LOGOUT</a>
</body>
</html>