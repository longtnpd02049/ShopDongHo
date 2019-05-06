package com.xTNL.Dao;

import com.xTNL.entities.Chitietdathang;
import com.xTNL.entities.Dathang;

public interface ThanhtoanDao {
	public void insert(Dathang dathang,double tongTien);
	public boolean insert(int idDatHang,int idChitietSP,int SoLuong);
	public int getMaxDatHang();
}
