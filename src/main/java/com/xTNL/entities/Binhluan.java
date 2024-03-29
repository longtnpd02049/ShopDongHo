package com.xTNL.entities;
// Generated Nov 17, 2018 10:54:28 PM by Hibernate Tools 5.0.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Binhluan generated by hbm2java
 */
@Entity
@Table(name = "binhluan", catalog = "duan2")
public class Binhluan implements java.io.Serializable {

	private Integer idBinhLuan;
	private Chitietsp chitietsp;
	private int gioiTinh;
	private String hoTen;
	private int danhGia;
	private String email;
	private double phone;
	private String noiDungBinhLuan;
	private Date thoiGianBinhLuan;
	private Date thoiGianXacNhan;
	private Date thoiGianSua;
	private String status;

	public Binhluan() {
	}

	public Binhluan(Chitietsp chitietsp, int gioiTinh, String hoTen, int danhGia, double phone, String noiDungBinhLuan,
			Date thoiGianBinhLuan, String status) {
		this.chitietsp = chitietsp;
		this.gioiTinh = gioiTinh;
		this.hoTen = hoTen;
		this.danhGia = danhGia;
		this.phone = phone;
		this.noiDungBinhLuan = noiDungBinhLuan;
		this.thoiGianBinhLuan = thoiGianBinhLuan;
		this.status = status;
	}

	public Binhluan(Chitietsp chitietsp, int gioiTinh, String hoTen, int danhGia, String email, double phone,
			String noiDungBinhLuan, Date thoiGianBinhLuan, Date thoiGianXacNhan, Date thoiGianSua, String status) {
		this.chitietsp = chitietsp;
		this.gioiTinh = gioiTinh;
		this.hoTen = hoTen;
		this.danhGia = danhGia;
		this.email = email;
		this.phone = phone;
		this.noiDungBinhLuan = noiDungBinhLuan;
		this.thoiGianBinhLuan = thoiGianBinhLuan;
		this.thoiGianXacNhan = thoiGianXacNhan;
		this.thoiGianSua = thoiGianSua;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IdBinhLuan", unique = true, nullable = false)
	public Integer getIdBinhLuan() {
		return this.idBinhLuan;
	}

	public void setIdBinhLuan(Integer idBinhLuan) {
		this.idBinhLuan = idBinhLuan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdChiTietSP", nullable = false)
	public Chitietsp getChitietsp() {
		return this.chitietsp;
	}

	public void setChitietsp(Chitietsp chitietsp) {
		this.chitietsp = chitietsp;
	}

	@Column(name = "GioiTinh", nullable = false)
	public int getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Column(name = "HoTen", nullable = false)
	public String getHoTen() {
		return this.hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	@Column(name = "DanhGia", nullable = false)
	public int getDanhGia() {
		return this.danhGia;
	}

	public void setDanhGia(int danhGia) {
		this.danhGia = danhGia;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Phone", nullable = false, precision = 22, scale = 0)
	public double getPhone() {
		return this.phone;
	}

	public void setPhone(double phone) {
		this.phone = phone;
	}

	@Column(name = "NoiDungBinhLuan", nullable = false)
	public String getNoiDungBinhLuan() {
		return this.noiDungBinhLuan;
	}

	public void setNoiDungBinhLuan(String noiDungBinhLuan) {
		this.noiDungBinhLuan = noiDungBinhLuan;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianBinhLuan", nullable = false, length = 19)
	public Date getThoiGianBinhLuan() {
		return this.thoiGianBinhLuan;
	}

	public void setThoiGianBinhLuan(Date thoiGianBinhLuan) {
		this.thoiGianBinhLuan = thoiGianBinhLuan;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianXacNhan", length = 19)
	public Date getThoiGianXacNhan() {
		return this.thoiGianXacNhan;
	}

	public void setThoiGianXacNhan(Date thoiGianXacNhan) {
		this.thoiGianXacNhan = thoiGianXacNhan;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianSua", length = 19)
	public Date getThoiGianSua() {
		return this.thoiGianSua;
	}

	public void setThoiGianSua(Date thoiGianSua) {
		this.thoiGianSua = thoiGianSua;
	}

	@Column(name = "Status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
