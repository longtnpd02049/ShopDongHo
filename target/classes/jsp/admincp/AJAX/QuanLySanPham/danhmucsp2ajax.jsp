<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group">
	<label>Danh mục sản phẩm 2*</label>
    <select class="form-control" name="idDanhMuc2">
    	<c:if test="${ListDanhMuc2.size()>0}">
    		<c:forEach items="${ListDanhMuc2}" var="item">
	    		<option value="${item.idDanhMucSp2}">${item.name}</option>
	    	</c:forEach>
    	</c:if>
   		<c:if test="${ListDanhMuc2.size()==0}">
	    	<option value="">Không chọn</option>
    	</c:if>
	</select>
</div>