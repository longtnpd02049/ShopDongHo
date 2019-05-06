package com.xTNL.controller;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.Dao.HieuSPDao;
import com.xTNL.Dao.SanPhamDao;
import com.xTNL.DaoImpl.DanhMucSPDaoImpl;
import com.xTNL.DaoImpl.HieuSPDaoImpl;
import com.xTNL.DaoImpl.SanPhamDaoImpl;
import com.xTNL.entities.Gallery;
import com.xTNL.utils.MySessionFactory;
@Controller
public class HomeController {
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String index(Model m) {
		DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
		HieuSPDao hieuspModel = new HieuSPDaoImpl();
		SanPhamDao sanphamModel = new SanPhamDaoImpl();
		m.addAttribute("dmspList",danhmucspModel.getAll());
		m.addAttribute("hieuspList",hieuspModel.getAll());
		m.addAttribute("Listspmoi", sanphamModel.getHangMoi());
		return "home";
	}
}