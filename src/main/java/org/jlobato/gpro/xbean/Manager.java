package org.jlobato.gpro.xbean;

import java.io.Serializable;

/**
 * The Class Manager.
 */
public class Manager implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8367737645346070331L;

	/** The code manager. */
	private String codeManager;
	
	/** The name manager. */
	private String nameManager;
	
	
	/**
	 * Instantiates a new manager.
	 *
	 * @param codeManager the code manager
	 * @param nameManager the name manager
	 */
	public Manager(String codeManager, String nameManager) {
		super();
		this.codeManager = codeManager;
		this.nameManager = nameManager;
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
	 * @param codeManager the new code manager
	 */
	public void setCodeManager(String codeManager) {
		this.codeManager = codeManager;
	}
	
	/**
	 * Gets the name manager.
	 *
	 * @return the name manager
	 */
	public String getNameManager() {
		return nameManager;
	}
	
	/**
	 * Sets the name manager.
	 *
	 * @param nameManager the new name manager
	 */
	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

}
