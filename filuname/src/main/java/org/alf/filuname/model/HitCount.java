package org.alf.filuname.model;

import java.util.Date;

public interface HitCount {

	public static final String VISIT_DATE = "visit_date";

	public static final String WEBSITE = "website";

	public static final String COUNT = "count";

	public Date getVisitDate();

	public void setVisitDate(Date visitDate);
	
	public String getWebsite();

	public void setWebsite(String website);
	
	public int getCount();

	public void setCount(int count);

}
