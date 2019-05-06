<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-lg-12">
		<div style="text-align:center;">
			<div class="form-group">
           		<label>Tên khách hàng: </label> ${ThongTinDH.tenKhachHang}<br/>
           		<label>Email: </label> ${ThongTinDH.email}<br/>
           		<label>Số điện thoại 1: </label> 0<fmt:formatNumber pattern="###,###,###">${ThongTinDH.sdt1}</fmt:formatNumber><br/>
           		<c:if test="${ThongTinDH.sdt2!=null && ThongTinDH.sdt2!=0}">
           			<label>Số điện thoại 2: </label> 0<fmt:formatNumber pattern="###,###,###">${ThongTinDH.sdt2}</fmt:formatNumber><br/>
           		</c:if>
           		<label>Địa chỉ: </label> ${ThongTinDH.diaChi}<br/>
           		<label>Thời gian đặt hàng: </label> <fmt:formatDate value="${ThongTinDH.thoiGianDatHang}" pattern="HH:mm:ss _ dd/MM/yyyy" /><br/>
           		<c:choose>
           			<c:when test="${ThongTinDH.status.equalsIgnoreCase('wait')}">
           			
           			</c:when>
           			<c:when test="${ThongTinDH.status.equalsIgnoreCase('ok')}">
           				<label>Thời gian xác nhận: </label> <fmt:formatDate value="${ThongTinDH.thoiGianXacNhanDonHang}" pattern="HH:mm:ss _ dd/MM/yyyy" /><br/>
           			</c:when>
           			<c:when test="${ThongTinDH.status.equalsIgnoreCase('success')}">
           				<label>Thời gian xác nhận: </label> <fmt:formatDate value="${ThongTinDH.thoiGianXacNhanDonHang}" pattern="HH:mm:ss _ dd/MM/yyyy" /><br/>
           				<label>Thời gian tất: </label> <fmt:formatDate value="${ThongTinDH.thoiGianHoanTatDonHang}" pattern="HH:mm:ss _ dd/MM/yyyy" /><br/>
           			</c:when>
           		</c:choose>
           		<label>Ghi chú: </label> ${ThongTinDH.ghiChu}<br/>
           		<label>Tình trạng: </label> 
           		<c:choose>
           			<c:when test="${ThongTinDH.status.equalsIgnoreCase('wait')}">
          					Chưa xác nhận ( <img src="../images/erroricon.png" width="24" height="24"> )
          				</c:when>
           			<c:when test="${ThongTinDH.status.equalsIgnoreCase('ok')}">
           				Đã xác nhận ( <img src="../images/Semi-success-icon.png" width="24" height="24"> )
           			</c:when>
           			<c:when test="${ThongTinDH.status.equalsIgnoreCase('success')}">
           				Đã hoàn tất ( <img src="../images/successicon.png" width="24" height="24"> )
           			</c:when>
           			<c:when test="${ThongTinDH.status.equalsIgnoreCase('deleted')}">
           				Đã hủy đơn hàng
           			</c:when>
          			</c:choose>
           		<br/>
               </div>
           </div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="form-group">
				<label>Tổng số lượng hàng: <span style="color:blue;">${TongsoHang}</span></label>
			</div>
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="dataTables-example" style="text-align: center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Sản phẩm</th>
						<th>Giá tiền</th>
						<th>Số lượng</th>
						<th>Tổng Giá tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ListCTDH}" var="item">
						<tr class="odd gradeX">
							<td>${item.idChiTietDatHang}</td>
							<td>
								<a href="../details?idsp=${item.chitietsp.idChiTietSp}" target="_blank"><img src="${pageContext.servletContext.contextPath}/${item.chitietsp.image}" width="70" height="80" /></a><br/>
								${item.chitietsp.tenSp}<br/><br/>
							</td>
							<td><fmt:formatNumber pattern="###,###,###">${item.chitietsp.giaSp}</fmt:formatNumber> VNĐ</td>
							<td>${item.soLuong}</td>
							<td><fmt:formatNumber pattern="###,###,###">${item.soLuong*item.chitietsp.giaSp}</fmt:formatNumber> VNĐ</td>
						</tr>
						
					</c:forEach>
					<tr>
						<td colspan="5">Tổng tiền cần thanh toán: <fmt:formatNumber pattern="###,###,###">${ThongTinDH.tongTien}</fmt:formatNumber> VNĐ</td>
					</tr>
				</tbody>
			</table>
			<!-- /.table-responsive -->
			<div align="center">
				<a href="quanlydathang"><input type="button" class="btn btn-default" value="Quay lại"/></a>
			</div>
			<div style="text-align:center;padding-bottom:50px;">
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
	</div>
</div>