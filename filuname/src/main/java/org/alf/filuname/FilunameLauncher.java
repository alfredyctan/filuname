package org.alf.filuname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@ImportResource({
	"classpath:/bootstrap.xml", 
	"classpath:/batch-file-import.xml", 
	"classpath:/exclusion-import.xml", 
	"classpath:/scheduler.xml", 
	"classpath:/datasource.xml", 
	"classpath:/exclusion-dao.xml",
	"classpath:/hitcount-dao.xml"
})
@SpringBootApplication
public class FilunameLauncher extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FilunameLauncher.class, args);
	}
}
