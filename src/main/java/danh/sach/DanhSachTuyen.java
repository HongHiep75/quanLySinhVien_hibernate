package danh.sach;

import java.util.Scanner;

import dao.TuyenDao;
import enity.LaiXe;
import enity.Tuyen;
import run.QuanLyPhanCong;

public class DanhSachTuyen extends DanhSach<Tuyen> {
  
	public DanhSachTuyen() {
		this.setDanhSach(new TuyenDao().getLists());
	}
	
	
	public int maTuyenMax() {
		if (this.getDanhSach().isEmpty())
			return LaiXe.getAUTO();
		int max = this.getDanhSach().stream().mapToInt(Tuyen::getMaTuyen).max().getAsInt();
		return ++max;
	}

	public void themDanhSachTuyen() {
		Tuyen.setAUTO(this.maTuyenMax());
		Scanner sc = new Scanner(System.in);
		System.out.println("Số lượng tuyến muốn thêm: ");
		int soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		while (soLuong <= 0) {
			System.out.println("Nhập lại số lượng (số lượng > 0): ");
			soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		}
		Tuyen tuyen;
		for (int i = 0; i < soLuong; i++) {
			tuyen = themTuyen(sc);
			this.add(tuyen);
			new TuyenDao().save(tuyen);
		}

	}

	private Tuyen themTuyen(Scanner sc) {
		System.out.println("----------------------------\n");
		System.out.println("Nhập khoảng cách: ");
		double khoangCach = QuanLyPhanCong.kiemTraDauVaoDouble(sc);
		System.out.println("Nhập số điểm dừng: ");
		int soDiemDung = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		Tuyen tuyen = new Tuyen(khoangCach, soDiemDung);
		return tuyen;
	}

	@Override
	public void hienThongTin() {
		this.getDanhSach().stream().forEach(Tuyen::hienThongTin);

	}

	public boolean chucNangThemTuyen() {
		try {
			this.themDanhSachTuyen();
			this.hienThongTin();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
