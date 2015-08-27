package netgloo.dto;

import java.io.Serializable;

public class ContactDTO implements Serializable {

	// =================================================
	// Instance Variables
	// =================================================
	private static final long serialVersionUID = 1L;
	private String phoneno;
	

	// =================================================
	// Constructors
	// =================================================

	/**
	 * 
	 */
	public ContactDTO() {
		super();
	}

	/**
	 * @param code
	 * @param desc
	 */
	public ContactDTO(final String phoneno, final String deviceid) {
		super();
		this.phoneno = phoneno;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	// =================================================
	// Accessors
	// =================================================

}
