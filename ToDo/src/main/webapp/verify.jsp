<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<span>We already send a verification  code to your email.</span>
        
        <form action="verify" method="post">
            <input type="text" name="authcode" >
            <input type="submit" value="verify">
        </form>

</body>
</html>