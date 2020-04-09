package org.jlobato.gpro.results.service;

import java.util.List;

import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResults;

/**
 * 
 * @author JLOBATO
 *
 */
public interface IManagerService {
	
	/**
	 * 
	 * @return
	 */
	public List<Manager> findAll();
	
	/**
	 * 
	 * @param results
	 */
	public void putResults(ManagerResults results);
}
