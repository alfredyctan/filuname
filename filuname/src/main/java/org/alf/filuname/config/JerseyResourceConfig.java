package org.alf.filuname.config;


import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
@ApplicationPath("/servlet")
public class JerseyResourceConfig extends ResourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(JerseyResourceConfig.class);

	public JerseyResourceConfig(@Context ServletContext context) {
		logger.info("Jersey register  {}", context);
		WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(context);
		List<?> resources = appCtx.getBean("restfulResources", List.class);
		for (Object resource : resources) {
			register(resource);
		}
	}

}