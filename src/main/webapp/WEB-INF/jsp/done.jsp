<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Response Saved</title>
</head>
<body>
<p>Redirecting to home page in 2 seconds</p>
<script>
alert("response saved");
window.setTimeout(function(){

    window.location.href = "http://localhost:9090/SimpleSpringProject/user";

}, 2000);
</script>
</body>
</html>