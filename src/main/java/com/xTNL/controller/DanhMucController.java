package com.xTNL.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.Dao.HieuSPDao;
import com.xTNL.DaoImpl.DanhMucSPDaoImpl;
import com.xTNL.DaoImpl.HieuSPDaoImpl;

@Controller
public class DanhMucController {
//	@RequestMapping(value="danhmuc",method=RequestMethod.GET)
//	public String danhmuc2(@RequestParam(value="id")String id,HttpServletRequest request,Model m) {
//		try {
//			DanhMucSPDao dmdao = new DanhMucSPDaoImpl();
//			HieuSPDao hieuspModel = new HieuSPDaoImpl();
//			/*Bắt đầu phân trang*/
//			int limit = 12;
//			int selectPage = 0;
//			if(request.getParameter("pageid")!=null) {
//				selectPage = Integer.parseInt(request.getParameter("pageid"));
//			}
//			int start = dmdao.findStart(limit, selectPage);
//			int count = dmdao.getSPByDanhMuc(Integer.parseInt(id)).size();
//			int pages = dmdao.findPages(count, limit);
//			//System.out.println("start "+start); //Vị trí bắt đầu
//			//System.out.println("count "+count); //Tổng số sản phẩm
//			//System.out.println("pages "+pages); //Tổng số trang
//			/*Kết thúc phân trang*/
//			
//			if(dmdao.getDanhMucById(Integer.parseInt(id)).equals(null)) { //Nếu không tồn tại id quay lại trang chủ
//				return "redirect:home";
//			}else {
//				m.addAttribute("dmspList",dmdao.getAll());
//				m.addAttribute("hieuspList",hieuspModel.getAll());
//				m.addAttribute("ListspByIdDanhMucsp", dmdao.getSPByIdDanhMucTheoPage(Integer.parseInt(id),start,limit));
//				m.addAttribute("DanhmucspById", dmdao.getDanhMucById(Integer.parseInt(id)));
//				m.addAttribute("PageCount",pages);
//				m.addAttribute("IdDanhMuc",id);
//				if(selectPage==0) {
//					selectPage=1;
//				}
//				m.addAttribute("selectPage",selectPage);
//			}
//			return "danhmucsp";
//		}catch(Exception ex) {
//			return "redirect:home";
//		}
//		
//	}
	
//	@RequestMapping(value="danhmuc2",method=RequestMethod.GET)
//	public String danhmuc(@RequestParam(value="id")String id,@RequestParam(value="id2")String id2,HttpServletRequest request,Model m) {
//		
//		try {
//			DanhMucSPDao dmdao = new DanhMucSPDaoImpl();
//			HieuSPDao hieuspModel = new HieuSPDaoImpl();
//			/*Bắt đầu phân trang*/
//			int limit = 12;
//			int selectPage = 0;
//			if(request.getParameter("pageid")!=null) {
//				selectPage = Integer.parseInt(request.getParameter("pageid"));
//			}
//			int start = dmdao.findStart(limit, selectPage);
//			int count = dmdao.getSPByDanhMuc(Integer.parseInt(id),Integer.parseInt(id2)).size();
//			int pages = dmdao.findPages(count, limit);
//			//System.out.println("start "+start); //Vị trí bắt đầu
//			//System.out.println("count "+count); //Tổng số sản phẩm
//			//System.out.println("pages "+pages); //Tổng số trang
//			/*Kết thúc phân trang*/
//			
//			if(dmdao.getDanhMucById(Integer.parseInt(id)).equals(null)) { //Nếu không tồn tại id quay lại trang chủ
//				return "redirect:home";
//			}else if(dmdao.getDanhMucById2(Integer.parseInt(id),Integer.parseInt(id2)).equals(null)) { //Nếu không tồn tại id quay lại trang chủ
//				return "redirect:home";
//			}else {
//				m.addAttribute("dmspList",dmdao.getAll());
//				m.addAttribute("hieuspList",hieuspModel.getAll());
//				m.addAttribute("ListspByIdDanhMucsp", dmdao.getSPByIdDanhMucTheoPage(Integer.parseInt(id),Integer.parseInt(id2),start,limit));
//				m.addAttribute("DanhmucspById", dmdao.getDanhMucById(Integer.parseInt(id)));
//				m.addAttribute("DanhmucspById2", dmdao.getDanhMucById2(Integer.parseInt(id),Integer.parseInt(id2)));
//				m.addAttribute("PageCount",pages);
//				m.addAttribute("IdDanhMuc",id);
//				m.addAttribute("IdDanhMuc2",id2);
//				if(selectPage==0) {
//					selectPage=1;
//				}
//				m.addAttribute("selectPage",selectPage);
//			}
//			return "danhmucsp";
//		}catch(Exception ex) {
//			return "redirect:home";
//		}
//	}
	
	@RequestMapping(value="danhmuc",method=RequestMethod.GET)
	public String danhmuc(Model m,HttpServletRequest request) {
		
		try {
			DanhMucSPDao dmdao = new DanhMucSPDaoImpl();
			HieuSPDao hieuspModel = new HieuSPDaoImpl();
			m.addAttribute("dmspList",dmdao.getAll());
			m.addAttribute("hieuspList",hieuspModel.getAll());
			String id = request.getParameter("id");
			String id2 = request.getParameter("id2");
			//Nếu id = null hoặc trống => quay lại trang chủ, ngược lại:
			// - kiểm tra id2 null hoặc trống, nếu null hoặc trống => kiểm tra id danhmuc1 tồn tại hay không => nếu k tồn tại => quay về trang chủ, ngược lại kiểm tra id trong danhmuc2 nếu tồn tại => quay về trang chủ
			// - ngược lại id2 có giá trị => kiểm tra id danhmuc1 tồn tại hay không, nếu id danhmuc1 k tồn tại quay về trang chủ => nếu id danhmuc1 tồn tại, kiểm tra id2 danhmuc2 tồn tại hay không,không tồn tại quay về trang chủ, tồn tại k làm gì
			if(id==null || id=="") {
				return "redirect:home";
			}else {
				if(id2=="" || id2==null) { //id2 null hoặc trống
					if(dmdao.getDanhMucById(Integer.parseInt(id))==null) {
						return "redirect:home";
					}else {
						if(dmdao.checkdmsptrongdmsp2byId(Integer.parseInt(id))!=null) {
							return "redirect:home";
						}
					}
				}else { //ngược lại id2 có giá trị
					if(dmdao.getDanhMucById(Integer.parseInt(id))==null) { //nếu id danhmuc1 không tồn tại
						return "redirect:home";
					}else { //nếu tồn tại kiểm tra iddanhmuc1 và id2 danhmuc2 trong bảng danhmuc2
						if(dmdao.getDanhMucById2(Integer.parseInt(id),Integer.parseInt(id2))==null) {
							return "redirect:home";
						}
					}
				}
			}
			return "danhmucsp";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:home";
		}
		
	}
	
	@RequestMapping(value="danhmucajax",method=RequestMethod.POST)
	public String danhmucajax(Model m, HttpServletRequest request,@RequestParam("id")String id) { //Danh mục ajax
		try {
			DanhMucSPDao dmdao = new DanhMucSPDaoImpl();
			HieuSPDao hieuspModel = new HieuSPDaoImpl();
			String id2 = request.getParameter("id2");
			/*Bắt đầu phân trang*/
			int limit = 12;
			int selectPage = 0;
			if(request.getParameter("pageid")!=null) {
				selectPage = Integer.parseInt(request.getParameter("pageid"));
			}
			int start,count,pages;
			if(id2==null) {
				start = dmdao.findStart(limit, selectPage);
				count = dmdao.getSPByDanhMuc(Integer.parseInt(id)).size();
				pages = dmdao.findPages(count, limit);
			}else {
				start = dmdao.findStart(limit, selectPage);
				count = dmdao.getSPByDanhMuc(Integer.parseInt(id),Integer.parseInt(id2)).size();
				pages = dmdao.findPages(count, limit);
			}
			
			//System.out.println("start "+start); //Vị trí bắt đầu
			//System.out.println("count "+count); //Tổng số sản phẩm
			//System.out.println("pages "+pages); //Tổng số trang
			/*Kết thúc phân trang*/
			if(id2==null) {
				if(dmdao.getDanhMucById(Integer.parseInt(id))==null) { //Nếu không tồn tại id quay lại trang chủ
					return "redirect:home";
				}else {
					//Nếu tồn tại id nhưng người dùng cố xóa id2 => check danhmuc2 xem có id tồn tại không, nếu tồn tại => trang chủ
					if(dmdao.checkdmsptrongdmsp2byId(Integer.parseInt(id))==null) {
						m.addAttribute("dmspList",dmdao.getAll());
						m.addAttribute("hieuspList",hieuspModel.getAll());
						if(id2==null) {
							m.addAttribute("ListspByIdDanhMucsp", dmdao.getSPByIdDanhMucTheoPage(Integer.parseInt(id),start,limit));
							m.addAttribute("DanhmucspById", dmdao.getDanhMucById(Integer.parseInt(id)));
						}else {
							m.addAttribute("ListspByIdDanhMucsp", dmdao.getSPByIdDanhMucTheoPage(Integer.parseInt(id),Integer.parseInt(id2),start,limit));
							m.addAttribute("DanhmucspById", dmdao.getDanhMucById(Integer.parseInt(id)));
							m.addAttribute("DanhmucspById2", dmdao.getDanhMucById2(Integer.parseInt(id),Integer.parseInt(id2)));
						}
						m.addAttribute("PageCount",pages);
						m.addAttribute("IdDanhMuc",id);
						if(selectPage==0) {
							selectPage=1;
						}
						m.addAttribute("selectPage",selectPage);
						
					}else {
						return "redirect:home";
					}
				}
			}else {
				if(dmdao.getDanhMucById(Integer.parseInt(id))==null) { //Nếu không tồn tại id quay lại trang chủ
					return "redirect:home";
				}else if(dmdao.getDanhMucById2(Integer.parseInt(id2))==null){
					return "redirect:home";
				}else {
					m.addAttribute("dmspList",dmdao.getAll());
					m.addAttribute("hieuspList",hieuspModel.getAll());
					if(id2==null) {
						m.addAttribute("ListspByIdDanhMucsp", dmdao.getSPByIdDanhMucTheoPage(Integer.parseInt(id),start,limit));
						m.addAttribute("DanhmucspById", dmdao.getDanhMucById(Integer.parseInt(id)));
					}else {
						m.addAttribute("ListspByIdDanhMucsp", dmdao.getSPByIdDanhMucTheoPage(Integer.parseInt(id),Integer.parseInt(id2),start,limit));
						m.addAttribute("DanhmucspById", dmdao.getDanhMucById(Integer.parseInt(id)));
						m.addAttribute("DanhmucspById2", dmdao.getDanhMucById2(Integer.parseInt(id),Integer.parseInt(id2)));
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("IdDanhMuc",id);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("selectPage",selectPage);
				}
			}
			return "danhmucsp/center-side";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:home";
		}
	}
}
