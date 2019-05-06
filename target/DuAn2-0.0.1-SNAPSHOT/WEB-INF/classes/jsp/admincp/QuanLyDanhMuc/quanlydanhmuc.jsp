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
					<h1 class="page-header">Quản lý Danh mục cha</h1>
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
									<th>Name</th>
									<th>Gổm bao nhiêu danh mục con</th>
									<th>Thời gian tạo</th>
									<th>Thời gian sửa</th>
									<th>SortId</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ListDanhMuc}" var="item">
									<tr class="odd gradeX">
										<td>${item.idDanhMucSp}</td>
										<td>${item.name}</td>
										<td>
											<c:if test="${item.danhmucsp2s.size()>0}">
												Có <b>${item.danhmucsp2s.size()}</b> danh mục con<br/>
												<c:forEach items="${item.danhmucsp2s}" var="item2">
													- ${item2.name}<br/>
												</c:forEach>
											</c:if>
										</td>
										<td><fmt:formatDate value="${item.thoiGianTao}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
										<td><fmt:formatDate value="${item.thoiGianSua}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
										<td>${item.sortId}</td>
										<td>
											<a href="suadanhmuc?id=${item.idDanhMucSp}">Sửa</a> | 
											<a href="xoadanhmuc?id=${item.idDanhMucSp}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>							
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /.table-responsive -->
						<div align="center">
							<a href="themdanhmuc"><input type="button" class="btn btn-default" value="Thêm danh mục cha"/></a>
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
