package org.jlobato.gpro.xbean.results;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ManagerResult implements Serializable {
	
	private String codeManager;

	private int gridPosition;
	
	private int racePosition;

	
	public ManagerResult() {
		super();
	}

	public ManagerResult(String codeManager, int gridPosition, int racePosition) {
		super();
		this.codeManager = codeManager;
		this.gridPosition = gridPosition;
		this.racePosition = racePosition;
	}
	
	public int getGridPosition() {
		return gridPosition;
	}

	public void setGridPosition(int gridPosition) {
		this.gridPosition = gridPosition;
	}

	public int getRacePosition() {
		return racePosition;
	}

	public void setRacePosition(int racePosition) {
		this.racePosition = racePosition;
	}

	@Override
	public String toString() {
		return "ManagerResult [codeManager=" + codeManager + ", gridPosition=" + gridPosition + ", racePosition="
				+ racePosition + "]";
	}

	public String getCodeManager() {
		return codeManager;
	}

	public void setCodeManager(String codManager) {
		this.codeManager = codManager;
	}
	
	

}
