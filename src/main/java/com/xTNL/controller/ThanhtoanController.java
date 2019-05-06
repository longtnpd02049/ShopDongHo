package com.xTNL.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.Dao.HieuSPDao;
import com.xTNL.Dao.SanPhamDao;
import com.xTNL.Dao.ThanhtoanDao;
import com.xTNL.DaoImpl.DanhMucSPDaoImpl;
import com.xTNL.DaoImpl.HieuSPDaoImpl;
import com.xTNL.DaoImpl.SanPhamDaoImpl;
import com.xTNL.DaoImpl.ShopCartDaoImpl;
import com.xTNL.DaoImpl.ThanhtoanDaoImpl;
import com.xTNL.entities.Chitietdathang;
import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Dathang;

@Controller
public class ThanhtoanController {
	@RequestMapping(value="/thanhtoan",method=RequestMethod.GET)
	public String thanhtoan(Model m) {
		try {
			DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
			HieuSPDao hieuspModel = new HieuSPDaoImpl();
			m.addAttribute("dmspList",danhmucspModel.getAll());
			m.addAttribute("hieuspList",hieuspModel.getAll());
			return "thanhtoan";
		}catch(Exception ex) {
			return "redirect:home";
		}
	}
	@RequestMapping(value="/thanhtoan",method=RequestMethod.POST)
	public String thanhtoan(Model m,@Valid @ModelAttribute("thanhtoan")Dathang dathang,HttpSession session) {
		try {
			ThanhtoanDao thanhtoan = new ThanhtoanDaoImpl();
			Chitietdathang ctdh = new Chitietdathang();
			ShopCartDaoImpl shopcartdao = new ShopCartDaoImpl();
			double tongTien = shopcartdao.tongGiaTien();
			thanhtoan.insert(dathang,tongTien); //Thêm thông tin khách hàng
			/*Thêm thông tin giỏ hàng*/
			//Lấy giá trị id cao nhất DatHang.IdDatHang = Chitietdathag.IdDatHang
			//Dùng vòng lặp lấy tất cả sản phẩm trong giỏ hàng => Set IdChitietSP và số lượng từ listgiohang
			int getMaxDatHang = thanhtoan.getMaxDatHang();
			if(getMaxDatHang>0) {
				for(int i =0;i<shopcartdao.listgioHang.size();i++) {
					thanhtoan.insert(getMaxDatHang, shopcartdao.listgioHang.get(i).getIdChiTietSp(), shopcartdao.listgioHang.get(i).getSoLuongDat());
				}
			}
			/*Kết thúc thêm thông tin giỏ hàng*/
			session.setAttribute("hoten", dathang.getTenKhachHang());
			session.setAttribute("email", dathang.getEmail());
			session.setAttribute("Sdt1",dathang.getSdt1());
			session.setAttribute("Sdt2",dathang.getSdt2());
			session.setAttribute("diachi",dathang.getDiaChi());
			session.setAttribute("ghichu",dathang.getGhiChu());
			return "redirect:hoantatdathang";
		}catch(Exception ex) {
			return "redirect:home";
		}
	}
	@RequestMapping(value="/hoantatdathang",method=RequestMethod.GET)
	public String hoantatdathang(Model m,HttpSession session) {
		try {
			ShopCartDaoImpl shopcartdao = new ShopCartDaoImpl();
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
			return "hoantatdathang";
		}catch(Exception ex) {
			return "redirect:home";
		}
	}
}
