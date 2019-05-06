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
                    <h1 class="page-header">Sửa Danh mục sản phẩm 1</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <f:form method="post" modelAttribute="editdanhmuc2">
            	<div class="form-group">
                     <input class="form-control" type="hidden" name="idDanhMucSp2" value="${DanhmucspByID2.idDanhMucSp2}" placeholder="Nhập Id danh mục 2"/>
                </div>
            	<div class="form-group">
            		<label>Tên danh mục*</label>
                     <input class="form-control" type="text" name=name value="${DanhmucspByID2.name}" placeholder="Nhập tên danh mục"/>
                </div>
                <div class="form-group">
                	<label>Tên danh mục cha*</label>
                	<select class="form-control" name="danhmucsp.idDanhMucSp">
                		<c:forEach items="${ListDanhMuc}" var="item">
                			<c:choose>
                				<c:when test="${item.idDanhMucSp==DanhmucspByID2.danhmucsp.idDanhMucSp}">
                					<option value="${item.idDanhMucSp}" selected="selected">${item.name}</option>
                				</c:when>
                				<c:otherwise>
                					<option value="${item.idDanhMucSp}">${item.name}</option>
                				</c:otherwise>
                			</c:choose>
                				
                		</c:forEach>
	                </select>
                </div>
                <div class="form-group">
                     <input class="form-control" type="hidden" name="thoiGianTao" placeholder="Nhập thời gian tạo danh mục" value="<fmt:formatDate value="${DanhmucspByID2.thoiGianTao}" pattern="yyyy/MM/dd HH:mm:ss" />"/>
                </div>
                <div class="form-group">
                	<label>Thứ tự danh mục*</label>
                    <input class="form-control" type="text" name="sortId" value="${DanhmucspByID2.sortId}" placeholder="Nhập sortID"/>
                </div>
	            <div class="form-group" align="center">
	            	<input type="submit" class="btn btn-default" value="Cập nhật"/>
	            	<a href="quanlydanhmuc2"><input type="button" class="btn btn-default" value="Quay lại"/></a>
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
