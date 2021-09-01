<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="com.spring.model.*"%>
<%
String user = (String) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("/SimpleSpringProject/login");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form List</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<c:set var="count" value="0" scope="page" />
<table class="table">
  <thead>
    <tr>
	      <th scope="col">#</th>
	      <c:forEach var="emp" items="${questions}">
	      	<th scope="col">${emp.getQuestion()}</th>
	      </c:forEach>
    </tr>
  </thead>

  <tbody>
    
    <%ArrayList<Response> res = (ArrayList<Response>) request.getAttribute("answers"); 
    ArrayList<Response> ques = (ArrayList<Response>) request.getAttribute("questions");
    int no_responses = (int) request.getAttribute("tot_q");
    int siz = ques.size();
    int k =0;
    while(no_responses >0){
    	%>
    	<tr>
    	<th scope="row"><c:set var="count" value="${count + 1}" scope="page"/>${count}</th>
    	<%
    	for(int i=0;i<res.size();i++){
    		if(i==siz){
    			break;
    		}
    		%>
    		<td><%= res.get(k).getAnswer() %></td>
    		<%
    		
    		k=k+1;
    	}
    	%>
    	</tr>
    	<%
    	no_responses = no_responses -1;
    }
    
    %>
    
  </tbody>

</table>
<script>
function copy(link,lin){
	alert(link);
	var l = link + lin;
	l.select();
	document.execCommand('copy');
	alert('copied');
}
</script>
</body>
</html>