package enity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import chuc.nang.chung.HienThongTin;
@Entity(name = "TUYEN")
public class Tuyen implements HienThongTin,Serializable {
    @Transient
	private static int AUTO = 100;
    @Id
    @Column(name = "ID_TUYEN")
	private int maTuyen;
    @Column(name = "KHOANG_CACH")
	private double khoangCach;
    @Column(name = "DIEM_DUNG")
	private int soDiemDung;


	public Tuyen(int maTuyen, double khoangCach, int soDiemDung) {
		super();
		this.maTuyen = maTuyen;
		this.khoangCach = khoangCach;
		this.soDiemDung = soDiemDung;
	}

	public Tuyen(int maTuyen) {
		this.maTuyen = maTuyen;
	}
	public Tuyen() {
	}
	public Tuyen(double khoangCach, int soDiemDung) {
		super();
		this.maTuyen = AUTO++;
		this.khoangCach = khoangCach;
		this.soDiemDung = soDiemDung;
	}

	public static int getAUTO() {
		return AUTO;
	}

	public static void setAUTO(int aUTO) {
		AUTO = aUTO;
	}

	public int getMaTuyen() {
		return maTuyen;
	}

	public void setMaTuyen(int maTuyen) {
		this.maTuyen = maTuyen;
	}

	public double getKhoangCach() {
		return khoangCach;
	}

	public void setKhoangCach(double khoangCach) {
		this.khoangCach = khoangCach;
	}

	public int getSoDiemDung() {
		return soDiemDung;
	}

	public void setSoDiemDung(int soDiemDung) {
		this.soDiemDung = soDiemDung;
	}

	@Override
	public void hienThongTin() {
		StringBuilder builder = new StringBuilder();
		builder.append(" + maTuyen: ");
		builder.append(this.maTuyen);
		builder.append(" - Khoảng Cách: ");
		builder.append(this.khoangCach);
		builder.append(" - Số điểm dừng: ");
		builder.append(this.soDiemDung);
		System.out.println(builder.toString());

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.maTuyen);
		builder.append("-");
		builder.append(this.khoangCach);
		builder.append("-");
		builder.append(this.soDiemDung);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTuyen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuyen other = (Tuyen) obj;
		return maTuyen == other.maTuyen;
	}

}
