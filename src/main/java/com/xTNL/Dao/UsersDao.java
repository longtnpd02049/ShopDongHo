package com.xTNL.Dao;

import java.util.List;

import com.xTNL.entities.Danhmucsp2;
import com.xTNL.entities.Users;

public interface UsersDao {
	public Users checkLogin(String username,String password);
	public Users checkForgot(String username,String password,String password2);
	public Users updateForgot(String username,String password,String password2);
	public List<Users> getAllNotDeleted();
	public List<Users> getAllDeleted();
	public Users BlockUnblockUserById(int id,String valueStatus); //value (ok: mở khóa | blocked: khóa)
	public Users XoaUserById(int id); //xóa nhân viên theo id
	public Users getUserById(int id); //Lấy nhân viên theo id
	public void create(Users u); //Thêm nhân viên theo đối tượng
	public void remove(Users u); //xóa nhân viên theo đối tượng (dùng getUserById => mới có thể dùng hàm này)
	public void update(Users u); //Cập nhật nhân viên theo đối tượng
	public void removeAlluserdeleted(); //Xóa tất cả lịch sử nhân viên đã bị xóa (Xóa nhân viên có Status = deleted)
	
	public List<Users> getAll(); //Lấy tất cả user
	public List<Users> getAllUsersTheoPage(int start,int limit); //lấy tất cả user chia theo page
	public List<Users> getAllByStatus(String status); //Lấy tất cả user by status
	public List<Users> getAllUsersByStatusTheoPage(String status,int start,int limit);//lấy tất cả user by status chia theo page
	
	public List<Users> getAllByNameUser(String key); //Lấy tất cả user bởi name user không lấy user deleted
	public List<Users> getAllByNameStatus(String key,String status); //Lấy tất cả user by username và status
	public List<Users> getAllUsersByKeyTheoPage(String key,int start,int limit); //lấy tất cả user chia theo page dùng cho search theo username
	public List<Users> getAllUsersByKeyStatusTheoPage(String key,String status,int start,int limit);//lấy tất cả user by status chia theo page dùng cho search theo username
	
}
