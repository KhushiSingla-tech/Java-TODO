<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="dao.TodoDao" %>
<%@page import="connection.GetConnection" %>
<%@page import="model.ToDo" %>
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
					<h3 class="text-center">Edit TODO</h3>
					
					<%
										int id = Integer.parseInt(request.getParameter("id"));
													
														TodoDao td = new TodoDao(GetConnection.getCon());
														ToDo user = td.getTodoById(id);
										%>
					
						<form action="update" method="post">
						<input type="hidden" value="<%=user.getId()%>" name="id">
							<div class="form-group">
								<label for="exampleInputEmail1">Name</label> <input type="text"
									class="form-control" id="name" name="username" value="<%=user.getName()%>">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">TODO</label> <input type="text"
									class="form-control" id="todo" name="todo" value="<%=user.getTodo()%>">
							</div>
							<div class="form-group col-md-4">
								<label for="inputState">Status</label> 
								<select id="status" Class="form-control" name="status">
								<%
									if("Pending".equals(user.getStatus()))
									{%>
										<option value="Pending">Pending</option>
										<option value="Complete">Complete</option>
									<%}
									else
									{%>
										<option value="Complete">Complete</option>
										<option value="Pending">Pending</option>
									<%}
								%>
								</select>
							</div>
							<div class="text-center">
							<button type="submit" class="btn btn-primary">EDIT</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>