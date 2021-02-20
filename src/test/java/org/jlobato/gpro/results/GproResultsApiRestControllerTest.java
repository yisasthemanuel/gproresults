package org.jlobato.gpro.results;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.jlobato.gpro.xbean.results.ManagerResults;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * The Class GproResultsApiRestControllerTest.
 */
public class GproResultsApiRestControllerTest extends GproResultsApiRestControllerAbstractTest {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GproResultsApiRestControllerTest.class);


	/**
	 * Sets the up.
	 */
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * Gets the managers list.
	 *
	 * @return the managers list
	 * @throws Exception the exception
	 */
	@Test
	public void getManagersList() throws Exception {
		String uri = "/managers";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
		String content = mvcResult.getResponse().getContentAsString();
		List<Manager> managerList = super.mapFromJsonReference(content, new TypeReference<List<Manager>>() {});
		assertThat(managerList).isNotNull();
		
		logger.debug(managerList.toString());
		
		assertThat(managerList.size()).isPositive();
	}
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 * @throws Exception the exception
	 */
	@Test
	public void getResults() throws Exception {
		String uri = "/managers/results/74/12";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
		
		String content = mvcResult.getResponse().getContentAsString();
		List<ManagerResult> resultsList = mapFromJsonReference(content, new TypeReference<List<ManagerResult>>() {});
		
		assertThat(resultsList).isNotNull();
		
		assertThat(resultsList.size()).isEqualTo(10);
		
		logger.debug(resultsList.toString());
		
		assertThat(resultsList.get(0).getCodeManager()).isEqualTo("NEVZA");
		assertThat(resultsList.get(0).getRacePosition()).isEqualTo(4);
		
		assertThat(resultsList.get(8).getCodeManager()).isEqualTo("JESUS");
		assertThat(resultsList.get(8).getRacePosition()).isEqualTo(27);
		
		assertThat(resultsList.get(1).getCodeManager()).isEqualTo("CARLO");
		assertThat(resultsList.get(1).getRacePosition()).isEqualTo(13);
		
	}
	
	/**
	 * Update results.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateResults() throws Exception {
		String idSeason = "52";
		String idRace = "1";
		
		
		ManagerResults results = new ManagerResults();
		results.setIdSeason(Short.valueOf(idSeason));
		results.setIdRace(Short.valueOf(idRace));
		
		ManagerResult resultJesus = new ManagerResult.ManagerResultBuilder("JESUS").racePosition(3).gridPosition(2).codeManager("JESUS").build();
		ManagerResult resultNevza = new ManagerResult.ManagerResultBuilder("NEVZA").racePosition(29).gridPosition(7).build();
		ManagerResult resultPablo = new ManagerResult.ManagerResultBuilder("PABLO").racePosition(14).build();
		ManagerResult resultDiego = new ManagerResult("DIEGO", 4, 8);
		
		
		results.setResults(Arrays.asList(resultJesus, resultNevza, resultPablo, resultDiego));
		
		logger.debug(results.toString());
		
		String content = mapToJson(results);
		
		logger.debug(content);
		
		String uri = "/managers/results";
		
		//Se graba ok
		mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(content))
				.andExpect(status().isOk());
		
		//Ahora recuperamos y vemos si todo es una verdad absoluta
		uri = "/managers/results/" + idSeason + "/" + idRace;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
		
		content = mvcResult.getResponse().getContentAsString();
		List<ManagerResult> resultsList = mapFromJsonReference(content, new TypeReference<List<ManagerResult>>() {});
		
		assertThat(resultsList).isNotNull();
		
		logger.debug(resultsList.toString());
		
		//Hay datos
		assertThat(resultsList.size()).isPositive();
		
		//Comprobamos ciertos datos
		assertThat(resultsList.get(5).getCodeManager()).isEqualTo("JESUS");
		assertThat(resultsList.get(5).getRacePosition()).isEqualTo(3);
		assertThat(resultsList.get(5).getGridPosition()).isEqualTo(2);
		
		assertThat(resultsList.get(3).getCodeManager()).isEqualTo("PABLO");
		assertThat(resultsList.get(3).getGridPosition()).isNull();
	}
	
	/**
	 * Update manager position.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateManagerPosition() throws Exception {
		String idSeason = "80";
		String codeManager = "MIKKO";
		
		String uri = "/managers/update-position/" + idSeason + "/" + codeManager;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).param("position", "39")).andReturn();
		
		//Devuelve OK
		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(200);
	}

}
