<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

	<%
		if (session.getAttribute("username") == null)
			response.sendRedirect("login.jsp");
	%>

	<p>Login Successful</p>
	<p>
		hello
		<%=session.getAttribute("username")%></p>
	<br />
	
	<a href="LogoutServlet">Logout</a></t> <a href="personalDetails.jsp">Provide Personal Details</a>
</body>
</html>