package org.jlobato.gpro.results.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jlobato.gpro.dao.mybatis.facade.FachadaManager;
import org.jlobato.gpro.dao.mybatis.facade.FachadaManagerResult;
import org.jlobato.gpro.dao.mybatis.facade.FachadaTeam;
import org.jlobato.gpro.dao.mybatis.model.ManagerHistory;
import org.jlobato.gpro.dao.mybatis.model.Race;
import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.jlobato.gpro.xbean.results.ManagerResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicios relacionados con los managers.
 *
 * @author JLOBATO
 */
@Service
public class ManagerService implements IManagerService {
	
	/** The manager repository. */
	@Autowired
	private FachadaManager managerRepository;	
	
	/** The results repository. */
	@Autowired
	private FachadaManagerResult resultsRepository;
	
	/** The team repository. */
	@Autowired
	private FachadaTeam teamRepository;

	/**
	 * Devuelve la lista de todos los managers de la aplicación.
	 *
	 * @return the list
	 */
	@Override
	public List<Manager> findAll() {
		List<Manager> result = new ArrayList<>();
		List<org.jlobato.gpro.dao.mybatis.model.Manager> lista = managerRepository.getManagersList();
		
		lista.forEach(manager -> 
			result.add(new Manager(manager.getCodeManager(), manager.getFirstName() + " " + manager.getLastName()))
		);
		
		return result;
	}


	/**
	 * Put results.
	 *
	 * @param results the results
	 */
	@Override
	public void putResults(ManagerResults results) {
		Race race = new Race();
		race.withIdRace(results.getIdRace()).withIdSeason(results.getIdSeason());
		
		for (Iterator<ManagerResult> managerResults = results.getResults().iterator(); managerResults.hasNext();) {
			ManagerResult managerResult = managerResults.next();
			org.jlobato.gpro.dao.mybatis.model.Manager manager = managerRepository.getManagerByCode(managerResult.getCodeManager());
			
			resultsRepository.updateManagerResult(manager,
					race,
					(managerResult.getGridPosition() == null) ? null : managerResult.getGridPosition().shortValue(),
					(managerResult.getRacePosition() == null) ? null : managerResult.getRacePosition().shortValue()
				);
		}
	}


	/**
	 * Gets the results.
	 *
	 * @param idSeason the id season
	 * @param idRace the id race
	 * @return the results
	 */
	@Override
	public List<ManagerResult> getResults(String idSeason, String idRace) {
		List<ManagerResult> result = new ArrayList<>();
		Race race = new Race();
		race.setIdSeason(new Short(idSeason));
		race.setIdRace(new Short(idRace));
		List<org.jlobato.gpro.dao.mybatis.model.Manager> lista = managerRepository.getManagers(teamRepository.getDefaultTeam(), race);
		
		lista.forEach(manager -> {
			org.jlobato.gpro.dao.mybatis.model.ManagerResult resultado = resultsRepository.findManagerResult(manager, race);
			ManagerResult.ManagerResultBuilder builder = new ManagerResult.ManagerResultBuilder(manager.getCodeManager());
			if (resultado != null) {
				if (resultado.getGridPosition() != null) {
					builder.gridPosition(Integer.valueOf(resultado.getGridPosition()));
				}
				if (resultado.getRacePosition() != null ) {
					builder.racePosition(Integer.valueOf(resultado.getRacePosition()));
				}
			}
			result.add(builder.build());
		});
		
		return result;
	}


	/**
	 * Update manager position.
	 *
	 * @param idSeason the id season
	 * @param codeManager the code manager
	 * @param seasonPosition the season position
	 */
	@Override
	public void updateManagerPosition(String idSeason, String codeManager, Integer seasonPosition) {
		Short idManager = managerRepository.getManagerByCode(codeManager).getIdManager();
		List<ManagerHistory> currentManagerHistory = managerRepository.getManagerHistory(idManager, Short.valueOf(idSeason));
		currentManagerHistory.forEach(managerHistory -> {
			//Actualizamos posición
			managerHistory.setPosition(seasonPosition.shortValue());
			//Persistimos
			managerRepository.updateManagerHistory(managerHistory);
		});
	}
}
