package org.alf.filuname.dao;

import java.util.Date;
import java.util.List;

import org.alf.filuname.model.HitCount;

public interface HitCountDAO {

	public List<HitCount> getTopHitCount(String date, int rank);
	
}
