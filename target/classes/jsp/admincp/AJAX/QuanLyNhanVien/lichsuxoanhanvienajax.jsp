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
				<label>Tổng Users: <span style="color:blue;">${TongsoUser}</span></label>
			</div>
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="dataTables-example" style="text-align: center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Username</th>
						<th>Họ tên</th>
						<th>Giới tính</th>
						<th>Email</th>
						<th>Số điện thoại 1</th>
						<th>Số điện thoại 2</th>
						<th>Địa chỉ</th>
						<th>Thời gian tạo</th>
						<th>Thời gian sửa lần cuối</th>
						<th>Thời gian xóa</th>
						<th>Chức vụ</th>
						<th>Mô tả thêm</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ListUsersDeleted.size()==0}">
						<tr>
							<td colspan="14">Không tồn tại nhân viên nào</td>
						</tr>
					</c:if>
					<c:if test="${ListUsersDeleted.size()>0}">
						<c:forEach items="${ListUsersDeleted}" var="item">
							<tr class="odd gradeX">
								<td>${item.idUsername}</td>
								<td>${item.username}</td>
								<td>${item.hoTen}</td>
								<td>
									<c:choose>
										<c:when test="${item.gioiTinh==0}">Nam</c:when>
										<c:when test="${item.gioiTinh==1}">Nữ</c:when>
									</c:choose>
								</td>
								<td>${item.email}</td>
								<td>0<fmt:formatNumber pattern="###,###,###">${item.sdtUser1}</fmt:formatNumber></td>
								<td>
									<c:choose>
										<c:when test="${item.sdtUser2==null}">
											
										</c:when>
										<c:when test="${item.sdtUser2==0}">
											
										</c:when>
										<c:otherwise>
											0<fmt:formatNumber pattern="###,###,###">${item.sdtUser2}</fmt:formatNumber>
										</c:otherwise>
									</c:choose>
								</td>
								<td>${item.diaChi}</td>
								<td>
									<fmt:formatDate value="${item.thoiGianTao}" pattern="HH:mm:ss _ dd/MM/yyyy" />
								</td>
								<td>
									<fmt:formatDate value="${item.thoiGianSua}" pattern="HH:mm:ss _ dd/MM/yyyy" />
								</td>
								<td>
									<fmt:formatDate value="${item.thoiGianXoa}" pattern="HH:mm:ss _ dd/MM/yyyy" />
								</td>
								<td>
									<c:choose>
										<c:when test="${item.role.equalsIgnoreCase('Administrator')}">
											Administrator
										</c:when>
										<c:when test="${item.role.equalsIgnoreCase('Moderator')}">
											Moderator
										</c:when>
									</c:choose>	
								</td>
								<td>${item.moTaThem}</td>
								<td>
									<a href="xoalichsunhanvien?id=${item.idUsername}" onclick="return confirm('Bạn chắc chắn muốn xóa tài khoản ${item.username} khỏi lịch sử?')">Xóa</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- /.table-responsive -->
			<div align="center">
				<c:if test="${ListUsersDeleted.size()>0}">
					<a href="xoatatcalichsunhanvien"><input type="button" class="btn btn-default" value="Xóa tất cả lịch sử nhân viên"/></a>
				</c:if>
					<a href="quanlynhanvien"><input type="button" class="btn btn-default" value="Quay lại"/></a>
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
</html>