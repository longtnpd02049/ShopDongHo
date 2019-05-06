<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function funcSearchTheoGia(){
	var getGia = document.getElementById("gia").value;
	if(getGia=="0-0"){
		document.getElementById("gia-min").value="0";
		document.getElementById("gia-max").value="0";
	}else if(getGia=="0-5"){
		document.getElementById("gia-min").value="0";
		document.getElementById("gia-max").value="5";
	}else if(getGia=="5-10"){
		document.getElementById("gia-min").value="5";
		document.getElementById("gia-max").value="10";
	}else if(getGia=="10-20"){
		document.getElementById("gia-min").value="10";
		document.getElementById("gia-max").value="20";
	}else if(getGia=="20-30"){
		document.getElementById("gia-min").value="20";
		document.getElementById("gia-max").value="30";
	}else if(getGia=="30-40"){
		document.getElementById("gia-min").value="30";
		document.getElementById("gia-max").value="40";
	}else if(getGia=="40-50"){
		document.getElementById("gia-min").value="40";
		document.getElementById("gia-max").value="50";
	}else if(getGia=="50-100"){
		document.getElementById("gia-min").value="50";
		document.getElementById("gia-max").value="100";
	}else if(getGia=="100-0"){
		document.getElementById("gia-min").value="100";
		document.getElementById("gia-max").value="0";
	}
}
function funcSearchTheoGia2(){
	var getHieuSp = document.getElementById("hieusp").value;
	document.getElementById("idhieusp").value=getHieuSp;
}
function funcSearchSapxep(){
	var getSapxeptheo = document.getElementById("sapxeptheo").value;
	document.getElementById("idsapxeptheo").value=getSapxeptheo;
}

$(document).ready(function(){
	var a = document.getElementById("giaTimKiem").value;
	var b = document.getElementById("hieuTimKiem").value;
	var c = document.getElementById("sapxepTheo").value;
	if(a != "" && b != "" && c != ""){
		var giamin = new URLSearchParams(window.location.search).get("gia-min");
		var giamax = new URLSearchParams(window.location.search).get("gia-max");
		var idhieusp = new URLSearchParams(window.location.search).get("idhieusp");
		var idsapxeptheo = new URLSearchParams(window.location.search).get("idsapxeptheo");
		document.getElementById("gia").value = a;
		document.getElementById("hieusp").value = b;
		document.getElementById("sapxeptheo").value = c;
		
		document.getElementById("gia-min").value = giamin;
		document.getElementById("gia-max").value = giamax;
		document.getElementById("idhieusp").value = idhieusp;
		document.getElementById("idsapxeptheo").value = idsapxeptheo;
	}
});
</script>
<nav>
	<div class="menu-ngang">
		<ul>
			<li class="selected"><a href="home"><span>Trang Chủ</span></a></li>
			<li><a href="#"><span>Giới thiệu</span></a></li>
			<li><a href="#"><span>Tin tức</span></a></li>
			<li><a href="#"><span>Hướng dẫn</span></a></li>
			<li><a href="shopcart"><span>Thanh toán</span></a></li>
			<li><a href="#"><span>Liên hệ-Hỗ trợ</span></a></li>
		</ul>
		<select name="gia" id="gia" onchange="funcSearchTheoGia()" style="width:145px;height:35px">
			<option value="0-0">Tất cả mệnh giá</option>
			<option value="0-5">Giá dưới 500k</option>
			<option value="5-10">Giá từ 500k - 1 triệu</option>
			<option value="10-20">Giá từ 1 - 2 triệu</option>
			<option value="20-30">Giá từ 2 - 3 triệu</option>
			<option value="30-40">Giá từ 3 - 4 triệu</option>
			<option value="40-50">Giá từ 4 - 5 triệu</option>
			<option value="50-100">Giá từ 5 - 10 triệu</option>
			<option value="100-0">Giá trên 10 triệu</option>
		</select>
		<select name="hieusp" id="hieusp" onchange="funcSearchTheoGia2()" style="width:180px;height:35px;">
			<option value="0">Tất cả các hiệu sản phẩm</option>
			<c:forEach items="${hieuspList}" var="item">
				<option value="${item.idHieuSp}">${item.tenHieuSp}</option>
			</c:forEach>
		</select>
		<select name="sapxeptheo" id="sapxeptheo" onchange="funcSearchSapxep()" style="width:190px;height:35px;">
			<option value="0">Sắp xếp theo mới nhất</option>
			<option value="1">Sắp xếp theo cũ nhất</option>
			<option value="2">Sắp xếp theo giá thấp - cao</option>
			<option value="3">Sắp xếp theo giá cao - thấp</option>
		</select>
		
		<input type="hidden" name="giaTimKiem" id="giaTimKiem" value="${giamin}-${giamax}">
		<input type="hidden" name="hieuTimKiem" id="hieuTimKiem" value="${idhieusp}">
		<input type="hidden" name="sapxepTheo" id="sapxepTheo" value="${idsapxepTheo}">
		
		<div class="rmenu-ngang">
			<form id="search-site" action="search" method="get">
				<input type="hidden" name="gia-min" id="gia-min" value="0">
				<input type="hidden" name="gia-max" id="gia-max" value="0">
				<input type="hidden" name="idhieusp" id="idhieusp" value="0">
				<input type="hidden" name="idsapxeptheo" id="idsapxeptheo" value="0">
				<input class="topinput" id="search-keyword" name="key" type="text" placeholder="Bạn tìm gì..." autocomplete="off" maxlength="50">
				<button class="btntop" type="submit">
					<i class="icontgdd-topsearch"></i>
				</button>
			</form>
		</div>
	</div>
</nav>
<br />
<br />
<br />
<br />