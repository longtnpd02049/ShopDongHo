<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../samecontent/head.jsp" %>
<body>
    <div id="wrapper">
        <%@include file="../samecontent/menu.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Thêm nhân viên</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <f:form method="post" modelAttribute="adduser">
            	<div class="form-group">
            		<label>Tên tài khoản*</label>
                     <input class="form-control" name="username" placeholder="Nhập tên tài khoản" autocomplete="off" required/>
                </div>
                <div class="form-group">
                	<label>Chức vụ*</label>
	                <select class="form-control" name="role">
	                	<option value="Administrator">Administrator</option>
	                	<option value="Moderator">Moderator</option>
	                </select>
                </div>
                <div class="form-group">
            		<label>Password*</label>
                     <input class="form-control" name="password" type="password" placeholder="Nhập mật khẩu cấp 1" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Password 2*</label>
                     <input class="form-control" name="password2" type="password" placeholder="Nhập mật khẩu cấp 2" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Họ tên*</label>
                     <input class="form-control" name="hoTen" placeholder="Nhập họ và tên" autocomplete="off" required/>
                </div>
                <div class="form-group">
                	<label>Giới tính*</label>
	                <select class="form-control" name="gioiTinh">
	                	<option value="0">Nam</option>
	                	<option value="1">Nữ</option>
	                </select>
                </div>
                <div class="form-group">
            		<label>Số điện thoại 1*</label>
                     <input class="form-control" name="sdtUser1" placeholder="Nhập số điện thoại 1" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Số điện thoại 2</label>
                     <input class="form-control" name="sdtUser2" placeholder="Nhập số điện thoại 2" autocomplete="off"/>
                </div>
                <div class="form-group">
            		<label>Email</label>
                     <input class="form-control" name="email" placeholder="Nhập Email" autocomplete="off"/>
                </div>
                <div class="form-group">
                     <label>Địa chỉ*</label><textarea class="form-control" rows="3" name="diaChi" placeholder="Nhập địa chỉ nhân viên" required></textarea>
                </div>
                <div class="form-group">
                     <input class="form-control" type="hidden" name="status" value="ok" autocomplete="off"/>
                </div>
                <div class="form-group">
            		<label>Mô tả thêm</label>
                     <textarea class="form-control" rows="3" name="moTaThem" placeholder="Nhập mô tả thêm nhân viên"></textarea>
                </div>
	            <div class="form-group" align="center">
	            	<input type="submit" class="btn btn-default" value="Thêm nhân viên"/>
	            	<a href="quanlynhanvien"><input type="button" class="btn btn-default" value="Quay lại"/></a>
	            </div>
            </f:form>
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
