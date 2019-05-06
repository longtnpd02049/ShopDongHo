<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../samecontent/head.jsp" %>
<script src="../js/jquery-1.11.3.min.js"></script>
<script>
	$(document).ready(function(){
		$("#idDanhMucSp").change(function(){
			var idDanhMucSp = $("#idDanhMucSp").val();
			$.ajax({
				url:"danhmucsp2ajax",
				method:"POST",
				data:{id:idDanhMucSp},
				success:function(data){
					$("#idDanhMucSp2").html(data);
				}
			});
		});
		var idDanhMucSp = $("#idDanhMucSp").val();
		$.ajax({
			url:"danhmucsp2ajax",
			method:"POST",
			data:{id:idDanhMucSp},
			success:function(data){
				$("#idDanhMucSp2").html(data);
			}
		});
	});
</script>
<body>
    <div id="wrapper">
        <%@include file="../samecontent/menu.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Thêm sản phẩm</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <f:form method="post" enctype="multipart/form-data" modelAttribute="addsanpham">
             	<input class="form-control" type="hidden" name="users.idUsername" placeholder="Nhập IdUser" value="${IdUser}"/>
            	<div class="form-group">
            		<label>Tên sản phẩm*</label>
                    <input class="form-control" name="tenSp" placeholder="Nhập tên sản phẩm" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Hình ảnh sản phẩm*</label>
                    <input class="form-control" type="file" name="file" accept="image/*" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Danh mục sản phẩm 1*</label>
                    <select class="form-control" name="danhmucsp.idDanhMucSp" id="idDanhMucSp">
                    	<c:forEach items="${ListDanhMuc1}" var="item">
                    		<option value="${item.idDanhMucSp}">${item.name}</option>
                    	</c:forEach>
	                </select>
                </div>
                <div id="idDanhMucSp2">
                	<div class="form-group">
						<label>Danh mục sản phẩm 2*</label>
					    <select class="form-control" name="idDanhMuc2">
					   		<option value="">Không chọn</option>
						</select>
					</div>
                </div>
                <div class="form-group">
            		<label>Hiệu sản phẩm*</label>
                    <select class="form-control" name="hieusp.idHieuSp">
						<c:forEach items="${ListHieuSP}" var="item">
                    		<option value="${item.idHieuSp}">${item.tenHieuSp}</option>
                    	</c:forEach>
	                </select>
                </div>
                <div class="form-group">
            		<label>Giá*</label>
                    <input class="form-control" name="giaSp" placeholder="Nhập giá sản phẩm" autocomplete="off" required/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="hidden" name="soLuongDat" placeholder="Nhập số lượng đặt" value="1" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Số lượng trong kho*</label>
                    <input class="form-control" name="soLuongSp" placeholder="Nhập số lượng sản phẩm trong kho" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Đặc điểm nổi bật*</label>
                    <textarea class="form-control" rows="3" name="dacDiemNoiBat" placeholder="Nhập đặc điểm nổi bật" required></textarea>
                </div>
                <div class="form-group">
            		<label>Loại máy*</label>
                    <select class="form-control" name="loaiMay">
	                	<option value="Automatic (máy cơ)">Automatic (máy cơ)</option>
	                	<option value="Quartz (chạy pin)">Quartz (chạy pin)</option>
	                </select>
                </div>
                <div class="form-group">
            		<label>Kiểu dáng*</label>
                    <select class="form-control" name="kieuDang">
	                	<option value="Nam">Nam</option>
	                	<option value="Nữ">Nữ</option>
	                </select>
                </div>
                <div class="form-group">
            		<label>Mặt kính*</label>
                    <select class="form-control" name="matKinh">
	                	<option value="Hardlex">Hardlex</option>
	                	<option value="Sapphire chống xước">Sapphire chống xước</option>
	                </select>
                </div>
                <div class="form-group">
            		<label>Vỏ*</label>
                    <input class="form-control" name="vo" placeholder="Nhập vỏ" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Đường kính*</label>
                    <input class="form-control" name="duongKinh" placeholder="Nhập đường kính" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Độ dày*</label>
                    <input class="form-control" name="doDay" placeholder="Nhập độ dày" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Độ chịu nước*</label>
                    <input class="form-control" name="doChiuNuoc" placeholder="Nhập độ chịu nước" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Thời gian bảo hành*</label>
                    <input class="form-control" name="baoHanh" placeholder="Nhập thòi gian bảo hành" autocomplete="off" required/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="hidden" name="status" placeholder="Nhập status" value="ok" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Mô tả thêm</label>
                    <textarea class="form-control" rows="3" name="moTaThem" placeholder="Nhập mô tả thêm" required></textarea>
                </div>
                <div class="form-group" align="center">
					<input class="btn btn-default" type="submit" value="Thêm sản phẩm">
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
