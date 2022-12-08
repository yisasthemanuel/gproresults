package org.jlobato.gpro.results;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.jlobato.gpro.xbean.Manager;
import org.jlobato.gpro.xbean.results.ManagerResult;
import org.jlobato.gpro.xbean.results.ManagerResults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;

// TODO: Auto-generated Javadoc
/**
 * The Class GproResultsApiRestControllerTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
		assertThat(status).isEqualTo(HttpStatus.OK.value());
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
		assertThat(status).isEqualTo(HttpStatus.OK.value());
		
		String content = mvcResult.getResponse().getContentAsString();
		List<ManagerResult> resultsList = mapFromJsonReference(content, new TypeReference<List<ManagerResult>>() {});
		
		assertThat(resultsList).isNotNull();
		
		assertThat(resultsList.size()).isEqualTo(10);
		
		logger.debug(resultsList.toString());
		
		assertThat(resultsList.get(0).getCodeManager()).isEqualTo(NEVZA);
		assertThat(resultsList.get(0).getRacePosition()).isEqualTo(4);
		
		assertThat(resultsList.get(8).getCodeManager()).isEqualTo(JESUS);
		assertThat(resultsList.get(8).getRacePosition()).isEqualTo(27);
		
		assertThat(resultsList.get(1).getCodeManager()).isEqualTo(CARLO);
		assertThat(resultsList.get(1).getRacePosition()).isEqualTo(13);
		
	}
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 * @throws Exception the exception
	 */
	@Test
	public void getSeasonResults() throws Exception {
		String uri = "/managers/results/74";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(HttpStatus.OK.value());
		
		String content = mvcResult.getResponse().getContentAsString();
		logger.debug("getSeasonResults.json: {}", content);
		List<ManagerResult> resultsList = mapFromJsonReference(content, new TypeReference<List<ManagerResult>>() {});
		
		assertThat(resultsList).isNotNull();
		
		assertThat(resultsList.size()).isGreaterThan(10);
		
		List<ManagerResult> yisasResults = resultsList.stream().filter(result -> result.getCodeManager().equals("JESUS")).collect(Collectors.toList());

		assertThat(yisasResults.size()).isEqualTo(17);
		
		//Carrera 12 de Yisas
		checkResult(yisasResults, JESUS, 12, 27, null);
		
		//Carrera 14 de Yisas
		checkResult(yisasResults, JESUS, 14, 6, null);
	}

	/**
	 * Check result.
	 *
	 * @param yisasResults the yisas results
	 * @param codeManager the code manager
	 * @param idRace the id race
	 * @param racePosition the race position
	 * @param gridPosition the grid position
	 */
	private void checkResult(List<ManagerResult> yisasResults, String codeManager, Integer idRace, Integer racePosition, Integer gridPosition) {
		List<ManagerResult> yisasRace;
		yisasRace = yisasResults.stream().filter(race -> race.getIdRace().equals(idRace)).collect(Collectors.toList());
		
		assertThat(yisasRace).hasSize(1);
		
		assertThat(yisasRace.get(0).getCodeManager()).isEqualTo(codeManager);
		assertThat(yisasRace.get(0).getRacePosition()).isEqualTo(racePosition);
		
		if (gridPosition == null) {
			assertThat(yisasRace.get(0).getGridPosition()).isNull();
		}
		else {
			assertThat(yisasRace.get(0).getGridPosition()).isEqualTo(gridPosition);
		}
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
		
		ManagerResult resultJesus = new ManagerResult.ManagerResultBuilder(JESUS).racePosition(3).gridPosition(2).codeManager(JESUS).build();
		ManagerResult resultNevza = new ManagerResult.ManagerResultBuilder(NEVZA).racePosition(29).gridPosition(7).idSeason(Integer.valueOf(idSeason)).idRace(Integer.valueOf(idRace)).build();
		ManagerResult resultPablo = new ManagerResult.ManagerResultBuilder(PABLO).racePosition(14).idSeason(Integer.valueOf(idSeason)).idRace(Integer.valueOf(idRace)).build();
		ManagerResult resultDiego = new ManagerResult(DIEGO, 4, 8);
		
		
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
		assertThat(status).isEqualTo(HttpStatus.OK.value());
		
		content = mvcResult.getResponse().getContentAsString();
		List<ManagerResult> resultsList = mapFromJsonReference(content, new TypeReference<List<ManagerResult>>() {});
		
		assertThat(resultsList).isNotNull();
		
		logger.debug(resultsList.toString());
		
		//Hay datos
		assertThat(resultsList.size()).isPositive();
		
		//Comprobamos ciertos datos
		assertThat(resultsList.get(5).getCodeManager()).isEqualTo(JESUS);
		assertThat(resultsList.get(5).getRacePosition()).isEqualTo(3);
		assertThat(resultsList.get(5).getGridPosition()).isEqualTo(2);
		
		assertThat(resultsList.get(3).getCodeManager()).isEqualTo(PABLO);
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
		String codeManager = MIKKO;
		
		String uri = "/managers/update-position/" + idSeason + "/" + codeManager;
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).param("position", "14")).andReturn();
		
		//Devuelve OK
		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(HttpStatus.OK.value());
	}
	
	/**
	 * Update All results.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateAllResults() throws Exception {
		String idSeason = "52";
		String idRace = "1";
		
		List<ManagerResults> allResults = new ArrayList<ManagerResults>();
		
		
		ManagerResults results = new ManagerResults();
		results.setIdSeason(Short.valueOf(idSeason));
		results.setIdRace(Short.valueOf(idRace));
		
		ManagerResult resultJesus = new ManagerResult.ManagerResultBuilder(JESUS).racePosition(3).gridPosition(2).codeManager(JESUS).idSeason(Integer.valueOf(idSeason)).idRace(Integer.valueOf(idRace)).build();
		ManagerResult resultNevza = new ManagerResult.ManagerResultBuilder(NEVZA).racePosition(29).gridPosition(7).build();
		ManagerResult resultPablo = new ManagerResult.ManagerResultBuilder(PABLO).racePosition(14).build();
		ManagerResult resultDiego = new ManagerResult(DIEGO, 4, 8);
		
		results.setResults(Arrays.asList(resultJesus, resultNevza, resultPablo, resultDiego));
		
		allResults.add(results);
		
		idSeason = "52";
		idRace = "2";
		
		results = new ManagerResults();
		results.setIdSeason(Short.valueOf(idSeason));
		results.setIdRace(Short.valueOf(idRace));
		
		resultJesus = new ManagerResult.ManagerResultBuilder(JESUS).racePosition(1).gridPosition(2).codeManager(JESUS).build();
		resultNevza = new ManagerResult.ManagerResultBuilder(NEVZA).racePosition(15).gridPosition(27).build();
		resultPablo = new ManagerResult.ManagerResultBuilder(MIKKO).racePosition(13).gridPosition(3).build();
		
		results.setResults(Arrays.asList(resultJesus, resultNevza, resultPablo));
		
		allResults.add(results);
		
		logger.debug(allResults.toString());
		
		String content = mapToJson(allResults);
		
		logger.debug(content);
		
		String uri = "/managers/results-all";
		
		//Se graba ok
		mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(content))
				.andExpect(status().isOk());
		
		//Ahora recuperamos y vemos si todo es una verdad absoluta
		idRace = "1";
		uri = "/managers/results/" + idSeason + "/" + idRace;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(HttpStatus.OK.value());
		
		content = mvcResult.getResponse().getContentAsString();
		List<ManagerResult> resultsList = mapFromJsonReference(content, new TypeReference<List<ManagerResult>>() {});
		
		assertThat(resultsList).isNotNull();
		
		logger.debug(resultsList.toString());
		
		//Hay datos
		assertThat(resultsList.size()).isPositive();
		
		//Comprobamos ciertos datos
		Iterator<ManagerResult> iterator = resultsList.iterator();
		while (iterator.hasNext()) {
			ManagerResult theResult = iterator.next();
			if (theResult.getCodeManager().equals(JESUS)) {
				assertThat(theResult.getCodeManager()).isEqualTo(JESUS);
				assertThat(theResult.getRacePosition()).isEqualTo(3);
				assertThat(theResult.getGridPosition()).isEqualTo(2);				
			} else if (theResult.getCodeManager().equals(PABLO)) {
				assertThat(theResult.getCodeManager()).isEqualTo(PABLO);
				assertThat(theResult.getGridPosition()).isNull();
			} else if (theResult.getCodeManager().equals(NEVZA)) {
				assertThat(theResult.getCodeManager()).isEqualTo(NEVZA);
				assertThat(theResult.getRacePosition()).isEqualTo(29);
				assertThat(theResult.getGridPosition()).isEqualTo(7);
			}

		}
		
		//Segunda carrera
		idRace = "2";
		uri = "/managers/results/" + idSeason + "/" + idRace;
		mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		status = mvcResult.getResponse().getStatus();
		assertThat(status).isEqualTo(HttpStatus.OK.value());
		
		content = mvcResult.getResponse().getContentAsString();
		resultsList = mapFromJsonReference(content, new TypeReference<List<ManagerResult>>() {});
		
		assertThat(resultsList).isNotNull();
		
		logger.debug(resultsList.toString());
		
		//Hay datos
		assertThat(resultsList.size()).isPositive();
		
		iterator = resultsList.iterator();
		while (iterator.hasNext()) {
			ManagerResult theResult = iterator.next();
			if (theResult.getCodeManager().equals(JESUS)) {
				assertThat(theResult.getCodeManager()).isEqualTo(JESUS);
				assertThat(theResult.getRacePosition()).isEqualTo(1);
				assertThat(theResult.getGridPosition()).isEqualTo(2);				
			} else if (theResult.getCodeManager().equals(NEVZA)) {
				assertThat(theResult.getCodeManager()).isEqualTo(NEVZA);
				assertThat(theResult.getRacePosition()).isEqualTo(15);
				assertThat(theResult.getGridPosition()).isEqualTo(27);				
			} else if (theResult.getCodeManager().equals(MIKKO)) {
				assertThat(theResult.getCodeManager()).isEqualTo(MIKKO);
				assertThat(theResult.getRacePosition()).isEqualTo(13);
				assertThat(theResult.getGridPosition()).isEqualTo(3);				
			} 			
				
		}
		
	}

}
