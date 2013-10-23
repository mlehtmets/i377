package dao;

import java.io.File;
import java.util.Properties;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

import util.AntUtil;
import util.PropertyLoader;

public class SetupDao extends AbstractDao {
	
    public void createSchema() { 
    	String filePath = getClass().getResource("/schema.sql").getFile();

        executeSqlFromFile(filePath, new PropertyLoader().getProperties());
    }

    public void insertTestData() { 
    	String filePath = getClass().getResource("/testdata.sql").getFile();

        executeSqlFromFile(filePath, new PropertyLoader().getProperties()); 
    }
    
    public void destroy() {
		executeQuery("DROP SCHEMA PUBLIC CASCADE;");
	}
    
    private void executeSqlFromFile(String sqlFilePath, Properties prop) {

        Project project = new Project();
        project.init();

        SQLExec e = AntUtil.getTask(SQLExec.class, "sql");
        e.setProject(project);
        e.setTaskType("sql");
        e.setTaskName("sql");
        e.setSrc(new File(sqlFilePath));
        e.setDriver("org.hsqldb.jdbcDriver");
        e.setUserid("sa");
        e.setPassword("");
        e.setUrl(prop.getProperty("javax.persistence.jdbc.url"));
        e.execute();
    }
}
