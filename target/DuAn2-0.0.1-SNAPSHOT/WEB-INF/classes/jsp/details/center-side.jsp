<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<section>
	<div class="center-side">
		<div class="text-chitietsanpham">
			<a>Chi tiết sản phẩm</a>
		</div>
		<div class="chitietsanpham">
			<div class="hinhanh">
				<img class="mySlides" src="${chitietsp.image}" />
				<c:forEach items="${ListgalleryByID}" var="item">
					<img class="mySlides" src="${item}" />
				</c:forEach>
					<div class="slide">
						<img class="slide-small" src="${chitietsp.image}" width="90" height="90" onmousemove="currentDiv(1)">
					</div>
				<%int i=1;%>
				<c:forEach items="${ListgalleryByID}" var="item">
					<%
						i++;
					%>
					<div class="slide">
						<img class="slide-small" src="${item}" width="90" height="90" onmousemove="currentDiv(<%=i%>)">
					</div>
				</c:forEach>

			</div>
			<script>
				var slideIndex = 1;
				showDivs(slideIndex);

				function plusDivs(n) {
					showDivs(slideIndex += n);
				}
				function currentDiv(n) {
					showDivs(slideIndex = n);
				}

				function showDivs(n) {
					var i;
					var x = document.getElementsByClassName("mySlides");
					var dots = document.getElementsByClassName("slide-small");
					if (n > x.length) {
						slideIndex = 1
					}
					if (n < 1) {
						slideIndex = x.length
					}
					for (i = 0; i < x.length; i++) {
						x[i].style.display = "none";
					}
					for (i = 0; i < dots.length; i++) {
						dots[i].className = dots[i].className.replace(
								"slide-small", "");

					}
					x[slideIndex - 1].style.display = "block";

					dots[slideIndex - 1].className += "slide-small";

				}
			</script>
			<div class="thongtin">
				<div class="tensanpham">
					<a>${chitietsp.tenSp}</a>
				</div>
				<div class="giasanpham">
					<strong><fmt:formatNumber pattern="###,###,###">${chitietsp.giaSp}</fmt:formatNumber> VNĐ</strong>
				</div>
				<div class="phuongthuc">
					<ul>
						<li class="giaohang">
							<div title="Giao hàng toàn quốc - Giao hàng miễn phí">
								Giao Hàng<br />Toàn Quốc
							</div>
						</li>
						<li class="thanhtoan">
							<div title="Phương thức thanh toán">
								Thanh Toán<br />Khi Nhận Hàng
							</div>
						</li>
						<li class="doihang">
							<div title="Đổi hàng">
								Đổi hàng<br />trong 7 ngày
							</div>
						</li>
					</ul>
				</div>
				<div class="thongtinphone">
					<a>Điện thoại đặt hàng</a><br /> <b>0125.908.6643</b>
				</div>
				<div class="onlinepromo">
					<b>Mua online tặng thêm quà</b>
					<div class="infopr">
						<span>Giảm ngay 1 triệu khi mua Online tại Website</span>
					</div>
				</div>
				<div class="thread-dich-vu">
					<ul>
						<li>Bảo hành từ <span>6-12</span> tháng <a href="#">Xem
								chi tiết bảo hành</a>
						</li>
					</ul>
				</div>
				<div class="area_order">
					<a href="addcart?idsp=${idsp}" class="buy_now"><b>Mua ngay </b><span>Giao
							trong 1 giờ hoặc nhận tại siêu thị</span></a> <a class="buy_repay " href="#"><b>Mua
							trả góp 0%</b><span>Thủ tục đơn giản</span></a> <a class="buy_repay s "
						href="#"><b>Trả góp qua thẻ</b><span>Visa, Master, JCB</span></a>

				</div>
			</div>
			<div class="thongtinchitiet2">
				<div class="thongtinchitiet">
					<div class="thongtinchitiet-left">
						<span>Đặc điểm nổi bật của ${chitietsp.tenSp}</span><br />
						<a>${chitietsp.dacDiemNoiBat}</a></br>
						<script>
							$(document).ready(function() {
								$(".form-close").click(function() {
									$(".container").css({"background-color" : "black","opacity" : "1","filter" : "alpha(opacity=1)"});
									$("footer").css({"background-color" : "black","opacity" : "1","filter" : "alpha(opacity=1)"});
									$("#end-side").css({"background-color" : "black","opacity" : "1","filter" : "alpha(opacity=1)"});
									$(".thongtinnguoidungbinhluan").hide();});
									$("#btnSendCmt").click(function() {
									if ($('#noidungbinhluan').val().length == 0) {
										$("#errorbinhluan").text("Vui lòng nhập nội dung bình luận");
										$("#errorbinhluan").css({"display" : "block"});
										$(".container").css({"background-color" : "black","opacity" : "1","filter" : "alpha(opacity=1)"});
									} else if (
										$('#noidungbinhluan').val().length > 0 && $('#noidungbinhluan').val().length < 15) {
										$("#errorbinhluan").text("Nội dung bình luận quá ngắn");
										$("#errorbinhluan").css({"display" : "block"});
										$(".container").css({"background-color" : "black","opacity" : "1","filter" : "alpha(opacity=1)"});
									} else if ($('#noidungbinhluan').val().length > 255) {
										$("#errorbinhluan").text("Nội dung bình luận quá dài");
										$("#errorbinhluan").css({"display" : "block"});
										$(".container").css({"background-color" : "black","opacity" : "1","filter" : "alpha(opacity=1)"});
									} else if ($('#noidungbinhluan').val().length >= 15 && $('#noidungbinhluan').val().length <= 255) {
										$(".thongtinnguoidungbinhluan").show();
										$("#errorbinhluan").css({"display" : "none"});
										$(".container").css({"background-color" : "black","opacity" : "0.11","filter" : "alpha(opacity=110)"});
										$("footer").css({"background-color" : "black","opacity" : "0.11","filter" : "alpha(opacity=110)"});
										$("#end-side").css({"background-color" : "black","opacity" : "0.11","filter" : "alpha(opacity=110)"});
									} else {
										$("#errorbinhluan").text("Vui lòng nhập nội dung bình luận");
										$("#errorbinhluan").css({"display" : "block"});
										$(".container").css({"background-color" : "black","opacity" : "1","filter" : "alpha(opacity=1)"});
									}

								});
								$("#noidungbinhluan").click(function() {
									$("#btnSendCmt").show();
								});
								$("#noidungbinhluan").keyup(function() {
									var value = $(this).val();
									$("#noiDungBinhLuan").text(value);
								}).keyup();
							});
						</script>
						<div class="binhluan">
							<div class="cmtbinhluan">
								<span>Bình luận</span><br />
								<div class="noidungbinhluan">
									<textarea placeholder="Mời bạn để lại bình luận..." id="noidungbinhluan" style="overflow-y: visible;"></textarea>
									<br>
									<div id="errorbinhluan"
										style="display: none; padding-top: 10px; color: red; float: left;"></div>
									<a class="btnSend" id="btnSendCmt">Gửi</a>
								</div>
							</div>
							<div class="showbinhluan">
								<span>Xem các bình luận</span>
								<div class="showthongtinbinhluan">
									<c:if test="${!empty ListbinhluanByID}">
									   <c:forEach items="${ListbinhluanByID}" var="bl">
											<c:choose>
												<c:when test="${bl.status=='ok'}">
													<div class="avatar">
														<a>${bl.hoTen.charAt(0)}</a> 
													</div>
													<div class="ten">${bl.hoTen}
														<c:choose>
															<c:when test="${bl.danhGia==1}">
																<div class="saovang" style="margin-left:10px;"></div>
															</c:when>
															<c:when test="${bl.danhGia==2}">
																<div class="saovang" style="margin-left:10px;"></div>
																<div class="saovang"></div>
															</c:when>
															<c:when test="${bl.danhGia==3}">
																<div class="saovang" style="margin-left:10px;"></div>
																<div class="saovang"></div>
																<div class="saovang"></div>
															</c:when>
															<c:when test="${bl.danhGia==4}">
																<div class="saovang" style="margin-left:10px;"></div>
																<div class="saovang"></div>
																<div class="saovang"></div>
																<div class="saovang"></div>
															</c:when>
															<c:when test="${bl.danhGia==5}">
																<div class="saovang" style="margin-left:10px;"> </div>
																<div class="saovang"></div>
																<div class="saovang"></div>
																<div class="saovang"></div>
																<div class="saovang"></div>
															</c:when>
														</c:choose>
													</div>
													<div class="showcmt">${bl.noiDungBinhLuan}</div>
													<div class="actionuser">
														<a class="cmttraloi">Trả lời</a> <a class="timecomment"><fmt:formatDate value="${bl.thoiGianBinhLuan}" pattern="HH:mm:ss _ dd/MM/yyyy" /></a>
													</div><br/>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:if>
									<c:if test="${empty ListbinhluanByID}">
										<div class="showcmt">Không có bình luận nào</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="thongtinchitiet-right">
						<span>Thông số kĩ thuật</span><br>
						<ul class="thongsokithuat">
							<li><span>Thương hiệu:</span> <a>${chitietsp.hieusp.tenHieuSp}</a></li>
							<li><span>Loại máy:</span> <a>${chitietsp.loaiMay }</a></li>
							<li><span>Kiểu dáng:</span> <a>${chitietsp.kieuDang }</a></li>
							<li><span>Mặt kính:</span> <a>${chitietsp.matKinh }</a></li>
							<li><span>Vỏ:</span> <a>${chitietsp.vo }</a></li>
							<li><span>Đường kính:</span> <a>${chitietsp.duongKinh }</a></li>
							<li><span>Độ dày:</span> <a>${chitietsp.doDay }</a></li>
							<li><span>Độ chịu nước:</span> <a>${chitietsp.doChiuNuoc }</a></li>
							<li><span>Bảo hành:</span> <a>${chitietsp.baoHanh }</a></li>
						</ul>
					</div>
				</div>

			</div>
		</div>
	</div>
</section>