package org.alf.filuname.report;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.alf.filuname.model.HitCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Path("/report")
public class JaxrsReportService implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(JaxrsReportService.class);
	
	@GET
	@Path("/request")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<HitCount> requestReport(@QueryParam("date") String date) {
		logger.info("report" + date);
		List<HitCount> visits = new LinkedList<>();
		visits.add(new org.alf.filuname.model.impl.HitCount());
		return visits;
	}

}
