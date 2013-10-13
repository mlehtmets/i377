package dao;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

public class SetupDao extends AbstractDao {

    public void createSchema() { 
        executeSqlFromFile(getClassPathFile("/andmed.sql")); 
        
        String query = 
        		"CREATE SEQUENCE seq1 AS INTEGER START WITH 1"
				+ "CREATE TABLE unit ("
				+ "id BIGINT NOT NULL PRIMARY KEY,"
				+ "name VARCHAR(255) NOT NULL,"
				+ "code VARCHAR(255) NOT NULL);";

        try {
        	st = getConnection().createStatement();
        	st.execute(query);
        } catch (Exception e){
        	throw new RuntimeException(e);
        } finally {
        	closeResources();
        }
    }

    public void insertTestData() { 
        executeSqlFromFile(getClassPathFile("/testandmed.sql")); 
        
        String query = 
        		"INSERT INTO UNIT VALUES(NEXT VALUE FOR seq1,'CEO','1');"
        		+ "INSERT INTO UNIT VALUES(NEXT VALUE FOR seq1,'Administration','1-1');"
        		+ "INSERT INTO UNIT VALUES(NEXT VALUE FOR seq1,'Legal','1-1-1');"
        		+ "INSERT INTO UNIT VALUES(NEXT VALUE FOR seq1,'Archives','1-1-2');"
        		+ "INSERT INTO UNIT VALUES(NEXT VALUE FOR seq1,'Production','1-2');"
        		+ "INSERT INTO UNIT VALUES(NEXT VALUE FOR seq1,'Sales','2');";

        try {
        	st = getConnection().createStatement();
        	st.execute(query);
        } catch (Exception e){
        	throw new RuntimeException(e);
        } finally {
        	closeResources();
        }
    }
    
    private String getClassPathFile(String fileName) { 
        return getClass().getClassLoader().getResource(fileName).getFile();
    }

    private void executeSqlFromFile(String sqlFilePath) {

        Project project = new Project();
        project.init();

        SQLExec e = new SQLExec();
        e.setProject(project);
        e.setTaskType("sql");
        e.setTaskName("sql");
        e.setSrc(new File(sqlFilePath));
        e.setDriver("org.hsqldb.jdbcDriver");
        e.setUserid("sa");
        e.setPassword("");
        e.setUrl(DB_URL);
        e.execute();
    }
}
