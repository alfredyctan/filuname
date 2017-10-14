package org.alf.filuname.report;

import java.util.List;

import org.alf.filuname.model.HitCount;

public interface ReportService {

	public List<HitCount> requestReport(String date);
	
}
