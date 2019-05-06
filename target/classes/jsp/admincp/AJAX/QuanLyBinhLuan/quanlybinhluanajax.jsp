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
				<label>Tổng bình luận: <span style="color:blue;">${TongsoBinhLuan}</span></label>
			</div>
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="dataTables-example" style="text-align: center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Sản phẩm</th>
						<th>Họ tên</th>
						<th>Giới tính</th>
						<th>Đánh giá</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Nội dung bình luận</th>
						<th>Thời gian bình luận</th>
						<th>Thời gian duyệt</th>
						<th>Thời gian sửa</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ListBinhLuanSP.size()>0}">
						<c:forEach items="${ListBinhLuanSP}" var="item">
							<tr class="odd gradeX">
								<td>${item.idBinhLuan}</td>
								<td><a href="../details?idsp=${item.chitietsp.idChiTietSp}" target="_blank"><img src="${pageContext.servletContext.contextPath}/${item.chitietsp.image}" width="70" height="80" /></a></td>
								<td>${item.hoTen}</td>
								<td><c:choose>
										<c:when test="${item.gioiTinh==0}">Nam</c:when>
										<c:when test="${item.gioiTinh==1}">Nữ</c:when>
									</c:choose></td>
	
								<td><c:choose>
										<c:when test="${item.danhGia==1}">
											<div class="saovang" style="margin-left: 10px;"></div>
										</c:when>
										<c:when test="${item.danhGia==2}">
											<div class="saovang" style="margin-left: 10px;"></div>
											<div class="saovang"></div>
										</c:when>
										<c:when test="${item.danhGia==3}">
											<div class="saovang" style="margin-left: 10px;"></div>
											<div class="saovang"></div>
											<div class="saovang"></div>
										</c:when>
										<c:when test="${item.danhGia==4}">
											<div class="saovang" style="margin-left: 10px;"></div>
											<div class="saovang"></div>
											<div class="saovang"></div>
											<div class="saovang"></div>
										</c:when>
										<c:when test="${item.danhGia==5}">
											<div class="saovang" style="margin-left: 10px;"></div>
											<div class="saovang"></div>
											<div class="saovang"></div>
											<div class="saovang"></div>
											<div class="saovang"></div>
										</c:when>
									</c:choose></td>
								<td>${item.email}</td>
								<td class="center">0<fmt:formatNumber pattern="###,###,###">${item.phone}</fmt:formatNumber></td>
								<td class="center">${item.noiDungBinhLuan}</td>
								<td class="center"><fmt:formatDate value="${item.thoiGianBinhLuan}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
								<td class="center">
									<c:choose>
										<c:when test="${item.status=='ok'}">
											<fmt:formatDate value="${item.thoiGianXacNhan}" pattern="HH:mm:ss _ dd/MM/yyyy" />
										</c:when>
									</c:choose>
								</td>
								<td class="center"><fmt:formatDate value="${item.thoiGianSua}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
								<td>
									<c:choose>
										<c:when test="${item.status=='wait'}">
											<img src="../images/erroricon.png" width="24" height="24">
										</c:when>
										<c:when test="${item.status=='ok'}">
											<img src="../images/successicon.png" width="24" height="24">
										</c:when>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${item.status=='wait'}">
											<a href="duyetbinhluan?id=${item.idBinhLuan}">Duyệt</a> | <a
												href="suabinhluan?id=${item.idBinhLuan}">Sửa</a> | <a
												href="xoabinhluan?id=${item.idBinhLuan}"
												onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
										</c:when>
										<c:when test="${item.status=='ok'}">
											<a href="suabinhluan?id=${item.idBinhLuan}">Sửa</a> | <a
												href="xoabinhluan?id=${item.idBinhLuan}"
												onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ListBinhLuanSP.size()==0}">
						<tr>
							<td colspan="13">Không có bình luận nào</td>
						</tr>
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
		</div>
	</div>
</div>