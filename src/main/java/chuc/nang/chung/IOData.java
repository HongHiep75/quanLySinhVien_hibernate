package chuc.nang.chung;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;

import hibernate.util.HibernateUtil;


public interface IOData<T> {

	Set<T> getLists();

	default boolean save(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		try {
			session.save(t);
			session.getTransaction().commit();;
		}catch (JDBCConnectionException e) {
			System.out.println("Ket noi database khong thanh cong");
			session.getTransaction().rollback();
			return false;
		}catch (ConstraintViolationException e) {
			System.out.println("kiem tra lai id co the id da ton tai");
			session.getTransaction().rollback();
			return false;
		}catch (SQLGrammarException e) {
			System.out.println("Loi cu phap sql");
			session.getTransaction().rollback();
			return false;
		}catch (HibernateException e) {
			session.getTransaction().rollback();
			return false;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();;
			return false;
		} finally {
			session.close();
		}
		return true;
	}
}
