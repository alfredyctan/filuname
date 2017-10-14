package org.alf.filuname.model;

import java.util.Date;

public interface HitCount {

	public static final String VISIT_DATE = "visit_date";

	public static final String WEBSITE = "website";

	public static final String COUNT = "count";

	public Date getVisitDate();

	public String getWebsite();

	public int getCount();

}
