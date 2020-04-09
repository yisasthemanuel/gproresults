package org.jlobato.gpro.xbean.results;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ManagerResults implements Serializable {
	
	public ManagerResults() {
		super();
	}

	protected short idSeason;
	
	protected short idRace;
	
	protected List<ManagerResult> results;

	public short getIdSeason() {
		return idSeason;
	}

	public void setIdSeason(short idSeason) {
		this.idSeason = idSeason;
	}

	public short getIdRace() {
		return idRace;
	}

	public void setIdRace(short idRace) {
		this.idRace = idRace;
	}

	public List<ManagerResult> getResults() {
		return results;
	}

	public void setResults(List<ManagerResult> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "ManagerResults [idSeason=" + idSeason + ", idRace=" + idRace + ", results=" + results + "]";
	}

}
