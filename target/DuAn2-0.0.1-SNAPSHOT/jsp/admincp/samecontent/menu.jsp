<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index">SmartWatchAdmin</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user" style="text-align: center;">
				<li><a href="#"><i class="fa fa-user fa-fw"></i> User
						Profile</a></li>
				<li class="divider"></li>
				<li>Username: ${username}</li>
				<li>Chức vụ: ${role}</li>
				<li class="divider"></li>
				<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
						Logout</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li><a href="../home" target="_blank"><i></i>Trang Chủ WebShop</a></li>
				<li><a href="index.html"><i class="fa fa-dashboard fa-fw"></i>Thống kê</a></li>
				<c:if test="${role.equalsIgnoreCase('Administrator')}">
					<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>Quản lý danh mục sản phẩm<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="quanlydanhmuc">Danh mục sản phẩm 1</a></li>
							<li><a href="quanlydanhmuc2">Danh mục sẩn phẩm 2</a></li>
						</ul>
					</li>
				</c:if>
				<li><a href="quanlysanpham"><i class="fa fa-edit fa-fw"></i>Quản lý sản phẩm</a></li>
				<c:if test="${role.equalsIgnoreCase('Administrator')}">
					<li><a href="quanlyhieusanpham"><i class="fa fa-edit fa-fw"></i>Quản lý hiệu sản phẩm</a></li>
				</c:if>
				<li><a href="quanlybinhluan"><i class="fa fa-edit fa-fw"></i>Quản lý bình luận</a></li>
				<li><a href="forms.html"><i class="fa fa-edit fa-fw"></i>Quản lý đơn hàng</a></li>
				<c:if test="${role.equalsIgnoreCase('Administrator')}">
					<li><a href="quanlynhanvien"><i class="fa fa-edit fa-fw"></i>Quản lý Nhân viên</a></li>
				</c:if>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>