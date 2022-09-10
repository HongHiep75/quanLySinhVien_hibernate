package enity;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import chuc.nang.chung.HienThongTin;


public class LichTrinhLamViecLaiXe implements HienThongTin,Serializable {

	private LaiXe laiXe;
	
	private Map<Tuyen, Integer> phanCong = new HashMap<>();

	public LichTrinhLamViecLaiXe(LaiXe laiXe) {
		super();
		this.laiXe = laiXe;
	}

	public LichTrinhLamViecLaiXe() {
	}

	public LichTrinhLamViecLaiXe(LaiXe laiXe, Map<Tuyen, Integer> phanCong) {
		super();
		this.laiXe = laiXe;
		this.phanCong = phanCong;
	}

	public LaiXe getLaiXe() {
		return laiXe;
	}

	public void setLaiXe(LaiXe laiXe) {
		this.laiXe = laiXe;
	}

	public Map<Tuyen, Integer> getPhanCong() {
		return phanCong;
	}
    
	public void setPhanCong(Map<Tuyen, Integer> phanCong) {
		this.phanCong = phanCong;
	}

	public String getHoVaTen() {
		return this.laiXe.getHoVaTen();
	}

	public int soLuongTuyen() {
		return this.phanCong.size();
	}

	
	
	public boolean themPhanCong(Tuyen tuyen, int soLuot) {
		if (this.phanCong.containsKey(tuyen) || soLuot > 15 || soLuot < 1) {
			return false;
		}
		this.phanCong.put(tuyen, soLuot);
		return true;

	}

	public double tongKhoangCachChay() {
		int tong = 0;
		for (Map.Entry<Tuyen, Integer> entry : this.phanCong.entrySet()) {
			tong += entry.getKey().getKhoangCach() * entry.getValue();
		}

		return tong;

	}

	@Override
	public int hashCode() {
		return Objects.hash(laiXe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichTrinhLamViecLaiXe other = (LichTrinhLamViecLaiXe) obj;
		return Objects.equals(laiXe, other.laiXe);
	}

	@Override
	public void hienThongTin() {
		if (this.laiXe == null)
			return;
		this.laiXe.hienThongTin();
		this.phanCong.forEach((k, v) -> {
			k.hienThongTin();
			System.out.println("   * Số lần đi: " + v);
		});

	}

}
