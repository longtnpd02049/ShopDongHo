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
 * Chitietsp generated by hbm2java
 */
@Entity
@Table(name = "chitietsp", catalog = "duan2")
public class Chitietsp implements java.io.Serializable {

	private Integer idChiTietSp;
	private Danhmucsp danhmucsp;
	private Hieusp hieusp;
	private Users users;
	private Integer idDanhMuc2;
	private String tenSp;
	private double giaSp;
	private int soLuongDat;
	private int soLuongSp;
	private Date thoiGianTaoSp;
	private Date thoiGianSuaSp;
	private String dacDiemNoiBat;
	private String loaiMay;
	private String kieuDang;
	private String matKinh;
	private String vo;
	private String duongKinh;
	private String doDay;
	private String doChiuNuoc;
	private String baoHanh;
	private String status;
	private String image;
	private String moTaThem;
	private Set<Gallery> galleries = new HashSet<Gallery>(0);
	private Set<Binhluan> binhluans = new HashSet<Binhluan>(0);
	private Set<Chitietdathang> chitietdathangs = new HashSet<Chitietdathang>(0);

	public Chitietsp() {
	}

	public Chitietsp(Danhmucsp danhmucsp, Hieusp hieusp, Users users, String tenSp, double giaSp, int soLuongDat,
			int soLuongSp, Date thoiGianTaoSp, String dacDiemNoiBat, String loaiMay, String kieuDang, String matKinh,
			String vo, String duongKinh, String doDay, String doChiuNuoc, String baoHanh, String status, String image) {
		this.danhmucsp = danhmucsp;
		this.hieusp = hieusp;
		this.users = users;
		this.tenSp = tenSp;
		this.giaSp = giaSp;
		this.soLuongDat = soLuongDat;
		this.soLuongSp = soLuongSp;
		this.thoiGianTaoSp = thoiGianTaoSp;
		this.dacDiemNoiBat = dacDiemNoiBat;
		this.loaiMay = loaiMay;
		this.kieuDang = kieuDang;
		this.matKinh = matKinh;
		this.vo = vo;
		this.duongKinh = duongKinh;
		this.doDay = doDay;
		this.doChiuNuoc = doChiuNuoc;
		this.baoHanh = baoHanh;
		this.status = status;
		this.image = image;
	}

	public Chitietsp(Danhmucsp danhmucsp, Hieusp hieusp, Users users, Integer idDanhMuc2, String tenSp, double giaSp,
			int soLuongDat, int soLuongSp, Date thoiGianTaoSp, Date thoiGianSuaSp, String dacDiemNoiBat, String loaiMay,
			String kieuDang, String matKinh, String vo, String duongKinh, String doDay, String doChiuNuoc,
			String baoHanh, String status, String image, String moTaThem, Set<Gallery> galleries,
			Set<Binhluan> binhluans, Set<Chitietdathang> chitietdathangs) {
		this.danhmucsp = danhmucsp;
		this.hieusp = hieusp;
		this.users = users;
		this.idDanhMuc2 = idDanhMuc2;
		this.tenSp = tenSp;
		this.giaSp = giaSp;
		this.soLuongDat = soLuongDat;
		this.soLuongSp = soLuongSp;
		this.thoiGianTaoSp = thoiGianTaoSp;
		this.thoiGianSuaSp = thoiGianSuaSp;
		this.dacDiemNoiBat = dacDiemNoiBat;
		this.loaiMay = loaiMay;
		this.kieuDang = kieuDang;
		this.matKinh = matKinh;
		this.vo = vo;
		this.duongKinh = duongKinh;
		this.doDay = doDay;
		this.doChiuNuoc = doChiuNuoc;
		this.baoHanh = baoHanh;
		this.status = status;
		this.image = image;
		this.moTaThem = moTaThem;
		this.galleries = galleries;
		this.binhluans = binhluans;
		this.chitietdathangs = chitietdathangs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IdChiTietSP", unique = true, nullable = false)
	public Integer getIdChiTietSp() {
		return this.idChiTietSp;
	}

	public void setIdChiTietSp(Integer idChiTietSp) {
		this.idChiTietSp = idChiTietSp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdDanhMuc", nullable = false)
	public Danhmucsp getDanhmucsp() {
		return this.danhmucsp;
	}

	public void setDanhmucsp(Danhmucsp danhmucsp) {
		this.danhmucsp = danhmucsp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdHieuSP", nullable = false)
	public Hieusp getHieusp() {
		return this.hieusp;
	}

	public void setHieusp(Hieusp hieusp) {
		this.hieusp = hieusp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUser", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "IdDanhMuc2")
	public Integer getIdDanhMuc2() {
		return this.idDanhMuc2;
	}

	public void setIdDanhMuc2(Integer idDanhMuc2) {
		this.idDanhMuc2 = idDanhMuc2;
	}

	@Column(name = "TenSP", nullable = false)
	public String getTenSp() {
		return this.tenSp;
	}

	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}

	@Column(name = "GiaSP", nullable = false, precision = 22, scale = 0)
	public double getGiaSp() {
		return this.giaSp;
	}

	public void setGiaSp(double giaSp) {
		this.giaSp = giaSp;
	}

	@Column(name = "SoLuongDat", nullable = false)
	public int getSoLuongDat() {
		return this.soLuongDat;
	}

	public void setSoLuongDat(int soLuongDat) {
		this.soLuongDat = soLuongDat;
	}

	@Column(name = "SoLuongSP", nullable = false)
	public int getSoLuongSp() {
		return this.soLuongSp;
	}

	public void setSoLuongSp(int soLuongSp) {
		this.soLuongSp = soLuongSp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianTaoSP", nullable = false, length = 19)
	public Date getThoiGianTaoSp() {
		return this.thoiGianTaoSp;
	}

	public void setThoiGianTaoSp(Date thoiGianTaoSp) {
		this.thoiGianTaoSp = thoiGianTaoSp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ThoiGianSuaSP", length = 19)
	public Date getThoiGianSuaSp() {
		return this.thoiGianSuaSp;
	}

	public void setThoiGianSuaSp(Date thoiGianSuaSp) {
		this.thoiGianSuaSp = thoiGianSuaSp;
	}

	@Column(name = "DacDiemNoiBat", nullable = false)
	public String getDacDiemNoiBat() {
		return this.dacDiemNoiBat;
	}

	public void setDacDiemNoiBat(String dacDiemNoiBat) {
		this.dacDiemNoiBat = dacDiemNoiBat;
	}

	@Column(name = "LoaiMay", nullable = false)
	public String getLoaiMay() {
		return this.loaiMay;
	}

	public void setLoaiMay(String loaiMay) {
		this.loaiMay = loaiMay;
	}

	@Column(name = "KieuDang", nullable = false)
	public String getKieuDang() {
		return this.kieuDang;
	}

	public void setKieuDang(String kieuDang) {
		this.kieuDang = kieuDang;
	}

	@Column(name = "MatKinh", nullable = false)
	public String getMatKinh() {
		return this.matKinh;
	}

	public void setMatKinh(String matKinh) {
		this.matKinh = matKinh;
	}

	@Column(name = "Vo", nullable = false)
	public String getVo() {
		return this.vo;
	}

	public void setVo(String vo) {
		this.vo = vo;
	}

	@Column(name = "DuongKinh", nullable = false)
	public String getDuongKinh() {
		return this.duongKinh;
	}

	public void setDuongKinh(String duongKinh) {
		this.duongKinh = duongKinh;
	}

	@Column(name = "DoDay", nullable = false)
	public String getDoDay() {
		return this.doDay;
	}

	public void setDoDay(String doDay) {
		this.doDay = doDay;
	}

	@Column(name = "DoChiuNuoc", nullable = false)
	public String getDoChiuNuoc() {
		return this.doChiuNuoc;
	}

	public void setDoChiuNuoc(String doChiuNuoc) {
		this.doChiuNuoc = doChiuNuoc;
	}

	@Column(name = "BaoHanh", nullable = false)
	public String getBaoHanh() {
		return this.baoHanh;
	}

	public void setBaoHanh(String baoHanh) {
		this.baoHanh = baoHanh;
	}

	@Column(name = "Status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "Image", nullable = false)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "MoTaThem")
	public String getMoTaThem() {
		return this.moTaThem;
	}

	public void setMoTaThem(String moTaThem) {
		this.moTaThem = moTaThem;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chitietsp", cascade = CascadeType.ALL)
	public Set<Gallery> getGalleries() {
		return this.galleries;
	}

	public void setGalleries(Set<Gallery> galleries) {
		this.galleries = galleries;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chitietsp",cascade = CascadeType.ALL)
	public Set<Binhluan> getBinhluans() {
		return this.binhluans;
	}

	public void setBinhluans(Set<Binhluan> binhluans) {
		this.binhluans = binhluans;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chitietsp",cascade = CascadeType.ALL)
	public Set<Chitietdathang> getChitietdathangs() {
		return this.chitietdathangs;
	}

	public void setChitietdathangs(Set<Chitietdathang> chitietdathangs) {
		this.chitietdathangs = chitietdathangs;
	}

}
