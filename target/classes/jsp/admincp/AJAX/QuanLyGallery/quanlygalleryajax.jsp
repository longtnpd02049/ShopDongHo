<%@page import="com.xTNL.entities.Binhluan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-lg-12">
		<div align="center" style="font-size:18px;">
           	<img src="${pageContext.servletContext.contextPath}/${chitietsp.image}" width="100" height="150"/><br/><br/>
           	<p>${chitietsp.tenSp}</p><br/><br/>
           </div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="form-group">
				<label>Tổng số Gallery: <span style="color:blue;">${TongsoGallery}</span></label>
				<div align="right">
					<a href="themgallery?id=${chitietsp.idChiTietSp}"><input type="button" class="btn btn-default" value="Thêm gallery"/></a>
					<c:if test="${chitietsp.galleries.size()>0}">
						<a href="xoaallgallery?id=${chitietsp.idChiTietSp}"><input type="button" class="btn btn-default" value="Xóa tất cả gallery"/></a>
					</c:if>
					<a href="quanlysanpham"><input type="button" class="btn btn-default" value="Quay lại"/></a>
				</div>
			</div>
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="dataTables-example" style="text-align: center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Image</th>
						<th>Thời gian tạo</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${chitietsp.galleries.size()==0}">
							<tr>
								<td colspan="4">Không có hình ảnh nào</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${getAllGallerry}" var="item">
								<tr class="odd gradeX">
									<td>${item.idGallery}</td>
									<td><img src="${pageContext.servletContext.contextPath}/${item.images}" width="100" height="150"/></td>
									<td><fmt:formatDate value="${item.thoiGianTao}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
									<td>
										<a href="xoagallery?id=${item.idGallery}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>							
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
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