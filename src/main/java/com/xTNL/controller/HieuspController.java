package com.xTNL.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.Dao.HieuSPDao;
import com.xTNL.Dao.SanPhamDao;
import com.xTNL.DaoImpl.DanhMucSPDaoImpl;
import com.xTNL.DaoImpl.HieuSPDaoImpl;
import com.xTNL.DaoImpl.SanPhamDaoImpl;

@Controller
public class HieuspController {
//	@RequestMapping(value="/hieusp",method=RequestMethod.GET)
//	public String index(@RequestParam(value="id")String id,HttpServletRequest request,Model m) {
//		try {	
//			DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
//			HieuSPDao hieuspModel = new HieuSPDaoImpl();
//			/*Bắt đầu phân trang*/
//			int limit = 12;
//			int selectPage = 0;
//			if(request.getParameter("pageid")!=null) {
//				selectPage = Integer.parseInt(request.getParameter("pageid"));
//			}
//			int start = hieuspModel.findStart(limit, selectPage);
//			int count = hieuspModel.getSpByIdHieuSP(Integer.parseInt(id)).size();
//			int pages = hieuspModel.findPages(count, limit);
//			//System.out.println("start "+start); //Vị trí bắt đầu
//			//System.out.println("count "+count); //Tổng số sản phẩm
//			//System.out.println("pages "+pages); //Tổng số trang
//			/*Kết thúc phân trang*/
//			if(hieuspModel.getHieuSpById(Integer.parseInt(id)).equals(null)) { //Nếu không tồn tại id hiệu sản phẩm quay lại trang chủ
//				return "redirect:home";
//			}else {
//				m.addAttribute("dmspList",danhmucspModel.getAll());
//				m.addAttribute("hieuspList",hieuspModel.getAll());
//				m.addAttribute("ListspByIdHieuSp", hieuspModel.getSPByIdHieuSPTheoPage(Integer.parseInt(id),start,limit));
//				m.addAttribute("hieuSpById", hieuspModel.getHieuSpById(Integer.parseInt(id)));
//				m.addAttribute("PageCount",pages);
//				if(selectPage==0) {
//					selectPage=1;
//				}
//				m.addAttribute("selectPage",selectPage);
//				m.addAttribute("IdDanhMuc",id);
//			}
//			return "hieusp";
//		}catch(Exception ex) {
//			return "redirect:home";
//		}
//	}
	
	@RequestMapping(value="/hieusp",method=RequestMethod.GET)
	public String index(HttpServletRequest request,Model m) {
		try {
			String id = request.getParameter("id");
			DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
			HieuSPDao hieuspModel = new HieuSPDaoImpl();
			if(id.equals("") || id.equals(null)) {
				return "redirect:home";
			}else {
				if(hieuspModel.getHieuSpById(Integer.parseInt(id))==null) {
					return "redirect:home";
				}
			}
			
			m.addAttribute("dmspList",danhmucspModel.getAll());
			m.addAttribute("hieuspList",hieuspModel.getAll());
			return "hieusp";
		}catch(Exception ex) {
			return "redirect:home";
		}
	}
	
	@RequestMapping(value="/hieuspajax",method=RequestMethod.POST)
	public String hieuspajax(@RequestParam(value="id")String id,HttpServletRequest request,Model m) {
		try {	
			DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
			HieuSPDao hieuspModel = new HieuSPDaoImpl();
			/*Bắt đầu phân trang*/
			int limit = 12;
			int selectPage = 0;
			if(request.getParameter("pageid")!=null) {
				selectPage = Integer.parseInt(request.getParameter("pageid"));
			}
			int start = hieuspModel.findStart(limit, selectPage);
			int count = hieuspModel.getSpByIdHieuSP(Integer.parseInt(id)).size();
			int pages = hieuspModel.findPages(count, limit);
			//System.out.println("start "+start); //Vị trí bắt đầu
			//System.out.println("count "+count); //Tổng số sản phẩm
			//System.out.println("pages "+pages); //Tổng số trang
			/*Kết thúc phân trang*/
			if(hieuspModel.getHieuSpById(Integer.parseInt(id)).equals(null)) { //Nếu không tồn tại id hiệu sản phẩm quay lại trang chủ
				return "redirect:home";
			}else {
				m.addAttribute("dmspList",danhmucspModel.getAll());
				m.addAttribute("hieuspList",hieuspModel.getAll());
				m.addAttribute("ListspByIdHieuSp", hieuspModel.getSPByIdHieuSPTheoPage(Integer.parseInt(id),start,limit));
				m.addAttribute("hieuSpById", hieuspModel.getHieuSpById(Integer.parseInt(id)));
				m.addAttribute("PageCount",pages);
				if(selectPage==0) {
					selectPage=1;
				}
				m.addAttribute("selectPage",selectPage);
				m.addAttribute("IdDanhMuc",id);
			}
			return "hieusp/center-side";
		}catch(Exception ex) {
			return "redirect:home";
		}
	}
}
