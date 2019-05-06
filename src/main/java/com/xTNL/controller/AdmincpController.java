package com.xTNL.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mysql.jdbc.Blob;
import com.xTNL.Dao.AdminDatHangDao;
import com.xTNL.Dao.BinhLuanDao;
import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.Dao.DoanhThuDao;
import com.xTNL.Dao.HieuSPDao;
import com.xTNL.Dao.SanPhamDao;
import com.xTNL.Dao.UsersDao;
import com.xTNL.DaoImpl.AdminDatHangImpl;
import com.xTNL.DaoImpl.BinhLuanDaoImpl;
import com.xTNL.DaoImpl.DanhMucSPDaoImpl;
import com.xTNL.DaoImpl.DoanhThuDaoImpl;
import com.xTNL.DaoImpl.HieuSPDaoImpl;
import com.xTNL.DaoImpl.SanPhamDaoImpl;
import com.xTNL.DaoImpl.UsersDaoImpl;
import com.xTNL.entities.Binhluan;
import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Danhmucsp;
import com.xTNL.entities.Danhmucsp2;
import com.xTNL.entities.Dathang;
import com.xTNL.entities.Gallery;
import com.xTNL.entities.Hieusp;
import com.xTNL.entities.Users;
import com.xTNL.utils.MySessionFactory;
import com.xTNL.utils.PhanTrangUtil;

import javassist.compiler.ast.Symbol;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class AdmincpController {
	private static final Logger logger = LoggerFactory.getLogger(AdmincpController.class);
	@Autowired
	ServletContext context;
	
	/*Bắt đầu chức năng admin - Quản lý bình luận*/
	@RequestMapping(value="admincp/quanlybinhluan",method=RequestMethod.GET)
	public String quanlybinhluan(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				return "admincp/QuanLyBinhLuan/quanlybinhluan";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/quanlybinhluanajax",method=RequestMethod.POST)
	public String quanlybinhluanajax(Model m,HttpSession session,HttpServletRequest request) { //Quản lý bình luận AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				BinhLuanDao bldao = new BinhLuanDaoImpl();
				int limit = 5;
				int selectPage = 0;
				if(request.getParameter("pageid")!=null) {
					selectPage = Integer.parseInt(request.getParameter("pageid"));
				}
				int start = PhanTrangUtil.findStart(limit, selectPage);
				int count = 0;
				String status = request.getParameter("status");
				if(status.equals("all")) {
					count = bldao.getAll().size();
				}else if(status.equals("wait")) {
					count = bldao.getAllBinhLuanByStatus(status).size();
				}else if(status.equals("ok")) {
					count = bldao.getAllBinhLuanByStatus(status).size();
				}
				int pages = PhanTrangUtil.findPages(count, limit);
				if(selectPage==0) {
					selectPage=1;
				}
				m.addAttribute("PageCount",pages);
				m.addAttribute("selectPage",selectPage);
				m.addAttribute("TongsoBinhLuan",count);
				if(status.equals("all")) {
					m.addAttribute("ListBinhLuanSP", bldao.getAllBinhLuanTheoPage(start, limit));
				}else if(status.equals("wait")){
					m.addAttribute("ListBinhLuanSP", bldao.getAllBinhLuanByStatusTheoPage(status,start, limit));
				}else if(status.equals("ok")){
					m.addAttribute("ListBinhLuanSP", bldao.getAllBinhLuanByStatusTheoPage(status,start, limit));
				}
				return "admincp/AJAX/QuanLyBinhLuan/quanlybinhluanajax";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/duyetbinhluan",method=RequestMethod.GET)
	public String duyetbinhluan(Model m,@RequestParam(value="id")String id,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				BinhLuanDao bldao = new BinhLuanDaoImpl();
				bldao.DuyetBinhLuanById(Integer.parseInt(id));
				return "redirect:quanlybinhluan";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xoabinhluan",method=RequestMethod.GET)
	public String xoabinhluan(Model m,@RequestParam(value="id")String id,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				BinhLuanDao bldao = new BinhLuanDaoImpl();
				bldao.XoaBinhLuanById(Integer.parseInt(id));
				return "redirect:quanlybinhluan";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/suabinhluan",method=RequestMethod.GET)
	public String suabinhluan(@RequestParam(value="id")String id,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				BinhLuanDao bldao = new BinhLuanDaoImpl();
				m.addAttribute("BinhLuanById",bldao.getBinhLuanByIdBinhLuan(Integer.parseInt(id)));
				return "admincp/QuanLyBinhLuan/suabinhluan";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/suabinhluan",method=RequestMethod.POST)
	public String suabinhluan(@ModelAttribute("editcomment")Binhluan bl,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				BinhLuanDao bldao = new BinhLuanDaoImpl();
				bldao.update(bl);
				return "redirect:quanlybinhluan";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng admin - Quản lý bình luận*/
	
	/*Bắt đầu chức năng admin - Quản lý nhân viên - Chức năng này chỉ dành cho Administrator*/
	@RequestMapping(value="admincp/quanlynhanvien",method=RequestMethod.GET)
	public String quanlynhanvien(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					return "admincp/QuanLyNhanVien/quanlynhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/quanlynhanvienajax",method=RequestMethod.POST)
	public String quanlynhanvienajax(Model m,HttpSession session,HttpServletRequest request) { //Quản lý nhân viên - AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					int limit = 5;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count = 0;
					String status = request.getParameter("status");
					String key = request.getParameter("key");
					if(key=="") {
						if(status.equals("all")) {
							count = usersdao.getAll().size();
						}else if(status.equals("blocked")) {
							count = usersdao.getAllByStatus(status).size();
						}else if(status.equals("ok")) {
							count = usersdao.getAllByStatus(status).size();
						}
					}else {
						if(status.equals("all")) {
							count = usersdao.getAllByNameUser(key).size();
						}else if(status.equals("blocked")) {
							count = usersdao.getAllByNameStatus(key,status).size();
						}else if(status.equals("ok")) {
							count = usersdao.getAllByNameStatus(key,status).size();
						}
					}
					
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoUser",count);
					if(key=="") {
						if(status.equals("all")) {
							m.addAttribute("ListUsers", usersdao.getAllUsersTheoPage(start, limit));
						}else if(status.equals("blocked")){
							m.addAttribute("ListUsers", usersdao.getAllUsersByStatusTheoPage(status,start, limit));
						}else if(status.equals("ok")){
							m.addAttribute("ListUsers", usersdao.getAllUsersByStatusTheoPage(status,start, limit));
						}
					}else {
						if(status.equals("all")) {
							m.addAttribute("ListUsers", usersdao.getAllUsersByKeyTheoPage(key,start, limit));
						}else if(status.equals("blocked")){
							m.addAttribute("ListUsers", usersdao.getAllUsersByKeyStatusTheoPage(key,status,start, limit));
						}else if(status.equals("ok")){
							m.addAttribute("ListUsers", usersdao.getAllUsersByKeyStatusTheoPage(key,status,start, limit));
						}
					}
					return "admincp/AJAX/QuanLyNhanVien/quanlynhanvienajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/blocknhanvien",method=RequestMethod.GET)
	public String blockuser(Model m,@RequestParam(value="id")String id,HttpSession session) { //Khóa tài khoản nhân viên
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					usersdao.BlockUnblockUserById(Integer.parseInt(id), "blocked");
					return "redirect:quanlynhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/unblocknhanvien",method=RequestMethod.GET)
	public String unblockuser(Model m,@RequestParam(value="id")String id,HttpSession session) { //Mở khóa tài khoản nhân viên
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					usersdao.BlockUnblockUserById(Integer.parseInt(id), "ok");
					return "redirect:quanlynhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/themnhanvien",method=RequestMethod.GET) //Thêm nhân viên
	public String themnhanvien(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					return "admincp/QuanLyNhanVien/themnhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
		
	}
	@RequestMapping(value="admincp/themnhanvien",method=RequestMethod.POST) //Thêm nhân viên
	public String themnhanvien(@ModelAttribute("adduser")Users u,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					usersdao.create(u);
					return "redirect:quanlynhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xoanhanvien",method=RequestMethod.GET)
	public String xoanhanvien(Model m,@RequestParam(value="id")String id,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					usersdao.XoaUserById(Integer.parseInt(id));
					return "redirect:quanlynhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/suanhanvien",method=RequestMethod.GET) //Sửa nhân viên
	public String suanhanvien(@RequestParam(value="id")String id,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					m.addAttribute("UserById",usersdao.getUserById(Integer.parseInt(id)));
					return "admincp/QuanLyNhanVien/suanhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
		
	}
	@RequestMapping(value="admincp/suanhanvien",method=RequestMethod.POST) //Sửa nhân viên
	public String suanhanvien(@ModelAttribute("edituser")Users u,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					usersdao.update(u);
					return "redirect:quanlynhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/lichsuxoanhanvien",method=RequestMethod.GET)
	public String lichsuxoanhanvien(Model m,HttpSession session) { //Xem lich sử các nhân viên bị xóa
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					return "admincp/QuanLyNhanVien/lichsuxoanhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/lichsuxoanhanvienajax",method=RequestMethod.POST)
	public String lichsuxoanhanvienajax(Model m,HttpSession session,HttpServletRequest request) { //Xem lich sử các nhân viên bị xóa
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					int limit = 5;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count;
					String key = request.getParameter("key");
					if(key=="") {
						count = usersdao.getAllDeleted().size();
					}else {
						count = usersdao.getAllByNameStatus(key,"deleted").size();
					}
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoUser",count);
					if(key=="") {
						m.addAttribute("ListUsersDeleted", usersdao.getAllUsersByStatusTheoPage("deleted",start, limit));
					}else {
						m.addAttribute("ListUsersDeleted", usersdao.getAllUsersByKeyStatusTheoPage(key,"deleted",start, limit));
					}
					return "admincp/AJAX/QuanLyNhanVien/lichsuxoanhanvienajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/xoalichsunhanvien",method=RequestMethod.GET)
	public String xoalichsunhanvien(@RequestParam(value="id")String id,Model m,HttpSession session) { //Xem lich sử các nhân viên bị xóa
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					usersdao.remove(usersdao.getUserById(Integer.parseInt(id)));
					return "redirect:lichsuxoanhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xoatatcalichsunhanvien",method=RequestMethod.GET)
	public String xoatatcalichsunhanvien(Model m,HttpSession session) { //Xem lich sử các nhân viên bị xóa
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					UsersDao usersdao = new UsersDaoImpl();
					usersdao.removeAlluserdeleted();
					return "redirect:lichsuxoanhanvien";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng admin - Quản lý nhân viên - Chức năng này chỉ dành cho Administrator*/
	
	/*Bắt đầu chức năng admin - Quản lý hiệu sản phẩm - Chức năng này chỉ dành cho Administrator*/
	@RequestMapping(value="admincp/quanlyhieusanpham",method=RequestMethod.GET)
	public String quanlyhieusanpham(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					return "admincp/QuanLyHieuSanPham/quanlyhieusanpham";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/quanlyhieusanphamajax",method=RequestMethod.POST)
	public String quanlyhieusanpham(Model m,HttpSession session,HttpServletRequest request) { //Quản lý hiệu sản phẩm AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					HieuSPDao hieuspdao = new HieuSPDaoImpl();
					int limit = 7;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count = 0;
					String key = request.getParameter("key");
					if(key.equals("")) {
						count = hieuspdao.getAll().size();
					}else {
						count = hieuspdao.getAllHieuSPByName(key).size();
					}
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoHieuSP",count);
					if(key.equals("")) {
						m.addAttribute("ListHieuSP", hieuspdao.getAllHieuSPTheoPage(start, limit));
					}else {
						m.addAttribute("ListHieuSP", hieuspdao.getAllHieuSPByNameTheoPage(key,start, limit));
					}
					return "admincp/AJAX/QuanLyHieuSanPham/quanlyhieusanphamajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/themhieusanpham",method=RequestMethod.GET)
	public String themhieusanpham(Model m,HttpSession session) { //thêm hiệu sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					HieuSPDao hieuspdao = new HieuSPDaoImpl();
					m.addAttribute("getMaxsortID",hieuspdao.getSortIdMax());
					return "admincp/QuanLyHieuSanPham/themhieusanpham";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index"; 
		}
	}
	@RequestMapping(value="admincp/themhieusanpham",method=RequestMethod.POST)
	public String themhieusanpham(@ModelAttribute("addhieusanpham")Hieusp hieusp,Model m,HttpSession session) { //thêm hiệu sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					HieuSPDao hieuspdao = new HieuSPDaoImpl();
					hieuspdao.create(hieusp);
					return "redirect:quanlyhieusanpham";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/suahieusanpham",method=RequestMethod.GET) //sửa hiệu sản phẩm
	public String suahieusanpham(@RequestParam(value="id")String id,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					HieuSPDao hieuspdao = new HieuSPDaoImpl();
					m.addAttribute("HieuspByID", hieuspdao.getHieuSpById(Integer.parseInt(id)));
					return "admincp/QuanLyHieuSanPham/suahieusanpham";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/suahieusanpham",method=RequestMethod.POST)
	public String suahieusanpham(@ModelAttribute("edithieusanpham")Hieusp hieusp,Model m,HttpSession session) { //sửa hiệu sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					HieuSPDao hieuspdao = new HieuSPDaoImpl();				
					hieuspdao.update(hieusp);
					return "redirect:quanlyhieusanpham";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/xoahieusanpham",method=RequestMethod.GET)
	public String xoahieusanpham(Model m,@RequestParam(value="id")String id,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					HieuSPDao hieuspdao = new HieuSPDaoImpl();	
					hieuspdao.XoaHieuSpById(Integer.parseInt(id));
					return "redirect:quanlyhieusanpham";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng admin - Quản lý hiệu sản phẩm - Chức năng này chỉ dành cho Administrator*/
		
	/*Bắt đầu chức năng admin - Quản lý danh mục sản phẩm 1 - Chức năng này chỉ dành cho Administrator*/
	@RequestMapping(value="admincp/quanlydanhmuc",method=RequestMethod.GET)
	public String quanlydanhmuc(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					return "admincp/QuanLyDanhMuc/quanlydanhmuc";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/quanlydanhmucajax",method=RequestMethod.POST)
	public String quanlydanhmucajax(HttpServletRequest request,Model m,HttpSession session) { //Quản lý danh mục AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					DanhMucSPDao danhmucspdao = new DanhMucSPDaoImpl();
					int limit = 7;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count = 0;
					String key = request.getParameter("key");
					if(key.equals("")) {
						count = danhmucspdao.getAll().size();
					}else {
						count = danhmucspdao.getAllByName(key).size();
					}
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoDanhmuc",count);
					if(key.equals("")) {
						m.addAttribute("ListDanhMuc", danhmucspdao.getAllDanhMucTheoPage(start, limit));
					}else {
						m.addAttribute("ListDanhMuc", danhmucspdao.getAllDanhMucByNameTheoPage(key,start, limit));
					}
					return "admincp/AJAX/QuanLyDanhMuc/quanlydanhmucajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/themdanhmuc",method=RequestMethod.GET)
	public String themdanhmuc(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspDao = new DanhMucSPDaoImpl();
					m.addAttribute("getMaxsortID",dmspDao.getSortIdMax());
					return "admincp/QuanLyDanhMuc/themdanhmuc";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/themdanhmuc",method=RequestMethod.POST)
	public String themdanhmuc(@ModelAttribute(value="addDanhmucsp")Danhmucsp dmsp,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspDao = new DanhMucSPDaoImpl();
					dmspDao.create(dmsp);
					return "redirect:quanlydanhmuc";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/suadanhmuc",method=RequestMethod.GET) //sửa danh mục sản phẩm
	public String suadanhmuc(@RequestParam(value="id")String id,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspdao = new DanhMucSPDaoImpl();
					m.addAttribute("DanhmucspByID", dmspdao.getDanhMucById(Integer.parseInt(id)));
					return "admincp/QuanLyDanhMuc/suadanhmuc";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/suadanhmuc",method=RequestMethod.POST)
	public String suadanhmuc(@ModelAttribute("editdanhmuc")Danhmucsp dmsp,Model m,HttpSession session) { //sửa danh mục sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspdao = new DanhMucSPDaoImpl();
					dmspdao.update(dmsp);
					return "redirect:quanlydanhmuc";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xoadanhmuc",method=RequestMethod.GET)
	public String xoadanhmuc(Model m,@RequestParam(value="id")String id,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspdao = new DanhMucSPDaoImpl();
					dmspdao.XoaDanhmucspById(Integer.parseInt(id));
					return "redirect:quanlydanhmuc";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng admin - Quản lý danh mục sản phẩm 1 - Chức năng này chỉ dành cho Administrator*/
	
	/*Bắt đầu chức năng admin - Quản lý danh mục sản phẩm 2 - Chức năng này chỉ dành cho Administrator*/
	@RequestMapping(value="admincp/quanlydanhmuc2",method=RequestMethod.GET)
	public String quanlydanhmuc2(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					return "admincp/QuanLyDanhMuc2/quanlydanhmuc2";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/quanlydanhmuc2ajax",method=RequestMethod.POST)
	public String quanlydanhmuc2ajax(HttpServletRequest request,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					DanhMucSPDao danhmucspdao = new DanhMucSPDaoImpl();
					int limit = 7;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count = 0;
					String key = request.getParameter("key");
					if(key.equals("")) {
						count = danhmucspdao.getAll2().size();
					}else {
						count = danhmucspdao.getAllDm2ByName(key).size();
					}
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoDanhmuc2",count);
					if(key.equals("")) {
						m.addAttribute("ListDanhMuc2", danhmucspdao.getAllDanhMuc2TheoPage(start, limit));
					}else {
						m.addAttribute("ListDanhMuc2", danhmucspdao.getAllDanhMuc2ByNameTheoPage(key,start, limit));
					}	
					return "admincp/AJAX/QuanLyDanhMuc/quanlydanhmuc2ajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/themdanhmuc2",method=RequestMethod.GET)
	public String themdanhmuc2(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao danhmucspdao = new DanhMucSPDaoImpl();
					m.addAttribute("ListDanhMuc", danhmucspdao.getAll());
					return "admincp/QuanLyDanhMuc2/themdanhmuc2";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/themdanhmuc2",method=RequestMethod.POST)
	public String themdanhmuc2(@ModelAttribute(value="addDanhmucsp2")Danhmucsp2 dmsp2,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao danhmucspdao = new DanhMucSPDaoImpl();
					danhmucspdao.create2(dmsp2);
					return "redirect:quanlydanhmuc2";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/suadanhmuc2",method=RequestMethod.GET) //sửa danh mục sản phẩm
	public String suadanhmuc2(@RequestParam(value="id")String id,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspdao = new DanhMucSPDaoImpl();
					m.addAttribute("ListDanhMuc", dmspdao.getAll());
					m.addAttribute("DanhmucspByID2", dmspdao.getDanhMucById2(Integer.parseInt(id)));
					return "admincp/QuanLyDanhMuc2/suadanhmuc2";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/suadanhmuc2",method=RequestMethod.POST)
	public String suadanhmuc2(@ModelAttribute("editdanhmuc2")Danhmucsp2 dmsp2,Model m,HttpSession session) { //sửa danh mục 2 sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspdao = new DanhMucSPDaoImpl();
					dmspdao.update2(dmsp2);
					return "redirect:quanlydanhmuc2";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/xoadanhmuc2",method=RequestMethod.GET)
	public String xoadanhmuc2(Model m,@RequestParam(value="id")String id,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					DanhMucSPDao dmspdao = new DanhMucSPDaoImpl();
					dmspdao.XoaDanhmucspById2(Integer.parseInt(id));
					return "redirect:quanlydanhmuc2";
				}else {
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng admin - Quản lý danh mục sản phẩm 2 - Chức năng này chỉ dành cho Administrator*/
	
	/*Bắt đầu chức năng Quản lý sản phẩm*/
	@RequestMapping(value="admincp/quanlysanpham",method=RequestMethod.GET)
	public String quanlysanpham(Model m,HttpSession session,HttpServletRequest request) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				HieuSPDaoImpl hieusp = new HieuSPDaoImpl();
				m.addAttribute("hieuspList", hieusp.getAll());
				return "admincp/QuanLySanPham/quanlysanpham";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/quanlysanphamajax",method=RequestMethod.POST)
	public String quanlysanphamajax(Model m,HttpSession session,HttpServletRequest request) { //Quản lý sản phẩm AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();
				BinhLuanDao bldao = new BinhLuanDaoImpl();
				String idsapxeptheo = request.getParameter("idsapxeptheo");
				String giaMin = request.getParameter("giaMin");
				String giaMax = request.getParameter("giaMax");
				String idHieuSp = request.getParameter("idHieuSp");
				String key = request.getParameter("key");
				int limit = 2;
				int selectPage = 0;
				if(request.getParameter("pageid")!=null) {
					selectPage = Integer.parseInt(request.getParameter("pageid"));
				}
				int start = spdao.findStart(limit, selectPage);
				int count;
				if(key=="") {
					count = spdao.getAllSPByDieuKien(Integer.parseInt(idsapxeptheo),Double.parseDouble(giaMin),Double.parseDouble(giaMax),Integer.parseInt(idHieuSp)).size();
				}else {
					count = spdao.getSPTimKiem(key,Double.parseDouble(giaMin),Double.parseDouble(giaMax),Integer.parseInt(idHieuSp)).size();
				}
				int pages = spdao.findPages(count, limit);
				if(selectPage==0) {
					selectPage=1;
				}
				
				
				m.addAttribute("PageCount",pages);
				m.addAttribute("selectPage",selectPage);
				m.addAttribute("TongsoSP",count);
				if(key=="") {
					m.addAttribute("ListSanPham",spdao.getSPTheoPage(Integer.parseInt(idsapxeptheo),Double.parseDouble(giaMin),Double.parseDouble(giaMax),Integer.parseInt(idHieuSp),start, limit));
				}else {
					m.addAttribute("ListSanPham",spdao.getSPSearchTheoPage(key,Integer.parseInt(idsapxeptheo),Double.parseDouble(giaMin),Double.parseDouble(giaMax),Integer.parseInt(idHieuSp),start, limit));
				}
				return "admincp/AJAX/QuanLySanPham/quanlysanphamajax";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	
	@RequestMapping(value="admincp/themsanpham",method=RequestMethod.GET)
	public String themsanpham(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				HieuSPDao hieuspdao = new HieuSPDaoImpl();
				DanhMucSPDao dmdao = new DanhMucSPDaoImpl();
				m.addAttribute("ListDanhMuc1",dmdao.getAll());
				m.addAttribute("ListDanhMuc2",dmdao.getAll2());
				m.addAttribute("ListHieuSP", hieuspdao.getAll());
				m.addAttribute("IdUser",session.getAttribute("id"));
				return "admincp/QuanLySanPham/themsanpham";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value = "admincp/themsanpham", method = RequestMethod.POST)
	public String themsanpham(@Valid @ModelAttribute("addsanpham")Chitietsp ctsp,@RequestParam("file")MultipartFile file,HttpServletRequest request,Model m,HttpSession session) {// Thêm sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(file.isEmpty()) {
					System.out.println("Vui long chon file");
				}else {
					SanPhamDao spdao = new SanPhamDaoImpl();
					try {
						String date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
						String rootDirectory = request.getSession().getServletContext().getRealPath("/");
						String getDuoiFile = file.getContentType();
						String duoiFile=getDuoiFile.substring(getDuoiFile.lastIndexOf("/")).replace("/","");
						file.transferTo(new File(rootDirectory+"resources\\images\\"+date+"."+duoiFile));
						//String rootDirectorySql = rootDirectory.replace("\\", "/");
						//ctsp.setImage(rootDirectorySql+"resources/images/"+date+"."+duoiFile);
						//System.out.println(rootDirectory+"resources\\images");
						ctsp.setImage("images/"+date+"."+duoiFile);
						spdao.create(ctsp);
					}catch(Exception ex) {
						System.out.println("Lỗi lưu file");
						ex.printStackTrace();
					}
				}
				return "redirect:quanlysanpham";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/danhmucsp2ajax",method=RequestMethod.POST)
	public String danhmucsp2ajax(Model m,@RequestParam(value="id")String iddanhmuc,HttpSession session) { //Hàm cho phép chọn danh mục 1 => đổ danh mục 2 tương ứng AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				DanhMucSPDao dmdao = new DanhMucSPDaoImpl();
				m.addAttribute("ListDanhMuc2",dmdao.getAlldmsp2ByIddmsp(Integer.parseInt(iddanhmuc)));
				return "admincp/AJAX/QuanLySanPham/danhmucsp2ajax";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/suasanpham",method=RequestMethod.GET)
	public String suasanpham(@RequestParam(value="id")String id,Model m , HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();
				DanhMucSPDao dmdao = new DanhMucSPDaoImpl();
				HieuSPDao hieuspdao = new HieuSPDaoImpl();
				m.addAttribute("chitietsp",spdao.showThongTinByID2(Integer.parseInt(id)));
				m.addAttribute("ListDanhMuc1",dmdao.getAll());
				m.addAttribute("ListDanhMuc2",dmdao.getAll2());
				m.addAttribute("ListHieuSP", hieuspdao.getAll());
				return "admincp/QuanLySanPham/suasanpham";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/suasanpham",method=RequestMethod.POST)
	public String suasanpham(@Valid @ModelAttribute("editsanpham")Chitietsp ctsp,@RequestParam("file")MultipartFile file,Model m,HttpSession session,HttpServletRequest request) { //sửa sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();
				if(file.isEmpty()) {
					System.out.println("Không chọn ảnh, Ảnh cũ sẽ được giữ nguyên");
				}else {
					System.out.println("update ảnh mới");
					String rootDirectory = request.getSession().getServletContext().getRealPath("/")+"resources\\";
					String duongdanFile = ctsp.getImage();
					//duongdanFile = duongdanFile.replace("/", "\\");
					Files.deleteIfExists(Paths.get(rootDirectory+duongdanFile)); //Xóa ảnh từ đường dẫn trong db nếu tồn tại
					
					String date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
					String getDuoiFile = file.getContentType();
					String duoiFile=getDuoiFile.substring(getDuoiFile.lastIndexOf("/")).replace("/","");
					file.transferTo(new File(rootDirectory+"images\\"+date+"."+duoiFile));
					//String rootDirectorySql = rootDirectory.replace("\\", "/");
					//ctsp.setImage(rootDirectorySql+"resources/images/"+date+"."+duoiFile);
					ctsp.setImage("images/"+date+"."+duoiFile);
				}
				spdao.update(ctsp);
				return "redirect:quanlysanpham";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/xoasanpham",method=RequestMethod.GET)
	public String xoasanpham(Model m,@RequestParam(value="id")String id,HttpSession session,HttpServletRequest request) { //Xóa sản phẩm
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				String rootDirectory = request.getSession().getServletContext().getRealPath("/")+"resources\\";
//				System.out.println(rootDirectory);
				SanPhamDao spdao = new SanPhamDaoImpl();
				Chitietsp sp = new Chitietsp();
				sp=spdao.showThongTinByID2(Integer.parseInt(id));
				String duongdanFile = sp.getImage();
//				duongdanFile = duongdanFile.replace("/", "\\");
				Files.deleteIfExists(Paths.get(rootDirectory+duongdanFile)); //Xóa ảnh từ đường dẫn trong db nếu tồn tại
				
				//Xóa all ảnh gallery tương ứng nếu xóa sản phẩm đó
				Gallery gl = new Gallery();
				List<Gallery> ListImageGallery = spdao.getAllGallery(Integer.parseInt(id));
				for(int i=0;i<ListImageGallery.size();i++) {
					String duongdanFile2 = ListImageGallery.get(i).getImages();
					Files.deleteIfExists(Paths.get(rootDirectory+duongdanFile2)); //Xóa ảnh từ đường dẫn trong db nếu tồn tại
				}
				spdao.remove(spdao.showThongTinByID2(Integer.parseInt(id)));
				return "redirect:quanlysanpham";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng Quản lý sản phẩm*/	
	
	
	
	/*Bắt đầu chức năng quản lý gallery*/
	@RequestMapping(value="admincp/quanlygallery",method=RequestMethod.GET)
	public String quanlygallery(@RequestParam(value="id")String id,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				return "admincp/QuanLyGallery/quanlygallery";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/quanlygalleryajax",method=RequestMethod.POST)
	public String quanlygalleryajax(@RequestParam(value="id")String id,Model m,HttpSession session,HttpServletRequest request) { //Quản lý Gallery AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();
				int limit = 2;
				int selectPage = 0;
				if(request.getParameter("pageid")!=null) {
					selectPage = Integer.parseInt(request.getParameter("pageid"));
				}
				int start = PhanTrangUtil.findStart(limit, selectPage);
				int count = spdao.getAllGallery(Integer.parseInt(id)).size();
				int pages = PhanTrangUtil.findPages(count, limit);
				if(selectPage==0) {
					selectPage=1;
				}
				m.addAttribute("PageCount",pages);
				m.addAttribute("selectPage",selectPage);
				m.addAttribute("TongsoGallery",count);
				m.addAttribute("chitietsp",spdao.showThongTinByID(Integer.parseInt(id)));
				m.addAttribute("getAllGallerry",spdao.getAllGalleryTheoPage(Integer.parseInt(id),start,limit));
				return "admincp/AJAX/QuanLyGallery/quanlygalleryajax";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/themgallery",method=RequestMethod.GET)
	public String themgallery(@RequestParam(value="id")String id,Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();
				m.addAttribute("chitietsp",spdao.showThongTinByID(Integer.parseInt(id)));
				return "admincp/QuanLyGallery/themgallery";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/themgallery",method=RequestMethod.POST)
	public String themgallery(@Valid @ModelAttribute(value="addgallery")Gallery gallery,@RequestParam(value="files")MultipartFile[] files,Model m,HttpSession session,HttpServletRequest request) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();

//				if(files.length==1) {
//					System.out.println("Vui lòng chọn nhiều hơn 1 ảnh");
//				}else {
					for (int i = 0; i < files.length; i++) {
						MultipartFile file = files[i];
						try {
							String date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());//Lấy thời gian hiện tại làm tên file ảnh
							String rootDirectory = request.getSession().getServletContext().getRealPath("/");
							String getDuoiFile = file.getContentType();
							String duoiFile=getDuoiFile.substring(getDuoiFile.lastIndexOf("/")).replace("/","");
							file.transferTo(new File(rootDirectory+"resources\\images\\"+date+"_"+i+"."+duoiFile));
							//String rootDirectorySql = rootDirectory.replace("\\", "/");
							//gallery.setImages(rootDirectorySql+"resources/images/"+date+"_"+i+"."+duoiFile);
							gallery.setImages("images/"+date+"_"+i+"."+duoiFile);
							spdao.create(gallery);
						} catch (Exception ex) {
							ex.printStackTrace();
							return "redirect:index";
						}
					//}
					System.out.println("Thêm ảnh thành công");
				}
			    return "redirect:quanlysanpham";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xoagallery",method=RequestMethod.GET)
	public String xoagallery(@RequestParam(value="id")String id,Model m,HttpSession session,HttpServletRequest request) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();
				//Xóa đường dẫn file ảnh
				Gallery gl = new Gallery();
				String rootDirectory = request.getSession().getServletContext().getRealPath("/")+"resources\\";
				gl=spdao.ShowGalleryByIDGallery(Integer.parseInt(id));
				String duongdanFile = gl.getImages();
				//duongdanFile = duongdanFile.replace("/", "\\");
				Files.deleteIfExists(Paths.get(rootDirectory+duongdanFile)); //Xóa ảnh từ đường dẫn trong db nếu tồn tại
				
				spdao.XoaGalleryByIdGallery(Integer.parseInt(id));
				String referer = request.getHeader("Referer");
			    return "redirect:"+ referer;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xoaallgallery",method=RequestMethod.GET)
	public String xoaallgallery(@RequestParam(value="id")String id,Model m,HttpSession session,HttpServletRequest request) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				SanPhamDao spdao = new SanPhamDaoImpl();
				Gallery gl = new Gallery();
				List<Gallery> ListImageGallery = spdao.getAllGallery(Integer.parseInt(id));
				String rootDirectory = request.getSession().getServletContext().getRealPath("/")+"resources\\";
				for(int i=0;i<ListImageGallery.size();i++) {
					String duongdanFile = ListImageGallery.get(i).getImages();
					//duongdanFile = duongdanFile.replace("/", "\\");
					Files.deleteIfExists(Paths.get(rootDirectory+duongdanFile)); //Xóa ảnh từ đường dẫn trong db nếu tồn tại
				}
				spdao.XoaAllGalleryByIdSP(Integer.parseInt(id));
				String referer = request.getHeader("Referer");
			    return "redirect:"+ referer;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng quản lý gallery*/
	
	/*Bắt đầu chức năng quản lý đặt hàng*/
	@RequestMapping(value="admincp/quanlydathang",method=RequestMethod.GET)
	public String quanlydathang(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				return "admincp/QuanLyDatHang/quanlydathang";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/quanlydathangajax",method=RequestMethod.POST)
	public String quanlydathangajax(Model m,HttpSession session,HttpServletRequest request) { //Quản lý đặt hàng AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				AdminDatHangDao adminDatHangDao = new AdminDatHangImpl();
				int limit = 4;
				int selectPage = 0;
				if(request.getParameter("pageid")!=null) {
					selectPage = Integer.parseInt(request.getParameter("pageid"));
				}
				int start = PhanTrangUtil.findStart(limit, selectPage);
				int count = 0;
				count = adminDatHangDao.getAll().size();
				int pages = PhanTrangUtil.findPages(count, limit);
				if(selectPage==0) {
					selectPage=1;
				}
				m.addAttribute("PageCount",pages);
				m.addAttribute("selectPage",selectPage);
				m.addAttribute("TongsoDonDatHang",count);
				m.addAttribute("ListDatHang",adminDatHangDao.getAllDatHangTheoPage(start, limit));
				return "admincp/AJAX/QuanLyDatHang/quanlydathangajax";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xacnhandonhang",method=RequestMethod.GET)
	public String xacnhandonhang(Model m,HttpSession session,@RequestParam(value="id")String id) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				AdminDatHangDao adminDatHangDao = new AdminDatHangImpl();
				adminDatHangDao.setStatusByID(Integer.parseInt(id), "ok",(int)session.getAttribute("id"));
				return "redirect:quanlydathang";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/huydonhang",method=RequestMethod.GET)
	public String huydonhang(Model m,HttpSession session,@RequestParam(value="id")String id) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				AdminDatHangDao adminDatHangDao = new AdminDatHangImpl();
				adminDatHangDao.setStatusByID(Integer.parseInt(id), "deleted",(int)session.getAttribute("id"));
				return "redirect:quanlydathang";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/hoantatdonhang",method=RequestMethod.GET)
	public String hoantatdonhang(Model m,HttpSession session,@RequestParam(value="id")String id) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				AdminDatHangDao adminDatHangDao = new AdminDatHangImpl();
				adminDatHangDao.setStatusByID(Integer.parseInt(id), "success",(int)session.getAttribute("id"));
				return "redirect:quanlydathang";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/chitietdathang",method=RequestMethod.GET)
	public String chitietdathang(Model m,HttpSession session,@RequestParam(value="id")String id) { //Xem chi tiết các mặt hàng mà khách hàng đặt
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				return "admincp/QuanLyDatHang/chitietdathang";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/chitietdathangajax",method=RequestMethod.POST)
	public String chitietdathangajax(Model m,HttpSession session,@RequestParam(value="id")String id,HttpServletRequest request) { // Chi tiết đặt hàng AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				AdminDatHangDao adminDHdao = new AdminDatHangImpl();
				int limit = 3;
				int selectPage = 0;
				if(request.getParameter("pageid")!=null) {
					selectPage = Integer.parseInt(request.getParameter("pageid"));
				}
				int start = PhanTrangUtil.findStart(limit, selectPage);
				int count = 0;
				count = adminDHdao.getAllByIdDatHang(Integer.parseInt(id)).size();
				int pages = PhanTrangUtil.findPages(count, limit);
				if(selectPage==0) {
					selectPage=1;
				}
				m.addAttribute("PageCount",pages);
				m.addAttribute("selectPage",selectPage);
				m.addAttribute("TongsoHang",count);
				m.addAttribute("ListCTDH",adminDHdao.getAllByIdDatHangTheoPage(Integer.parseInt(id),start,limit));
				m.addAttribute("ThongTinDH",adminDHdao.getDatHangByID(Integer.parseInt(id)));
				return "admincp/AJAX/QuanLyDatHang/chitietdathangajax";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xemdonhangdahuy",method=RequestMethod.GET)
	public String xemdonhangdahuy(Model m,HttpSession session) { //Xem các đơn hàng đã hủy (Chỉ admin mới xem được)
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					return "admincp/QuanLyDatHang/xemdonhangdahuy";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/xemdonhangdahuyajax",method=RequestMethod.POST)
	public String xemdonhangdahuyajax(Model m,HttpSession session,HttpServletRequest request) { //Xem các đơn hàng đã hủy (Chỉ admin mới xem được) AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					AdminDatHangDao adminDatHangDao = new AdminDatHangImpl();
					int limit = 4;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count = 0;
					count = adminDatHangDao.getAllDonHangByStatus("deleted").size();
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoDonDatHang",count);
					m.addAttribute("ListDatHang",adminDatHangDao.getAllDatHangByStatusTheoPage("deleted",start, limit));
					return "admincp/AJAX/QuanLyDatHang/xemdonhangdahuyajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/xemdonhangdahoantat",method=RequestMethod.GET)
	public String xemdonhangdahoantat(Model m,HttpSession session) { //Xem các đơn hàng đã hoàn tất (Chỉ admin mới xem được)
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					return "admincp/QuanLyDatHang/xemdonhangdahoantat";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/xemdonhangdahoantatajax",method=RequestMethod.POST)
	public String xemdonhangdahoantatajax(Model m,HttpSession session,HttpServletRequest request) { //Xem các đơn hàng đã hoàn tất (Chỉ admin mới xem được) AJAX
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) {
					AdminDatHangDao adminDatHangDao = new AdminDatHangImpl();
					int limit = 4;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count = 0;
					count = adminDatHangDao.getAllDonHangByStatus("success").size();
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoDonDatHang",count);
					m.addAttribute("ListDatHang",adminDatHangDao.getAllDatHangByStatusTheoPage("success",start, limit));
					return "admincp/AJAX/QuanLyDatHang/xemdonhangdahoantatajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	/*Kết thúc chức năng quản lý đặt hàng*/
	
	/*Bắt đầu chức năng admin - Quản lý doanh thu - Chức năng này chỉ dành cho Administrator*/
	@RequestMapping(value="admincp/quanlydoanhthu",method=RequestMethod.GET)
	public String quanlydoanhthu(Model m,HttpSession session) {
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					return "admincp/QuanLyDoanhThu/quanlydoanhthu";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	@RequestMapping(value="admincp/quanlydoanhthuajax",method=RequestMethod.POST)
	public String quanlydoanhthuajax(Model m,HttpSession session,HttpServletRequest request) { //Search doanh thu theo toàn bộ thời gian
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					//if(date.equalsIgnoreCase("Toàn bộ thời gian")) {
						DoanhThuDao dtdao = new DoanhThuDaoImpl();
						String date1 = request.getParameter("date");
						String date2 = request.getParameter("date2");
						int limit = 3;
						int selectPage = 0;
						if(request.getParameter("pageid")!=null) {
							selectPage = Integer.parseInt(request.getParameter("pageid"));
						}
						int start = PhanTrangUtil.findStart(limit, selectPage);
						int count;
						if(date1.equals("")) {
							count = dtdao.getAll().size();
						}else {
							Date datea = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
							Date dateb = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
							count = dtdao.getAll(datea,dateb).size();
						}
						
						int pages = PhanTrangUtil.findPages(count, limit);
						if(selectPage==0) {
							selectPage=1;
						}
						m.addAttribute("PageCount",pages);
						m.addAttribute("selectPage",selectPage);
						m.addAttribute("TongsoDoanhThu",count);
						double tongTien=0;
						if(date1.equals("")) {
							for(int i=0;i<dtdao.getAll().size();i++) {
								tongTien+=dtdao.getAll().get(i).getTongTien();
							}
						}else {
							Date datea = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
							Date dateb = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
							for(int i=0;i<dtdao.getAll(datea,dateb).size();i++) {
								tongTien+=dtdao.getAll(datea,dateb).get(i).getTongTien();
							}
						}
						if(date1.equals("")) {
							m.addAttribute("ListDoanhThu",dtdao.getAllDatHangTheoPage(start,limit));
						}else {
							Date datea = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
							Date dateb = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
							m.addAttribute("ListDoanhThu",dtdao.getAllByDateDatHangTheoPage(datea,dateb,start,limit));
						}
						m.addAttribute("tongDoanhThu",tongTien);
					//}
					return "admincp/AJAX/QuanLyDoanhThu/quanlydoanhthuajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	
	@RequestMapping(value="admincp/quanlydoanhthuajax2",method=RequestMethod.POST)
	public String quanlydoanhthuajax(HttpServletRequest request,@RequestParam("date1")@DateTimeFormat(pattern = "yyyy-MM-dd") String date1 ,@RequestParam("date2")@DateTimeFormat(pattern = "yyyy-MM-dd") String date2,Model m,HttpSession session) { //Search doanh thu theo khoảng thời gian
		try {
			if(session.getAttribute("username")==null) {
				m.addAttribute("tk",new Users());
				return "redirect:login";
			}else {
				if(session.getAttribute("role").equals("Administrator") || session.getAttribute("role").equals("administrator")) { //Chỉ Administrator mới đc quyền quản lý nhân viên
					Date datea = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
					Date dateb = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
					double tongTien=0;
					DoanhThuDao dtdao = new DoanhThuDaoImpl();
					int limit = 2;
					int selectPage = 0;
					if(request.getParameter("pageid")!=null) {
						selectPage = Integer.parseInt(request.getParameter("pageid"));
					}
					int start = PhanTrangUtil.findStart(limit, selectPage);
					int count = dtdao.getAll(datea,dateb).size();
					int pages = PhanTrangUtil.findPages(count, limit);
					if(selectPage==0) {
						selectPage=1;
					}
					m.addAttribute("PageCount",pages);
					m.addAttribute("selectPage",selectPage);
					m.addAttribute("TongsoDoanhThu",count);
					for(int i=0;i<dtdao.getAll(datea,dateb).size();i++) {
						tongTien+=dtdao.getAll(datea,dateb).get(i).getTongTien();
					}
					m.addAttribute("ListDoanhThu",dtdao.getAllByDateDatHangTheoPage(datea,dateb,start,limit));
					m.addAttribute("tongDoanhThu",tongTien);
					return "admincp/AJAX/QuanLyDoanhThu/quanlydoanhthuajax";
				}else { //Nếu user k có chức vị là Administrator (Truy cập vào quản lý nhân viên => quay lại trang quản trị)
					return "redirect:index";
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:index";
		}
	}
	/*Kết thúc chức năng admin - Quản lý doanh thu - Chức năng này chỉ dành cho Administrator*/
	
	/*Test AJAX*/
	@RequestMapping(value="admincp/testajax",method=RequestMethod.GET)
	public String testajax() {
		return "admincp/testAJAX";
	}
	@RequestMapping(value="admincp/testwelcomeajax",method=RequestMethod.POST)
	public String testwelcomeajax2(Model m,@RequestParam(value="username") String username) {
		m.addAttribute("username",username);
		return "admincp/testwelcomeajax";
	}
	/*Ket thuc Test AJAX*/
}
