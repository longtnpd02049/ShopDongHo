package com.xTNL.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.Dao.HieuSPDao;
import com.xTNL.Dao.SanPhamDao;
import com.xTNL.Dao.ShopCartDao;
import com.xTNL.DaoImpl.DanhMucSPDaoImpl;
import com.xTNL.DaoImpl.HieuSPDaoImpl;
import com.xTNL.DaoImpl.SanPhamDaoImpl;
import com.xTNL.DaoImpl.ShopCartDaoImpl;
import com.xTNL.entities.Chitietsp;

@Controller
public class CartController {
	private ShopCartDaoImpl shopcartdao = new ShopCartDaoImpl();
	private SanPhamDao spdao = new SanPhamDaoImpl();
	
	@RequestMapping(value="/shopcart",method=RequestMethod.GET)
	public String shopcart(Model m,HttpSession session) {
		if(session.getAttribute("SHOPPINGCART")==null) { //nếu session giỏ hàng xóa sau 15 phút => xóa sp trong arraylist
			shopcartdao.listgioHang.removeAll(shopcartdao.listgioHang);
		}else {
			shopcartdao.listgioHang = (ArrayList<Chitietsp>) session.getAttribute("SHOPPINGCART");
		}
		DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
		HieuSPDao hieuspModel = new HieuSPDaoImpl();
		m.addAttribute("dmspList",danhmucspModel.getAll());
		m.addAttribute("hieuspList",hieuspModel.getAll());
		m.addAttribute("tongtien",shopcartdao.tongGiaTien());
		return "shopcart";
	}
	
	//Thêm sản phẩm vào giỏ hàng
	@RequestMapping(value = "/addcart", method = RequestMethod.GET)
	public String addCart(@RequestParam(value = "idsp") int idsp, Model m,HttpSession session) {	
		// Tu id => Tim ra doi tuong
		// Luu vao List<Product> listProduct . add vo
		// Tao session
		// Luu vao session. VD: session.addAtribute("SHOPPINGCART", listProduct)
		// Lay gio hang : List<Product> = (List<Product>)
		// session.getAtribute("SHOPPINGCART');
		shopcartdao.ShopCartDaoImpl();
		if(session.getAttribute("SHOPPINGCART")==null) { //nếu session giỏ hàng xóa sau 15 phút => xóa sp trong arraylist
			shopcartdao.listgioHang.removeAll(shopcartdao.listgioHang);
		}else {
			shopcartdao.listgioHang = (ArrayList<Chitietsp>) session.getAttribute("SHOPPINGCART");
		}
//		
//		if(shopcartdao.addShopcart(idsp)==false) {
//			System.out.println("Thêm vào giỏ hàng thất bại");
//		}else {
			shopcartdao.addShopcart(idsp);
			for(int i=0;i<shopcartdao.listgioHang.size();i++) {
				if(shopcartdao.listgioHang.get(i).getIdChiTietSp().equals(idsp)) {
					System.out.println("Thêm thành công sản phẩm "+ shopcartdao.listgioHang.get(i).getTenSp() + " có id: " +idsp + " số lượng: "+shopcartdao.listgioHang.get(i).getSoLuongDat());
				}
			}
			session.setAttribute("SHOPPINGCART", shopcartdao.listgioHang);
//		}
		return "redirect:home";
	}
	
	//Xóa sản phẩm khỏi giỏ hàng
	@RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	public String deleteCart(@RequestParam(value = "idsp") int idsp, Model m,HttpSession session) {	
		shopcartdao.ShopCartDaoImpl();
		if(session.getAttribute("SHOPPINGCART")==null) { //nếu session giỏ hàng xóa sau 15 phút => xóa sp trong arraylist
			shopcartdao.listgioHang.removeAll(shopcartdao.listgioHang);
		}else {
			shopcartdao.listgioHang = (ArrayList<Chitietsp>) session.getAttribute("SHOPPINGCART");
		}
		if(shopcartdao.deleteShopcart(idsp)==true) {
			System.out.println("xóa thành công sản phẩm có id:"+idsp);
			session.setAttribute("SHOPPINGCART", shopcartdao.listgioHang);
		}else {
			System.out.println("Không tồn tại sản phẩm "+idsp+ " trong giỏ hàng");	
		}
		return "redirect:shopcart";
	}
	
	//Xóa tất cả sản phẩm khỏi giỏ hàng
	@RequestMapping(value = "/deleteallcart", method = RequestMethod.GET)
	public String deleteAllCart(Model m,HttpSession session) {	
		shopcartdao.listgioHang.removeAll(shopcartdao.listgioHang);
		session.setAttribute("SHOPPINGCART", null);
		return "redirect:shopcart";
	}
	
	//Update giỏ hàng
	@RequestMapping(value = "/updatecart", method = RequestMethod.GET)
	public String updateCart(@RequestParam(value="idsp") int idsp,@RequestParam(value="Quantity") int Quantity,Model m,HttpSession session) {	
		shopcartdao.ShopCartDaoImpl();
		if(session.getAttribute("SHOPPINGCART")==null) { //nếu session giỏ hàng xóa sau 15 phút => xóa sp trong arraylist
			shopcartdao.listgioHang.removeAll(shopcartdao.listgioHang);
		}else {
			shopcartdao.listgioHang = (ArrayList<Chitietsp>) session.getAttribute("SHOPPINGCART");
		}
		if(shopcartdao.updateShopcart(idsp,Quantity)==true) {
			System.out.println("Update thành công sản phẩm có id:"+idsp);
			session.setAttribute("SHOPPINGCART", shopcartdao.listgioHang);
		}else {
			System.out.println("Không tồn tại sản phẩm "+idsp+ " trong giỏ hàng");	
		}
		return "redirect:shopcart";
	}
}
