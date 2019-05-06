<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="samecontent/head.jsp" %>
<script src="../js/jquery-1.11.3.min.js"></script>
<script>
	function buttonclickAJAX(){
		$("#submit").click(function(){
			var user = $("#username").val();
			$.ajax({
				type:"POST",
				data:{username:user},
				url:"testwelcomeajax",
				success: function(result){
					$("#msg").html(result)
				}
			});
		});
		setInterval(buttonclickAJAX, 5000);	
	}
	function inputchangeAJAX(){
		$("#username").change(function(){
			var user = $("#username").val();
			$.ajax({
				type:"POST",
				data:{username:user},
				url:"testwelcomeajax",
				success: function(result){
					$("#msg2").html(result);
				}
			});
		});
		setInterval(inputchangeAJAX, 5000);	
	}
	function reloadevery3s(){
		var user = $("#username").val();
		$.ajax({
			type:"POST",
			data:{username:user},
			url:"testwelcomeajax",
			success: function(result){
				$("#msg2").html(result);
			}
		});
	}
	$(document).ready(function(){
		setInterval(reloadevery3s, 3000);	
	});
</script>
<body>
    <div id="wrapper">
        <%@include file="samecontent/menu.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Test AJAX</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
           	<div class="form-group">
           		<label>Tên tài khoản*</label>
                    <input class="form-control" name="username" id="username" placeholder="Nhập tên tài khoản"/>
               </div>
            <div class="form-group" align="center">
            	<input type="submit" class="btn btn-default" id="submit" name="submit" value="Test"/>
            </div>
            <br/>
            <div id="msg"></div>
            <div id="msg2"></div>
         </div>
	 </div>
            <!-- /.row -->          
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../vendor/raphael/raphael.min.js"></script>
    <script src="../vendor/morrisjs/morris.min.js"></script>
    <script src="../data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>
	
</body>


</html>
