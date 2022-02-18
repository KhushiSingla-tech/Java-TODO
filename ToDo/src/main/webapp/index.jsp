<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection" %>
<%@page import="connection.GetConnection" %>
<%@page import="dao.TodoDao" %>
<%@page import="java.util.List" %>
<%@page import="model.ToDo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="component/css_js.jsp"%>
</head>
<body>

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
	
	<%
		String msg = (String)session.getAttribute("msg");
				if(msg != null)
				{
		%>
			<div class="alert alert-success" role="alert">
			  <%=msg%>
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
             <span aria-hidden="true">&times;</span> 
             </button>
			</div>
			<%
			session.removeAttribute("msg");
			%>
		<%
		}
		%>
	
	<%
		String msgfail = (String)session.getAttribute("msgfail");
				if(msgfail != null)
				{
		%>
			<div class="alert alert-danger" role="alert">
			  <%=msgfail%>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
              </button>
			</div>
			<%
			session.removeAttribute("msgfail");
			%>
		<%
		}
		%>

	<h1 class="text-center">TODO List</h1>

	<div class="container">
		<table class="table table-striped" border="1px">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Name</th>
					<th scope="col">TODO</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
			<%
			TodoDao td = new TodoDao(GetConnection.getCon());
					List<ToDo> list = td.getTodo();
					
					for(ToDo user : list)
					{
			%>
					<tr>
					<th scope="row"><%=user.getId() %></th>
					<td scope="col"><%=user.getName() %></td>
					<td><%=user.getTodo() %></td>
					<td><%=user.getStatus() %></td>
					<td>
						<a href="edit.jsp?id=<%=user.getId() %>" class="btn btn-sm btn-success"> Edit </a>
						<a href="delete?id=<%=user.getId() %>" class="btn btn-sm btn-danger"> Delete </a>
					</td>
				    </tr>
				<%}
			%>
				
			</tbody>
		</table>
	</div>

</body>
</html>