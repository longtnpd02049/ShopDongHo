package com.xTNL.utils;

public class PhanTrangUtil {
	public static int findStart(int limit, int selectPage) { //Hàm tìm kiếm vị trí bắt đầu lấy từ cơ sở dữ liệu
		int start = 0;
		if(selectPage>1) {
			start = (selectPage-1)*limit;
		}
		return start;
	}
	public static int findPages(int count, int limit) { //Hàm trả về tổng số trang (Tổng tất cả sản phẩm / Số sản phẩm 1 trang = tổng số trang page, làm tròn cộng thêm 1)
		int pages = ((count % limit)==0) ? (count/limit) : (Math.floorDiv(count, limit)+1);
		return pages;
	}
	
}
