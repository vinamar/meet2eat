package netgloo.dto;

import java.io.Serializable;

public class StatusDTO implements Serializable {

	// =================================================
	// Instance Variables
	// =================================================
	private static final long serialVersionUID = 1L;
	private int id;
	private String status;
	private String desc;
	

	// =================================================
	// Constructors
	// =================================================

	/**
	 * 
	 */
	public StatusDTO() {
		super();
	}

	/**
	 * @param code
	 * @param desc
	 */
	public StatusDTO(final int id, final String status, final String desc) {
		super();
		this.id = id;
		this.status = status;
		this.desc = desc;
	}

	// =================================================
	// Accessors
	// =================================================
	/**
	 * @return the desc
	 */
	public final String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public final void setDesc(final String desc) {
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
