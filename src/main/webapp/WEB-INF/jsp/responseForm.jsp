<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String user = (String) session.getAttribute("user");
String code = (String) session.getAttribute("code");
if (user == null) {
	response.sendRedirect("/SimpleSpringProject/login");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Response User Forms</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/noui/nouislider.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/main.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<!--===============================================================================================-->
</head>
<body>

			<span class="contact100-form-title"> Form Builder </span>
			<div id="output">
				<form action="userResponses" method="POST">
					${form}
					<input type ="hidden" name="questions" id="questions"/>
					<input type ="hidden" name="answers" id="answers"/>
					<input type ="hidden" name="user" value="<%= user%>"/>
					<input type ="hidden" name="code" value="<%= code %>" />
					<button>Submit</button>
				</form>
			</div>
			<div id="ans"></div>
			

	<script type="text/javascript">
		$(document).ready(function() {
            $("button").click(function() {
 
                var total_questions = document.getElementsByName("no_q")[0].value
                var ques = parseInt(total_questions);
                var obj = {}
                var question_hid = document.getElementById("questions");
                var answer_hid = document.getElementById("answers");
                for(var i=2;i<=5;i++){
                	var question = document.getElementsByName("q_"+i)[0].value
                	var answer = document.getElementsByName("a_"+i);
                	if(answer.length == 1){
                		obj[question] = answer[0].value
                		answer_hid.value += answer[0].value + "#"
                		question_hid.value += question +"#";
                	}
                	else{
                		var check = [];
                        $("input[type=checkbox]:checked").each(function(){
                            check.push($(this).val());
                        });
                		obj[question] = check
                		question_hid.value += question +"#";
                		answer_hid.value += check + "#"
                	}
                }
                console.log(obj);
            });
           
        });
		
	</script>

	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function() {
			$(this).select2({
				minimumResultsForSearch : 20,
				dropdownParent : $(this).next('.dropDownSelect2')
			});

			$(".js-select2").each(function() {
				$(this).on('select2:close', function(e) {
					if ($(this).val() == "Please chooses") {
						$('.js-show-service').slideUp();
					} else {
						$('.js-show-service').slideUp();
						$('.js-show-service').slideDown();
					}
				});
			});
		})
	</script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/noui/nouislider.min.js"></script>
	<script>
		var filterBar = document.getElementById('filter-bar');

		noUiSlider.create(filterBar, {
			start : [ 1500, 3900 ],
			connect : true,
			range : {
				'min' : 1500,
				'max' : 7500
			}
		});

		var skipValues = [ document.getElementById('value-lower'),
				document.getElementById('value-upper') ];

		filterBar.noUiSlider.on('update', function(values, handle) {
			skipValues[handle].innerHTML = Math.round(values[handle]);
			$('.contact100-form-range-value input[name="from-value"]').val(
					$('#value-lower').html());
			$('.contact100-form-range-value input[name="to-value"]').val(
					$('#value-upper').html());
		});
	</script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());

		gtag('config', 'UA-23581568-13');
	</script>

</body>
</html>
