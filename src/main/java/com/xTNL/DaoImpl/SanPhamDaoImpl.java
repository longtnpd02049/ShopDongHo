package com.xTNL.DaoImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xTNL.Dao.SanPhamDao;
import com.xTNL.entities.Binhluan;
import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Danhmucsp;
import com.xTNL.entities.Gallery;
import com.xTNL.utils.MySessionFactory;

public class SanPhamDaoImpl implements SanPhamDao{
	@Override
	public List<Chitietsp> getAll(){
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp order by idChiTietSp desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Chitietsp> getHangMoi() {
		//List<Chitietsp> listHangMoi = new ArrayList<Chitietsp>();
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp order by idChiTietSp desc").setMaxResults(8);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Chitietsp> getHangBanChay() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//hàm tìm kiếm thông tin sản phẩm theo Id sản phẩm
	@Override
	public Chitietsp showThongTinByID(int idsp) {
		Chitietsp ctsp = new Chitietsp();
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Chitietsp where IdChiTietSP = :Id");
			query.setInteger("Id", idsp);
			ctsp=(Chitietsp) query.uniqueResult();
			tran.commit();
			return ctsp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ctsp;
	}
	@Override
	public Chitietsp showThongTinByID2(int idsp) {
		Chitietsp ctsp = new Chitietsp();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Chitietsp where IdChiTietSP = :Id");
			query.setInteger("Id", idsp);
			ctsp=(Chitietsp) query.uniqueResult();
			tran.commit();
			return ctsp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ctsp;
	}
	@Override
	public Gallery ShowGalleryByIDGallery(int id) {
		Gallery gallery = new Gallery();
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Gallery where IdGallery = :Id");
			query.setInteger("Id", id);
			gallery=(Gallery) query.uniqueResult();
			tran.commit();
			return gallery;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return gallery;
	}

	@Override
	public List<Gallery> getGalleryImageByIdsp(int idsp) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createSQLQuery("SELECT Images from Gallery where IdChiTietSP = :Id ORDER BY RAND()").setMaxResults(2);
			query.setInteger("Id", idsp);
			//System.out.println(query.list());
			return (List<Gallery>)query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void create(Chitietsp ctsp) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			ctsp.setThoiGianTaoSp(date);
			session.save(ctsp);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void create(Gallery gallery) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			gallery.setThoiGianTao(date);
			session.save(gallery);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public Chitietsp XoaSPById(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			Chitietsp ctsp = (Chitietsp) session.get(Chitietsp.class, id);
			session.delete(ctsp);
			tran.commit();
			return ctsp;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void remove(Chitietsp ctsp) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			Transaction tran = session.beginTransaction();
			session.delete(ctsp);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Chitietsp ctsp) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			ctsp.setThoiGianSuaSp(date);
			session.update(ctsp);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Gallery> getAllGallery(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Gallery where idChiTietSp = :id order by idGallery desc");
			query.setInteger("id", id);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void XoaGalleryByIdGallery(int id) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			Gallery gl = (Gallery) session.get(Gallery.class, id);
			session.delete(gl);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void XoaAllGalleryByIdSP(int id) {
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		Query query2 = session.createQuery("delete Gallery where IdChiTietSP = :id").setInteger("id",id);
		query2.executeUpdate();
		tran.commit();
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
	public List<Chitietsp> getSPTimKiem(String keyword,double giaMin,double giaMax,int idHieuSp){
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			if(idHieuSp==0) {
				if(giaMin==0 && giaMax==0) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' ");
					return query.list();
				}else if(giaMin==0 && giaMax==5) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000");
					return query.list();
				}else if(giaMin==5 && giaMax==10) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000");
					return query.list();
				}else if(giaMin==10 && giaMax==20) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000");
					return query.list();
				}else if(giaMin==20 && giaMax==30) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000");
					return query.list();
				}else if(giaMin==30 && giaMax==40) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000");
					return query.list();
				}else if(giaMin==40 && giaMax==50) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000");
					return query.list();
				}else if(giaMin==50 && giaMax==100) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000");
					return query.list();
				}else if(giaMin==100 && giaMax==0) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000");
					return query.list();
				}
			}else {
				if(giaMin==0 && giaMax==0) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==0 && giaMax==5) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==5 && giaMax==10) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==10 && giaMax==20) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==20 && giaMax==30) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==30 && giaMax==40) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==40 && giaMax==50) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==50 && giaMax==100) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}else if(giaMin==100 && giaMax==0) {
					Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 and idHieuSp = :id");
					query.setInteger("id",idHieuSp);
					return query.list();
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<Chitietsp> getSPSearchTheoPage(String keyword,int idsapxeptheo,double giaMin,double giaMax,int idHieuSp,int start,int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			if(idHieuSp==0) {
				if(giaMin==0 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' order by idChiTietSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) {//sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) {//sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' order by giaSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==0 && giaMax==5) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 order by idChiTietSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) {//sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) {//sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 order by giaSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
					
				}else if(giaMin==5 && giaMax==10) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==10 && giaMax==20) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==20 && giaMax==30) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==30 && giaMax==40) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==40 && giaMax==50) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==50 && giaMax==100) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==100 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}
			}else {
				if(giaMin==0 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==0 && giaMax==5) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp<500000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==5 && giaMax==10) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}					
				}else if(giaMin==10 && giaMax==20) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==20 && giaMax==30) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}			
				}else if(giaMin==30 && giaMax==40) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==40 && giaMax==50) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}		
				}else if(giaMin==50 && giaMax==100) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==100 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where tenSp Like '%"+keyword+"%' and giaSp>10000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Chitietsp> getSPTheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp order by idChiTietSp desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Chitietsp> getSPTheoPage(int idsapxeptheo, double giaMin, double giaMax, int idHieuSp, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			if(idHieuSp==0) {
				if(giaMin==0 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp order by idChiTietSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) {//sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) {//sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp order by giaSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==0 && giaMax==5) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by idChiTietSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) {//sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) {//sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by giaSp desc");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
					
				}else if(giaMin==5 && giaMax==10) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==10 && giaMax==20) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==20 && giaMax==30) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==30 && giaMax==40) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==40 && giaMax==50) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==50 && giaMax==100) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==100 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by idChiTietSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by idChiTietSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by giaSp ASC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by giaSp DESC");
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}
			}else {
				if(giaMin==0 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==0 && giaMax==5) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==5 && giaMax==10) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}					
				}else if(giaMin==10 && giaMax==20) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==20 && giaMax==30) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}			
				}else if(giaMin==30 && giaMax==40) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==40 && giaMax==50) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}		
				}else if(giaMin==50 && giaMax==100) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}else if(giaMin==100 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						return query.list();
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	
	@Override
	public List<Chitietsp> getAllSPByDieuKien(int idsapxeptheo, double giaMin, double giaMax, int idHieuSp) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			if(idHieuSp==0) {
				if(giaMin==0 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp order by idChiTietSp desc");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) {//sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) {//sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp order by giaSp desc");
						return query.list();
					}
				}else if(giaMin==0 && giaMax==5) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by idChiTietSp desc");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) {//sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) {//sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp<500000 order by giaSp desc");
						return query.list();
					}
					
				}else if(giaMin==5 && giaMax==10) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by idChiTietSp DESC");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 order by giaSp DESC");
						return query.list();
					}
				}else if(giaMin==10 && giaMax==20) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by idChiTietSp DESC");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 order by giaSp DESC");
						return query.list();
					}
				}else if(giaMin==20 && giaMax==30) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by idChiTietSp DESC");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by giaSp DESC");
						return query.list();
					}
				}else if(giaMin==30 && giaMax==40) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 order by idChiTietSp DESC");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 order by giaSp DESC");
						return query.list();
					}
				}else if(giaMin==40 && giaMax==50) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by idChiTietSp DESC");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 order by giaSp DESC");
						return query.list();
					}
				}else if(giaMin==50 && giaMax==100) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by idChiTietSp DESC");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 order by giaSp DESC");
						return query.list();
					}
				}else if(giaMin==100 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by idChiTietSp DESC");
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by idChiTietSp ASC");
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by giaSp ASC");
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 order by giaSp DESC");
						return query.list();
					}
				}
			}else {
				if(giaMin==0 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}
				}else if(giaMin==0 && giaMax==5) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp<500000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}
				}else if(giaMin==5 && giaMax==10) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=500000 and giaSp<=1000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}					
				}else if(giaMin==10 && giaMax==20) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=1000000 and giaSp<=2000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}
				}else if(giaMin==20 && giaMax==30) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=2000000 and giaSp<=3000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}			
				}else if(giaMin==30 && giaMax==40) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=3000000 and giaSp<=4000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}
				}else if(giaMin==40 && giaMax==50) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=4000000 and giaSp<=5000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}		
				}else if(giaMin==50 && giaMax==100) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>=5000000 and giaSp<=10000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}
				}else if(giaMin==100 && giaMax==0) {
					if(idsapxeptheo==0) { //sắp xếp theo mới nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by idChiTietSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==1) { //sắp xếp theo cũ nhất
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by idChiTietSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==2) { //sắp xếp theo giá thấp đến cao
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by giaSp ASC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}else if(idsapxeptheo==3) { //sắp xếp theo giá cao đến thấp
						Query query = session.createQuery("from Chitietsp where giaSp>10000000 and idHieuSp = :id order by giaSp DESC");
						query.setInteger("id",idHieuSp);
						return query.list();
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Gallery> getAllGalleryTheoPage(int id, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Gallery where idChiTietSp = :id order by idGallery desc");
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