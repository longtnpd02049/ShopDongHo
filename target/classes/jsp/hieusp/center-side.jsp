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
			Hiệu sản phẩm: <a style="color:red">${hieuSpById.tenHieuSp}</a>
		</div>
		<div class="hangmoinhat">
			<c:forEach items="${ListspByIdHieuSp}" var="item">
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
			<c:if test="${PageCount>1}">
				<ul class="pagination justify-content-center">
					<c:if test="${selectPage!=1}">
						<li class="page-item"> <a class="page-link" id="1" style="cursor:pointer;">Trang đầu</a> </li>
			        	<li class="page-item"> <a class="page-link" id="${selectPage-1}" style="cursor:pointer;">Trang trước</a> </li>
					</c:if>
					<c:if test="${selectPage==1}">
						<li class="page-item disabled"> <a>Trang đầu</a> </li>
			        	<li class="page-item disabled"> <a>Trang trước</a> </li>
					</c:if>
			        
			        
			        <c:if test="${selectPage>4}">
			        	<c:if test="${PageCount-selectPage>=4}">
					        <c:forEach var = "i" begin = "${selectPage-2}" end = "${selectPage+2}">
					        	<c:if test="${i==selectPage}">
					        		<li class="page-item active"><a class="page-link" id="${i}">${i}</a></li>
					        	</c:if>
					        	<c:if test="${i!=selectPage}">
					        		<li class="page-item"><a class="page-link" id="${i}" style="cursor:pointer;">${i}</a></li>
					        	</c:if>
					        </c:forEach>
				        </c:if>
				        <c:if test="${PageCount-selectPage<4}">
				        	<c:forEach var = "i" begin = "${PageCount-4}" end = "${PageCount}">
					        	<c:if test="${i==selectPage}">
					        		<li class="page-item active"><a class="page-link" id="${i}">${i}</a></li>
					        	</c:if>
					        	<c:if test="${i!=selectPage}">
					        		<li class="page-item"><a class="page-link" id="${i}" style="cursor:pointer;">${i}</a></li>
					        	</c:if>
					        </c:forEach>
				        </c:if>
			        </c:if>
			        <c:if test="${selectPage<=4}">
			        	<c:if test="${PageCount>=5}">
				        	<c:forEach var = "i" begin = "1" end = "5">
					        	<c:if test="${i==selectPage}">
					        		<li class="page-item active"><a class="page-link" id="${i}">${i}</a></li>
					        	</c:if>
					        	<c:if test="${i!=selectPage}">
					        		<li class="page-item"><a class="page-link" id="${i}" style="cursor:pointer;">${i}</a></li>
					        	</c:if>
					        </c:forEach>
			        	</c:if>
			        	<c:if test="${PageCount<5}">
				        	<c:forEach var = "i" begin = "1" end = "${PageCount}">
					        	<c:if test="${i==selectPage}">
					        		<li class="page-item active"><a class="page-link" id="${i}">${i}</a></li>
					        	</c:if>
					        	<c:if test="${i!=selectPage}">
					        		<li class="page-item"><a class="page-link" id="${i}" style="cursor:pointer;">${i}</a></li>
					        	</c:if>
					        </c:forEach>
			        	</c:if>
			        </c:if>
			        
			        
			        <c:if test="${selectPage!=PageCount}">
			       	 	<li class="page-item"> <a class="page-link" id="${selectPage+1}" style="cursor:pointer;">Trang tiếp</a> </li>
			        	<li class="page-item"> <a class="page-link" id="${PageCount}" style="cursor:pointer;">Trang cuối</a> </li>
			        </c:if>
			        <c:if test="${selectPage==PageCount}">
			       	 	<li class="page-item disabled"> <a>Trang tiếp</a> </li>
			        	<li class="page-item disabled"> <a>Trang cuối</a> </li>
			        </c:if>
			    </ul>
			 </c:if>
			 <c:if test="${PageCount>1}">
			 	<br/>${selectPage} trong tổng số ${PageCount}<br/>
			 </c:if>
		</div>
	</div>
</section>