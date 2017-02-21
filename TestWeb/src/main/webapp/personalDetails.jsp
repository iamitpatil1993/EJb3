<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>

	<%
		if (session.getAttribute("username") == null)
			response.sendRedirect("login.jsp");
	%>
	
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<form action="PersonalDetailsServlet" method="post">
			<div class="form-group">
				<label for="inputEmail">Email</label> <input type="email"
					class="form-control" id="inputEmail" name="email"
					placeholder="Email">
			</div>
			<div class="form-group">
				<label for="firstName">First Name</label> <input type="text"
					class="form-control" id="firstName", name="firstName" placeholder="First Name">
			</div>
			<div class="form-group">
				<label for="lastName">Last Name</label> <input type="text"
					class="form-control" id="lastName", name="lastName" placeholder="Last Name">
			</div>
			<button type="submit" class="btn btn-primary">Save</button>
		</form>
		<p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message").toString() %></p>	</div>
		
		<p><%= request.getAttribute("detailsMap") == null ? "" :  request.getAttribute("detailsMap").toString() %></p>
		
	<div class="col-md-2"></div>

</body>
</html>