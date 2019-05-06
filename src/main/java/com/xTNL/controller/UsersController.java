package com.xTNL.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xTNL.Dao.AdminDatHangDao;
import com.xTNL.Dao.BinhLuanDao;
import com.xTNL.Dao.SanPhamDao;
import com.xTNL.Dao.UsersDao;
import com.xTNL.DaoImpl.AdminDatHangImpl;
import com.xTNL.DaoImpl.BinhLuanDaoImpl;
import com.xTNL.DaoImpl.SanPhamDaoImpl;
import com.xTNL.DaoImpl.UsersDaoImpl;
import com.xTNL.entities.Users;

@Controller
public class UsersController {
	/*Bắt đầu chức năng admin - Login - Logout - Forget*/
	@RequestMapping(value="/admincp",method=RequestMethod.GET)
	public String admincp(Model mm,HttpSession session) {
		if(session.getAttribute("username")==null) {
			mm.addAttribute("tk",new Users());
			return "redirect:admincp/login";
		}else {
			return "redirect:admincp/index";
		}
	}
	/*Login*/
	@RequestMapping(value="/admincp/login",method=RequestMethod.GET)
	public String login(Model mm,HttpSession session) {
		if(session.getAttribute("username")==null) {
			mm.addAttribute("tk",new Users());
			return "admincp/login";
		}else {
			return "redirect:index";
		}
	}
	@RequestMapping(value = "/admincp/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("tk") Users users,Model mm,HttpSession session) {
		UsersDao model = new UsersDaoImpl();
		Users u = model.checkLogin(users.getUsername(), users.getPassword());
		if(u==null) {
			mm.addAttribute("error","Tài khoản hoặc mật khẩu không đúng");
			return "admincp/login";
		}else {
			if(u.getRole().equalsIgnoreCase("Administrator") || u.getRole().equalsIgnoreCase("Moderator")) {
				if(u.getStatus().equalsIgnoreCase("blocked")) {
					mm.addAttribute("error","Tài khoản này đã bị khóa");
					return "admincp/login";
				}else if(u.getStatus().equalsIgnoreCase("deleted")){
					mm.addAttribute("error","Tài khoản hoặc mật khẩu không đúng");
					return "admincp/login";
				}else {
					session.setAttribute("id", u.getIdUsername());
					session.setAttribute("username", u.getUsername());
					session.setAttribute("password",u.getPassword());
					session.setAttribute("password2", u.getPassword2());
					session.setAttribute("role", u.getRole());
				}
			}else {
				mm.addAttribute("error","Tài khoản này không đủ quyền truy cập trang quản trị");
				return "admincp/login";
			}
		}
		return "redirect:index";
	}
	/*Kết thúc Login*/
	
	/*Forgot*/
	@RequestMapping(value="/admincp/forgot",method=RequestMethod.GET)
	public String forgot(Model mm,HttpSession session) {
		if(session.getAttribute("username")==null) {
			mm.addAttribute("tk",new Users());
			return "admincp/forgot";
		}else {
			return "redirect:index";
		}
	}
	@RequestMapping(value="/admincp/forgot",method=RequestMethod.POST)
	public String forgot(@ModelAttribute("tk") Users users,Model mm,HttpSession session) {
		UsersDao model = new UsersDaoImpl();
		Users u = model.checkForgot(users.getUsername(), users.getPassword(), users.getPassword2());
		if(u==null) {
			mm.addAttribute("error","Tài khoản hoặc mật khẩu 2 không đúng");
		}else {
			if(u.getStatus().equalsIgnoreCase("blocked")) {
				mm.addAttribute("error","Tài khoản này đã bị khóa");
			}else if(u.getStatus().equalsIgnoreCase("deleted")){
				mm.addAttribute("error","Tài khoản hoặc mật khẩu 2 không đúng");
			}else {
				model.updateForgot(users.getUsername(), users.getPassword(), users.getPassword2());
				mm.addAttribute("success","Đã đổi lại thành công");	
			}
		}
		return "admincp/forgot";
	}
	/*Kết thúc Forgot*/
	
	//Vào trang chủ admin
	@RequestMapping(value="admincp/index",method=RequestMethod.GET)
	public String index(Model mm,HttpSession session) {
		if(session.getAttribute("username")==null) {
			mm.addAttribute("tk",new Users());
			return "redirect:login";
		}else {
			BinhLuanDao bldao = new BinhLuanDaoImpl();
			SanPhamDao spdao = new SanPhamDaoImpl();
			AdminDatHangDao dathangdao = new AdminDatHangImpl();
			UsersDao userdao = new UsersDaoImpl();
			mm.addAttribute("countBL",bldao.getAll().size());
			mm.addAttribute("countSP",spdao.getAll().size());
			mm.addAttribute("countDatHang",dathangdao.getAll().size());
			mm.addAttribute("countUser",userdao.getAll().size());
			return "admincp/index";
		}
	}
	//đăng xuất trang admin
	@RequestMapping(value="admincp/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		if(session.getAttribute("username")!=null) {
			session.removeAttribute("id");
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.removeAttribute("password2");
			session.removeAttribute("role");
		}
		return "redirect:login";
	}
	/*Kết thúc chức năng admin - Login - Logout - Forget*/
}
