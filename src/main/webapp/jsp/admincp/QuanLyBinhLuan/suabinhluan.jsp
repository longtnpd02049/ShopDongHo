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
                    <h1 class="page-header">Sửa bình luận</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div align="center">
            	<img src="${pageContext.servletContext.contextPath}/${BinhLuanById.chitietsp.image}" width="100" height="150"/>
            </div>
            <f:form method="post" modelAttribute="editcomment">
            	<div class="form-group">
                     <input class="form-control" type="hidden" value="${BinhLuanById.idBinhLuan}" name="idBinhLuan" autocomplete="off"/>
                </div>
                <div class="form-group">
                     <input class="form-control" type="hidden" value="${BinhLuanById.chitietsp.idChiTietSp}" name="chitietsp.idChiTietSp" autocomplete="off"/>
                </div>
                <div class="form-group">
                     <label>Họ tên*</label><input class="form-control" value="${BinhLuanById.hoTen}" name="hoTen" autocomplete="off" required/>
                </div>
                <div class="form-group">
                     <label>Giới tính*</label>
                     <select class="form-control">
                     	<c:choose>
                     		<c:when test="${BinhLuanById.gioiTinh==0}">
                     			<option value="0" name="gioiTinh" selected="selected">Nam</option>
                        		<option value="1" name="gioiTinh">Nữ</option>
                     		</c:when>
                     		<c:when test="${BinhLuanById.gioiTinh==1}">
                     			<option value="0" name="gioiTinh">Nam</option>
                        		<option value="1" name="gioiTinh" selected="selected" >Nữ</option>
                     		</c:when>
                     	</c:choose>
                        
                     </select>
                </div>
                <div class="form-group">
                     <label>Đánh giá*</label>
                     <select class="form-control" name="danhGia">
                     	<c:choose>
                     		<c:when test="${BinhLuanById.danhGia==1}">
                     			<option value="1" selected="selected">1 Sao</option>
                         		<option value="2">2 Sao</option>
		                        <option value="3">3 Sao</option>
		                        <option value="4">4 Sao</option>
		                        <option value="5">5 Sao</option>
                     		</c:when>
                     		<c:when test="${BinhLuanById.danhGia==2}">
                     			<option value="1">1 Sao</option>
                         		<option value="2" selected="selected">2 Sao</option>
		                        <option value="3">3 Sao</option>
		                        <option value="4">4 Sao</option>
		                        <option value="5">5 Sao</option>
                     		</c:when>
                     		<c:when test="${BinhLuanById.danhGia==3}">
                     			<option value="1">1 Sao</option>
                         		<option value="2">2 Sao</option>
		                        <option value="3" selected="selected">3 Sao</option>
		                        <option value="4">4 Sao</option>
		                        <option value="5">5 Sao</option>
                     		</c:when>
                     		<c:when test="${BinhLuanById.danhGia==4}">
                     			<option value="1">1 Sao</option>
                         		<option value="2">2 Sao</option>
		                        <option value="3">3 Sao</option>
		                        <option value="4" selected="selected">4 Sao</option>
		                        <option value="5">5 Sao</option>
                     		</c:when>
                     		<c:when test="${BinhLuanById.danhGia==5}">
                     			<option value="1">1 Sao</option>
                         		<option value="2">2 Sao</option>
		                        <option value="3">3 Sao</option>
		                        <option value="4">4 Sao</option>
		                        <option value="5" selected="selected">5 Sao</option>
                     		</c:when>
                     	</c:choose>
                     </select>
                </div>
                <div class="form-group">
                     <label>Email</label><input class="form-control" name="email" value="${BinhLuanById.email}" autocomplete="off"/>
                </div>
                <div class="form-group">
                	 <c:set var = "phone" value = "${BinhLuanById.phone}" />
                	 <fmt:parseNumber var = "i" type="number" value="${phone}"></fmt:parseNumber>
                     <label>Phone*</label><input class="form-control" name="phone" value="0<c:out value = "${i}" />" autocomplete="off" required/>
                </div>
                <c:if test="${BinhLuanById.thoiGianXacNhan!=null}">
	                <div class="form-group">
	                      <input class="form-control" type="hidden" value="<fmt:formatDate value="${BinhLuanById.thoiGianXacNhan}" pattern="yyyy/MM/dd HH:mm:ss" />" name="thoiGianXacNhan" autocomplete="off"/>
	                </div>
                </c:if>
                
                <div class="form-group">
                     <label>Nội dung bình luận*</label><textarea class="form-control" rows="3" name="noiDungBinhLuan" required>${BinhLuanById.noiDungBinhLuan}</textarea>
                </div>
                <div class="form-group">
                	<input class="form-control" type="hidden" name="thoiGianBinhLuan" value="<fmt:formatDate value="${BinhLuanById.thoiGianBinhLuan}" pattern="yyyy/MM/dd HH:mm:ss" />" autocomplete="off"/>
                </div>
                <div class="form-group">
                     <label>Trạng thái*</label>
                     <div class="form-group">
	                     <select class="form-control" name="status">
	                     	<c:choose>
	                     		<c:when test="${BinhLuanById.status=='ok'}">
	                     			<option value="ok" selected="selected">Duyệt bình luận</option>
	                        		<option value="wait">Ẩn bình luận</option>
	                     		</c:when>
	                     		<c:when test="${BinhLuanById.status=='wait'}">
	                     			<option value="ok">Duyệt bình luận</option>
	                        		<option value="wait" selected="selected">Ẩn bình luận</option>
	                     		</c:when>
	                     	</c:choose>
	                        
	                     </select>
	                 </div>
	            </div>
	            <div class="form-group" align="center">
	            	<input type="submit" class="btn btn-default" value="Update"/>
	            	<a href="quanlybinhluan"><input type="button" class="btn btn-default" value="Quay lại"/></a>
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
