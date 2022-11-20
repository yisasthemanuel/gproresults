package org.jlobato.gpro.results.rest;

import java.util.Iterator;
import java.util.List;

import org.jlobato.gpro.results.service.IManagerService;
import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.jlobato.gpro.xbean.results.ManagerResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class ResultsController.
 */
@RestController
@RequestMapping("/managers")
@CrossOrigin
public class ResultsController {

	/**
	 * Servicio. Es la parte de negocio de la aplicacion. Se implementa con una interfaz y se utiliza el
	 * patrón business delegate 
	 */
	@Autowired
	private IManagerService service;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@GetMapping
	@ResponseBody
	public List<Manager> findAll() {
		return service.findAll();
	}
	
	/**
	 * Update.
	 *
	 * @param resource the resource
	 */
	@PutMapping(value = "/results")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody ManagerResults resource) {
		//TODO Utilizar precondiciones Guava. Luego cambiar a precondiciones de la 1.8
		//TODO Utilizar RestPreconditions (baeldung)
		//Preconditions.checkNotNull(resource);
		//RestPreconditions.checkNotNull(service.getById(resource.getId()));
		service.putResults(resource);
	}
	
	/**
	 * Update.
	 *
	 * @param resource the resource
	 */
	@PutMapping(value = "/results-all")
	@ResponseStatus(HttpStatus.OK)
	public void updateAll(@RequestBody List<ManagerResults> resource) {
		Iterator<ManagerResults> results = resource.iterator();
		while (results.hasNext()) {
			ManagerResults theSingleResults = results.next();
			service.putResults(theSingleResults);
		}
	}
	
	/**
	 * Gets the results.
	 *
	 * @param season the season
	 * @param race the race
	 * @return the results
	 */
	@GetMapping(value = "results/{season}/{race}")
	@ResponseBody
	public List<ManagerResult> getResults(@PathVariable String season, @PathVariable String race) {
		//TODO Utilizar precondiciones Guava. Luego cambiar a precondiciones de la 1.8
		//TODO Utilizar RestPreconditions (baeldung)
		//Preconditions.checkNotNull(resource);
		//RestPreconditions.checkNotNull(service.getById(resource.getId()));
		return service.getResults(season, race);
	}
	
	/**
	 * Update.
	 *
	 * @param resource the resource
	 */
	@PutMapping(value = "/update-position/{season}/{codeManager}")
	@ResponseStatus(HttpStatus.OK)
	public void updateManagerPosition(@PathVariable String season, @PathVariable String codeManager, @RequestParam Integer position) {
		//TODO Utilizar precondiciones Guava. Luego cambiar a precondiciones de la 1.8
		//TODO Utilizar RestPreconditions (baeldung)
		//Preconditions.checkNotNull(resource);
		//RestPreconditions.checkNotNull(service.getById(resource.getId()));
		service.updateManagerPosition(season, codeManager, position);
	}
	
}
