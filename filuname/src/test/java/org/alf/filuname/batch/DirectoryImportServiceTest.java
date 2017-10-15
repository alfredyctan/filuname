package org.alf.filuname.batch;

import java.io.File;

import org.alf.filuname.util.JUnit4Util;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DirectoryImportServiceTest {

	private JUnit4Mockery context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery();
	}

	@After
	public void tearDown() throws Exception {
		context.assertIsSatisfied();
	}

	@Test
	public void testImportDirectory() {
		JUnit4Util.startCurrentTest(getClass());

		ImportService fileImportService = context.mock(ImportService.class);
		
		context.checking(new Expectations() {{
			oneOf(fileImportService).doImport("target/test-classes/import/data-junit.csv".replaceAll("\\/", "\\" + File.separator));
			oneOf(fileImportService).doImport("target/test-classes/import/data.csv".replaceAll("\\/", "\\"+ File.separator));
		}});		
		
		DirectoryImportService service = new DirectoryImportService(); 
		service.setBatchFileImportService(fileImportService);
		service.setDirectory("target/test-classes/import");
		service.setPattern(".*\\.csv$");
		service.trigger();

		JUnit4Util.endCurrentTest(getClass());
	}
}
