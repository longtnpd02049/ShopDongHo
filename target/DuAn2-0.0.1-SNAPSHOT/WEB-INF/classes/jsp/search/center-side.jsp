<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<section>
	<div class="center-side">
		<div class="text-timkiem">
			<a>Tìm kiếm sản phẩm</a>
		</div><br/><br/><br/><br/>
		<div class="thongtin-timkiem" align="center" style="font-size:17px;">
			Từ khóa tìm kiếm: <a style="color:red;">${keyword}</a><br>
			Mức giá: <a style="color:red;">${giaTimKiem}</a><br>
			Hiệu sản phẩm: <a style="color:red;">${hieuTimKiem}</a><br>
			Có <a style="color:red;">${soluongKQ}</a> kết quả tìm được
		</div>
		<br/>
		<c:forEach items="${thongtinsearch}" var="item">
				<div class="sanpham">
					<a href="details?idsp=${item.idChiTietSp}"> <img src="${item.image}"/>
					<div class="thongtinsp">
						<h3>${item.tenSp}</h3>
						<h4>Giá: <fmt:formatNumber pattern="###,###,###">${item.giaSp}</fmt:formatNumber> VNĐ</h4>
						<span class="textkm">Khuyến mãi trị giá đến <strong>500.000₫</strong></span>
						<p class="info">
							<span>Loại máy: ${item.loaiMay}</span><br>
							<span>Kiểu dáng: ${item.kieuDang}</span><br>
							<span>Mặt kính: ${item.matKinh}</span><br>
							<span>Vỏ: ${item.vo}</span><br>
							<span>Độ chịu nước: ${item.doChiuNuoc}</span>
						</p>
					</div>
					</a>
					<label class="new">Mới ra mắt</label>
				</div>
			</c:forEach>
	</div>
</section>