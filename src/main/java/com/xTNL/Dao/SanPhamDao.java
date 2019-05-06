package com.xTNL.Dao;

import java.util.List;

import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Gallery;

public interface SanPhamDao {
	public List<Chitietsp> getAll();
	public List<Chitietsp> getHangMoi();
	public List<Chitietsp> getHangBanChay();
	public Chitietsp showThongTinByID(int idsp); //Show thông tin ở trang details
	public Chitietsp showThongTinByID2(int idsp); //Show thông tin từ curren session => xóa
	public List<Gallery> getGalleryImageByIdsp(int idsp); //get all image
	public List<Chitietsp> getSPTimKiem(String keyword,double giaMin,double giaMax,int idHieuSp);
	public void create(Chitietsp ctsp);
	public void create(Gallery gallery);
	public void remove(Chitietsp ctsp);
	public void update(Chitietsp ctsp);
	public Chitietsp XoaSPById(int id);
	public List<Gallery> getAllGallery(int id); //Show các ảnh mô tả thêm của 1 sản phẩm
	public List<Gallery> getAllGalleryTheoPage(int id,int start,int limit); //Show các ảnh mô tả thêm của 1 sản phẩm
	public Gallery ShowGalleryByIDGallery(int id); //Show thông tin từ curren session => xóa
	public void XoaGalleryByIdGallery(int id);
	public void XoaAllGalleryByIdSP(int id);
	
	/*Hàm xếp theo trang*/
	public int findStart(int limit,int selectPage); //trả về vị trí tìm kiếm tiếp theo để mysql limit
	public int findPages(int count,int limit);
	public List<Chitietsp> getSPSearchTheoPage(String keyword,int idsapxeptheo,double giaMin,double giaMax,int idHieuSp,int start,int limit);
	
	//lấy tất cả sản phẩm theo page
	public List<Chitietsp> getSPTheoPage(int start,int limit);
	
	//Lấy tất cả sản phẩm theo giá, hiệu sản phẩm, sắp xếp theo
	public List<Chitietsp> getSPTheoPage(int idsapxeptheo,double giaMin,double giaMax,int idHieuSp,int start,int limit);
	
	//lấy tổng tất cả sản phẩm by giá, hiệu => sắp xếp theo page (getSPTheoPage)
	public List<Chitietsp> getAllSPByDieuKien(int idsapxeptheo,double giaMin,double giaMax,int idHieuSp); 
}