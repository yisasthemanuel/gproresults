package org.jlobato.gpro.results;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jlobato.gpro.results.service.IManagerService;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The Class GproResultsApiApplicationTests.
 */
@SpringBootTest
class GproResultsApiApplicationTests {
	
	/** The service. */
	@Autowired
	IManagerService service;

	/**
	 * Context loads.
	 */
	@Test
	void contextLoads() {
		assertThat(1).isEqualTo(1);
	}
	
	/**
	 * When get results then return manager reults.
	 */
	@Test
	void whenGetResults_thenReturnManagerReults( ) {
		//Dados
		String idSeason = "74";
		String idRace = "12";
		
		//Entonces
		List<ManagerResult> resultados = service.getResults(idSeason, idRace);
		
		assertThat(resultados.get(0).getCodeManager()).isEqualTo("NEVZA");
		assertThat(resultados.get(0).getRacePosition()).isEqualTo(4);
		assertThat(resultados.get(8).getCodeManager()).isEqualTo("JESUS");
		assertThat(resultados.get(8).getRacePosition()).isEqualTo(27);
	}

}
