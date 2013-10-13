package listeners;


import java.io.File;
import java.sql.*;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import dao.AbstractDao;
import dao.SetupDao;

public class InitializerListener implements ServletContextListener {

    public InitializerListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextInitialized(ServletContextEvent arg0) {
        SetupDao setup = new SetupDao();
        if(!new File(System.getProperty("user.home") + AbstractDao.DB_URL + ".script").exists()){
        	System.out.println("Cannot find database, creating new");
            setup.createSchema();
            setup.insertTestData();
        }
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                System.out.println(String.format("Error deregistering driver %s: %s", driver, e));
            }

        }
    }
}