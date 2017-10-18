package org.alf.filuname.dao;

import java.util.List;

import org.alf.filuname.model.Exclusion;

public interface ExclusionDAO {

	public List<Exclusion> getExclusions();

	public void replace(List<Exclusion> exclusions);
	
}
