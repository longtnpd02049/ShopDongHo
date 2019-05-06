<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../samecontent/head.jsp"%>
<style>
.saokmau {
	background: url(../images/nav_logo242.png) no-repeat -65px -230px;
	display: inline-block;
	height: 13px;
	overflow: hidden;
	position: relative;
	top: 1px;
	width: 15px;
}

.saovang {
	background: url(../images/nav_logo242.png) no-repeat -110px -230px;
	display: inline-block;
	height: 13px;
	overflow: hidden;
	position: relative;
	top: 1px;
	width: 15px;
}
</style>
<body>
	<div id="wrapper">
		<%@include file="../samecontent/menu.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Quản lý bình luận</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">

					<!-- /.panel-heading -->
					<div class="panel-body">
						<table width="100%"
							class="table table-striped table-bordered table-hover"
							id="dataTables-example" style="text-align: center;">
							<thead>
								<tr>
									<th>Id</th>
									<th>Sản phẩm</th>
									<th>Họ tên</th>
									<th>Giới tính</th>
									<th>Đánh giá</th>
									<th>Email</th>
									<th>Phone</th>
									<th>Nội dung bình luận</th>
									<th>Thời gian bình luận</th>
									<th>Thời gian duyệt</th>
									<th>Thời gian sửa</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ListBinhLuanSP}" var="item">
									<tr class="odd gradeX">
										<td>${item.idBinhLuan}</td>
										<td><a href="../details?idsp=${item.chitietsp.idChiTietSp}" target="_blank"><img src="${pageContext.servletContext.contextPath}/${item.chitietsp.image}" width="70" height="80" /></a></td>
										<td>${item.hoTen}</td>
										<td><c:choose>
												<c:when test="${item.gioiTinh==0}">Nam</c:when>
												<c:when test="${item.gioiTinh==1}">Nữ</c:when>
											</c:choose></td>

										<td><c:choose>
												<c:when test="${item.danhGia==1}">
													<div class="saovang" style="margin-left: 10px;"></div>
												</c:when>
												<c:when test="${item.danhGia==2}">
													<div class="saovang" style="margin-left: 10px;"></div>
													<div class="saovang"></div>
												</c:when>
												<c:when test="${item.danhGia==3}">
													<div class="saovang" style="margin-left: 10px;"></div>
													<div class="saovang"></div>
													<div class="saovang"></div>
												</c:when>
												<c:when test="${item.danhGia==4}">
													<div class="saovang" style="margin-left: 10px;"></div>
													<div class="saovang"></div>
													<div class="saovang"></div>
													<div class="saovang"></div>
												</c:when>
												<c:when test="${item.danhGia==5}">
													<div class="saovang" style="margin-left: 10px;"></div>
													<div class="saovang"></div>
													<div class="saovang"></div>
													<div class="saovang"></div>
													<div class="saovang"></div>
												</c:when>
											</c:choose></td>
										<td>${item.email}</td>
										<td class="center">0<fmt:formatNumber pattern="###,###,###">${item.phone}</fmt:formatNumber></td>
										<td class="center">${item.noiDungBinhLuan}</td>
										<td class="center"><fmt:formatDate value="${item.thoiGianBinhLuan}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
										<td class="center">
											<c:choose>
												<c:when test="${item.status=='ok'}">
													<fmt:formatDate value="${item.thoiGianXacNhan}" pattern="HH:mm:ss _ dd/MM/yyyy" />
												</c:when>
											</c:choose>
										</td>
										<td class="center"><fmt:formatDate value="${item.thoiGianSua}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
										<td>
											<c:choose>
												<c:when test="${item.status=='wait'}">
													<img src="../images/erroricon.png" width="24" height="24">
												</c:when>
												<c:when test="${item.status=='ok'}">
													<img src="../images/successicon.png" width="24" height="24">
												</c:when>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${item.status=='wait'}">
													<a href="duyetbinhluan?id=${item.idBinhLuan}">Duyệt</a> | <a
														href="suabinhluan?id=${item.idBinhLuan}">Sửa</a> | <a
														href="xoabinhluan?id=${item.idBinhLuan}"
														onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
												</c:when>
												<c:when test="${item.status=='ok'}">
													<a href="suabinhluan?id=${item.idBinhLuan}">Sửa</a> | <a
														href="xoabinhluan?id=${item.idBinhLuan}"
														onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
												</c:when>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /.table-responsive -->


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
