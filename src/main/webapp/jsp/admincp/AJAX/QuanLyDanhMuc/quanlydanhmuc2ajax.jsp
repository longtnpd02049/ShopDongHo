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
				<label>Tổng danh mục con: <span style="color:blue;">${TongsoDanhmuc2}</span></label>
				<p style="float:right;"><a href="themdanhmuc2"><input type="button" class="btn btn-default" value="Thêm danh mục con" /></a></p>
			</div>
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="dataTables-example" style="text-align: center;">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Thuộc danh mục cha</th>
						<th>Thời gian tạo</th>
						<th>Thời gian sửa</th>
						<th>SortId</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ListDanhMuc2.size()>0}">
						<c:forEach items="${ListDanhMuc2}" var="item">
							<tr class="odd gradeX">
								<td>${item.idDanhMucSp2}</td>
								<td>${item.name}</td>
								<td>${item.danhmucsp.name}</td>
								<td><fmt:formatDate value="${item.thoiGianTao}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
								<td><fmt:formatDate value="${item.thoiGianSua}" pattern="HH:mm:ss _ dd/MM/yyyy" /></td>
								<td>${item.sortId}</td>
								<td>
									<a href="suadanhmuc2?id=${item.idDanhMucSp2}">Sửa</a> | 
									<a href="xoadanhmuc2?id=${item.idDanhMucSp2}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>							
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ListDanhMuc2.size()==0}">
						<tr>
							<td colspan="7">Không có danh mục nào</td>
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