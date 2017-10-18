package org.alf.filuname.model;

import java.util.Date;

public interface Exclusion {

	public static final String ID = "id";
	
	public static final String HOST = "host";

	public static final String EXCLUDED_SINCE = "excluded_since";

	public static final String EXCLUDED_TILL = "excluded_till";

	public String getHost();

	public void setHost(String host);

	public Date getExcludedSince();

	public void setExcludedSince(Date excludedSince);

	public Date getExcludedTill();

	public void setExcludedTill(Date excludedTill);
	
}
