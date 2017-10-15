package org.alf.filuname.batch;

import java.io.File;
import java.util.regex.Pattern;

import org.alf.filuname.service.ScheduledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryImportService implements ScheduledService {

	private static final Logger logger = LoggerFactory.getLogger(DirectoryImportService.class);
	
	private ImportService batchFileImportService;
	
	private File directory;
	
	private Pattern pattern;
	
	@Override
	public void trigger() {
		logger.info("directory for import [{}{}{}]", directory, File.separator, pattern);
		String[] filenames = directory.list((file, name)->pattern.matcher(name).matches());
		if (filenames != null) {
			for (String filename : filenames) {
				batchFileImportService.doImport(directory.getPath() + File.separatorChar + filename);
			}
		}
	} 

	public void setBatchFileImportService(ImportService batchFileImportService) {
		this.batchFileImportService = batchFileImportService;
	}
	
	public void setDirectory(String directory) {
		this.directory = new File(directory);
	}
	
	public void setPattern(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}
	
}
