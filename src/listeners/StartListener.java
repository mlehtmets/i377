package listeners;


import java.io.File;
import java.sql.*;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import dao.AbstractDao;
import dao.SetupDao;

public class StartListener implements ServletContextListener {

    public StartListener() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        SetupDao setup = new SetupDao();
        if(!databaseExists()){
        	System.out.println("Cannot find database, creating new");
            setup.createSchema();
            setup.insertTestData();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    	
    }
    
    private boolean databaseExists() {
        return new File(System.getProperty("user.home") + "/data/mlehtmets/db.script").exists();
     }
}