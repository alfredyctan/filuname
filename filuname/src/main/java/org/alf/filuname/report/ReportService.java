package org.alf.filuname.report;

import java.util.List;

import org.alf.filuname.model.Visit;

public interface ReportService {

	public List<Visit> requestReport(String date);
	
}
