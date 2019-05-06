package com.xTNL.controller;
import javax.servlet.ServletRequest;
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
public class SearchController {
	//http://localhost:8080/DuAn2/search?gia-min=0&gia-max=0&idhieusp=0&idsapxeptheo=0&key=%C4%91%E1%BB%93ng+h%E1%BB%93
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(@RequestParam(value="key")String keyword,@RequestParam(value="idsapxeptheo")String idsapxeptheo,ServletRequest request,@RequestParam(value="gia-min")Double giaMin,@RequestParam(value="gia-max")Double giaMax,@RequestParam(value="idhieusp")int idhieusp,Model m) {
		try {
			if(keyword.equals("")) {
				return "redirect:home";
			}else {
				DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
				SanPhamDao spdao = new SanPhamDaoImpl();
				HieuSPDao hieuspModel = new HieuSPDaoImpl();
				
				
				/*Bắt đầu phân trang*/
				int limit = 8;
				int selectPage = 0;
				if(request.getParameter("pageid")!=null) {
					selectPage = Integer.parseInt(request.getParameter("pageid"));
				}
				int start = spdao.findStart(limit, selectPage);
				int count = spdao.getSPTimKiem(keyword, giaMin, giaMax, idhieusp).size();
				int pages = spdao.findPages(count, limit);
				//System.out.println("start "+start); //Vị trí bắt đầu
				//System.out.println("count "+count); //Tổng số sản phẩm
				//System.out.println("pages "+pages); //Tổng số trang
				/*Kết thúc phân trang*/
				
				
				
				m.addAttribute("dmspList",danhmucspModel.getAll());
				m.addAttribute("hieuspList",hieuspModel.getAll());
				m.addAttribute("keyword",keyword);
				m.addAttribute("soluongKQ",spdao.getSPTimKiem(keyword, giaMin, giaMax, idhieusp).size());
				m.addAttribute("thongtinsearch",spdao.getSPSearchTheoPage(keyword,Integer.parseInt(idsapxeptheo),giaMin,giaMax,idhieusp,start,limit));
				m.addAttribute("PageCount",pages);
				if(selectPage==0) {
					selectPage=1;
				}
				m.addAttribute("selectPage",selectPage);
				if(giaMin==0 && giaMax==0) {
					m.addAttribute("giaTimKiem","Tất cả mệnh giá");
				}else if(giaMin==0 && giaMax==5) {
					m.addAttribute("giaTimKiem","Giá dưới 500k");
				}else if(giaMin==5 && giaMax==10) {
					m.addAttribute("giaTimKiem","Giá từ 500k - 1 triệu");
				}else if(giaMin==10 && giaMax==20) {
					m.addAttribute("giaTimKiem","Giá từ 1 - 2 triệu");
				}else if(giaMin==20 && giaMax==30) {
					m.addAttribute("giaTimKiem","Giá từ 2 - 3 triệu");
				}else if(giaMin==30 && giaMax==40) {
					m.addAttribute("giaTimKiem","Giá từ 3 - 4 triệu");
				}else if(giaMin==40 && giaMax==50) {
					m.addAttribute("giaTimKiem","Giá từ 4 - 5 triệu");
				}else if(giaMin==50 && giaMax==100) {
					m.addAttribute("giaTimKiem","Giá từ 5 - 10 triệu");
				}else if(giaMin==100 && giaMax==0) {
					m.addAttribute("giaTimKiem","Giá trên 10 triệu");
				}
				if(idhieusp==0) {
					m.addAttribute("hieuTimKiem","Tất cả các hiệu sản phẩm");
				}else {
					m.addAttribute("hieuTimKiem",hieuspModel.getHieuSpById(idhieusp).getTenHieuSp());
				}
				if(Integer.parseInt(idsapxeptheo)==0) {
					m.addAttribute("sapxepTheo","Sắp xếp theo mới nhất");
				}else if(Integer.parseInt(idsapxeptheo)==1) {
					m.addAttribute("sapxepTheo","Sắp xếp theo cũ nhất");
				}else if(Integer.parseInt(idsapxeptheo)==2) {
					m.addAttribute("sapxepTheo","Sắp xếp theo giá thấp - cao");
				}else if(Integer.parseInt(idsapxeptheo)==3) {
					m.addAttribute("sapxepTheo","Sắp xếp theo giá cao - thấp");
				}
				m.addAttribute("giamin",request.getParameter("gia-min"));
				m.addAttribute("giamax",request.getParameter("gia-max"));
				m.addAttribute("idhieusp",idhieusp);
				m.addAttribute("idsapxepTheo",Integer.parseInt(idsapxeptheo));
				return "search";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:home";
		}
	}
	
	@RequestMapping(value="/searchajax",method=RequestMethod.POST)
	public String searchajax(@RequestParam(value="key")String keyword,@RequestParam(value="idsapxeptheo")String idsapxeptheo,ServletRequest request,@RequestParam(value="giamin")Double giaMin,@RequestParam(value="giamax")Double giaMax,@RequestParam(value="idhieusp")int idhieusp,Model m) {
		try {
			if(keyword.equals("")) {
				return "redirect:home";
			}else {
				DanhMucSPDao danhmucspModel = new DanhMucSPDaoImpl();
				SanPhamDao spdao = new SanPhamDaoImpl();
				HieuSPDao hieuspModel = new HieuSPDaoImpl();
				
				
				/*Bắt đầu phân trang*/
				int limit = 8;
				int selectPage = 0;
				if(request.getParameter("pageid")!=null) {
					selectPage = Integer.parseInt(request.getParameter("pageid"));
				}
				int start = spdao.findStart(limit, selectPage);
				int count = spdao.getSPTimKiem(keyword, giaMin, giaMax, idhieusp).size();
				int pages = spdao.findPages(count, limit);
				//System.out.println("start "+start); //Vị trí bắt đầu
				//System.out.println("count "+count); //Tổng số sản phẩm
				//System.out.println("pages "+pages); //Tổng số trang
				/*Kết thúc phân trang*/
				
				
				
				m.addAttribute("dmspList",danhmucspModel.getAll());
				m.addAttribute("hieuspList",hieuspModel.getAll());
				m.addAttribute("keyword",keyword);
				m.addAttribute("soluongKQ",spdao.getSPTimKiem(keyword, giaMin, giaMax, idhieusp).size());
				m.addAttribute("thongtinsearch",spdao.getSPSearchTheoPage(keyword,Integer.parseInt(idsapxeptheo),giaMin,giaMax,idhieusp,start,limit));
				m.addAttribute("PageCount",pages);
				if(selectPage==0) {
					selectPage=1;
				}
				m.addAttribute("selectPage",selectPage);
				if(giaMin==0 && giaMax==0) {
					m.addAttribute("giaTimKiem","Tất cả mệnh giá");
				}else if(giaMin==0 && giaMax==5) {
					m.addAttribute("giaTimKiem","Giá dưới 500k");
				}else if(giaMin==5 && giaMax==10) {
					m.addAttribute("giaTimKiem","Giá từ 500k - 1 triệu");
				}else if(giaMin==10 && giaMax==20) {
					m.addAttribute("giaTimKiem","Giá từ 1 - 2 triệu");
				}else if(giaMin==20 && giaMax==30) {
					m.addAttribute("giaTimKiem","Giá từ 2 - 3 triệu");
				}else if(giaMin==30 && giaMax==40) {
					m.addAttribute("giaTimKiem","Giá từ 3 - 4 triệu");
				}else if(giaMin==40 && giaMax==50) {
					m.addAttribute("giaTimKiem","Giá từ 4 - 5 triệu");
				}else if(giaMin==50 && giaMax==100) {
					m.addAttribute("giaTimKiem","Giá từ 5 - 10 triệu");
				}else if(giaMin==100 && giaMax==0) {
					m.addAttribute("giaTimKiem","Giá trên 10 triệu");
				}
				if(idhieusp==0) {
					m.addAttribute("hieuTimKiem","Tất cả các hiệu sản phẩm");
				}else {
					m.addAttribute("hieuTimKiem",hieuspModel.getHieuSpById(idhieusp).getTenHieuSp());
				}
				if(Integer.parseInt(idsapxeptheo)==0) {
					m.addAttribute("sapxepTheo","Sắp xếp theo mới nhất");
				}else if(Integer.parseInt(idsapxeptheo)==1) {
					m.addAttribute("sapxepTheo","Sắp xếp theo cũ nhất");
				}else if(Integer.parseInt(idsapxeptheo)==2) {
					m.addAttribute("sapxepTheo","Sắp xếp theo giá thấp - cao");
				}else if(Integer.parseInt(idsapxeptheo)==3) {
					m.addAttribute("sapxepTheo","Sắp xếp theo giá cao - thấp");
				}
				m.addAttribute("giamin",request.getParameter("gia-min"));
				m.addAttribute("giamax",request.getParameter("gia-max"));
				m.addAttribute("idhieusp",idhieusp);
				m.addAttribute("idsapxepTheo",Integer.parseInt(idsapxeptheo));
				return "search/center-side";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:home";
		}
	}
	
}
