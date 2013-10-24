package dao;

import java.sql.*;
import java.util.*;

import javax.persistence.*;

import util.JpaUtil;
import model.Unit;
import dao.AbstractDao;

public class UnitDao {

	private static void close(EntityManager em) {
		if (em != null) em.close();
	}

	public static List<Unit> findAllUnits(){ //KORRAS
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();

			TypedQuery<Unit> query = em.createQuery("SELECT p FROM Unit p", Unit.class);
			return query.getResultList();
		} finally {
			close(em);
		}
	}

	public static void insertUnit(String name, String code) { //KORRAS
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			em.getTransaction().begin();

			Unit unit = new Unit();
			unit.setName(name);
			unit.setCode(code);
//			unit.setId(id);
//			unit.setSuper_unit_id(super_unit_id);
			em.persist(unit);

			em.getTransaction().commit();
		} finally {
			close(em);
		}
	}

	public static void deleteUnitList(){ //KORRAS
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			em.getTransaction().begin();
			 Query query = em.createQuery("DELETE FROM Unit u WHERE u.id is not empty");
			query.executeUpdate();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void deleteUnit(Long id){ //KORRAS
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			em.getTransaction().begin();
			
			Unit unitx = em.find(Unit.class, id);
			if (unitx!=null) 
				em.remove(unitx);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	public void loadUnit(){
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			
			Unit u = em.find(Unit.class, 1L);
			
		}finally{
			em.close();
		}
	} 
	
	public void changeUnit(){ //KORRAS
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			em.getTransaction().begin();

			Unit unitx = em.find(Unit.class, 2L);
			if (unitx!=null) 
				((Unit) em).setName(unitx.getName());
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public Unit findByName(String name) { //KORRAS
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();

			TypedQuery<Unit> query = em.createQuery(
					"select p from Unit p where p.name = :name", Unit.class);
			query.setParameter("name", name);
			return query.getSingleResult();

		} finally {
			close(em);
		}
	}


	public void save(Unit unit){
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			em.getTransaction().begin();
			if(unit.getId() == null){
				em.persist(unit);
			} else {
				em.merge(unit);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public static void persist(String name) {
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			em.getTransaction().begin();

			Unit unit = new Unit();
			unit.setName(name);
			em.persist(unit);

			em.getTransaction().commit();
		} finally {
			close(em);
		}
	}
}
