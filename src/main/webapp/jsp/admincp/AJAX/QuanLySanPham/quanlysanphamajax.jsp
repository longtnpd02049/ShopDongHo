<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<div class="row">
	<div class="col-lg-12">
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="form-group">
				<label>Tổng sản phẩm: <span style="color:blue;">${TongsoSP}</span></label>
				<p style="float:right;"><a href="themsanpham"><input type="button" class="btn btn-default" value="Thêm sản phẩm mới"/></a></p>
			</div>
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="dataTables-example" style="text-align: center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Tên sản phẩm</th>
						<th>Danh mục</th>
						<th>Hiệu</th>
						<th>Giá</th>
						<th>Số lượng còn</th>
						<th>Tổng số bình luận</th>
						<th>Tổng số gallery</th>
						<c:if test="${role.equalsIgnoreCase('Administrator')}">
							<th>Người tạo</th>
						</c:if>
						<th>Thời gian tạo</th>
						<th>Thời gian sửa</th>
						<th>Mô tả về sản phẩm</th>
						<th>Trạng thái</th>
						<th>Mô tả thêm</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ListSanPham.size()>0}">
						<c:forEach items="${ListSanPham}" var="item">
							<tr class="odd gradeX">
								<td>${item.idChiTietSp}</td>
								<td>
									<a href="../details?idsp=${item.idChiTietSp}" target="_blank"><img src="${pageContext.servletContext.contextPath}/${item.image}" width="70" height="80" /></a><br/>
									${item.tenSp}<br/><br/>
									<a href="quanlygallery?id=${item.idChiTietSp}">Quản lý Gallery</a>
								</td>
								<%-- Bắt đầu lấy đường dẫn tên danh mục | Nếu chitietsp.iddanhmuc2==danhmuc2.iddanhmuc2 => Tên danh mục 2 | ngược lại chỉ xuất ra tên danh mục 1 --%>
								<td>
									${item.danhmucsp.name}
									<c:if test="${item.danhmucsp.danhmucsp2s.size()>0}">
										<c:forEach items="${item.danhmucsp.danhmucsp2s}" var="item2">
											<c:if test="${item.idDanhMuc2==item2.idDanhMucSp2}">
												> ${item2.name}
											</c:if>
										</c:forEach>
									</c:if>
								</td>
								<%-- Kết thúc đường dẫn danh mục --%>
								<td>${item.hieusp.tenHieuSp}</td>
								<td><fmt:formatNumber pattern="###,###,###">${item.giaSp}</fmt:formatNumber> VNĐ</td>
								<td>${item.soLuongSp}</td>
								<td>
								<%
									Integer i =0;
									Integer i2=0;
								%>
									<c:if test="${item.binhluans.size()==0}">Chưa có bình luận nào</c:if>
									<c:if test="${item.binhluans.size()>0}">
										<b>${item.binhluans.size()}</b>
										<c:forEach items="${item.binhluans}" var="item2">
												<c:if test="${item2.status=='ok'}">
													<%
														i+=1;
													%>
												</c:if>
												<c:if test="${item2.status=='wait'}">
													<%
														i2+=1;
													%>
												</c:if>
										</c:forEach><br/>
										- Đã duyệt: <b><%=i%></b><br/>
										- Chưa duyệt: <b><%=i2%></b>
									</c:if>
								</td>
								<td>${item.galleries.size()}</td>
								<c:if test="${role.equalsIgnoreCase('Administrator')}">
									<td>${item.users.username} (${item.users.hoTen})</td>
								</c:if>
								<td><fmt:formatDate value="${item.thoiGianTaoSp}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${item.thoiGianSuaSp}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
								<td>
									- Đặc điểm nổi bật: ${item.dacDiemNoiBat}<br/>
									- Loại máy: ${item.loaiMay}<br/>
									- Kiểu dáng: ${item.kieuDang}<br/>
									- Mặt kính: ${item.matKinh}<br/>
									- Vỏ: ${item.vo}<br/>
									- Đường kính: ${item.duongKinh}<br/>
									- Độ dày: ${item.doDay}<br/>
									- Độ chịu nước: ${item.doChiuNuoc}<br/>
									- Thời gian bảo hành: ${item.baoHanh}
								</td>
								<td>
									<c:choose>
										<c:when test="${item.status=='ok'}">
											<img src="../images/successicon.png" width="24" height="24">
										</c:when>
										<c:when test="${item.status=='hethang'}">
											<label>Hết hàng</label>
										</c:when>
										<c:when test="${item.status=='giamgia'}">
											<label>Đang giảm giá</label>
										</c:when>
									</c:choose>
								</td>
								<td>${item.moTaThem}</td>
								<td>
									<a href="suasanpham?id=${item.idChiTietSp}">Sửa</a> | 
									<a href="xoasanpham?id=${item.idChiTietSp}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>							
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ListSanPham.size()==0}">
						<tr>
							<td colspan="15">Không có sản phẩm nào</td>
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
		