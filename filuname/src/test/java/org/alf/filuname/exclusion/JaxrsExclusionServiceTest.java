package org.alf.filuname.exclusion;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.alf.filuname.batch.ImportService;
import org.alf.filuname.dao.ExclusionDAO;
import org.alf.filuname.model.Exclusion;
import org.alf.filuname.util.JUnit4Util;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JaxrsExclusionServiceTest {

	private JUnit4Mockery context;
	
	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery();
	}

	@After
	public void tearDown() throws Exception {
		context.assertIsSatisfied();
	}

	@Test
	public void test() {
		JUnit4Util.startCurrentTest(getClass());
		ExclusionDAO dao = context.mock(ExclusionDAO.class);
		
		context.checking(new Expectations() {{
			allowing(dao).replace((List)with(any(List.class)));
		}});		

		JaxrsExclusionService service = new JaxrsExclusionService();
		service.setUrl("http://private-1de182-mamtrialrankingadjustments4.apiary-mock.com/exclusions");
		service.setExclusionDAO(dao);
		
		service.trigger();
		JUnit4Util.endCurrentTest(getClass());
	}

}
