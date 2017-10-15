package org.alf.filuname.batch;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

//@Transactional
public class ScheduledImportService implements ImportService {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledImportService.class);
	
	private JobLauncher jobLauncher;
	
	private Job job;
	
	@Override
	public void importFile(String file) {
		logger.info("path {}", file);
		try {
			
			JobExecution execution = jobLauncher.run(
				job, 
				new JobParametersBuilder()
				.addString("import-file", file)
				.toJobParameters()
			);
			logger.info("Exit Status : {}", execution.getStatus());
		} catch (JobExecutionAlreadyRunningException | 
				JobRestartException | 
				JobInstanceAlreadyCompleteException | 
				JobParametersInvalidException e) {
			logger.error("error on processing patch job, reason:{}", e.getMessage());
			logger.debug("details..", e);
		}
	}
	
	@Override
	public void init() {
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}

	@Override
	public void dispose() {
	}
	
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
}
