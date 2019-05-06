package com.xTNL.DaoImpl;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.xTNL.Dao.DoanhThuDao;
import com.xTNL.entities.Dathang;
import com.xTNL.utils.MySessionFactory;

public class DoanhThuDaoImpl implements DoanhThuDao{

	@Override
	public List<Dathang> getAll() {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status='success' order by IdDatHang desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dathang> getAll(Date date1, Date date2) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status='success' AND ThoiGianHoanTatDonHang >= :date1 AND ThoiGianHoanTatDonHang <= :date2 order by IdDatHang desc");
			query.setDate("date1", date1);
			query.setDate("date2", date2);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dathang> getAllDatHangTheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status='success' order by IdDatHang desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dathang> getAllByDateDatHangTheoPage(Date date1, Date date2, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Dathang WHERE Status='success' AND ThoiGianHoanTatDonHang >= :date1 AND ThoiGianHoanTatDonHang <= :date2 order by IdDatHang desc");
			query.setDate("date1", date1);
			query.setDate("date2", date2);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
