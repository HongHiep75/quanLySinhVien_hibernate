package danh.sach;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.LaiXeDao;
import enity.LaiXe;
import run.QuanLyPhanCong;

public class DanhSachLaiXe extends DanhSach<LaiXe> {

	public DanhSachLaiXe() {
		this.setDanhSach(new LaiXeDao().getLists());
	}

	@Override
	public void hienThongTin() {
		this.getDanhSach().stream().forEach(LaiXe::hienThongTin);

	}

	public int maNhanVienMax() {
		if (this.getDanhSach().isEmpty())
			return LaiXe.getAUTO();
		int max = this.getDanhSach().stream().mapToInt(LaiXe::getMaNhaVien).max().getAsInt();
		return ++max;
	}

	public void themNhanVien() {
		// cai dat lai ma AuTo de cho id tu tang chinh xac
		LaiXe.setAUTO(this.maNhanVienMax());
		Scanner sc = new Scanner(System.in);
		System.out.println("Số lượng lái xe muốn thêm: ");
		int soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		while (soLuong <= 0) {
			System.out.println("Nhập lại số lượng (số lượng > 0): ");
			soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		}
		LaiXe laiXe;
		for (int i = 0; i < soLuong; i++) {
			laiXe = themLaiXe(sc);
			this.add(laiXe);
			new LaiXeDao().save(laiXe);
		}

	}

	private LaiXe themLaiXe(Scanner sc) {
		System.out.println("----------------------------\n");
		System.out.println("Nhập tên Lái xe: ");
		String tenNhanVien = QuanLyPhanCong.kiemTraDauVaoString(sc);
		System.out.println("Nhập địa chỉ: ");
		String diaChi = sc.nextLine();
		System.out.println("Nhập số điện thoại: ");
		String soDienThoai = QuanLyPhanCong.kiemTraSoDienThoai(sc);
		System.out.println("Trình độ: ");
		String trinhDo = kiemTraTrinhDoInput(sc);
		LaiXe laiXe = new LaiXe(tenNhanVien, diaChi, soDienThoai, trinhDo);
		return laiXe;
	}

	private String kiemTraTrinhDoInput(Scanner sc) {
		Pattern p = Pattern.compile("[^a-fA-F]{1}$");
		String input = sc.nextLine();
		while (p.matcher(input).find()) {
			System.out.println("Thông tin không chính xác trình độ từ A tời F");
			input = sc.nextLine();
		}
		return input;
	}

	public boolean chucNangThemNhanVien() {
		try {
			this.themNhanVien();
			this.hienThongTin();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
