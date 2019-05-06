package com.xTNL.Dao;

import com.xTNL.entities.Chitietsp;

public interface ShopCartDao {
	public boolean checkSPtronggiohang(int idsp);
	public boolean addShopcart(int idsp);
	public boolean deleteShopcart(int idsp);
	public double tongGiaTien();
	public boolean updateShopcart(int idsp,int quantity);
}