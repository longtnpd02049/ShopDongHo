package com.xTNL.DaoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xTNL.Dao.ThanhtoanDao;
import com.xTNL.entities.Chitietdathang;
import com.xTNL.entities.Dathang;
import com.xTNL.utils.MySessionFactory;

public class ThanhtoanDaoImpl implements ThanhtoanDao{

	@Override
	public void insert(Dathang dathang,double tongTien) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			//Query query = session.createQuery("INSERT INTO Dathang(TenKhachHang,Email,Sdt1,Sdt2,DiaChi,ThoiGianDatHang,GhiChu,Status) values(:tenkhachhang,:email,:sdt1,:sdt2,:diachi,:thoigiandathang,:ghichu,'wait')");
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			dathang.setThoiGianDatHang(date);
			dathang.setStatus("wait");
			dathang.setTongTien(tongTien);
			Transaction tran = session.beginTransaction();
			session.save(dathang);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public int getMaxDatHang() {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran=session.beginTransaction();
			Query query = session.createSQLQuery("Select MAX(IdDatHang) from Dathang");
			tran.commit();
			return (int) query.uniqueResult();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean insert(int idDatHang, int idChitietSP, int SoLuong) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			Query query = session.createSQLQuery("INSERT INTO Chitietdathang(IdDatHang,IdChiTietSP,SoLuong) values(:idDatHang,:idChitietSP,:SoLuong)");
			query.setInteger("idDatHang", idDatHang);
			query.setInteger("idChitietSP", idChitietSP);
			query.setInteger("SoLuong", SoLuong);
			query.executeUpdate();
			tran.commit();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}


}
