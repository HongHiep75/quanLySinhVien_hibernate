package enity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "NHAN_VIEN")
public class LaiXe extends NhanVien implements Serializable {
	
	@Column(name = "TRINH_DO")
	private String trinhDo;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "PHAN_CONG_LAI_XE",
//			joinColumns = {@JoinColumn(name = "ID_NV", referencedColumnName = "MA_NV")},
//					inverseJoinColumns = {@JoinColumn(name = "ID_TUYEN", referencedColumnName = "ID_TUYEN")})
//	@MapKeyClass(Tuyen.class)
//	@Column(name = "SO_LAN_DI")
//	private Map<Tuyen, Integer> phanCong = new HashMap<>();
//	
//	
//	
//	public Map<Tuyen, Integer> getPhanCong() {
//		return phanCong;
//	}
//
//	public void setPhanCong(Map<Tuyen, Integer> phanCong) {
//		this.phanCong = phanCong;
//	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_NV")
	Set<PhanCong> phanCong = new LinkedHashSet<PhanCong>();
	public LaiXe() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Set<PhanCong> getPhanCong() {
		return phanCong;
	}


	public void setPhanCong(Set<PhanCong> phanCong) {
		this.phanCong = phanCong;
	}
     public boolean kiemTraPhanCong() {
    	 return !this.getPhanCong().isEmpty();
     }

	public LaiXe(String hoVaTen, String diaChi, String soDienThoai, String trinhDo) {
		super(hoVaTen, diaChi, soDienThoai);
		this.trinhDo = trinhDo;
	}

	public LaiXe(int maNhaVien, String hoVaTen, String diaChi, String soDienThoai, String trinhDo) {
		super(maNhaVien, hoVaTen, diaChi, soDienThoai);
		this.trinhDo = trinhDo;
	}

	public LaiXe(int maNhaVien) {
		super(maNhaVien, null, null, null);
	}

	public String getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}

	@Override
	public void hienThongTin() {
		StringBuilder builder = new StringBuilder();
		builder.append("- maNV: ");
		builder.append(this.getMaNhaVien());
		builder.append(" - Họ và tên: ");
		builder.append(this.getHoVaTen());
		builder.append(" - Địa chỉ: ");
		builder.append(this.getDiaChi());

		builder.append(" - Số điện thoại: ");
		builder.append(this.getSoDienThoai());
		builder.append(" - Trình độ: ");
		builder.append(this.getTrinhDo());
		System.out.println(builder.toString());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(this.getMaNhaVien());
		builder.append("-");
		builder.append(this.getHoVaTen());
		builder.append("-");
		builder.append(this.getDiaChi());
		builder.append("-");
		builder.append(this.getSoDienThoai());
		builder.append("-");
		builder.append(this.getTrinhDo());
		return builder.toString();
	}

}
