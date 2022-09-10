package dao;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import chuc.nang.chung.IOData;
import enity.LaiXe;
import enity.PhanCong;
import hibernate.util.HibernateUtil;


public class PhanCongLaiXeDao implements IOData<PhanCong> {

	@Override
	public Set<PhanCong> getLists() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "FROM PHAN_CONG_LAI_XE";
		Query q = session.createQuery(sql,PhanCong.class);
		List<PhanCong> lists = new ArrayList<>();
		try {
			lists =	q.getResultList();
		} catch (JDBCConnectionException e) {
			System.out.println("Ket noi database khong thanh cong");

		} catch (SQLGrammarException e) {
			System.out.println("Loi cu phap sql");
		} catch (HibernateException e) {

		} catch (Exception e) {

		} finally {
			session.close();
		}
		if (lists.isEmpty()) {
			return new LinkedHashSet<>();
		}	 
		return new LinkedHashSet<>(lists);
	}	

}
