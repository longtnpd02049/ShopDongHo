package com.xTNL.Dao;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.xTNL.entities.Dathang;
import com.xTNL.entities.Hieusp;

public interface DoanhThuDao {
	public List<Dathang> getAll(); //Get toàn bộ thời gian
	public List<Dathang> getAll(Date date1,Date date2); //Get theo khoảng thời gian
	
	public List<Dathang> getAllDatHangTheoPage(int start,int limit); //Dùng cho phân trang get toàn bộ
	public List<Dathang> getAllByDateDatHangTheoPage(Date date1,Date date2,int start,int limit); //Dùng cho phân trang get toàn bộ
}
