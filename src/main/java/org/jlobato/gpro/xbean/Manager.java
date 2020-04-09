package org.jlobato.gpro.xbean;

import java.io.Serializable;

public class Manager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8367737645346070331L;

	/**
	 * 
	 */
	private String codeManager;
	/**
	 * 
	 */
	private String nameManager;
	
	
	/**
	 * 
	 * @param codeManager
	 * @param nameManager
	 */
	public Manager(String codeManager, String nameManager) {
		super();
		this.codeManager = codeManager;
		this.nameManager = nameManager;
	}
	
	public String getCodeManager() {
		return codeManager;
	}
	public void setCodeManager(String codeManager) {
		this.codeManager = codeManager;
	}
	public String getNameManager() {
		return nameManager;
	}
	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

}
