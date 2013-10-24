package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Unit;
import util.JpaUtil;
import dao.SetupDao;

public class Main {

	public static void main(String[] args) {
//		 new SetupDao().createSchema();
//		 new SetupDao().insertTestData();

		//insertUnit("Mart", "Mart", (long)1, (long)2);

		// findAllUnits();
		// deleteUnit((long)2);
		deleteUnitList();
		System.out.println(findAllUnits());

		JpaUtil.closeFactory();
	}

	private static void close(EntityManager em) {
		if (em != null) em.close();
	}

	public static void insertUnit(String name, String code, Long id, Long super_unit_id) {
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();
			em.getTransaction().begin();

			Unit unit = new Unit();
			unit.setName(name);
			unit.setCode(code);
			unit.setId(id);
			unit.setSuper_unit_id(super_unit_id);
			em.merge(unit);

			em.getTransaction().commit();
		} finally {
			close(em);
		}
	}

	public static List<Unit> findAllUnits(){
		EntityManager em = null;
		try {
			em = JpaUtil.getFactory().createEntityManager();

			TypedQuery<Unit> query = em.createQuery("SELECT p FROM Unit p", Unit.class);
			return query.getResultList();
		} finally {
			close(em);
		}
	}

	public static void deleteUnitList(){
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
	
	public static void deleteUnit(Long id){
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
	
	 public static Unit findByName(String name) {
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

}
