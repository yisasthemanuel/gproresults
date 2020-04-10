package org.jlobato.gpro.results.service;

import java.util.List;

import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.jlobato.gpro.xbean.results.ManagerResults;

// TODO: Auto-generated Javadoc
/**
 * The Interface IManagerService.
 *
 * @author JLOBATO
 */
public interface IManagerService {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Manager> findAll();
	
	/**
	 * Put results.
	 *
	 * @param results the results
	 */
	public void putResults(ManagerResults results);
	
	/**
	 * Gets the results.
	 *
	 * @param idSeason the id season
	 * @param idRace the id race
	 * @return the results
	 */
	public List<ManagerResult> getResults(String idSeason, String idRace);
}
