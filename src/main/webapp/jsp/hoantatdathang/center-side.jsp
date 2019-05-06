<%@page import="com.xTNL.DaoImpl.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section>
	<div class="center-side">
		<h2 class="text-center" style="color:green;">Hoàn tất đặt hàng</h2>
		<div style="text-align:center;padding-top:50px;font-size:18px;">
			<div class="form-group">
				<label>Họ tên</label>: ${hoten}<br/>
			</div>
			<c:if test="${email!=''}">
				<div class="form-group">
					<label>Email</label>: ${email}<br/>
				</div>
			</c:if>
			<div class="form-group">
				<label>Số điện thoại 1</label>: 0<fmt:formatNumber pattern="###,###,###">${Sdt1}</fmt:formatNumber><br/>
			</div>
			<c:if test="${Sdt2!=null}">
				<div class="form-group">
					<label>Số điện thoại 2</label>: 0<fmt:formatNumber pattern="###,###,###">${Sdt2}</fmt:formatNumber><br/>
				</div>
			</c:if>
			<div class="form-group">
				<label>Địa chỉ</label>: ${diachi}<br/>
			</div>
			<c:if test="${ghichu!=''}">
				<div class="form-group">
					<label>Ghi chú</label>: ${ghichu}<br/>
				</div>
			</c:if>
		</div><br/>
		<h3>Thông tin đặt hàng</h3>
		<table id="cart" class="table condensed" style="margin: 35px; width: 95%; color: white;">
			<thead>
				<tr align="center">
					<th style="width: 50%">Tên sản phẩm</th>
					<th style="width: 20%">Giá</th>
					<th style="width: 10%">Số lượng</th>
					<th style="width: 30%">Thành tiền</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${SHOPPINGCART}" var="item">
					<tr>
						<td data-th="Product">
							<div class="row">
								<div class="col-sm-2 hidden-xs">
									<img src="${item.image}" alt="${item.tenSp}" class="img-responsive" width="550" height="450">
								</div>
								<div class="col-sm-10" style="margin-top:5px;">
									<h4 class="nomargin">${item.tenSp}</h4>
									<p>${item.moTaThem}</p>
								</div>
							</div>
						</td>
						<td data-th="Price"><div style="margin-top:15px;"></a><fmt:formatNumber pattern="###,###,###">${item.giaSp}</fmt:formatNumber> đ</div></td>
						<td data-th="Quantity"><div style="margin-top:15px;">${item.soLuongDat}</div></td>
						<td data-th="Subtotal"><div style="margin-top:15px;"><fmt:formatNumber pattern="###,###,###">${item.giaSp*item.soLuongDat}</fmt:formatNumber> đ</div></td>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
			<tfoot>
				<tr>
					<tr>
						<td class="hidden-xs text-center" colspan="4"><strong>Tổng tiền thanh toán: <a style="color:red;text-decoration: none;"><fmt:formatNumber pattern="###,###,###">${tongtien}</fmt:formatNumber></a> đ</strong></td>
					</tr>
				</tr>
			</tfoot>
		</table>
	</div>
</section>