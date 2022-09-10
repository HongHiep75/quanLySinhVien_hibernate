package danh.sach;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import dao.LaiXeDao;
import dao.PhanCongLaiXeDao;
import dao.TuyenDao;
import enity.LaiXe;
import enity.LichTrinhLamViecLaiXe;
import enity.PhanCong;
import enity.Tuyen;
import run.QuanLyPhanCong;

public class DanhSachPhanCong extends DanhSach<LichTrinhLamViecLaiXe> {

	public DanhSachPhanCong() {
		this.chuyenDoiLaiXeSangDanhSachPhanCong();
	}

	// them thong tin phan cong
	public void themmDanhSachPhanCong(DanhSachLaiXe danhSachLaiXe, DanhSachTuyen danhSachTuyen) {
		if (danhSachLaiXe.isEmpty()) {
			danhSachLaiXe.setDanhSach(new LaiXeDao().getLists());
		}
		if (danhSachTuyen.isEmpty()) {
			danhSachTuyen.setDanhSach(new TuyenDao().getLists());
		}
		// kiem tra xem danh sach co phan tu hay khong
		// neu khong dung lai
		if (danhSachLaiXe.isEmpty() || danhSachTuyen.isEmpty()) {
			System.out.println("Danh sách lái xe hoặc danh sách tuyến trống");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Chọn lai xe bằng cách nhập mã lai xe");
		int maNhanVien = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		LaiXe laiXe = danhSachLaiXe.traRaDoiTuongTheoMa(new LaiXe(maNhanVien));
		LichTrinhLamViecLaiXe lichTrinhLamViecLaiXe = new LichTrinhLamViecLaiXe(laiXe);
		// kiem tra trong danh sach phan cong da ton tai lai xe chua
		while (laiXe == null || this.contains(lichTrinhLamViecLaiXe) || !laiXe.getPhanCong().isEmpty()) {
			System.out.println("Nhân viên không tồn tại hoặc nhân viên đã được thêm vui lòng nhập lại");
			maNhanVien = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			laiXe = danhSachLaiXe.traRaDoiTuongTheoMa(new LaiXe(maNhanVien));
			lichTrinhLamViecLaiXe = new LichTrinhLamViecLaiXe(laiXe);
		}
		PhanCong phanCong = new PhanCong();
		phanCong.setLaixe(laiXe);
		System.out.println("Thêm công việc cho nhân viên " + laiXe.getHoVaTen());

		System.out.println("Muốn thêm bao nhiêu tuyến ? ");

		int soLuongTuyen = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		for (int i = 0; i < soLuongTuyen; i++) {
			System.out.println("Nhập mã tuyến");
			int maTuyen = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			Tuyen tuyen = danhSachTuyen.traRaDoiTuongTheoMa(new Tuyen(maTuyen));
			while (tuyen == null) {
				System.out.println("Nhập lại mã tuyến, mã không tồn tại");
				maTuyen = QuanLyPhanCong.kiemTraDauVaoInt(sc);
				tuyen = danhSachTuyen.traRaDoiTuongTheoMa(new Tuyen(maTuyen));
			}
			phanCong.setTuyen(tuyen);
			System.out.println("Nhập số lần đi");
			int soLuot = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			while (soLuot > 15) {
				System.out.println("Nhập số lần không quá 15");
				soLuot = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			}
			phanCong.setSoLanDi(soLuot);
			lichTrinhLamViecLaiXe.themPhanCong(tuyen, soLuot);

			boolean check = new PhanCongLaiXeDao().save(phanCong);
			if (!check) {
				System.out.println("Khong thanh cong kiem tra lai ma tuyen ");
				i--;
				continue;
			}
			this.add(lichTrinhLamViecLaiXe);
		}
	}

	@Override
	public void hienThongTin() {
		this.getDanhSach().stream().forEach(LichTrinhLamViecLaiXe::hienThongTin);

	}

	public void chuyenDoiLaiXeSangDanhSachPhanCong() {
		Set<LaiXe> listLaiXe = new LaiXeDao().getLists();
		// loc cac lai xe da duoc phan cong vao list
		List<LaiXe> list = listLaiXe.stream().filter(LaiXe::kiemTraPhanCong).collect(Collectors.toList());
		// chuyen thong tin lai xe, tuyen ,so lan di vao lamViecLaiXe
		// them vao danh sach
		list.forEach(laiXe -> {
			LichTrinhLamViecLaiXe lamViecLaiXe = new LichTrinhLamViecLaiXe();
			lamViecLaiXe.setLaiXe(laiXe);
			laiXe.getPhanCong().forEach(p -> {
				lamViecLaiXe.themPhanCong(p.getTuyen(), p.getSoLanDi());
			});
			this.add(lamViecLaiXe);
		});
	}

	public void sapXepTheoTen() {
		if (this.isEmpty())
			return;
		Set<LichTrinhLamViecLaiXe> list = this.getDanhSach().stream()
				.sorted(Comparator.comparing(LichTrinhLamViecLaiXe::getHoVaTen))
				.collect(Collectors.toCollection(LinkedHashSet::new));
		this.setDanhSach(list);

	}

	public void sapXepTheoSoLuongTuyen() {
		if (this.isEmpty())
			return;
		Set<LichTrinhLamViecLaiXe> list = this.getDanhSach().stream()
				.sorted(Comparator.comparing(LichTrinhLamViecLaiXe::soLuongTuyen).reversed())
				.collect(Collectors.toCollection(LinkedHashSet::new));
		this.setDanhSach(list);

	}

	public boolean sapXepDanhSach() {
		if (this.isEmpty()) {
			return false;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Chọn kiểu sắp xếp");
		System.out.println("1. Theo họ tên lái xe");
		System.out.println("2.Theo số lượng tuyến (giảm dần)");
		int chon = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		while (chon < 1 || chon > 2) {
			System.out.println("Vui lòng chọn lại");
			chon = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		}

		if (chon == 1) {
			this.sapXepTheoTen();
			this.hienThongTin();
		} else {
			this.sapXepTheoSoLuongTuyen();
			this.hienThongTin();

		}
		return true;

	}

	public boolean tongKetQuangDuongLaiXe() {
		if (this.isEmpty()) {
			return false;
		}
		this.getDanhSach().forEach(t -> {
			t.getLaiXe().hienThongTin();
			System.out.println("Tổng khoảng cách chạy trong ngày: " + t.tongKhoangCachChay());

		});
		return true;

	}

	public boolean chucNangThemPhanCong(DanhSachLaiXe danhSachLaiXe, DanhSachTuyen danhSachTuyen) {
		try {
			this.themmDanhSachPhanCong(danhSachLaiXe, danhSachTuyen);
			this.hienThongTin();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
