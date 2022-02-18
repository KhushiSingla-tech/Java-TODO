<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="component/css_js.jsp"%>
</head>
<body style="background-color: #f0f1f2;">

	<%
		if(session.getAttribute("auth") == null)
		{
			response.sendRedirect("user.jsp");
		}
	%>

	<%@include file="component/navbar.jsp"%>
	
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	%>

	<div class="container">
		<div class="row p-5">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body">
					<h3 class="text-center">Add TODO</h3>
						<form action="add" method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">Name</label> <input type="text"
									class="form-control" id="name" name="username">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">TODO</label> <input type="text"
									class="form-control" id="todo" name="todo">
							</div>
							<div class="form-group col-md-4">
								<label for="inputState">Status</label> 
								<select id="status" Class="form-control" name="status">
									<option selected>--Select--</option>
									<option value="Pending">Pending</option>
									<option value="Complete">Complete</option>
								</select>
							</div>
							<div class="text-center">
							<button type="submit" class="btn btn-primary">ADD</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>