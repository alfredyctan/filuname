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

@Path("/report")
public class JaxrsReportService implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(JaxrsReportService.class);
	
	private ReportService reportService;
	
	@GET
	@Path("/request")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<HitCount> requestReport(@QueryParam("date") String date) {
		logger.info("requesting report for date:[{}] from JAX-RS", date);
		try {
			return reportService.requestReport(date);
		} catch (Exception e) {
			logger.error("error on receiving report request, reason:[{}]", e.getMessage());
			logger.debug("details", e);
			return new LinkedList<>();
		}
	}
	
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}
