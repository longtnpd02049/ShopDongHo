<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
			<c:choose>
				<c:when test="${item.status=='ok'}">
					<label class="new">Mới ra mắt</label>
				</c:when>
				<c:when test="${item.status=='hethang'}">
					<label class="giareonline">Hết hàng</label>
				</c:when>
				<c:when test="${item.status=='giamgia'}">
						<label class="tragop0phantram">Đang giảm giá</label>
					</c:when>
				</c:choose>
			</div>
		</c:forEach>
	</div>
	
	<div class="page">
		<c:choose>
			<c:when test="${IdDanhMuc2==null}">
			<c:if test="${PageCount>1}">
				TRANG: 
				<c:forEach var = "i" begin = "1" end = "${PageCount}">
					<c:if test="${i==selectPage}">
		        		<a><button class="buttonpage disabled">${i}</button></a> 
		        	</c:if>
					<c:if test="${i!=selectPage}">
		        		<a href="danhmuc?id=${IdDanhMuc}&pageid=${i}"><button class="buttonpage">${i}</button></a>
		        	</c:if>
		     	</c:forEach>
		     	<br/><br/>${selectPage} trong tổng số ${PageCount}
			</c:if>
		</c:when>
		<c:otherwise>
			<c:if test="${PageCount>1}">
				TRANG: 
				<c:forEach var = "i" begin = "1" end = "${PageCount}">
					<c:if test="${i==selectPage}">
		        		<a><button class="buttonpage disabled">${i}</button></a> 
		        	</c:if>
					<c:if test="${i!=selectPage}">
		        		<a href="danhmuc2?id=${IdDanhMuc}&id2=${IdDanhMuc2}&pageid=${i}"><button class="buttonpage">${i}</button></a>
		        	</c:if>
		     	</c:forEach>
		     	<br/><br/>${selectPage} trong tổng số ${PageCount}
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>