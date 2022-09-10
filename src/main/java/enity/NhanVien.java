package enity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


import chuc.nang.chung.HienThongTin;


@MappedSuperclass
public abstract class NhanVien implements HienThongTin,Serializable {
	@Transient
	private static int AUTO = 10000;
	@Id
	@Column(name = "MA_NV")
	private int maNhaVien;
	@Column(name = "HO_TEN")
	private String hoVaTen;
	@Column(name = "DIA_CHI")
	private String diaChi;
	@Column(name = "SO_DIEN_THOAI")
	private String soDienThoai;

	public NhanVien() {
		super();
	}

	public NhanVien(String hoVaTen, String diaChi, String soDienThoai) {
		this.maNhaVien = AUTO++;
		this.hoVaTen = hoVaTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public NhanVien(int maNhaVien, String hoVaTen, String diaChi, String soDienThoai) {
		super();
		this.maNhaVien = maNhaVien;
		this.hoVaTen = hoVaTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}
	public NhanVien(int maNhaVien) {
		this.maNhaVien = maNhaVien;
	}
	
	public static int getAUTO() {
		return AUTO;
	}

	public static void setAUTO(int aUTO) {
		AUTO = aUTO;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public int getMaNhaVien() {
		return maNhaVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhaVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return maNhaVien == other.maNhaVien;
	}

}
