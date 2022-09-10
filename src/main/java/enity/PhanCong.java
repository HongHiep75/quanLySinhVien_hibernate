package enity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import chuc.nang.chung.HienThongTin;

@Entity(name = "PHAN_CONG_LAI_XE")
public class PhanCong implements Serializable, HienThongTin {
//	@Id
//	@Column(name = "ID_NV")
//	private int maNv;
//	@Id
//	@Column(name = "ID_TUYEN")
//	private int maTuyen;
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_NV")
	private LaiXe laixe;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_TUYEN")
	private Tuyen tuyen;
	
	
	
	public LaiXe getLaixe() {
		return laixe;
	}

	public void setLaixe(LaiXe laixe) {
		this.laixe = laixe;
	}

	public Tuyen getTuyen() {
		return tuyen;
	}

	public void setTuyen(Tuyen tuyen) {
		this.tuyen = tuyen;
	}

	@Column(name = "SO_LAN_DI")
	private int soLanDi;

//	public int getMaNv() {
//		return maNv;
//	}
//
//	public void setMaNv(int maNv) {
//		this.maNv = maNv;
//	}

	public int getSoLanDi() {
		return soLanDi;
	}

	public void setSoLanDi(int soLanDi) {
		this.soLanDi = soLanDi;
	}
//	 public int getMaTuyen() {
//		return maTuyen;
//	}
//	public void setMaTuyen(int maTuyen) {
//		this.maTuyen = maTuyen;
//	}

	@Override
	public void hienThongTin() {
//		System.out.println("  + maTuyen:" + this.getMaTuyen() + " so lan di:" + this.soLanDi);

	}

	@Override
	public int hashCode() {
		return Objects.hash(laixe, tuyen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhanCong other = (PhanCong) obj;
		return Objects.equals(laixe, other.laixe) && Objects.equals(tuyen, other.tuyen);
	}



}
