package com.xTNL.DaoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.xTNL.Dao.UsersDao;
import com.xTNL.entities.Binhluan;
import com.xTNL.entities.Users;
import com.xTNL.utils.EncryptUtil;
import com.xTNL.utils.MySessionFactory;

public class UsersDaoImpl implements UsersDao{
	private EncryptUtil mahoa = new EncryptUtil();
	public UsersDaoImpl() {}
	@Override
	public Users checkLogin(String username, String password) {
		Users u = null;	
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		Query query = session.createQuery("from Users where username = :user and password = :pass1");
		query.setString("user", username);
		query.setString("pass1",mahoa.md5(password));
		u=(Users) query.uniqueResult();
		tran.commit();
		return u;
	}
	@Override
	public Users checkForgot(String username, String password, String password2) {
		Users u = null;
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		Query query = session.createQuery("from Users where username = :user and password2 = :pass2");
		query.setString("user", username);
		query.setString("pass2",mahoa.md5(password2));
		u=(Users) query.uniqueResult();
		return u;
	}
	
	@Override
	public Users updateForgot(String username, String password, String password2) {
		Users u = null;
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		Query query2 = session.createQuery("update Users set password = :pass1 where username = :user and password2 = :pass2  and status !='blocked'");
		query2.setString("user", username);
		query2.setString("pass1",mahoa.md5(password));
		query2.setString("pass2",mahoa.md5(password2));
		query2.executeUpdate();
		tran.commit();
		return u;
	}
	@Override
	public List<Users> getAllNotDeleted() {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users where Status!='deleted' order by idUsername desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public Users BlockUnblockUserById(int id, String valueStatus) { //Khóa | mở khóa tài khản nhân viên
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			Users u = (Users) session.get(Users.class, id);
			u.setStatus(valueStatus);
			u.setThoiGianSua(date);
			session.update(u);
			tran.commit();
			return u;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public Users XoaUserById(int id) {
//		try {
//			Session session = MySessionFactory.getSessionFactory().openSession();
//			Transaction tran = session.beginTransaction();
//			Users u = (Users) session.get(Users.class, id);
//			session.delete(u);
//			tran.commit();
//			return u;
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		return null;
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			Users u = (Users) session.get(Users.class, id);
			u.setStatus("deleted");
			u.setThoiGianXoa(date);
			session.update(u);
			tran.commit();
			return u;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public void create(Users u) { // Thêm mới nhân viên
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			u.setPassword(new EncryptUtil().md5(u.getPassword()));
			u.setPassword2(new EncryptUtil().md5(u.getPassword2()));
			u.setThoiGianTao(date);
			Transaction tran = session.beginTransaction();
			session.save(u);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void remove(Users u) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			session.delete(u);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Get user by iduser => trả về user => remove đối tượng user có thể xóa các liên quan FK
	@Override
	public Users getUserById(int id) {
		Users u = null;	
		Session session = MySessionFactory.getSessionFactory().getCurrentSession();
		Transaction tran = session.beginTransaction();
		Query query = session.createQuery("from Users where idUsername = :id");
		query.setInteger("id", id);
		u=(Users) query.uniqueResult();
		tran.commit();
		return u;
	}
	@Override
	public void update(Users u) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			u.setPassword(new EncryptUtil().md5(u.getPassword()));
			u.setPassword2(new EncryptUtil().md5(u.getPassword2()));
			u.setThoiGianSua(date);
			session.update(u);
			tran.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public List<Users> getAllDeleted() {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users where Status='deleted' order by idUsername desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public void removeAlluserdeleted() {
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		Query query2 = session.createQuery("delete Users where Status = :status").setString("status", "deleted");
		query2.executeUpdate();
		tran.commit();
	}
	@Override
	public List<Users> getAll() {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users WHERE Status != 'deleted' order by idUsername desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Users> getAllUsersTheoPage(int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users WHERE Status != 'deleted' order by idUsername desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Users> getAllByStatus(String status) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users where status = :status order by idUsername desc");
			query.setString("status", status);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Users> getAllUsersByStatusTheoPage(String status, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			if(status.equals("blocked")){
				Query query = session.createQuery("from Users where status = :status order by idUsername desc");
				query.setString("status", status);
				query.setFirstResult(start);
				query.setMaxResults(limit);
				return query.list();
			}else if(status.equals("ok")){
				Query query = session.createQuery("from Users where status = :status order by idUsername desc");
				query.setString("status", status);
				query.setFirstResult(start);
				query.setMaxResults(limit);
				return query.list();
			}else {
				Query query = session.createQuery("from Users where status = :status order by idUsername desc");
				query.setString("status", status);
				query.setFirstResult(start);
				query.setMaxResults(limit);
				return query.list();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Users> getAllByNameUser(String key) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users WHERE Status != 'deleted' AND Username LIKE '%"+key+"%' order by idUsername desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Users> getAllByNameStatus(String key, String status) {
		try {
			Session session = MySessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users WHERE Status = '"+status+"' AND Username LIKE '%"+key+"%' order by idUsername desc");
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Users> getAllUsersByKeyTheoPage(String key, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users WHERE Status != 'deleted' AND Username LIKE '%"+key+"%' order by idUsername desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Users> getAllUsersByKeyStatusTheoPage(String key, String status, int start, int limit) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			session.beginTransaction().begin();
			Query query = session.createQuery("from Users WHERE Status = '"+status+"' AND Username LIKE '%"+key+"%' order by idUsername desc");
			query.setFirstResult(start);
			query.setMaxResults(limit);
			return query.list();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
