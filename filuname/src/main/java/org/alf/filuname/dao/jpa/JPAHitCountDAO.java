package org.alf.filuname.dao.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.alf.filuname.dao.HitCountDAO;
import org.alf.filuname.model.HitCount;

public class JPAHitCountDAO implements HitCountDAO {

	private EntityManagerFactory factory;

	public JPAHitCountDAO() {
	}
	
	public JPAHitCountDAO(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public JPAHitCountDAO(Map<String, Object> jpaProps) {
		this.factory = Persistence.createEntityManagerFactory("Hibernate", jpaProps);
	}

	@Override
	public List<HitCount> getTopHitCounts(String date, int rank) {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNativeQuery(
			"SELECT TOP ? * FROM HitCount WHERE visit_date = ? ORDER BY count DESC",
			org.alf.filuname.model.impl.HitCount.class
		);
		query.setParameter(1, rank);
		query.setParameter(2, date);
		return (List) query.getResultList();
	}

	@Override
	public List<HitCount> getHitCounts() {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNativeQuery(
			"SELECT * FROM HitCount",
			org.alf.filuname.model.impl.HitCount.class
		);
		return (List) query.getResultList();
	}
}
