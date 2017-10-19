package org.alf.filuname.exclusion;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.alf.filuname.dao.ExclusionDAO;
import org.alf.filuname.model.impl.Exclusion;
import org.alf.filuname.service.ScheduledService;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JaxrsExclusionService implements ScheduledService {

	private static final Logger logger = LoggerFactory.getLogger(JaxrsExclusionService.class);
	
	private Client client;
	
	private String url;

	private ExclusionDAO exclusionDAO;
	
	public JaxrsExclusionService() {
		client = ClientBuilder.newClient();
	}

	public JaxrsExclusionService(String proxy) {
		client = ClientBuilder.newClient(new ClientConfig().property(ClientProperties.PROXY_URI, proxy));
	}
	
	@Override
	public void trigger() {
		try {
			logger.info("retrieving exclusions from target:[{}]", url);
			List<Exclusion> exclusions = client.target(url).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Exclusion>> () {});
			logger.info("importing [{}] exclusion(s)", exclusions.size());
			for (Exclusion exclusion : exclusions) {
				logger.info("{}", exclusion);
			}
			exclusionDAO.replace((List)exclusions);
		} catch (Exception e) {
			logger.error("error on retrieving exclusions", e);
		}
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setExclusionDAO(ExclusionDAO exclusionDAO) {
		this.exclusionDAO = exclusionDAO;
	}
}
