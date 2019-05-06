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
                    <h1 class="page-header">Thêm gallery sản phẩm</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div align="center" style="font-size:18px;">
            	<img src="${pageContext.servletContext.contextPath}/${chitietsp.image}" width="100" height="150"/><br/><br/>
            	<p>${chitietsp.tenSp}</p><br/><br/>
            </div>
            <f:form method="post" enctype="multipart/form-data" modelAttribute="addgallery">
            	<div class="form-group" align="center">
            		<input class="form-control" type="hidden" name="chitietsp.idChiTietSp" value="${chitietsp.idChiTietSp}"/>
				</div>
                <div class="form-group" align="center">
					<label for="image">Chọn hình ảnh: </label>
                    <input class="form-control" type="file" name="files" multiple="multiple" accept="image/*"/>
				</div>
                <div class="form-group" align="center">
					<input class="btn btn-default" type="submit" value="Thêm gallery">
					<a href="quanlygallery?id=${chitietsp.idChiTietSp}"><input type="button" class="btn btn-default" value="Quay lại"/></a>
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
