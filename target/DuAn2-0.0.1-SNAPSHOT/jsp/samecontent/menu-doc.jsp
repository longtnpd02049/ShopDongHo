<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<base href="${pageContext.servletContext.contextPath}/">
<aside>
	<div class="left-side">
		<div class="menu-left">
			<div class="menu-left2">
				<div class="menu-tieude">
					<span>Danh mục sản phẩm</span>
				</div>
				<div class="menu-content">
					<ul>
						<c:forEach items="${dmspList}" var="dmspList">
							<c:choose>
								<c:when test="${dmspList.danhmucsp2s.size()>0}">
									<li class="active has-sub"><a><span><c:out value="${dmspList.name}"/></span></a>
										<ul>
										<c:forEach items="${dmspList.danhmucsp2s}" var="dmspList2">
											<li><a href='danhmuc2?id=${dmspList.idDanhMucSp}&id2=${dmspList2.idDanhMucSp2}'><span><c:out value="${dmspList2.name}"/></span></a></li>
										</c:forEach>
										</ul>
									</li>
								</c:when>
								<c:otherwise>
									<li><a href='danhmuc?id=${dmspList.idDanhMucSp}'><span><c:out value="${dmspList.name}"/></span></a>
									</li>
								</c:otherwise>
							</c:choose>
							
							
						</c:forEach>
					</ul>
				</div>
			</div>
			<br> <br>
			<div class="menu-left2">
				<div class="menu-tieude">
					<span>Hiệu sản phẩm</span>
				</div>
				<div class="menu-content">
					<ul>
						<c:forEach items="${hieuspList}" var="hieuspList">
							<li><a href='hieusp?id=${hieuspList.idHieuSp}'><span><c:out value="${hieuspList.tenHieuSp}"/></span></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<br>

	</div>
</aside>