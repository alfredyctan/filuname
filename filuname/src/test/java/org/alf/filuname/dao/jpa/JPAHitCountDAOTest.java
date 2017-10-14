package org.alf.filuname.dao.jpa;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.alf.filuname.model.HitCount;
import org.alf.filuname.util.JUnit4Util;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JPAHitCountDAOTest {

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
	public void testGetTopHitCount() {
		JUnit4Util.startCurrentTest(getClass());

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/datasource.xml");
		
		DataSource dataSource = context.getBean("dataSource", DataSource.class);
		Map<String, Object> prop = new HashMap<>();
		prop.put("javax.persistence.nonJtaDataSource", dataSource);
		prop.put("javax.persistence.transactionType", "RESOURCE_LOCAL");

		JPAHitCountDAO dao = new JPAHitCountDAO(prop); 
		List<HitCount> actual = dao.getTopHitCount("2016-01-06", 3);
		
		System.out.println(actual);

		context.close();

		assertThat("List Actual Is Subset Of Expect Without Order", actual,
			containsInAnyOrder(
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "au.yahoo.com", 11492756),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "mail.live.com", 21536612),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "www.bing.com", 14065457)
			)
		);
				
		JUnit4Util.endCurrentTest(getClass());
	}

}
