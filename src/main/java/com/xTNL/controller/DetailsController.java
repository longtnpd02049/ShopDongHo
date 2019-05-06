package com.xTNL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xTNL.Dao.BinhLuanDao;
import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.Dao.HieuSPDao;
import com.xTNL.Dao.SanPhamDao;
import com.xTNL.DaoImpl.BinhLuanDaoImpl;
import com.xTNL.DaoImpl.DanhMucSPDaoImpl;
import com.xTNL.DaoImpl.HieuSPDaoImpl;
import com.xTNL.DaoImpl.SanPhamDaoImpl;
import com.xTNL.entities.Binhluan;

@Controller
public class DetailsController {
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public String details(@RequestParam(value="idsp")String id,Model m) {
		try{
			SanPhamDao spdao = new SanPhamDaoImpl();
			BinhLuanDao bldao = new BinhLuanDaoImpl();
			DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
			HieuSPDao hieuspModel = new HieuSPDaoImpl();
			if(spdao.showThongTinByID(Integer.parseInt(id)).equals(null)) {
				return "redirect:home";
			}else {
				m.addAttribute("idsp",id);
				m.addAttribute("dmspList",danhmucspModel.getAll());
				m.addAttribute("hieuspList",hieuspModel.getAll());
				m.addAttribute("chitietsp",spdao.showThongTinByID(Integer.parseInt(id)));
				m.addAttribute("ListbinhluanByID",bldao.getBinhLuanByIdsp(Integer.parseInt(id)));
				m.addAttribute("ListgalleryByID",spdao.getGalleryImageByIdsp(Integer.parseInt(id)));
			}
			return "details";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:home";
		}
	}
	@RequestMapping(value="/postcomment",method=RequestMethod.POST)
	public String postCommentDetails(@ModelAttribute(value="comment") Binhluan bl) {
		System.out.println(bl.getHoTen());
		BinhLuanDao blDao = new BinhLuanDaoImpl();
		blDao.create(bl);
		return "redirect:home";
	}
}