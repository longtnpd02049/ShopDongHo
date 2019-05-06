<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta charset="utf-8"/>
		<title>Login Form</title>
		<link rel="stylesheet" type="text/css" href="../css/loginstyle.css"/>
		<script src="../js/jquery.js"></script>
		<script src="../js/jquery.validate.min.js"></script>
		<style>
			.error{
				color:red;
				font-size:13px;
			
				margin:20px;
				padding-top:3px;
			}
		</style>
	</head>
	<body>
		
		<div class="login-box">
			<h1>Login</h1>
			<f:form modelAttribute="tk" method="post" id="form-login">
                        
			<div class="textbox">
				<i class="fa fa-user" aria-hidden="true"></i>
				<input  type="text" placeholder="Enter Username" id="username" maxlength="24" autocomplete="off" name="username" value="" onfocus="myFunction()" onkeypress=" return checkKiTuDacBiet(event)"/><p style="color:red;display:none;" class="error errorUsername"></p>   
			</div>

			<div class="textbox">
				<i class="fa fa-lock" aria-hidden="true"></i>
				<input type="password" placeholder="Enter Password" id="password" maxlength="24" name="password" value="" onfocus="myFunction()"/><p style="color:red;display:none;" class="error errorPassword"></p>
			</div>
			<button class="btn" type="submit" name="btlogin">Login</button>
			<div style="color:white;text-align:center;width:470px;">
                <p style="color:red;" id="errorNotice">
	                ${error}
				</p>
                <div style="font-size:18px;"  class="forgot">
                	<a href="forgot">Quên thông tin tài khoản?</a>
                </div>
             </div>
			</f:form>
		</div>
	</body>
	<script>
		$(function(){
			$('#form-login').validate({
				rules:{
					username:{
						required:true,
						minlength:4,
					},
					password:{
						required:true,
						minlength:6,
					}
				},
				messages:{
					username:{
						required: "Username không được để trống",
						minlength: "Username phải từ 4 kí tự trở lên",
						
					},
					password:{
						required:"Password không được để trống",
						minlength:"Password phải từ 6 kí tự trở lên",
					}
				}
				
			});
		});
		function myFunction() {
            document.getElementById("errorNotice").innerHTML="";
         }
		function checkKiTuDacBiet(evt)
	    {
			var regex = new RegExp("^[a-zA-Z0-9]+$");//chỉ cho nhập số và chữ
		    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		    if (!regex.test(key)) {
		       event.preventDefault();
		       return false;
		    }
	    }
	</script>
</html>