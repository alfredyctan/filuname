package org.alf.filuname.batch;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public class BatchFileImportService implements ImportService {

	private static final Logger logger = LoggerFactory.getLogger(BatchFileImportService.class);
	
	private JobLauncher jobLauncher;
	
	private Job job;
	
	@Override
	public void doImport(String source) {
		logger.info("path {}", source);
		try {
			
			JobExecution execution = jobLauncher.run(
				job, 
				new JobParametersBuilder()
				.addString("import-file", "file:" + source)
				.toJobParameters()
			);
			
			if (execution.getStatus() == BatchStatus.COMPLETED) {
				new File(source).delete();
			}
			
			logger.info("Exit Status : {}", execution.getStatus());
		} catch (JobExecutionAlreadyRunningException | 
				JobRestartException | 
				JobInstanceAlreadyCompleteException | 
				JobParametersInvalidException e) {
			logger.error("error on processing patch job, reason:{}", e.getMessage());
			logger.debug("details..", e);
		}
	}
	
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
}
