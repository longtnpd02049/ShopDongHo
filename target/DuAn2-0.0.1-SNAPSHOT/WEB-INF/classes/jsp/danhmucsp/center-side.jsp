<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<section>
	<div class="center-side">
		<div class="text-hangmoinhat">
			<a>Sản phẩm mới</a>
		</div>
		<br/><br/><br/>
		<div style="text-align:center;font-size:18px;">
			Danh mục sản phẩm: <a style="color:red">${DanhmucspById.name}</a>
			 <c:if test="${!empty DanhmucspById2.name}">
			 > 
			 <a style="color:red">${DanhmucspById2.name}</a>
			 </c:if>
		</div>
		<div class="hangmoinhat">
			<c:forEach items="${ListspByIdDanhMucsp}" var="item">
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
	</div>
</section>