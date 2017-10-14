package org.alf.filuname.model.impl;

import java.util.Date;

public class Visit implements org.alf.filuname.model.Visit {

	private Date date;
	
	private String website;
	
	private int count;

	public Visit() {
	}
	
	public Visit(Date date, String website, int count) {
		this.date = date;
		this.website = website;
		this.count = count;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getWebsite() {
		return website;
	}

	@Override
	public int getCount() {
		return count;
	}

}
