package org.alf.filuname.dao;

import java.util.List;

import org.alf.filuname.model.HitCount;

public interface HitCountDAO {

	public List<HitCount> getTopHitCounts(String date, int rank);
	
	public List<HitCount> getHitCounts();
	
}
