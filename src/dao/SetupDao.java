package dao;

import java.io.File;
import java.util.Properties;
import java.sql.*;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

import util.AntUtil;
import util.PropertyLoader;

public class SetupDao extends AbstractDao {
	
	public void createSchema() { //KORRAS
		String filePath = getClass().getResource("/schema.sql").getFile();
		System.out.println(filePath);
		executeSqlFromFile(filePath, new PropertyLoader().getProperties());
	}

	public void insertTestData() throws NullPointerException { //KORRAS
		String filePath = getClass().getResource("/testdata.sql").getFile();
		System.out.println(filePath);
			executeSqlFromFile(filePath, new PropertyLoader().getProperties());
	}

	  public void destroy() {
          executeQuery("DROP SCHEMA PUBLIC CASCADE;");
  }

	private void executeSqlFromFile(String sqlFilePath, Properties prop) {
		SQLExec e = AntUtil.getTask(SQLExec.class, "sql");
		e.setSrc(new File(sqlFilePath));
		e.setDriver("org.hsqldb.jdbcDriver");
		e.setUserid("sa");
		e.setPassword("");
		e.setUrl(prop.getProperty("javax.persistence.jdbc.url"));
		e.execute();
	}
}
