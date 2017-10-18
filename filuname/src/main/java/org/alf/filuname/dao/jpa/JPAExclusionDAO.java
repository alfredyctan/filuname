package org.alf.filuname.dao.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.alf.filuname.dao.ExclusionDAO;
import org.alf.filuname.model.Exclusion;

public class JPAExclusionDAO implements ExclusionDAO {

	private EntityManagerFactory factory;

	public JPAExclusionDAO() {
	}

	public JPAExclusionDAO(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public JPAExclusionDAO(Map<String, Object> jpaProps) {
		this.factory = Persistence.createEntityManagerFactory("Hibernate", jpaProps);
	}

	@Override
	public List<Exclusion> getExclusions() {
		EntityManager entityManager = factory.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<org.alf.filuname.model.impl.Exclusion> criteria = builder.createQuery(org.alf.filuname.model.impl.Exclusion.class);
		Root<org.alf.filuname.model.impl.Exclusion> table = criteria.from(org.alf.filuname.model.impl.Exclusion.class);
		criteria.select(table);
		TypedQuery<org.alf.filuname.model.impl.Exclusion> query = entityManager.createQuery(criteria);
		List<org.alf.filuname.model.impl.Exclusion> results = query.getResultList();
		return (List)results;
	}
	
	@Override
	public void replace(List<Exclusion> exclusions) {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE Exclusion");
		
		entityManager.getTransaction().begin();
		query.executeUpdate();
		for (Exclusion exclusion : exclusions) {
			entityManager.persist(exclusion);
		}
		entityManager.getTransaction().commit();
	}

}
