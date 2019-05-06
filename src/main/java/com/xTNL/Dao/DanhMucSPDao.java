package com.xTNL.Dao;

import java.util.List;

import com.xTNL.entities.Chitietsp;
import com.xTNL.entities.Danhmucsp;
import com.xTNL.entities.Danhmucsp2;

public interface DanhMucSPDao {
	public List<Danhmucsp> getAll();
	public List<Danhmucsp2> getAll2(); //Get tất cả danh mục 2s
	public List<Chitietsp> getSPByDanhMuc(int id);
	public List<Chitietsp> getSPByDanhMuc(int id,int id2);
	public Danhmucsp XoaDanhmucspById(int id);
	public Danhmucsp2 XoaDanhmucspById2(int id);
	public Danhmucsp getDanhMucById(int id);
	public Danhmucsp2 getDanhMucById2(int id);
	public Danhmucsp2 getDanhMucById2(int id,int id2);
	public void create(Danhmucsp dmsp);
	public void create2(Danhmucsp2 dmsp2);
	public void update(Danhmucsp dmsp);
	public void update2(Danhmucsp2 dmsp2);
	public int getSortIdMax();
	public int findStart(int limit,int selectPage); //trả về vị trí tìm kiếm tiếp theo để mysql limit
	public int findPages(int count,int limit);
	public List<Chitietsp> getSPByIdDanhMucTheoPage(int id,int start,int limit);
	public List<Chitietsp> getSPByIdDanhMucTheoPage(int id,int id2,int start,int limit);
	
	//Lấy tất cả danhmuc2 từ id danhmuc1 => dùng cho thêm sản phẩm ajax
	public List<Danhmucsp2> getAlldmsp2ByIddmsp(int id);
	public List<Danhmucsp> getAllDanhMucTheoPage(int start,int limit);
	
	public List<Danhmucsp> getAllByName(String key); //Lấy tất cả danh mục bởi tên
	public List<Danhmucsp> getAllDanhMucByNameTheoPage(String key,int start,int limit); //Lấy tất cả danh mục bởi tên chia theo page
	
	
	public List<Danhmucsp2> getAllDm2ByName(String key); //Lấy tất cả danh mục 2 bởi tên
	public List<Danhmucsp2> getAllDanhMuc2TheoPage(int start,int limit);
	public List<Danhmucsp2> getAllDanhMuc2ByNameTheoPage(String key,int start,int limit); //Lấy tất cả danh mục2 bởi tên chia theo page
	
	public Danhmucsp2 checkdmsptrongdmsp2byId(int id); //Kiểm tra danh mục 1 có tồn tại trong danh mục 2 không
}