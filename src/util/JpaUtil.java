package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;

public class JpaUtil {

	private static EntityManagerFactory emf;

	static {
		emf = Persistence.createEntityManagerFactory("my-hsql-unit",
				new PropertyLoader().getPropertiesAsMap());
	}

	public static EntityManagerFactory getFactory() {
		return emf;
	}

	public static void closeFactory() {
		emf.close();
	}

	public void contextDestroyed(ServletContextEvent event) {
		JpaUtil.getFactory().close();
	}
}
