package org.jlobato.gpro.results.rest;

import java.util.List;

import org.jlobato.gpro.results.service.IManagerService;
import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Manager> findAll() {
		//TODO Tirar del servicio correcto
		return service.findAll();
	}
	
	@RequestMapping(value = "/results", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody ManagerResults resource) {
		//TODO Utilizar precondiciones Guava. Luego cambiar a precondiciones de la 1.8
		//TODO Utilizar RestPreconditions (baeldung)
		//Preconditions.checkNotNull(resource);
		//RestPreconditions.checkNotNull(service.getById(resource.getId()));
		service.putResults(resource);
	}
	

/*	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Foo findOne(@PathVariable("id") Long id) {
		return RestPreconditions.checkFound(service.findOne(id));
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Long create(@RequestBody Foo resource) {
		Preconditions.checkNotNull(resource);
		return service.create(resource);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") Long id, @RequestBody Foo resource) {
		Preconditions.checkNotNull(resource);
		RestPreconditions.checkNotNull(service.getById(resource.getId()));
		service.update(resource);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.deleteById(id);
	}*/
}
