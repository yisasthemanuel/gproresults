package org.jlobato.gpro.results.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jlobato.gpro.dao.mybatis.facade.FachadaManager;
import org.jlobato.gpro.dao.mybatis.facade.FachadaManagerResultService;
import org.jlobato.gpro.dao.mybatis.facade.FachadaSeason;
import org.jlobato.gpro.dao.mybatis.model.Race;
import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.jlobato.gpro.xbean.results.ManagerResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicios relacionados con los managers
 * 
 * @author JLOBATO
 *
 */
@Service
public class ManagerService implements IManagerService {
	@Autowired
	private FachadaManager managerRepository;	
	
	@Autowired
	private FachadaManagerResultService resultsRepository;
	
	@Autowired
	private FachadaSeason seasonRepository;

	/**
	 * Devuelve la lista de todos los managers de la aplicación
	 */
	public List<Manager> findAll() {
		List<Manager> result = new ArrayList<>();
		List<org.jlobato.gpro.dao.mybatis.model.Manager> lista = managerRepository.getManagersList();
		
		lista.forEach(manager -> {
			result.add(new Manager(manager.getCodeManager(), manager.getFirstName() + " " + manager.getLastName()));
		});
		
		return result;
	}


	/**
	 * 
	 */
	public void putResults(ManagerResults results) {
		Race race = seasonRepository.getRace(results.getIdSeason(), results.getIdRace());
		for (Iterator<ManagerResult> managerResults = results.getResults().iterator(); managerResults.hasNext();) {
			ManagerResult managerResult = managerResults.next();
			org.jlobato.gpro.dao.mybatis.model.Manager manager = managerRepository.getManagerByCode(managerResult.getCodeManager());
			
			resultsRepository.updateManagerResult(manager,
					race,
					(managerResult.getGridPosition() == 0) ? null : new Short((short)managerResult.getGridPosition()),
					(managerResult.getRacePosition() == 0) ? null : new Short((short)managerResult.getRacePosition())
				);
		}
	}

}
