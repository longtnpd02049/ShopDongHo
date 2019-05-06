<%@page import="com.xTNL.DaoImpl.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section>
	<div class="center-side">
		<h2 class="text-center" style="color:green;">Giỏ hàng</h2>

		<table id="cart" class="table condensed" style="margin: 35px; width: 95%; color: white;">
			<thead>
				<tr align="center">
					<th style="width: 50%">Tên sản phẩm</th>
					<th style="width: 20%">Giá</th>
					<th style="width: 10%">Số lượng</th>
					<th style="width: 20%">Thành tiền</th>
					<th style="width: 10%"></th>
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
							<form action="updatecart" method="GET">
								<td data-th="Quantity"><div style="margin-top:12px;"><input id="Quantity" name="Quantity" class="form-control text-center" value="${item.soLuongDat}" type="number" min="1" max="100"></div></td>
								<td data-th="Subtotal"><div style="margin-top:15px;"><fmt:formatNumber pattern="###,###,###">${item.giaSp*item.soLuongDat}</fmt:formatNumber> đ</div></td>
								<td class="actions" data-th="">
								<input name="idsp" id="idsp" value="${item.idChiTietSp}" type="hidden"/>
								<button class="btn btn-info btn-sm" style="width:80px;height:30px;margin-bottom:5px;margin-top:5px;"> UPDATE <i class="fa fa-edit"></i></button>
							</form>
							<br/>
							<a href="deletecart?idsp=${item.idChiTietSp}"><button class="btn btn-danger btn-sm" style="width:80px;height:30px;"> DELETE <i class="fa fa-trash-o"></i></button></a>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
			<tfoot>
				<tr>
					<%
						ShopCartDaoImpl s = new ShopCartDaoImpl();
						if(s.listgioHang.isEmpty()){
					%>
							<td colspan="4" align="center">Giỏ hàng trống</td>
							
					<%	
						}else{
					%>
							<tr>
								<td class="hidden-xs text-center" colspan="4"><strong>Tổng tiền cần thanh toán: <a style="color:red;text-decoration: none;"><fmt:formatNumber pattern="###,###,###">${tongtien}</fmt:formatNumber></a> đ</strong></td>
								<td><a href="" class="btn btn-success btn-block">Thanh toán <i class="fa fa-angle-right"></i></a></td>
							</tr>
							<td colspan="4"><a href="home" class="btn btn-primary"><i class="fa fa-angle-left"></i> Tiếp tục mua hàng</a></td>
							<td><a href="deleteallcart" class="btn btn-primary">Xóa hết giỏ hàng <i class="fa fa-angle-right"></a></td>
					<%	
						}
					%>
				</tr>
			</tfoot>
		</table>
		<%
			if(s.listgioHang.isEmpty()){
		%>
				<center><a href="home" class="btn btn-primary"><i class="fa fa-angle-left"></i> Tiếp tục mua hàng</a></center>
				
		<%	
			}
		%>
		
	</div>
</section>