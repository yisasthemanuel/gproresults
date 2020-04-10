package org.jlobato.gpro.xbean.results;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerResults.
 */
@SuppressWarnings("serial")
public class ManagerResults implements Serializable {
	
	/**
	 * Instantiates a new manager results.
	 */
	public ManagerResults() {
		super();
	}

	/** The id season. */
	protected short idSeason;
	
	/** The id race. */
	protected short idRace;
	
	/** The results. */
	protected List<ManagerResult> results;

	/**
	 * Gets the id season.
	 *
	 * @return the id season
	 */
	public short getIdSeason() {
		return idSeason;
	}

	/**
	 * Sets the id season.
	 *
	 * @param idSeason the new id season
	 */
	public void setIdSeason(short idSeason) {
		this.idSeason = idSeason;
	}

	/**
	 * Gets the id race.
	 *
	 * @return the id race
	 */
	public short getIdRace() {
		return idRace;
	}

	/**
	 * Sets the id race.
	 *
	 * @param idRace the new id race
	 */
	public void setIdRace(short idRace) {
		this.idRace = idRace;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<ManagerResult> getResults() {
		return results;
	}

	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<ManagerResult> results) {
		this.results = results;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ManagerResults [idSeason=" + idSeason + ", idRace=" + idRace + ", results=" + results + "]";
	}

}
