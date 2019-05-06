package com.xTNL.Dao;

import java.util.List;

import com.xTNL.entities.Chitietdathang;
import com.xTNL.entities.Dathang;

public interface AdminDatHangDao {
	public List<Dathang> getAll(); //Hiển thị tất cả đơn hàng chưa duyệt và đã duyệt
	public List<Dathang> getAllDonHangByStatus(String status);
	public void setStatusByID(int id,String status,int idUser);//Status đơn hàng (wait:chưa xác nhận | ok: đã xác nhận | deleted: hủy | success: hoàn tất)
	
	public Dathang getDatHangByID(int idDatHang);
	public List<Dathang> getAllDatHangTheoPage(int start,int limit); //Dùng cho phân trang cho đơn hàng đã duyệt và chưa duyệt
	public List<Dathang> getAllDatHangByStatusTheoPage(String status,int start,int limit); //Dùng cho phân trang cho đơn hàng đã duyệt và chưa duyệt
	
	public List<Chitietdathang> getAllByIdDatHang(int id); // GetAll chitietdathang by IdDatHang
	public List<Chitietdathang> getAllByIdDatHangTheoPage(int id,int start,int limit); // GetAll chitietdathang by IdDatHang chia theo page
}
