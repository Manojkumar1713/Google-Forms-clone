<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String user = (String) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("/SimpleSpringProject/login");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>MM Forms</title>
<style type="text/css">
.navbar {
	overflow: hidden;
	background-color: #333;
	position: sticky;
	top: 0;
	width: 100%;
}

.navbar a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.navbar a:hover {
	background: #ddd;
	color: black;
}

.mains {
	padding: 16px;
	margin-top: 30px;
}

#submit {
	background-color: #4CAF50;
	padding: 5px 5px 5px 5px;
}

</style>
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


	<input type="hidden" value="0" id="total_count" />
	<div class="navbar">
		<a href="#home">Form Builder</a> 
		<a href="#" onclick="view();" id="viewForm">View Current Form</a> 
		<a href="#" class="add">Add Section</a> 
		<a href="#" class="remove">Remove Section</a>
		<a href="formsList?user=<%= user%>" target="_blank" class="">Your Forms</a>
		<a href="submitted?user=<%= user%>" target="_blank">Your Responses</a>
		<a href="logout" class="">Logout</a>
	</div>
	<div class="mains">
		<div id="new_chq" style="width: 100%"></div>
	</div>

	<input type="hidden" value="1" id="total_chq">

	<form action="formSubmit" method="post">
		<input type="hidden" name="final_output" id="out" /> 
		<input type="hidden" name="user" value="${ user}" />
		<input type="hidden" name="formId" id="formId" />
		<center>
			<button style="display: none;" id="submit" onclick="appending();">Submit</button>
		</center>
	</form>

	<script>
		$('.add').on('click', add);
		$('.remove').on('click', remove);

		function add() {
			var x = document.getElementById("submit");
			if (x.style.display === "none") {
				x.style.display = "block";
			}
			var new_chq_no = parseInt($('#total_chq').val()) + 1;
			var q = parseInt($('#total_chq').val());
			var new_input = "<div id ='new_"+new_chq_no +"'><div class='container-contact100'><div class='wrap-contact100' id='cont_"+new_chq_no+"'> <div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'>"
					+ "<span class='label-input100'>Question *</span>"
					+ "<input class='input100' id='q_"
					+ new_chq_no
					+ "' type='text' name='name' placeholder='Enter Your Question "
					+ q
					+ "' onClick='this.setSelectionRange(0, this.value.length)'>"
					+ "</div>"
					+ "<div class='wrap-input100 input100-select bg1'>"
					+ "<span class='label-input100'>Choose *</span>"
					+ "<div>"
					+ "<select class='js-select2' id ='id_"
					+ new_chq_no
					+ "' name='service_"
					+ new_chq_no
					+ "' onchange='getval(this,"
					+ new_chq_no
					+ ");' required>"
					+ "<option value = '1'>Please choose</option>"
					+ "<option value ='s'>small text</option>"
					+ "<option value ='l'>Large Text</option>"
					+ "<option value='m'>Multiple choices</option>"
					+ "<option value ='c'>checkbox </option>"
					+ "<option value ='r'>radio </option>"
					+ "</select> <input type ='hidden' value ='1' id='m_option_"+new_chq_no+"' />"
					+ "<div class='dropDownSelect2'></div>"
					+ "</div>"
					+ "</div></div></div>";

			$('#new_chq').append(new_input);

			$('#total_chq').val(new_chq_no);

		}

		function getval(selectTag, id) {
			if (selectTag.value == '1') {
				removeTag(id);

			}

			if (selectTag.value == 's') {
				removeTag(id);
				var new_chq = parseInt($('#total_count').val()) + 1;
				var text = "<div id='bar_"+new_chq+"'><div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'>"
						+ "<span class='label-input100'>Answer *</span>"
						+ "<input class='input100' type='text' name='name' placeholder='Small Answer' style='width: 100%' readonly>"
						+ "</div> </div></div> ";

				$('#cont_' + id).append(text);

				$('#total_count').val(new_chq);
			} else if (selectTag.value == 'l') {
				removeTag(id);
				var new_chq = parseInt($('#total_count').val()) + 1;
				var text = "<div id='bar_"+new_chq+"'><div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question' >"
						+ "<span class='label-input100'>Answer *</span>"
						+ "<input class='input100' type='textarea' name='name' placeholder='Large Answer' style='width: 100%' readonly>"
						+ "</div> </div> </div>";

				$('#cont_' + id).append(text);

				$('#total_count').val(new_chq);
			} else if (selectTag.value == 'm' || selectTag.value == 'c'
					|| selectTag.value == 'r') {
				removeTag(id);
				var new_chq = parseInt($('#total_count').val()) + 1;
				var text = "<div id='bar_"+new_chq+"'><div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'>"
						+ "<span class='label-input100'>Answer *</span>"
						+ "<input class='input100' type='input' id='o_"
						+ id
						+ "_1' placeholder='Enter Your option' style='width: 100%'>"
						+ "</div><div id='options'></div><span onclick='addButton("
						+ id
						+ ");'> Add option</span>"
						+ "<span onclick='removeButton("
						+ id
						+ ");'> remove option</span></div></div> ";

				$('#cont_' + id).append(text);
				$('#total_count').val(new_chq);
			}
		}
		function addButton(id) {
			var button_count = parseInt($('#m_option_' + id).val()) + 1;
			var text = "<div id='option_"+button_count+"'><div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'>"
					+ "<span class='label-input100'>Answer *</span>"
					+ "<input class='input100' type='input' id='o_"
					+ id
					+ "_"
					+ button_count
					+ "' placeholder='Enter Your option "
					+ button_count + "' style='width: 100%'>" + "</div> </div>";
			$('#new_' + id + ' #options').append(text);
			$('#m_option_' + id).val(button_count);
		}
		function removeButton(id) {
			var last = $('#m_option_' + id).val();
			if (last > 1) {
				$('#new_' + id + ' #option_' + last).remove();
				$('#m_option_' + id).val(last - 1);
			}
		}
		function removeTag(id) {
			var last = $('#total_count').val();

			if (last > 0) {
				$('#new_' + id + ' #bar_' + last).remove();
				$('#total_count').val(last - 1);
			}
		}
		function remove() {
			var last_chq_no = $('#total_chq').val();
			if(last_chq_no == 2){
				var x = document.getElementById("submit");
				if (x.style.display === "block") {
					x.style.display = "none";
				}
			}
			if (last_chq_no > 1) {
				$('#new_' + last_chq_no).remove();
				$('#total_chq').val(last_chq_no - 1);
			}
		}
		
		function generateString() {
			var form_input = "";
			var total_questions = parseInt($('#total_chq').val());
			for (var i = 2; i <= total_questions; i++) {
				form_input += "<div class='container-contact100'><div class='wrap-contact100'>";
				var question = $('#q_' + i).val();
				var option = $('#id_' + i).val();
				var q_temp = question;
				if (question == '') {
					var t = parseInt(i) - 1
					q_temp = "Question " + t;
				}
				if (option == 's') {
					form_input += "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'><input class='input100' type='text' name='q_"+i+"' value='"+q_temp+"' readonly/></div>"
							+ "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'>"
							+ "<input class='input100' type ='text' name='a_"+i+"' placeholder ='Enter your answer' required /></div>";
				} else if (option == 'l') {
					form_input += "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'><input class='input100' type='text' name='q_"+i+"' value='"+q_temp+"' readonly/></div>"
							+ "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'><input class='input100' type ='text'  placeholder ='Enter your answer' name='a_"+i+"' required /></div>";
				} else if (option == 'm') {
					var option_count = parseInt($('#m_option_' + i).val())
					form_input += "<input class='input100' type='text' name='q_"+i+"' value='"+q_temp+"' readonly/>"
							+ "<select name='a_"+i+"'><br>"
					for (var j = 1; j <= option_count; j++) {
						var option = $('#o_' + i + '_' + j).val();
						form_input += "<option value ='"+option+"'>" + option
								+ "</option>";
					}
					form_input += "</select>";
				} else if (option == 'r') {
					var option_count = parseInt($('#m_option_' + i).val())
					form_input += "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'><input class='input100' type='text' name='q_"+i+"' value='"+q_temp+"' readonly/></div>"
					for (var j = 1; j <= option_count; j++) {
						var option = $('#o_' + i + '_' + j).val();
						form_input += "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'><input class='input100' type='radio' name = 'a_"+i+"' value ='"+option+"'required>"
								+ option + " </div>";
					}
				} else if (option == 'c') {
					var option_count = parseInt($('#m_option_' + i).val())
					form_input += "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'><input class='input100' type='text' name='q_"+i+"' value='"+q_temp+"' readonly/></div>"
					for (var j = 1; j <= option_count; j++) {
						var option = $('#o_' + i + '_' + j).val();
						form_input += "<div class='wrap-input100 validate-input bg1' data-validate='Please Type Your Question'><input class='input100' type='checkbox' name = 'a_"+i+"' value ='"+option+"'>"
								+ option + " </div>";
					}
				}
				form_input += "</div></div>"
			}
			form_input += "<input type='hidden' name='no_q' value='"+total_questions+"'/>"
			return form_input;
		}
		function view(){
			var inp = generateString();
			localStorage.setItem("form_input", inp);
			window.open("http://localhost:9090/SimpleSpringProject/viewForm",
					"_blank");
			
		}
		function appending() {
			var inp = generateString();
			var form_id = getRandomString(6);
			document.getElementById('out').value = inp;
			document.getElementById('formId').value = form_id;
			alert('You form is saved');
		}

		function getRandomString(length) {
			var randomChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
			var result = '';
			for (var i = 0; i < length; i++) {
				result += randomChars.charAt(Math.floor(Math.random()
						* randomChars.length));
			}
			return result;
		}
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
