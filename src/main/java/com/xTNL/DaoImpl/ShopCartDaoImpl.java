package com.xTNL.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import com.xTNL.Dao.SanPhamDao;
import com.xTNL.Dao.ShopCartDao;
import com.xTNL.entities.Chitietsp;
import com.xTNL.utils.MySessionFactory;

public class ShopCartDaoImpl implements ShopCartDao{
	public static ArrayList<Chitietsp> listsanpham= new ArrayList<>();
	public static ArrayList<Chitietsp> listgioHang= new ArrayList<>();
	public void ShopCartDaoImpl() {
		listsanpham.removeAll(listsanpham);
		ShopCartDaoImpl getAllSP = new ShopCartDaoImpl();
		listsanpham.addAll(getAllSP.getAll());
	}
	public ArrayList<Chitietsp> getAll(){ //Lấy tất cả sản phẩm thêm vào list sản phẩm
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Chitietsp");
			return (ArrayList<Chitietsp>) query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean checkSPtronggiohang(int idsp) {
		for(int i=0;i<listgioHang.size();i++) {
			if(listgioHang.get(i).getIdChiTietSp().equals(idsp)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean addShopcart(int idsp) {
		boolean kiemTra = checkSPtronggiohang(idsp);
		for (int i = 0; i <listsanpham.size(); i++) {
				if(listsanpham.get(i).getIdChiTietSp().equals(idsp) && kiemTra==false){
					listgioHang.add(listsanpham.get(i));
					return true;
				}
		}
		if(kiemTra==true){
			int index =listgioHang.size();
			for (int i = 0; i < index; i++) {
				if(listgioHang.get(i).getIdChiTietSp().equals(idsp)){
					listgioHang.get(i).setSoLuongDat(listgioHang.get(i).getSoLuongDat()+1);
					index=i;
				}
			}
		}
		return false;
	}
	@Override
	public boolean deleteShopcart(int idsp) {
		for (int i = 0; i < listgioHang.size(); i++) {
			if(listgioHang.get(i).getIdChiTietSp().equals(idsp)){
				listgioHang.remove(i);
				return true;
			}
		}
		return false;
	}
	@Override
	public double tongGiaTien() {
		double tongtien=0;
		for(int i=0;i<listgioHang.size();i++) {
			tongtien += listgioHang.get(i).getGiaSp()*listgioHang.get(i).getSoLuongDat();
		}
		return tongtien;
	}
	@Override
	public boolean updateShopcart(int idsp, int quantity) {
		for (int i = 0; i < listgioHang.size(); i++) {
			if(listgioHang.get(i).getIdChiTietSp().equals(idsp)){
				listgioHang.get(i).setSoLuongDat(quantity);
				return true;
			}
		}
		return false;
	}
}
