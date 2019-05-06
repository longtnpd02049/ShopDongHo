<%@page import="com.xTNL.DaoImpl.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section>
	<div class="center-side">
		<h2 class="text-center" style="color:green;">Tiến hành thanh toán</h2>
		<div align="center" style="width:800px;margin-left:150px;padding-top:50px;">
			<f:form action="thanhtoan" modelAttribute="thanhtoan" method="POST">
				<div class="form-group">
					<label>Họ tên*</label>
					<input class="form-control" name="tenKhachHang" placeholder="Vui lòng nhập họ tên" required="required" autocomplete="off"/><br/>
				</div>
				<div class="form-group">
					<label>Email</label>
					<input class="form-control" name="email" placeholder="Vui lòng nhập email" autocomplete="off"/><br/>	
				<div class="form-group">
					<label>Số điện thoại 1*</label>
					<input class="form-control" name="sdt1" placeholder="Vui lòng nhập số điện thoại 1" required="required" autocomplete="off"/><br/>
				</div>
				<div class="form-group">
					<label>Số điện thoại 2</label>
					<input class="form-control" name="sdt2" placeholder="Vui lòng nhập số điện thoại 2" autocomplete="off"/><br/>
				</div>
				<div class="form-group">
					<label>Địa chỉ*</label>
				 	<textarea class="form-control" rows="3" name="diaChi" placeholder="Nhập địa chỉ" required="required"></textarea><br/>
				 </div>
				 <div class="form-group">
				 	<label>Ghi chú</label>
				 	<textarea class="form-control" rows="3" name="ghiChu" placeholder="Nhập ghi chú khi giao hàng"></textarea><br/>
				 </div>
				 <div class="form-group">
				 	<input class="btn btn-default" type="submit" value="Xác nhận mua hàng"/>
				 </div>
			</f:form>
		</div>
	</div>
</section>