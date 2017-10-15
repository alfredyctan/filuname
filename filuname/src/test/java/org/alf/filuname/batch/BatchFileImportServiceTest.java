package org.alf.filuname.batch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.List;

import org.alf.filuname.dao.HitCountDAO;
import org.alf.filuname.model.HitCount;
import org.alf.filuname.util.JUnit4Util;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource("classpath:/junit.properties")
@ContextConfiguration({ "classpath:/batch-file-import.xml", "classpath:/datasource.xml", "classpath:/hitcount-dao.xml", "classpath:/properties.xml"})
public class BatchFileImportServiceTest {

	@Autowired
	private BatchFileImportService service;
	
	@Autowired
	private HitCountDAO dao;
	
	
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
	public void testImportFile() {
		JUnit4Util.startCurrentTest(getClass());
		
		service.doImport("classpath:/import/data-junit.csv");
		List<HitCount> actual = dao.getHitCounts();

		assertThat("List Actual Is Subset Of Expect Without Order", actual,
			containsInAnyOrder(
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "au.yahoo.com", 11492756),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "mail.live.com", 21536612),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "www.bing.com", 14065457),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "www.ebay.com.au", 19831166),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "www.facebook.com", 104346720),
				new org.alf.filuname.model.impl.HitCount("2016-01-06", "www.wikipedia.org", 13246531),
				new org.alf.filuname.model.impl.HitCount("2016-01-27", "www.ebay.com.au", 23154653)
			)
		);

		
		JUnit4Util.endCurrentTest(getClass());
	}
}
