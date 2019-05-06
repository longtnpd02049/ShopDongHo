package com.xTNL.DaoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xTNL.Dao.DanhMucSPDao;
import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Danhmucsp;
import com.xTNL.entities.Danhmucsp2;
import com.xTNL.entities.Hieusp;
import com.xTNL.utils.MySessionFactory;

public class DanhMucSPDaoImpl implements DanhMucSPDao{
	public DanhMucSPDaoImpl() {}
	@Override
	public List<Danhmucsp> getAll(){   
		List<Danhmucsp> danhmucSPList = new ArrayList<Danhmucsp>();
//		try {
//			Session session = MySessionFactory.getSessionFactory().openSession();
//			session.beginTransaction().begin();
//			danhmucSPList = session.createCriteria(Danhmucsp.class).list();
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		return danhmucSPList;
//		List<Danhmucsp> danhmucSPList = new ArrayList<Danhmucsp>();
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp order by sortId asc");
			danhmucSPList = query.list();
			return danhmucSPList;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return danhmucSPList;
	}
	@Override
	public List<Chitietsp> getSPByDanhMuc(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp where idDanhMuc = :idDanhMuc order by idChiTietSp desc");
			query.setInteger("idDanhMuc", id);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Chitietsp> getSPByDanhMuc(int id, int id2) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp where idDanhMuc = :idDanhMuc and idDanhMuc2 = :idDanhMuc2 order by idChiTietSp desc");
			query.setInteger("idDanhMuc", id);
			query.setInteger("idDanhMuc2", id2);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public Danhmucsp getDanhMucById(int id) {
		Danhmucsp dmsp = new Danhmucsp();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Danhmucsp where idDanhMucSp = :Id");
			query.setInteger("Id", id);
			dmsp=(Danhmucsp) query.setMaxResults(1).uniqueResult();
			tran.commit();
			return dmsp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public Danhmucsp2 getDanhMucById2(int id) {
		Danhmucsp2 dmsp2 = new Danhmucsp2();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Danhmucsp2 where idDanhMucSp2 = :Id");
			query.setInteger("Id", id);
			dmsp2=(Danhmucsp2) query.setMaxResults(1).uniqueResult();
			tran.commit();
			return dmsp2;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public Danhmucsp2 getDanhMucById2(int id, int id2) {
		Danhmucsp2 dmsp = new Danhmucsp2();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Danhmucsp2 where idDanhMucSp = :Id and idDanhMucSp2 = :Id2");
			query.setInteger("Id", id);
			query.setInteger("Id2", id2);
			dmsp=(Danhmucsp2) query.setMaxResults(1).uniqueResult();
			tran.commit();
			return dmsp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public void create(Danhmucsp dmsp) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			dmsp.setThoiGianTao(date);
			session.save(dmsp);
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
			Query query = session.createSQLQuery("Select MAX(sortId) from Danhmucsp");
			tran.commit();
			return (int) query.uniqueResult();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	@Override
	public void update(Danhmucsp dmsp) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			dmsp.setThoiGianSua(date);
			session.update(dmsp);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void update2(Danhmucsp2 dmsp2) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			dmsp2.setThoiGianSua(date);
			session.update(dmsp2);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public Danhmucsp XoaDanhmucspById(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			Danhmucsp dmsp = (Danhmucsp) session.get(Danhmucsp.class, id);
			session.delete(dmsp);
			tran.commit();
			return dmsp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public Danhmucsp2 XoaDanhmucspById2(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			Danhmucsp2 dmsp2 = (Danhmucsp2) session.get(Danhmucsp2.class, id);
			session.delete(dmsp2);
			tran.commit();
			return dmsp2;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Danhmucsp2> getAll2() {
		List<Danhmucsp2> danhmucSPList2 = new ArrayList<Danhmucsp2>();
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp2 order by idDanhMucSp asc");
			danhmucSPList2 = query.list();
			return danhmucSPList2;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return danhmucSPList2;
	}
	@Override
	public void create2(Danhmucsp2 dmsp2) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			dmsp2.setThoiGianTao(date);
			session.save(dmsp2);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
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
	public List<Chitietsp> getSPByIdDanhMucTheoPage(int id, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp where idDanhMuc = :idDanhMuc order by idChiTietSp desc");
			query.setInteger("idDanhMuc", id);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Chitietsp> getSPByIdDanhMucTheoPage(int id,int id2, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp where idDanhMuc = :idDanhMuc and idDanhMuc2 = :idDanhMuc2 order by idChiTietSp desc");
			query.setInteger("idDanhMuc", id);
			query.setInteger("idDanhMuc2", id2);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Danhmucsp2> getAlldmsp2ByIddmsp(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp2 where idDanhMucSp = :idDanhMuc order by idDanhMucSp2 desc");
			query.setInteger("idDanhMuc", id);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Danhmucsp> getAllDanhMucTheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp order by idDanhMucSp desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Danhmucsp> getAllByName(String key) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp where name LIKE '%"+key+"%' order by idDanhMucSp desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Danhmucsp> getAllDanhMucByNameTheoPage(String key, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp where name LIKE '%"+key+"%' order by idDanhMucSp desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<Danhmucsp2> getAllDm2ByName(String key) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp2 where name LIKE '%"+key+"%' order by idDanhMucSp2 desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Danhmucsp2> getAllDanhMuc2TheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp2 order by idDanhMucSp2 desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Danhmucsp2> getAllDanhMuc2ByNameTheoPage(String key, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Danhmucsp2 where name LIKE '%"+key+"%' order by idDanhMucSp2 desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public Danhmucsp2 checkdmsptrongdmsp2byId(int id) {
		Danhmucsp2 dmsp2 = new Danhmucsp2();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Danhmucsp2 where idDanhMucSp = :Id");
			query.setInteger("Id", id);
			dmsp2=(Danhmucsp2) query.setMaxResults(1).uniqueResult();
			tran.commit();
			return dmsp2;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
