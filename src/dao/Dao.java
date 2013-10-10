package dao;

import java.sql.*;
import java.util.*;

import db.Unit;
import dao.AbstractDao;

public class Dao extends AbstractDao {

	public List<Unit> getAllUnits() throws SQLException {
		
		 List<Unit> data = new ArrayList<Unit>(); 
		  try {
			  st = getConnection().createStatement(); 
			  rs = st.executeQuery("SELECT * FROM data");
			  
			  while(rs.next()){
				  		int id = rs.getInt(1);
				  		String name = rs.getString(2);
				  		String code = rs.getString(3);
				  		Unit unit = new Unit(name, code); 
		        		unit.setId(id);
		        		data.add(unit);
		        }
		  } finally {
			  closeResources(); 

		    }
		return data; 
	}
	
	public List<Unit> searchUnit(String keyword) throws SQLException{
		List<Unit> data = new ArrayList<Unit>();
		
		try {
			st = getConnection().createStatement();
			pst = connection.prepareStatement("SELECT * FROM unit WHERE LCASE(NAME) LIKE ?");
			pst.setString(1, "%" + keyword.toLowerCase() + "%");
			rs = pst.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String code = rs.getString(3);
				Unit unit = new Unit(name, code);
				unit.setId(id);
				data.add(unit);
			}
		} finally {
			closeResources();
		}
		
		return data;
	}

	public int addUnit(Unit unit){
		int result = -1;
		
		try {
			st = getConnection().createStatement();
			pst = connection.prepareStatement("INSERT INTO UNIT VALUES(NEXT VALUE FOR seq1, ?,?)");
			pst.setString(1, unit.getName());
			pst.setString(2, unit.getCode());
			result = pst.executeUpdate();
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			closeResources();
		}
		
		return result;
	}
	
	public void flushDataDb(){
		try {
			st = getConnection().createStatement();
			st.execute("TRUNCATE SCHEMA public AND COMMIT");
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			closeResources();
		}
	}

	public boolean deleteUnit(int id){
		boolean done = false;
		
		try {
			st = getConnection().createStatement();
			pst = connection.prepareStatement("DELETE FROM unit WHERE ID = ?");
			pst.setInt(1, id);
			done = pst.execute();
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			closeResources();
		}
		
		return done;
	}
}
