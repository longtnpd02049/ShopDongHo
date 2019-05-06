package com.xTNL.Dao;

import java.util.ArrayList;
import java.util.List;

import com.xTNL.entities.Binhluan;
import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Hieusp;


public interface BinhLuanDao {
	public List<Binhluan> getAll();
	public List<Binhluan> getBinhLuanByIdsp(int idsp);
	public Binhluan getBinhLuanByIdBinhLuan(int id);
	public void update(Binhluan bl);
	public void create(Binhluan bl);
	public Binhluan DuyetBinhLuanById(int id);
	public Binhluan XoaBinhLuanById(int id);
	public List<Binhluan> getAllBinhLuanTheoPage(int start,int limit); //Dùng cho phân trang get toàn bộ
	public List<Binhluan> getAllBinhLuanByStatus(String status); //Lấy tất cả bình luận bởi trạng thái duyệt hoặc chưa duyệt (dùng cho quản lý bình luận ajax)
	public List<Binhluan> getAllBinhLuanByStatusTheoPage(String status,int start,int limit); //Dùng cho phân trang get theo trạng thái bình luận
}
