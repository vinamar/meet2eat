package netgloo.response.dto;

import java.io.Serializable;

public class ContactRespDTO implements Serializable {

	// =================================================
	// Instance Variables
	// =================================================
	private static final long serialVersionUID = 1L;
	private String phoneno;
	private boolean isAppRegistered;

	// =================================================
	// Constructors
	// =================================================

	/**
	 * 
	 */
	public ContactRespDTO() {
		super();
	}

	/**
	 * @param code
	 * @param desc
	 */
	public ContactRespDTO(final String phoneno, final String deviceid) {
		super();
		this.phoneno = phoneno;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public boolean isAppRegistered() {
		return isAppRegistered;
	}

	public void setAppRegistered(boolean isAppRegistered) {
		this.isAppRegistered = isAppRegistered;
	}

	
	// =================================================
	// Accessors
	// =================================================

}
