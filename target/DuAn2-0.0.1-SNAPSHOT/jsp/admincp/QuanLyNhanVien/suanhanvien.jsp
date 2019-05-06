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
                    <h1 class="page-header">Sửa thông tin nhân viên ( ${UserById.username} )</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <f:form method="post" modelAttribute="edituser">
            	<div class="form-group">
                     <input class="form-control" type="hidden" name="idUsername" value="${UserById.idUsername}" placeholder="Nhập Id tài khoản"/>
                </div>
            	<div class="form-group">
                     <input class="form-control" type="hidden" name="username" value="${UserById.username}" placeholder="Nhập tên tài khoản"/>
                </div>
                <div class="form-group">
            		<label>Username</label>
                     <input class="form-control" value="${UserById.username}" disabled="disabled"/>
                </div>
                <div class="form-group">
            		<label>Họ tên</label>
                     <input class="form-control" name="hoTen" placeholder="Nhập họ và tên" value="${UserById.hoTen}"/>
                </div>
                <div class="form-group">
                	<label>Giới tính*</label>
	                <select class="form-control" name="gioiTinh">
	                	<c:choose>
	                		<c:when test="${UserById.gioiTinh==0}">
	                			<option value="0" selected="selected">Nam</option>
	                			<option value="1">Nữ</option>
	                		</c:when>
	                		<c:when test="${UserById.gioiTinh==1}">
	                			<option value="0">Nam</option>
	                			<option value="1" selected="selected">Nữ</option>
	                		</c:when>
	                	</c:choose>
	                </select>
                </div>
                <div class="form-group">
                	<label>Chức vụ*</label>
	                <select class="form-control" name="role">
	                	<c:choose>
	                		<c:when test="${UserById.role.equalsIgnoreCase('Administrator')}">
	                			<option value="Administrator" selected="selected">Administrator</option>
	                			<option value="Moderator">Moderator</option>
	                		</c:when>
	                		<c:when test="${UserById.role.equalsIgnoreCase('Moderator')}">
	                			<option value="Administrator">Administrator</option>
	                			<option value="Moderator" selected="selected">Moderator</option>
	                		</c:when>
	                	</c:choose>
	                </select>
                </div>
                <div class="form-group">
            		<label>Password*</label>
                     <input class="form-control" name="password" placeholder="Nhập mật khẩu cấp 1 mới"/>
                </div>
                <div class="form-group">
            		<label>Password 2*</label>
                     <input class="form-control" name="password2" placeholder="Nhập mật khẩu cấp 2 mới"/>
                </div>
                
                <div class="form-group">
                	<c:set var = "phone1" value = "${UserById.sdtUser1}" />
                	<c:set var = "phone2" value = "${UserById.sdtUser2}" />
                	<fmt:parseNumber var = "i1" type="number" value="${phone1}"></fmt:parseNumber>
                	<fmt:parseNumber var = "i2" type="number" value="${phone2}"></fmt:parseNumber>
            		<label>Số điện thoại 1*</label>
                     <input class="form-control" name="sdtUser1" placeholder="Nhập số điện thoại 1" value="0<c:out value = "${i1}" />"/>
                </div>
                <div class="form-group">
            		<label>Số điện thoại 2</label>
                     <input class="form-control" name="sdtUser2" placeholder="Nhập số điện thoại 2" value="0<c:out value = "${i2}" />"/>
                </div>
                <div class="form-group">
            		<label>Email</label>
                     <input class="form-control" name="email" placeholder="Nhập Email" value="${UserById.email}"/>
                </div>
                <div class="form-group">
                     <label>Địa chỉ</label><textarea class="form-control" rows="3" name="diaChi" placeholder="Nhập địa chỉ nhân viên">${UserById.diaChi}</textarea>
                </div>
                <div class="form-group">
                     <input class="form-control" type="hidden" name="thoiGianTao" placeholder="Nhập thời gian tạo user" value="<fmt:formatDate value="${UserById.thoiGianTao}" pattern="yyyy/MM/dd HH:mm:ss" />"/>
                </div>
                
                <div class="form-group">
                	<label>Trạng thái</label>
                     <select class="form-control" name="status">
	                	<c:choose>
	                		<c:when test="${UserById.status.equalsIgnoreCase('ok')}">
	                			<option value="ok" selected="selected">Hoạt động</option>
	                			<option value="blocked">Khóa tài khoản</option>
	                		</c:when>
	                		<c:when test="${UserById.status.equalsIgnoreCase('blocked')}">
	                			<option value="ok">Hoạt động</option>
	                			<option value="blocked" selected="selected">Khóa tài khoản</option>
	                		</c:when>
	                	</c:choose>
	                </select>
                </div>
                <div class="form-group">
                     <label>Mô tả thêm</label><textarea class="form-control" rows="3" name="moTaThem" placeholder="Nhập mô tả thêm nhân viên">${UserById.moTaThem}</textarea>
                </div>
	            <div class="form-group" align="center">
	            	<input type="submit" class="btn btn-default" value="Cập nhật"/>
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
