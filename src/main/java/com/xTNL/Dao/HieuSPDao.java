package com.xTNL.Dao;

import java.util.List;

import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Danhmucsp;
import com.xTNL.entities.Danhmucsp2;
import com.xTNL.entities.Hieusp;
import com.xTNL.entities.Users;

public interface HieuSPDao {
	
	public Hieusp getHieuSpById(int id); //lấy hiệu sản phẩm theo id
	public List<Chitietsp> getSpByIdHieuSP(int id); //lấy sản phẩm theo hiệu sản phẩm
	public void create(Hieusp hieusp); //thêm hiệu sản phẩm
	public void update(Hieusp hieusp); //cập nhật hiệu sản phẩm
	public int getSortIdMax();
	public Hieusp XoaHieuSpById(int id);
	public int findStart(int limit,int selectPage); //trả về vị trí tìm kiếm tiếp theo để mysql limit
	public int findPages(int count,int limit);
	public List<Chitietsp> getSPByIdHieuSPTheoPage(int id,int start,int limit);
	
	public List<Hieusp> getAll(); //lấy toàn bộ list hiệu sản phẩm
	public List<Hieusp> getAllHieuSPTheoPage(int start,int limit); //Dùng cho phân trang get toàn bộ
	
	public List<Hieusp> getAllHieuSPByName(String key); //Lấy tất cả hiệu sản phẩm bởi tên (dùng cho quản lý hiệu sản phảm - tìm kiếm - ajax)
	public List<Hieusp> getAllHieuSPByNameTheoPage(String key,int start,int limit); //Dùng cho phân trang get theo từ khóa
}
