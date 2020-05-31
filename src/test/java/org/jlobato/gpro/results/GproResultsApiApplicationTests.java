package org.jlobato.gpro.results;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jlobato.gpro.results.service.IManagerService;
import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * The Class GproResultsApiApplicationTests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GproResultsApiApplicationTests {
	
	/** The service. */
	@Autowired
	IManagerService managerService;

	/**
	 * Context loads.
	 */
	@Test
	public void contextLoads() {
		assertThat(1).isEqualTo(1);
	}
	
	/**
	 * When get results then return manager reults.
	 */
	@Test
	public void whenGetResults_thenReturnManagerResults( ) {
		//Dados
		String idSeason = "74";
		String idRace = "12";
		
		//Entonces
		List<ManagerResult> resultados = managerService.getResults(idSeason, idRace);
		
		assertThat(resultados.get(0).getCodeManager()).isEqualTo("NEVZA");
		assertThat(resultados.get(0).getRacePosition()).isEqualTo(4);
		assertThat(resultados.get(1).getCodeManager()).isEqualTo("CARLO");
		assertThat(resultados.get(1).getRacePosition()).isEqualTo(13);
		assertThat(resultados.get(8).getCodeManager()).isEqualTo("JESUS");
		assertThat(resultados.get(8).getRacePosition()).isEqualTo(27);
	}
	
	@Test
	public void whenFindAll_thenReturnFindAll( ) {
		//Entonces
		List<Manager> resultados = managerService.findAll();
		
		assertThat(resultados.size()).isEqualTo(18);
	}

}
