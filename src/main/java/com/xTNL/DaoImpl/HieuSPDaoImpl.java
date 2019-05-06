package com.xTNL.DaoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xTNL.Dao.HieuSPDao;
import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Hieusp;
import com.xTNL.entities.Users;
import com.xTNL.utils.MySessionFactory;

public class HieuSPDaoImpl implements HieuSPDao{

	@Override
	public List<Hieusp> getAll() {
		List<Hieusp> hangspList = new ArrayList<Hieusp>();
//		try {
//			Session session = MySessionFactory.getSessionFactory().openSession();
//			session.beginTransaction().begin();
//			hangspList = session.createCriteria(Hangsp.class).list();
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		return hangspList;
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Hieusp order by sortId desc");
			hangspList = query.list();
			return hangspList;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return hangspList;
	}
	@Override
	public int findStart(int limit, int selectPage) {
		int start = 0;
		if(selectPage>1) {
			start = (selectPage-1)*limit;
		}
		return start;
	}
	@Override
	public int findPages(int count, int limit) {
		int pages = ((count % limit)==0) ? (count/limit) : (Math.floorDiv(count, limit)+1);
		return pages;
	}
	@Override
	public List<Chitietsp> getSpByIdHieuSP(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp where idHieuSp = :idHieuSp order by idChiTietSp desc");
			query.setInteger("idHieuSp", id);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Hieusp getHieuSpById(int id) {
		Hieusp hieusp = new Hieusp();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Hieusp where idHieuSp = :Id");
			query.setInteger("Id", id);
			hieusp=(Hieusp) query.setMaxResults(1).uniqueResult();
			tran.commit();
			return hieusp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return hieusp;
	}

	@Override
	public void create(Hieusp hieusp) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			hieusp.setThoiGianTao(date);
			session.save(hieusp);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Hieusp hieusp) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			hieusp.setThoiGianSua(date);
			session.update(hieusp);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public int getSortIdMax() {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran=session.beginTransaction();
			Query query = session.createSQLQuery("Select MAX(sortId) from Hieusp");
			tran.commit();
			return (int) query.uniqueResult();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public Hieusp XoaHieuSpById(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			Hieusp hieusp = (Hieusp) session.get(Hieusp.class, id);
			session.delete(hieusp);
			tran.commit();
			return hieusp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Chitietsp> getSPByIdHieuSPTheoPage(int id, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp where idHieuSp = :idHieuSp order by idChiTietSp desc");
			query.setInteger("idHieuSp", id);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Hieusp> getAllHieuSPTheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Hieusp order by sortId desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Hieusp> getAllHieuSPByName(String key) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Hieusp where tenHieuSp LIKE '%"+key+"%' order by sortId desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Hieusp> getAllHieuSPByNameTheoPage(String key, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Hieusp where tenHieuSp LIKE '%"+key+"%' order by sortId desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
