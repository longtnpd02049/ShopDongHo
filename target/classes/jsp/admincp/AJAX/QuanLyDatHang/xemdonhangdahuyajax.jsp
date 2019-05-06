<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-lg-12">
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="form-group">
				<label>Tổng đơn hàng: <span style="color:blue;">${TongsoDonDatHang}</span></label>
			</div>
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="dataTables-example" style="text-align: center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Tên khách hàng</th>
						<th>Email</th>
						<th>Sdt1</th>
						<th>Sdt2</th>
						<th>Địa chỉ</th>
						<c:if test="${role.equalsIgnoreCase('Administrator')}">
							<th>Người hủy</th>
						</c:if>
						<th>Thời gian đặt hàng</th>
						<th>Ghi chú</th>
						<th>Tổng tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ListDatHang.size()==0}">
						<tr>
							<c:if test="${role.equalsIgnoreCase('Administrator')}">
								<td colspan="10">Không có đơn đặt hàng nào</td>
							</c:if>
						</tr>
					</c:if>
					<c:if test="${ListDatHang.size()>0}">
						<c:forEach items="${ListDatHang}" var="item">
							<tr class="odd gradeX">
									<td>${item.idDatHang}</td>
									<td>${item.tenKhachHang}<br/><br/><a href="chitietdathang?id=${item.idDatHang}">Chi tiết đặt hàng</a></td>
									<td>${item.email}</td>
									<td>0<fmt:formatNumber pattern="###,###,###">${item.sdt1}</fmt:formatNumber></td>
									<td>
										<c:choose>
											<c:when test="${item.sdt2==null}">
												
											</c:when>
											<c:when test="${item.sdt2==0}">
												
											</c:when>
											<c:otherwise>
												0<fmt:formatNumber pattern="###,###,###">${item.sdt2}</fmt:formatNumber>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${item.diaChi}</td>
									<c:if test="${role.equalsIgnoreCase('Administrator')}">
										<td>
											<c:if test="${item.users.username!=null}">
												${item.users.username} (${item.users.hoTen})
											</c:if>
										</td>
									</c:if>
									<td><fmt:formatDate value="${item.thoiGianDatHang}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
									<td>${item.ghiChu}</td>
									<td><fmt:formatNumber pattern="###,###,###">${item.tongTien}</fmt:formatNumber> VNĐ</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- /.table-responsive -->
								
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
			<c:if test="${role.equalsIgnoreCase('Administrator')}">
				<div align="center">
					<a href="quanlydathang"><input type="button" class="btn btn-default" value="Quản lý đặt hàng"/></a>
					<a href="xemdonhangdahoantat"><input type="button" class="btn btn-default" value="Xem các đơn hàng đã hoàn tất"/></a>
				</div>
			</c:if>		
		</div>
	</div>
</div>