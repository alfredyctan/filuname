package org.alf.filuname.model.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Exclusion")
public class Exclusion implements org.alf.filuname.model.Exclusion {

	private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = org.alf.filuname.model.Exclusion.HOST)
	private String host;
	
	@Column(name = org.alf.filuname.model.Exclusion.EXCLUDED_SINCE)
	private Date excludedSince;

	@Column(name = org.alf.filuname.model.Exclusion.EXCLUDED_TILL)
	private Date excludedTill;

	public Exclusion() {
	}
	
	public Exclusion(String host, String excludedSince, String excludedTill) {
		this.host = host;
		try {
			this.excludedSince = (excludedSince == null) ? null : DATE_FORMAT.get().parse(excludedSince);
		} catch (ParseException e) {
			throw new RuntimeException("fail to parse excludedSince : [" + excludedSince + ']', e);
		}
		try {
			this.excludedTill = (excludedTill == null) ? null : DATE_FORMAT.get().parse(excludedTill);
		} catch (ParseException e) {
			throw new RuntimeException("fail to parse excludedTill : [" + excludedTill + ']', e);
		}
	}

	public Exclusion(String host, Date excludedSince, Date excludedTill) {
		this.host = host;
		this.excludedSince = excludedSince;
		this.excludedTill = excludedTill;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public Date getExcludedSince() {
		return excludedSince;
	}

	@Override
	public void setExcludedSince(Date excludedSince) {
		this.excludedSince = excludedSince;
	}

	@Override
	public Date getExcludedTill() {
		return excludedTill;
	}

	@Override
	public void setExcludedTill(Date excludedTill) {
		this.excludedTill = excludedTill;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exclusion [");
		if (host != null)
			builder.append("host=").append(host).append(", ");
		if (excludedSince != null)
			builder.append("excludedSince=").append(excludedSince).append(", ");
		if (excludedTill != null)
			builder.append("excludedTill=").append(excludedTill);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excludedSince == null) ? 0 : excludedSince.hashCode());
		result = prime * result + ((excludedTill == null) ? 0 : excludedTill.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
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
		Exclusion other = (Exclusion) obj;
		if (excludedSince == null) {
			if (other.excludedSince != null)
				return false;
		} else if (excludedSince != null) {
			if (other.excludedSince == null)
				return false;
		} else if (excludedSince.getTime() != other.excludedSince.getTime())
			return false;
		if (excludedTill == null) {
			if (other.excludedTill != null)
				return false;
		} else if (excludedTill != null) {
			if (other.excludedTill == null)
				return false;
		} else if (excludedTill.getTime() != other.excludedTill.getTime())
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		return true;
	}
}
