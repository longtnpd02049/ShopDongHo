package com.xTNL.entities;
// Generated Nov 17, 2018 10:54:28 PM by Hibernate Tools 5.0.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Dathang generated by hbm2java
 */
@Entity
@Table(name = "dathang", catalog = "duan2")
public class Dathang implements java.io.Serializable {

	private Integer idDatHang;
	private Users users;
	private String tenKhachHang;
	private String email;
	private double sdt1;
	private Double sdt2;
	private String diaChi;
	private Date thoiGianDatHang;
	private Date thoiGianXacNhanDonHang;
	private Date thoiGianHoanTatDonHang;
	private String ghiChu;
	private double tongTien;
	private String status;
	private String moTaThem;
	private Set<Chitietdathang> chitietdathangs = new HashSet<Chitietdathang>(0);

	public Dathang() {
	}

	public Dathang(String tenKhachHang, double sdt1, String diaChi, Date thoiGianDatHang, double tongTien,
			String status) {
		this.tenKhachHang = tenKhachHang;
		this.sdt1 = sdt1;
		this.diaChi = diaChi;
		this.thoiGianDatHang = thoiGianDatHang;
		this.tongTien = tongTien;
		this.status = status;
	}

	public Dathang(Users users, String tenKhachHang, String email, double sdt1, Double sdt2, String diaChi,
			Date thoiGianDatHang, Date thoiGianXacNhanDonHang, Date thoiGianHoanTatDonHang, String ghiChu,
			double tongTien, String status, String moTaThem, Set<Chitietdathang> chitietdathangs) {
		this.users = users;
		this.tenKhachHang = tenKhachHang;
		this.email = email;
		this.sdt1 = sdt1;
		this.sdt2 = sdt2;
		this.diaChi = diaChi;
		this.thoiGianDatHang = thoiGianDatHang;
		this.thoiGianXacNhanDonHang = thoiGianXacNhanDonHang;
		this.thoiGianHoanTatDonHang = thoiGianHoanTatDonHang;
		this.ghiChu = ghiChu;
		this.tongTien = tongTien;
		this.status = status;
		this.moTaThem = moTaThem;
		this.chitietdathangs = chitietdathangs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IdDatHang", unique = true, nullable = false)
	public Integer getIdDatHang() {
		return this.idDatHang;
	}

	public void setIdDatHang(Integer idDatHang) {
		this.idDatHang = idDatHang;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUser")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "TenKhachHang", nullable = false)
	public String getTenKhachHang() {
		return this.tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Sdt1", nullable = false, precision = 22, scale = 0)
	public double getSdt1() {
		return this.sdt1;
	}

	public void setSdt1(double sdt1) {
		this.sdt1 = sdt1;
	}

	@Column(name = "Sdt2", precision = 22, scale = 0)
	public Double getSdt2() {
		return this.sdt2;
	}

	public void setSdt2(Double sdt2) {
		this.sdt2 = sdt2;
	}

	@Column(name = "DiaChi", nullable = false)
	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianDatHang", nullable = false, length = 19)
	public Date getThoiGianDatHang() {
		return this.thoiGianDatHang;
	}

	public void setThoiGianDatHang(Date thoiGianDatHang) {
		this.thoiGianDatHang = thoiGianDatHang;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianXacNhanDonHang", length = 19)
	public Date getThoiGianXacNhanDonHang() {
		return this.thoiGianXacNhanDonHang;
	}

	public void setThoiGianXacNhanDonHang(Date thoiGianXacNhanDonHang) {
		this.thoiGianXacNhanDonHang = thoiGianXacNhanDonHang;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianHoanTatDonHang", length = 19)
	public Date getThoiGianHoanTatDonHang() {
		return this.thoiGianHoanTatDonHang;
	}

	public void setThoiGianHoanTatDonHang(Date thoiGianHoanTatDonHang) {
		this.thoiGianHoanTatDonHang = thoiGianHoanTatDonHang;
	}

	@Column(name = "GhiChu")
	public String getGhiChu() {
		return this.ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Column(name = "TongTien", nullable = false, precision = 22, scale = 0)
	public double getTongTien() {
		return this.tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	@Column(name = "Status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MoTaThem")
	public String getMoTaThem() {
		return this.moTaThem;
	}

	public void setMoTaThem(String moTaThem) {
		this.moTaThem = moTaThem;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dathang",cascade = CascadeType.ALL)
	public Set<Chitietdathang> getChitietdathangs() {
		return this.chitietdathangs;
	}

	public void setChitietdathangs(Set<Chitietdathang> chitietdathangs) {
		this.chitietdathangs = chitietdathangs;
	}

}