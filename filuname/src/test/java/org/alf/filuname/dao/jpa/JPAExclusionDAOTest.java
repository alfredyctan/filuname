package org.alf.filuname.dao.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.alf.filuname.model.HitCount;
import org.alf.filuname.model.Exclusion;
import org.alf.filuname.util.JUnit4Util;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/datasource.xml", "/exclusion-dao.xml"})
public class JPAExclusionDAOTest {

	@Autowired
	@Qualifier("exclusionDAO")
	private JPAExclusionDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetExclusions() {
		JUnit4Util.startCurrentTest(getClass());

		List<Exclusion> exclusions = new LinkedList<>(); 
		exclusions.add(new org.alf.filuname.model.impl.Exclusion("www.facebook.com", "2015-12-01", null));
		exclusions.add(new org.alf.filuname.model.impl.Exclusion("www.google.com", "2016-01-01", "2016-01-30"));
		exclusions.add(new org.alf.filuname.model.impl.Exclusion("mail.live.com", null, "2016-01-30"));
		
		List<Exclusion> actual = dao.getExclusions();

		for (Exclusion exclusion : actual) {
			System.out.println(exclusion);
		}
		
		assertThat("List Actual Is Subset Of Expect Without Order", actual,
			containsInAnyOrder(exclusions.toArray())
		);

		JUnit4Util.endCurrentTest(getClass());
	}
	
	@Test
	public void testReplace() {
		JUnit4Util.startCurrentTest(getClass());

		List<Exclusion> exclusions = new LinkedList<>(); 
		exclusions.add(new org.alf.filuname.model.impl.Exclusion("www.facebook.com.2", "2015-12-01", null));
		exclusions.add(new org.alf.filuname.model.impl.Exclusion("www.google.com.2", "2016-01-01", "2016-01-30"));
		exclusions.add(new org.alf.filuname.model.impl.Exclusion("mail.live.com.2", null, "2016-01-30"));
		
		dao.replace(exclusions);

		List<Exclusion> actual = dao.getExclusions();

		for (Exclusion exclusion : actual) {
			System.out.println(exclusion);
		}
		
		assertThat("List Actual Is Subset Of Expect Without Order", actual,
			containsInAnyOrder(exclusions.toArray())
		);

		JUnit4Util.endCurrentTest(getClass());
	}

}
