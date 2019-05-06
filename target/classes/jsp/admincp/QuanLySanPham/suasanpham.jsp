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
                    <h1 class="page-header">Sửa sản phẩm</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div align="center">
            	<img src="${pageContext.servletContext.contextPath}/${chitietsp.image}" width="100" height="150"/>
            </div>
            <f:form method="post" enctype="multipart/form-data" modelAttribute="editsanpham">
             	<input class="form-control" type="hidden" name="users.idUsername" placeholder="Nhập IdUser" value="${chitietsp.users.idUsername}"/>
             	<input class="form-control" type="hidden" name="idChiTietSp" placeholder="Nhập Id chi tiết sp" value="${chitietsp.idChiTietSp}"/>
            	<div class="form-group">
            		<label>Tên sản phẩm*</label>
                    <input class="form-control" name="tenSp" placeholder="Nhập tên sản phẩm" value="${chitietsp.tenSp}" autocomplete="off" required/>
                </div>
                <input class="form-control" type="hidden" name="image" placeholder="Nhập đường dẫn hình ảnh" value="${chitietsp.image}" autocomplete="off"/>
                <div class="form-group">
            		<label>Hình ảnh sản phẩm*</label>
                    <input class="form-control" type="file" name="file" accept="image/*" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Danh mục sản phẩm 1*</label>
                    <select class="form-control" name="danhmucsp.idDanhMucSp">
                    	<c:forEach items="${ListDanhMuc1}" var="item">
                    		<c:choose>
	                    		<c:when test="${chitietsp.danhmucsp.idDanhMucSp==item.idDanhMucSp}">
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
            		<label>Danh mục sản phẩm 2*</label>
                   	<select class="form-control" name="idDanhMuc2">
                   		<option value="" selected="selected">Không chọn</option>
	                	<c:forEach items="${ListDanhMuc2}" var="item">
	                		<c:choose>
	                    		<c:when test="${chitietsp.idDanhMuc2==item.idDanhMucSp2}">
	                    			<option value="${item.idDanhMucSp2}" selected="selected">${item.name}</option>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<option value="${item.idDanhMucSp2}">${item.name}</option>
	                    		</c:otherwise>
                    		</c:choose>
                    	</c:forEach>
	                </select>
                </div>
                <div class="form-group">
            		<label>Hiệu sản phẩm*</label>
                    <select class="form-control" name="hieusp.idHieuSp">
						<c:forEach items="${ListHieuSP}" var="item">
							<c:choose>
	                    		<c:when test="${chitietsp.hieusp.idHieuSp==item.idHieuSp}">
	                    			<option value="${item.idHieuSp}" selected="selected">${item.tenHieuSp}</option>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<option value="${item.idHieuSp}">${item.tenHieuSp}</option>
	                    		</c:otherwise>
                    		</c:choose>
                    	</c:forEach>
	                </select>
                </div>
                <div class="form-group">
                	<c:set var = "giaSp" value = "${chitietsp.giaSp}" />
	               	<fmt:parseNumber var = "i" type="number" value="${giaSp}"></fmt:parseNumber>
            		<label>Giá*</label>
                    <input class="form-control" name="giaSp" placeholder="Nhập giá sản phẩm" value="<c:out value = "${i}" />" type="number" autocomplete="off" required/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="hidden" name="soLuongDat" placeholder="Nhập số lượng đặt" value="1" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Số lượng trong kho*</label>
                    <input class="form-control" name="soLuongSp" placeholder="Nhập số lượng sản phẩm trong kho" value="${chitietsp.soLuongSp}" type="number" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Đặc điểm nổi bật*</label>
                    <textarea class="form-control" rows="3" name="dacDiemNoiBat" placeholder="Nhập đặc điểm nổi bật" required>${chitietsp.dacDiemNoiBat}</textarea>
                </div>
                <div class="form-group">
            		<label>Loại máy*</label>
                    <select class="form-control" name="loaiMay">
                    	<c:choose>
                    		<c:when test="${chitietsp.loaiMay=='Automatic (máy cơ)'}">
                    			<option value="Automatic (máy cơ)" selected="selected">Automatic (máy cơ)</option>
	                			<option value="Quartz (chạy pin)">Quartz (chạy pin)</option>
                    		</c:when>
                    		<c:when test="${chitietsp.loaiMay=='Quartz (chạy pin)'}">
                    			<option value="Automatic (máy cơ)">Automatic (máy cơ)</option>
	                			<option value="Quartz (chạy pin)" selected="selected">Quartz (chạy pin)</option>
                    		</c:when>
                    	</c:choose>
	                </select>
                </div>
                <div class="form-group">
            		<label>Kiểu dáng*</label>
                    <select class="form-control" name="kieuDang">
                    	<c:choose>
                    		<c:when test="${chitietsp.kieuDang=='Nam'}">
                    			<option value="Nam" selected="selected">Nam</option>
	                			<option value="Nữ">Nữ</option>
                    		</c:when>
                    		<c:when test="${chitietsp.kieuDang=='Nữ'}">
                    			<option value="Nam">Nam</option>
	                			<option value="Nữ" selected="selected">Nữ</option>
                    		</c:when>
                    	</c:choose>
	                </select>
                </div>
                <div class="form-group">
            		<label>Mặt kính*</label>
                    <select class="form-control" name="matKinh">
                    	<c:choose>
                    		<c:when test="${chitietsp.matKinh=='Hardlex'}">
                    			<option value="Hardlex" selected="selected">Hardlex</option>
	                			<option value="Sapphire chống xước">Sapphire chống xước</option>
                    		</c:when>
                    		<c:when test="${chitietsp.matKinh=='Sapphire chống xước'}">
                    			<option value="Hardlex">Hardlex</option>
	                			<option value="Sapphire chống xước" selected="selected">Sapphire chống xước</option>
                    		</c:when>
                    	</c:choose>
	                	
	                </select>
                </div>
                <div class="form-group">
            		<label>Vỏ*</label>
                    <input class="form-control" name="vo" placeholder="Nhập vỏ" value="${chitietsp.vo}" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Đường kính*</label>
                    <input class="form-control" name="duongKinh" placeholder="Nhập đường kính" value="${chitietsp.duongKinh}" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Độ dày*</label>
                    <input class="form-control" name="doDay" placeholder="Nhập độ dày" value="${chitietsp.doDay }" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Độ chịu nước*</label>
                    <input class="form-control" name="doChiuNuoc" placeholder="Nhập độ chịu nước" value="${chitietsp.doChiuNuoc }" autocomplete="off" required/>
                </div>
                <div class="form-group">
            		<label>Thời gian bảo hành*</label>
                    <input class="form-control" name="baoHanh" placeholder="Nhập thòi gian bảo hành" value="${chitietsp.baoHanh}" autocomplete="off" required/>
                </div>
                <div class="form-group">
                	<label>Tình trạng sản phẩm*</label>
                    <select class="form-control" name="status">
                    	<c:choose>
                    		<c:when test="${chitietsp.status=='ok'}">
                    			<option value="ok" selected="selected">Còn hàng</option>
	                			<option value="hethang">Hết hàng</option>
	                			<option value="giamgia">Giảm giá</option>
                    		</c:when>
                    		<c:when test="${chitietsp.status=='hethang'}">
	                			<option value="ok">Còn hàng</option>
	                			<option value="hethang" selected="selected">Hết hàng</option>
	                			<option value="giamgia">Giảm giá</option>
                    		</c:when>
                    		<c:when test="${chitietsp.status=='giamgia'}">
	                			<option value="ok">Còn hàng</option>
	                			<option value="hethang">Hết hàng</option>
	                			<option value="giamgia" selected="selected">Giảm giá</option>
                    		</c:when>
                    	</c:choose>
	                </select>
                </div>
                <div class="form-group">
            		<label>Mô tả thêm</label>
                    <textarea class="form-control" rows="3" name="moTaThem" placeholder="Nhập mô tả thêm">${chitietsp.moTaThem }</textarea>
                </div>
                <c:if test="${chitietsp.thoiGianTaoSp!=null}">
	                <div class="form-group">
	                      <input class="form-control" type="hidden" value="<fmt:formatDate value="${chitietsp.thoiGianTaoSp}" pattern="yyyy/MM/dd HH:mm:ss" />" name="thoiGianTaoSp" autocomplete="off" required/>
	                </div>
                </c:if>
                <div class="form-group" align="center">
					<input class="btn btn-default" type="submit" value="Sửa">
					<a href="quanlysanpham"><input type="button" class="btn btn-default" value="Quay lại"/></a>
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
