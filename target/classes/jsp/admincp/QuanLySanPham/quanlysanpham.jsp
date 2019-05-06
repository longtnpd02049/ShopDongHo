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
function funcSearchTheoGia(){
	var getGia = document.getElementById("gia").value;
	if(getGia=="0-0"){
		document.getElementById("gia-min").value="0";
		document.getElementById("gia-max").value="0";
	}else if(getGia=="0-5"){
		document.getElementById("gia-min").value="0";
		document.getElementById("gia-max").value="5";
	}else if(getGia=="5-10"){
		document.getElementById("gia-min").value="5";
		document.getElementById("gia-max").value="10";
	}else if(getGia=="10-20"){
		document.getElementById("gia-min").value="10";
		document.getElementById("gia-max").value="20";
	}else if(getGia=="20-30"){
		document.getElementById("gia-min").value="20";
		document.getElementById("gia-max").value="30";
	}else if(getGia=="30-40"){
		document.getElementById("gia-min").value="30";
		document.getElementById("gia-max").value="40";
	}else if(getGia=="40-50"){
		document.getElementById("gia-min").value="40";
		document.getElementById("gia-max").value="50";
	}else if(getGia=="50-100"){
		document.getElementById("gia-min").value="50";
		document.getElementById("gia-max").value="100";
	}else if(getGia=="100-0"){
		document.getElementById("gia-min").value="100";
		document.getElementById("gia-max").value="0";
	}
}
function funcSearchTheoGia2(){
	var getHieuSp = document.getElementById("hieusp").value;
	document.getElementById("idhieusp").value=getHieuSp;
}
function funcSearchSapxep(){
	var getSapxeptheo = document.getElementById("sapxeptheo").value;
	document.getElementById("idsapxeptheo").value=getSapxeptheo;
}
function one(){
	$("#xacnhan").click(function(){
		load_data();
		var giaMin = $("#gia-min").val();
  	  	var giaMax = $("#gia-max").val();
  	  	var idHieuSp = $("#idhieusp").val();
  	  	var idsapxeptheo = $("#idsapxeptheo").val();
  	  	var searchvalue = $("#search").val();
  	  	if(searchvalue!=''){
		  	$.ajax({  
		    	url:"quanlysanphamajax",  
		        method:"POST",  
		        data:{key:searchvalue,pageid:page,giaMin:giaMin,giaMax:giaMax,idHieuSp:idHieuSp,idsapxeptheo:idsapxeptheo},  
		        success:function(data){  
		        	$('#show').html(data);  
		   		}
		    });exit;
  	  	}
	});
}
function two(){ //Hiển thị khi back lại
	load_data();
	var giaMin = $("#gia-min").val();
  	var giaMax = $("#gia-max").val();
  	var idHieuSp = $("#idhieusp").val();
  	var idsapxeptheo = $("#idsapxeptheo").val();
  	var searchvalue = $("#search").val();
	if(searchvalue!=''){
		$.ajax({  
	    	url:"quanlysanphamajax",  
	        method:"POST",  
	        data:{key:searchvalue,pageid:page,giaMin:giaMin,giaMax:giaMax,idHieuSp:idHieuSp,idsapxeptheo:idsapxeptheo},  
	        success:function(data){  
	        	$('#show').html(data);  
	   		}
	    });
		exit;
	}
}
function three(){ //Hiển thị khi gõ vào search
	$("#search").keyup(function(){
		load_data();
		var giaMin = $("#gia-min").val();
  	  	var giaMax = $("#gia-max").val();
  	  	var idHieuSp = $("#idhieusp").val();
  	  	var idsapxeptheo = $("#idsapxeptheo").val();
  	  	var searchvalue = $("#search").val();
  	  	if(searchvalue!=''){
	  	  	$.ajax({  
		    	url:"quanlysanphamajax",
		        method:"POST",  
		        data:{key:searchvalue,pageid:page,giaMin:giaMin,giaMax:giaMax,idHieuSp:idHieuSp,idsapxeptheo:idsapxeptheo},  
		        success:function(data){  
		        	$('#show').html(data);  
		   		}
		    });exit;
  	  	}
	});
}
function load_data(page){
	//http://localhost:8080/DuAn2/admincp/quanlysanphamajax?giaMin=10&giaMax=20&idHieuSp=0&idsapxeptheo=1
	  var giaMin = $("#gia-min").val();
	  var giaMax = $("#gia-max").val();
	  var idHieuSp = $("#idhieusp").val();
	  var idsapxeptheo = $("#idsapxeptheo").val();
	  var searchvalue = $("#search").val();
	      $.ajax({  
	      	url:"quanlysanphamajax",  
	        method:"POST",  
	        data:{key:searchvalue,pageid:page,giaMin:giaMin,giaMax:giaMax,idHieuSp:idHieuSp,idsapxeptheo:idsapxeptheo},  
	        success:function(data){  
	        	$('#show').html(data);  
	        }  
	      });exit;
}  
$(document).ready(function(){  
	//load_data();
	funcSearchTheoGia();
	funcSearchTheoGia2();
	funcSearchSapxep();
	setTimeout(two,0);
	$(document).on('click', '.page-link', function(){  
		var page = $(this).attr("id");  
		load_data(page);
	});
	setInterval(one,0);
	setInterval(three,0);
});
</script>  
<body>
	<div id="wrapper">
		<%@include file="../samecontent/menu.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Quản lý Sản phẩm</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="form-group">
        		<label>Giá</label>
                <select class="form-control" name="gia" id="gia" onchange="funcSearchTheoGia()">
                	<option value="0-0">Tất cả mệnh giá</option>
					<option value="0-5">Giá dưới 500k</option>
					<option value="5-10">Giá từ 500k - 1 triệu</option>
					<option value="10-20">Giá từ 1 - 2 triệu</option>
					<option value="20-30">Giá từ 2 - 3 triệu</option>
					<option value="30-40">Giá từ 3 - 4 triệu</option>
					<option value="40-50">Giá từ 4 - 5 triệu</option>
					<option value="50-100">Giá từ 5 - 10 triệu</option>
					<option value="100-0">Giá trên 10 triệu</option>
                </select>
            </div>
			<div class="form-group">
				<label>Hiệu sản phẩm</label>
				<select class="form-control" name="hieusp" id="hieusp" onchange="funcSearchTheoGia2()">
					<option value="0">Tất cả các hiệu sản phẩm</option>
					<c:forEach items="${hieuspList}" var="item">
						<option value="${item.idHieuSp}">${item.tenHieuSp}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Sắp xếp theo</label>
				<select class="form-control" name="sapxeptheo" id="sapxeptheo" onchange="funcSearchSapxep()">
					<option value="0">Sắp xếp theo mới nhất</option>
					<option value="1">Sắp xếp theo cũ nhất</option>
					<option value="2">Sắp xếp theo giá thấp - cao</option>
					<option value="3">Sắp xếp theo giá cao - thấp</option>
				</select>
            </div>
            <div class="form-group" align="center">
            	<input type="hidden" name="gia-min" id="gia-min" value="0">
				<input type="hidden" name="gia-max" id="gia-max" value="0">
				<input type="hidden" name="idhieusp" id="idhieusp" value="0">
				<input type="hidden" name="idsapxeptheo" id="idsapxeptheo" value="0">
            	<input type="submit" class="btn btn-default" id="xacnhan" value="Xác nhận"/>
            </div>
            <div class="form-group" style="width:60%;float:left;">
            	<label>Tìm kiếm sản phẩm</label>
           		<input class="form-control" type="text" name="search" id="search" placeholder="Tìm kiếm theo tên sản phẩm..." autocomplete="off"/>
           	</div><br/><br/><br/>
			<div id="show"></div>
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
