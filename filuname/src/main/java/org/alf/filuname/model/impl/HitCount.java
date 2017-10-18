package org.alf.filuname.model.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(HitCount.PrimaryKey.class)
@Table(name = "HitCount")
public class HitCount implements org.alf.filuname.model.HitCount {

	private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	@Id
	@Basic(optional = false)
	@Column(name = org.alf.filuname.model.HitCount.VISIT_DATE, updatable=false)
	private Date visitDate;
	
	@Id
	@Basic(optional = false)
	@Column(name = org.alf.filuname.model.HitCount.WEBSITE, updatable=false)
	private String website;
	
	@Column(name = org.alf.filuname.model.HitCount.COUNT)
	private int count;

	public HitCount() {
	}
	
	public HitCount(Date visitDate, String website, int count) {
		this.visitDate = visitDate;
		this.website = website;
		this.count = count;
	}

	public HitCount(String visitDate, String website, int count) {
		try {
			this.visitDate = DATE_FORMAT.get().parse(visitDate);
		} catch (ParseException e) {
			throw new RuntimeException("fail to parse visitDate : [" + visitDate + ']', e);
		}
		this.website = website;
		this.count = count;
	}
	
	@Override
	public Date getVisitDate() {
		return visitDate;
	}

	@Override
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	@Override
	public String getWebsite() {
		return website;
	}

	@Override
	public void setWebsite(String website) {
		this.website = website;
	}
	
	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((visitDate == null) ? 0 : visitDate.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HitCount other = (HitCount) obj;
		if (count != other.count)
			return false;
		if (visitDate == null) {
			if (other.visitDate != null)
				return false;
		} else if (visitDate.getTime() != other.visitDate.getTime())
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HitCount [");
		if (visitDate != null)
			builder.append("visitDate=").append(visitDate).append(", ");
		if (website != null)
			builder.append("website=").append(website).append(", ");
		builder.append("count=").append(count).append("]");
		return builder.toString();
	}


	static class PrimaryKey implements Serializable {
		
		private static final long serialVersionUID = 8803994831216246415L;
		
		private Date visitDate;

		private String website;

	}
}

