<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../samecontent/head.jsp"%>
<body>
	<div id="wrapper">
		<%@include file="../samecontent/menu.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Quản lý nhân viên</h1>
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
									<th>Username</th>
									<th>Họ tên</th>
									<th>Giới tính</th>
									<th>Email</th>
									<th>Số điện thoại 1</th>
									<th>Số điện thoại 2</th>
									<th>Địa chỉ</th>
									<th>Thời gian tạo</th>
									<th>Thời gian sửa lần cuối</th>
									<th>Chức vụ</th>
									<th>Status</th>
									<th>Mô tả thêm</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ListUsers}" var="item">
									<tr class="odd gradeX">
										<td>${item.idUsername}</td>
										<td>${item.username}</td>
										<td>${item.hoTen}</td>
										<td>
											<c:choose>
												<c:when test="${item.gioiTinh==0}">Nam</c:when>
												<c:when test="${item.gioiTinh==1}">Nữ</c:when>
											</c:choose>
										</td>
										<td>${item.email}</td>
										<td>0<fmt:formatNumber pattern="###,###,###">${item.sdtUser1}</fmt:formatNumber></td>
										<td>
											<c:choose>
												<c:when test="${item.sdtUser2==null}">
													
												</c:when>
												<c:when test="${item.sdtUser2==0}">
													
												</c:when>
												<c:otherwise>
													0<fmt:formatNumber pattern="###,###,###">${item.sdtUser2}</fmt:formatNumber>
												</c:otherwise>
											</c:choose>
										</td>
										<td>${item.diaChi}</td>
										<td>
											<fmt:formatDate value="${item.thoiGianTao}" pattern="HH:mm:ss _ dd/MM/yyyy" />
										</td>
										<td>
											<fmt:formatDate value="${item.thoiGianSua}" pattern="HH:mm:ss _ dd/MM/yyyy" />
										</td>
										<td>
											<c:choose>
												<c:when test="${item.role.equalsIgnoreCase('Administrator')}">
													Administrator
												</c:when>
												<c:when test="${item.role.equalsIgnoreCase('Moderator')}">
													Moderator
												</c:when>
											</c:choose>	
										</td>
										<td>
											<c:choose>
												<c:when test="${item.status.equalsIgnoreCase('ok')}">
													<img src="../images/successicon.png" width="24" height="24">
												</c:when>
												<c:when test="${item.status.equalsIgnoreCase('blocked')}">
													<img src="../images/erroricon.png" width="24" height="24">
												</c:when>
											</c:choose>
										</td>
										<td>${item.moTaThem}</td>
										<td>
											<c:choose>
												<c:when test="${item.status.equalsIgnoreCase('ok')}">
													<a href="blocknhanvien?id=${item.idUsername}">Khóa</a> | 
													<a href="suanhanvien?id=${item.idUsername}">Sửa</a> | 
													<a href="xoanhanvien?id=${item.idUsername}" onclick="return confirm('Bạn chắc chắn muốn xóa tài khoản ${item.username}?')">Xóa</a>
												</c:when>
												<c:when test="${item.status.equalsIgnoreCase('blocked')}">
													<a href="unblocknhanvien?id=${item.idUsername}">Mở khóa</a> | 
													<a href="suanhanvien?id=${item.idUsername}">Sửa</a> | 
													<a href="xoanhanvien?id=${item.idUsername}" onclick="return confirm('Bạn chắc chắn muốn xóa tài khoản ${item.username}?')">Xóa</a>
												</c:when>
											</c:choose>
											
										</td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /.table-responsive -->
						<div align="center">
							<a href="themnhanvien"><input type="button" class="btn btn-default" value="Thêm nhân viên"/></a>
							<a href="lichsuxoanhanvien"><input type="button" class="btn btn-default" value="Xem lịch sử các nhân viên bị xóa"/></a>
						</div>
						<ul class="pagination justify-content-center">
					        <li class="page-item"> <a class="page-link" href="#">Trang trước</a> </li>
					        <li class="page-item"><a class="page-link" href="#">1</a></li>
					        <li class="page-item"><a class="page-link" href="#">2</a></li>
					        <li class="page-item"><a class="page-link" href="#">3</a></li>
					
					        <li class="page-item active">
					            <a class="page-link" href="#">4</a>
					        </li>
					
					        <li class="page-item"><a class="page-link" href="#">5</a></li>
					        <li class="page-item disabled">
					            <a class="page-link" href="#">
					                <span aria-hidden="true">»</span>
					            </a>
					        </li>
					    </ul>
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
