package org.alf.filuname.dao.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/datasource.xml", "/hitcount-dao.xml"})
public class JPAHitCountDAOTest {

	@Autowired
	@Qualifier("hitCountDAO")
	private JPAHitCountDAO dao;

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

		List<HitCount> actual = dao.getTopHitCounts("2016-01-06", 3);

		System.out.println("ACTUAL : " + actual);

		assertThat("List Actual Is Subset Of Expect Without Order", actual,
			containsInAnyOrder(new org.alf.filuname.model.impl.HitCount("2016-01-06", "www.facebook.com", 104346720),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "mail.live.com", 21536612),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "www.google.com", 26165099)
			)
		);

		JUnit4Util.endCurrentTest(getClass());
	}

}
