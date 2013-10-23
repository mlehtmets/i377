package dao;

import java.sql.*;
import java.util.*;

import javax.persistence.*;

import util.JpaUtil;
import model.Unit;
import dao.AbstractDao;

public class Dao extends AbstractDao {

	public List<Unit> getAllUnits() throws SQLException {

		List<Unit> units = new ArrayList<Unit>(); 
		try {
			st = getConnection().createStatement(); 
			rs = st.executeQuery("SELECT * FROM unit");

			while(rs.next()){
				Unit unit = new Unit();
				unit.setId(rs.getInt(1));
				unit.setName(rs.getString(2));
				unit.setCode(rs.getString(3));
				units.add(unit);
			}
		} finally {
			closeResources(); 

		}
		return units; 
	}

	public List<Unit> searchUnit(String name) throws SQLException{
		List<Unit> units = new ArrayList<Unit>();

		try {
			pst = getConnection().prepareStatement("SELECT * FROM unit WHERE UPPER(name) LIKE ?");
			pst.setString(1, "%"+name.toUpperCase()+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				Unit unit = new Unit();
				unit.setId(rs.getInt(1));
				unit.setName(rs.getString(2));
				unit.setCode(rs.getString(3));
				units.add(unit);
			}
		} finally {
			closeResources();
		}

		return units;
	}

	public void addUnit(Unit unit) throws SQLException{
		try {
			pst = getConnection().prepareStatement("INSERT INTO unit (id, name, code) VALUES (NEXT VALUE FOR seq1, ?, ?)");
			pst.setString(1, unit.getName());
			pst.setString(2, unit.getCode());
			pst.execute();
		} finally {
			closeResources();
		}
	}



	public void deleteList() throws SQLException{
		try{
			st = getConnection().createStatement();
			rs = st.executeQuery("TRUNCATE SCHEMA public and COMMIT");
		} finally {
			closeResources();
		}
	}




	public void deleteUnit(int id) throws SQLException {
		try{
			st = getConnection().createStatement();
			rs = st.executeQuery("DELETE FROM unit WHERE id = " + id);
		} finally {
			closeResources();
		}
}
	
//	public List<Unit> findAllUnits(){
//	EntityManager em = null;
//	try {
//		em = JpaUtil.getFactory().createEntityManager();
//
//		TypedQuery<Unit> query = em.createQuery("SELECT p FROM unit p", Unit.class);
//		return query.getResultList();
//	} finally {
//		em.close();
//	}
//}

//public Unit findById(int id) {
//	EntityManager em = null;
//	try {
//		em = JpaUtil.getFactory().createEntityManager();
//		return em.find(Unit.class, id);
//	} finally {
//		em.close();
//	}
//}

//	public void insertUnit(String name, String code, int id) {
//	EntityManager em = null;
//	try {
//		em = JpaUtil.getFactory().createEntityManager();
//		em.getTransaction().begin();
//
//		Unit unit = new Unit();
//		unit.setName(name);
//		unit.setCode(code);
//		unit.setId(id);
//		em.persist(unit);
//
//		em.getTransaction().commit();
//	} finally {
//		em.close();
//	}
//}
	
//	public void deleteUnitList(){
//	EntityManager em = null;
//	try {
//		em = JpaUtil.getFactory().createEntityManager();
//		em.createQuery("TRUNCATE SCHEMA public and COMMIT", Unit.class);
//	} finally {
//		em.close();
//	}
//}
	
//	public void deleteUnit(int id){
//		EntityManager em = null;
//		try {
//			em = JpaUtil.getFactory().createEntityManager();
//			em.getTransaction().begin();
//
//			Unit unit = em.find(Unit.class, id);
//			if (unit!=null) em.remove(unit);
//
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//	}
//	
//	public void save(Unit unit){
//		EntityManager em = null;
//		try {
//			em = JpaUtil.getFactory().createEntityManager();
//			em.getTransaction().begin();
//			if(unit.getId() == null){
//				em.persist(unit);
//			} else {
//				em.merge(unit);
//			}
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//	}

}
