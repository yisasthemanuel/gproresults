package org.jlobato.gpro.xbean.results;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerResult.
 */
@SuppressWarnings("serial")
public class ManagerResult implements Serializable {
	
	/** The code manager. */
	private String codeManager;

	/** The grid position. */
	private Integer gridPosition;
	
	/** The race position. */
	private Integer racePosition;
	
	/** The id season. */
	private Integer idSeason;
	
	/** The id race. */
	private Integer idRace;

	
	/**
	 * Instantiates a new manager result.
	 */
	public ManagerResult() {
		super();
	}

	/**
	 * Instantiates a new manager result.
	 *
	 * @param codeManager the code manager
	 * @param gridPosition the grid position
	 * @param racePosition the race position
	 */
	public ManagerResult(String codeManager, Integer gridPosition, Integer racePosition) {
		super();
		this.codeManager = codeManager;
		this.gridPosition = gridPosition;
		this.racePosition = racePosition;
	}
	
	/**
	 * Gets the grid position.
	 *
	 * @return the grid position
	 */
	public Integer getGridPosition() {
		return gridPosition;
	}

	/**
	 * Sets the grid position.
	 *
	 * @param gridPosition the new grid position
	 */
	public void setGridPosition(Integer gridPosition) {
		this.gridPosition = gridPosition;
	}

	/**
	 * Gets the race position.
	 *
	 * @return the race position
	 */
	public Integer getRacePosition() {
		return racePosition;
	}

	/**
	 * Sets the race position.
	 *
	 * @param racePosition the new race position
	 */
	public void setRacePosition(Integer racePosition) {
		this.racePosition = racePosition;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ManagerResult [codeManager=" + codeManager + ", gridPosition=" + gridPosition + ", racePosition="
				+ racePosition + "]";
	}

	/**
	 * Gets the code manager.
	 *
	 * @return the code manager
	 */
	public String getCodeManager() {
		return codeManager;
	}

	/**
	 * Sets the code manager.
	 *
	 * @param codManager the new code manager
	 */
	public void setCodeManager(String codManager) {
		this.codeManager = codManager;
	}
	
	/**
	 * Gets the id season.
	 *
	 * @return the id season
	 */
	public Integer getIdSeason() {
		return idSeason;
	}

	/**
	 * Sets the id season.
	 *
	 * @param idSeason the new id season
	 */
	public void setIdSeason(Integer idSeason) {
		this.idSeason = idSeason;
	}

	/**
	 * Gets the id race.
	 *
	 * @return the id race
	 */
	public Integer getIdRace() {
		return idRace;
	}

	/**
	 * Sets the id race.
	 *
	 * @param idRace the new id race
	 */
	public void setIdRace(Integer idRace) {
		this.idRace = idRace;
	}
	
	
	/**
	 * The Class ManagerResultBuilder.
	 */
	public static class ManagerResultBuilder {
		/** The code manager. */
		private String codeManager;

		/** The grid position. */
		private Integer gridPosition;
		
		/** The race position. */
		private Integer racePosition;

		/** The id season. */
		private Integer idSeason;

		/** The id race. */
		private Integer idRace;
		
		/**
		 * Instantiates a new manager result builder.
		 *
		 * @param codeManager the code manager
		 */
		public ManagerResultBuilder(String codeManager) {
			this.codeManager = codeManager;
		}
		
		/**
		 * Code manager.
		 *
		 * @param codeManager the code manager
		 * @return the manager result builder
		 */
		public ManagerResultBuilder codeManager(String codeManager) {
			this.codeManager = codeManager;
			return this;
		}
		
		/**
		 * Race position.
		 *
		 * @param racePosition the race position
		 * @return the manager result builder
		 */
		public ManagerResultBuilder racePosition(Integer racePosition) {
			this.racePosition = racePosition;
			return this;
		}

		/**
		 * Grid position.
		 *
		 * @param gridPosition the grid position
		 * @return the manager result builder
		 */
		public ManagerResultBuilder gridPosition(Integer gridPosition) {
			this.gridPosition = gridPosition;
			return this;
		}
		
		/**
		 * Id Season.
		 *
		 * @param idSeason the season id
		 * @return the manager result builder
		 */
		public ManagerResultBuilder idSeason(Integer idSeason) {
			this.idSeason = idSeason;
			return this;
		}
		
		/**
		 * Id Race.
		 *
		 * @param idRace the race id
		 * @return the manager result builder
		 */
		public ManagerResultBuilder idRace(Integer idRace) {
			this.idRace = idRace;
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the manager result
		 */
		public ManagerResult build() {
			ManagerResult result = new ManagerResult();
			result.setCodeManager(this.codeManager);
			result.setRacePosition(this.racePosition);
			result.setGridPosition(this.gridPosition);
			result.setIdSeason(this.idSeason);
			result.setIdRace(this.idRace);
			return result;
		}
	}
}
