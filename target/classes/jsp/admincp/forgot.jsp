<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Forgot Password</title>
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
		
		<div class="forgot-box">
			<h1>Forgot Password</h1>
			<f:form action="#" ModelAttribute="tk" method="post" id="form-forgot" name="form" onsubmit="checkRepass()">
                        
			<div class="textbox">
				<i class="fa fa-user" aria-hidden="true"></i>
				<input path="username" type="text" autocomplete="off" placeholder="Enter Username" id="username" maxlength="24" name="username" value="" onfocus="myFunction()" onkeypress=" return checkKiTuDacBiet(event)"/><p style="color:red;display:none;" class="error errorUsername"></p>   
			</div>

			<div class="textbox">
				<i class="fa fa-lock" aria-hidden="true"></i>
				<input path="password2" type="password" placeholder="Nhập mật khẩu 2" id="password2" maxlength="24" name="password2" value="" onfocus="myFunction()"/><p style="color:red;display:none;" class="error errorPassword2"></p>
			</div>
			<div class="textbox">
				<i class="fa fa-lock" aria-hidden="true"></i>
				<input path="password" type="password" placeholder="Nhập mật khẩu mới" id="password" maxlength="24" name="password" value="" onfocus="myFunction()"/><p style="color:red;display:none;" class="error errorPassword1"></p>
			</div>
			
			<button class="btn" type="submit" name="btForgot">Lấy lại mật khẩu</button>
			<div style="color:white;text-align:center;width:470px;">
                <p style="color:red;" id="errorNotice">${error}</p>
                <p style="color:green;" id="successNotice">${success}</p>
                <div style="font-size:18px;"  class="forgot">
                	<a href="login">Về trang chủ</a>
                </div>
             </div>
             
			</f:form>
		</div>
	</body>
	<script>
		
		$(function(){
			$('#form-forgot').validate({
				rules:{
					username:{
						required:true,
						minlength:4,
					},
					password2:{
						required:true,
						minlength:6,
					},
					password1:{
						required:true,
						minlength:6,
					}
				},
				messages:{
					username:{
						required: "Username không được để trống",
						minlength: "Username phải từ 4 kí tự trở lên",
						
					},
					password2:{
						required:"Password 2 không được để trống",
						minlength:"Password 2 phải từ 6 kí tự trở lên",
					},
					password1:{
						required:"Password không được để trống",
						minlength:"Password phải từ 6 kí tự trở lên",
					}
				}
			});
		});
		
		function myFunction() {
            document.getElementById("errorNotice").innerHTML="";
            document.getElementById("successNotice").innerHTML="";
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
		/*
		function checkRepass(){
			if(document.form.password1.value!=document.form.repassword1.value){
				document.getElementById('errorNotice').innerHTML = 'Mật khẩu xác nhận không trùng khớp';
				return false;
			}else{
				document.getElementById('errorNotice').innerHTML = '';
			}
		}
		*/
	</script>
</html>