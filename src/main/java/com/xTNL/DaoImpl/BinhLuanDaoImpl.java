package com.xTNL.DaoImpl;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xTNL.Dao.BinhLuanDao;
import com.xTNL.entities.Binhluan;
import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Hieusp;
import com.xTNL.utils.MySessionFactory;

public class BinhLuanDaoImpl implements BinhLuanDao{
	//Lấy toàn bộ bình luận
	@Override
	public List<Binhluan> getAll() {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Binhluan order by idBinhLuan desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	//Hiển thị bình luận theo id sản phẩm
	@Override
	public List<Binhluan> getBinhLuanByIdsp(int idsp) {
		//Binhluan bl = new Binhluan();
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Binhluan where IdChiTietSP = :Id");
			query.setInteger("Id", idsp);
			/*Bắt đầu lấy kí tự cuối của tên*/
//			String ten=bl.getHoTen().trim();
//			int i = bl.getHoTen().lastIndexOf(" ");
//			if(i>0) {
//				ten=ten.substring(i).trim();
//				this.KiTuHoTen=ten.charAt(0);
//			}else {
//				this.KiTuHoTen=ten.charAt(0);
//			}
//			this.test.add(this.KiTuHoTen);
			/*Kết thúc lấy kí tự cuối của tên*/
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(Binhluan bl) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			bl.setThoiGianBinhLuan(date);
			Transaction tran = session.beginTransaction();
			session.save(bl);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Duyệt bình luận sản phẩm theo id bình luận
	@Override
	public Binhluan DuyetBinhLuanById(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran = session.beginTransaction();
			Binhluan bl = (Binhluan) session.get(Binhluan.class, id);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			bl.setThoiGianXacNhan(date);
			bl.setStatus("ok");
			session.update(bl);
			tran.commit();
			return bl;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Binhluan XoaBinhLuanById(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			Binhluan bl = (Binhluan) session.get(Binhluan.class, id);
			session.delete(bl);
			tran.commit();
			return bl;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Binhluan getBinhLuanByIdBinhLuan(int id) {
		Binhluan bl = new Binhluan();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Binhluan where IdBinhLuan = :Id");
			query.setInteger("Id", id);
			bl = (Binhluan) query.uniqueResult();
			return bl;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return bl;
	}
	
	//Update bình luận
	@Override
	public void update(Binhluan bl) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			bl.setThoiGianSua(date);
			session.update(bl);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Binhluan> getAllBinhLuanTheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Binhluan order by idBinhLuan desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Binhluan> getAllBinhLuanByStatus(String status) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			if(status.equals("all")) {
				Query query = session.createQuery("from Binhluan order by idBinhLuan desc");
				return query.list();
			}else if(status.equals("wait")){
				Query query = session.createQuery("from Binhluan where status = :status order by idBinhLuan desc");
				query.setString("status", status);
				return query.list();
			}else if(status.equals("ok")){
				Query query = session.createQuery("from Binhluan where status = :status order by idBinhLuan desc");
				query.setString("status", status);
				return query.list();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Binhluan> getAllBinhLuanByStatusTheoPage(String status, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			if(status.equals("all")) {
				Query query = session.createQuery("from Binhluan order by idBinhLuan desc");
				query.setFirstResult(start);
				query.setMaxResults(limit);
				return query.list();
			}else if(status.equals("wait")) {
				Query query = session.createQuery("from Binhluan where status = :status order by idBinhLuan desc");
				query.setString("status", status);
				query.setFirstResult(start);
				query.setMaxResults(limit);
				return query.list();
			}else if(status.equals("ok")) {
				Query query = session.createQuery("from Binhluan where status = :status order by idBinhLuan desc");
				query.setString("status", status);
				query.setFirstResult(start);
				query.setMaxResults(limit);
				return query.list();
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
