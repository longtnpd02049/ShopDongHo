<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../samecontent/head.jsp"%>
<script src="../js/jquery-1.11.3.min.js"></script>
<script>
	function timkiem(){
		$("#search").keyup(function(){
			load_data();
			var tukhoa = $("#search").val();
			if(tukhoa!=''){
				$.ajax({
					url:"quanlydanhmucajax",
					method:"POST",
					data:{pageid:page,key:tukhoa},
					success:function(data){
						$("#show").html(data);
					}
				});
				exit;
			}
		});
	}
	function load_data(page){
		var tukhoa = $("#search").val();
    	$.ajax({  
	      	url:"quanlydanhmucajax",  
	        method:"POST",
	        data:{pageid:page,key:tukhoa},
	        success:function(data){  
	        	$('#show').html(data);  
	        }
    	});
    	exit;
	}
	$(document).ready(function(){
		//load_data();
		$(document).on('click', '.page-link', function(){  
			var page = $(this).attr("id");  
			load_data(page);
		});
		setInterval(timkiem,0);
		setTimeout(load_data,100);
	});
</script>
<body>
	<div id="wrapper">
		<%@include file="../samecontent/menu.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Quản lý Danh mục cha</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="form-group">
				<label>Tìm kiếm</label>
				<input class="form-control" type="text" name="search" id="search" placeholder="Tìm kiếm theo tên danh mục..." autocomplete="off"/>
			</div>
			<div class="row">
				<div id="show"></div>
			</div>
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
