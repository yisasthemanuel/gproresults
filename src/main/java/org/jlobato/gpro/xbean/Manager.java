package org.jlobato.gpro.xbean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class Manager.
 */
@ToString
@Slf4j
@Getter
@Setter
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
		log.debug("New Manager({})", this);
	}
	
	/**
	 * Instantiates a new manager.
	 */
	public Manager() {
		super();
	}
	
}
