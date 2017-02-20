<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
		<%
			if(session.getAttribute("username") != null)
				response.sendRedirect("loginSuccess.jsp");
		%>
	
		<form action=LoginServlet method="post">
			<label>UserName</label> <input type="text" name="username" required="true"> <br />
			<label>Password</label> <input type="password" name="password" required="true"><br />
			
			<input type="submit"> 
		</form>
		<p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message").toString() %></p>
		
		
</body>
</html>