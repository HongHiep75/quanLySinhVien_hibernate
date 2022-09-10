package dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import chuc.nang.chung.IOData;
import enity.LaiXe;
import hibernate.util.HibernateUtil;

public class LaiXeDao implements IOData<LaiXe> {
//	JDBCConnectionException
	@Override
	public Set<LaiXe> getLists() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "FROM NHAN_VIEN";
		Query q = session.createQuery(sql, LaiXe.class);
		List<LaiXe> lists = new ArrayList<LaiXe>();
		try {
			lists = q.getResultList();
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
