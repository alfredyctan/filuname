package org.alf.filuname.report;

import java.util.List;

import org.alf.filuname.dao.HitCountDAO;
import org.alf.filuname.model.HitCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOReportService implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(DAOReportService.class);
	
	private HitCountDAO hitCountDAO;
	
	public List<HitCount> requestReport(String date) {
		List<HitCount> hitCounts = hitCountDAO.getTopHitCounts(date, 5);
		logger.info("total hit counts : [{}]", hitCounts.size());
		return hitCounts;
	}
	
	public void setHitCountDAO(HitCountDAO hitCountDAO) {
		this.hitCountDAO = hitCountDAO;
	}
}
