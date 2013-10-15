package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.SetupDao;

public class StartListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
    	SetupDao setup = new SetupDao();
    	setup.destroy();
    	setup.createSchema();
    	setup.insertTestData();
	}
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}