package com.xTNL.DaoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xTNL.Dao.AdminDatHangDao;
import com.xTNL.entities.Chitietdathang;
import com.xTNL.entities.Dathang;
import com.xTNL.entities.Users;
import com.xTNL.utils.MySessionFactory;

public class AdminDatHangImpl implements AdminDatHangDao{
	@Override
	public List<Dathang> getAll() {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status='wait' OR Status='ok' order by IdDatHang desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void setStatusByID(int id, String status, int idUser) {
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		if(status.equalsIgnoreCase("ok")) {
			Query query2 = session.createQuery("Update Dathang SET Status = :status, IdUser = :idUser, ThoiGianXacNhanDonHang = :thoigianxacnhandonhang where IdDatHang = :id");
			query2.setInteger("id", id);
			query2.setInteger("idUser", idUser);
			query2.setString("status", status);
			query2.setTimestamp("thoigianxacnhandonhang", date);
			query2.executeUpdate();
		}else if(status.equalsIgnoreCase("success")) {
			Query query2 = session.createQuery("Update Dathang SET Status = :status, IdUser = :idUser, ThoiGianHoanTatDonHang = :thoigianhoantatdonhang where IdDatHang = :id");
			query2.setInteger("id", id);
			query2.setInteger("idUser", idUser);
			query2.setString("status", status);
			query2.setTimestamp("thoigianhoantatdonhang", date);
			query2.executeUpdate();
		}else if(status.equalsIgnoreCase("deleted")) {
			Query query2 = session.createQuery("Update Dathang SET Status = :status, IdUser = :idUser where IdDatHang = :id");
			query2.setInteger("id", id);
			query2.setInteger("idUser", idUser);
			query2.setString("status", status);
			query2.executeUpdate();
		}
		tran.commit();
	}

	@Override
	public List<Chitietdathang> getAllByIdDatHang(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietdathang WHERE IdDatHang = :id order by IdChiTietDatHang desc");
			query.setInteger("id", id);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Dathang getDatHangByID(int idDatHang) {
		try {
			Dathang dh = new Dathang();
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("From Dathang WHERE IdDatHang = :id");
			query.setInteger("id", idDatHang);
			return (Dathang) query.uniqueResult();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dathang> getAllDonHangByStatus(String status) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status = :status order by IdDatHang desc");
			query.setString("status", status);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dathang> getAllDatHangTheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status='wait' OR Status='ok' order by IdDatHang desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dathang> getAllDatHangByStatusTheoPage(String status, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status = :status order by IdDatHang desc");
			query.setString("status", status);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Chitietdathang> getAllByIdDatHangTheoPage(int id, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietdathang WHERE IdDatHang = :id order by IdChiTietDatHang desc");
			query.setInteger("id", id);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
