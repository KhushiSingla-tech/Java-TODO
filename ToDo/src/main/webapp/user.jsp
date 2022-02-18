<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="component/css_js.jsp"%>
<meta charset="ISO-8859-1">
<title>Start Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
</head>
<body>

	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	%>
	
	<%
	String msg = (String) session.getAttribute("msg");
	if (msg != null) {
	%>
	<div class="alert alert-success" role="alert">
		<%=msg%>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
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
	String msgfail = (String) session.getAttribute("msgfail");
	if (msgfail != null) {
	%>
	<div class="alert alert-danger" role="alert">
		<%=msgfail%>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<%
	session.removeAttribute("msgfail");
	%>
	<%
	}
	%>

	<div class="container">
		<div class="row">
			<div class="col-xl-l2 col-lg-12">
				<h1 class="text-center py-4">TODO App</h1>
			</div>
			<div class="col-xl-6">
				<div class="login-form border p-4 mb-4 rounded">
					<h2 class="border-bottom mb-4 pb-2">Login</h2>
					<form action="login" method="post">
						<div class="form-group">
							<label>Email</label> <input type="email" class="form-control"
								placeholder="Enter Email" name="email" required>
						</div>
						<div class="form-group">
							<label>Password</label>
							<div class="input-group">
							 <input type="password" id="loginpassword"
								class="form-control" placeholder="Enter Password"
								name="password" required>
							 <div class="input-group-append">
									<div class="input-group-text">
										<i class="far fa-eye" id="togglePasswordlogin"
											style="cursor: pointer;"></i>
									</div>
								</div>
						</div>
						</div>
						<button type="submit" class="btn btn-primary">Login</button>
					</form>
				</div>
			</div>
			<div class="col-xl-6">
				<div class="login-form border p-4 mb-4 rounded">
					<h2 class="border-bottom mb-4 pb-2">Register</h2>
					<form action="generate" method="post">
						<div class="form-group">
							<label>Name</label> <input type="text" class="form-control"
								placeholder="Enter Name" name="name" required>
						</div>
						<div class="form-group">
							<label>Email</label> <input type="email" class="form-control"
								placeholder="Enter Email" name="email" required>
						</div>
						<div class="form-group">
							<label>Password</label>
							<div class="input-group">
								<input type="password" class="form-control"
									placeholder="Enter Password" name="password" id="password"
									onChange="onChange()" required>
								<div class="input-group-append">
									<div class="input-group-text">
										<i class="far fa-eye" id="togglePassword"
											style="cursor: pointer;"></i>
									</div>
								</div>
							</div>
							<span id='demo'></span>
						</div>
						<div class="form-group">
							<label>Confirm Password</label>
							<div class="input-group">
							 <input type="password" class="form-control" placeholder="Confirm Password"
								name="confirm" id="confirm" onChange="onChange()" required>
							 <div class="input-group-append">
									<div class="input-group-text">
										<i class="far fa-eye" id="togglePasswordconfirm"
											style="cursor: pointer;"></i>
									</div>
								</div>
						</div>
							<span id='message'></span>
						</div>
						<button type="submit" class="btn btn-primary" onclick="chkpwd()">Register</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		$('#password, #confirm').on('keyup', function() {
			if ($('#password').val() == $('#confirm').val()) {
				$('#message').html('Matching').css('color', 'green');
			} else
				$('#message').html('Not Matching').css('color', 'red');
		});

		const togglePassword = document.querySelector('#togglePassword');
		const password = document.querySelector('#password');

		togglePassword.addEventListener('click', function(e) {
			const type = password.getAttribute('type') === 'password' ? 'text'
					: 'password';
			password.setAttribute('type', type);
			this.classList.toggle('fa-eye-slash');
		});
		
		const togglePasswordconfirm = document.querySelector('#togglePasswordconfirm');
		const passwordconfirm = document.querySelector('#confirm');

		togglePasswordconfirm.addEventListener('click', function(e) {
			const typeconfirm = passwordconfirm.getAttribute('type') === 'password' ? 'text'
					: 'password';
			passwordconfirm.setAttribute('type', typeconfirm);
			this.classList.toggle('fa-eye-slash');
		});
		
		const togglePasswordlogin = document.querySelector('#togglePasswordlogin');
		const passwordlogin = document.querySelector('#loginpassword');

		togglePasswordlogin.addEventListener('click', function(e) {
			const typelogin = passwordlogin.getAttribute('type') === 'password' ? 'text'
					: 'password';
			passwordlogin.setAttribute('type', typelogin);
			this.classList.toggle('fa-eye-slash');
		});
		
		function validatePassword() {
		    var password = $("#password").val();
		    
		    if (password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@%!])[0-9a-zA-Z@%!]{8,}$/))
		    {
			     $("#demo").html("Strong Password");
			     $("#demo").css('color','green');
			}
		    else
		    {
			     $("#demo").html("Week Password"); 
			     $("#demo").css('color', 'red');
		    }
		}

		$(document).ready(function() {
			$("#password").keyup(validatePassword);
		});
	</script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</body>
</html>