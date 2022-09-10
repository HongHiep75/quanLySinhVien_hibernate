package hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


//DESKTOP-HH4I0A5\\\\\\\\SQLEXPRESS:1433;databaseName=ShoppingDB;encrypt=false
public class HibernateUtil {
  private final static SessionFactory FACTORY;
  
  static {
	  Configuration conf = new Configuration();
	  Properties pros = new  Properties();
	  pros.put(Environment.DIALECT, "org.hibernate.dialect.OracleDialect");
	  pros.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
	  pros.put(Environment.URL, "jdbc:oracle:thin:@localhost:1521:xe");
	  pros.put(Environment.USER, "HIEP1");
	  pros.put(Environment.PASS, "12345");
	  
	  conf.setProperties(pros);
	  conf.addAnnotatedClass(enity.NhanVien.class);
	  conf.addAnnotatedClass(enity.LaiXe.class);
	  conf.addAnnotatedClass(enity.Tuyen.class);
	  conf.addAnnotatedClass(enity.PhanCong.class);
	  ServiceRegistry registry = new StandardServiceRegistryBuilder()
			                            .applySettings(conf.getProperties())
			                            .build();
	  FACTORY = conf.buildSessionFactory(registry);
  }
  
  public static SessionFactory getSessionFactory() {
	  return FACTORY;
  }
  
}
