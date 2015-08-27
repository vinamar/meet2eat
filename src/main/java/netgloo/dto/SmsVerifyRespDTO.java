package netgloo.dto;

import java.io.Serializable;

public class SmsVerifyRespDTO implements Serializable {

	// =================================================
	// Instance Variables
	// =================================================
	private static final long serialVersionUID = 1L;
	private String phoneno;
	private long smscode;
	private String status;
	private String desc;
	

	// =================================================
	// Constructors
	// =================================================

	/**
	 * 
	 */
	public SmsVerifyRespDTO() {
		super();
	}

	/**
	 * @param code
	 * @param desc
	 */
	public SmsVerifyRespDTO(final String phoneno, final long smscode, final String status, final String desc) {
		super();
		this.phoneno = phoneno;
		this.smscode = smscode;
		this.status = status;
		this.desc = desc;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public long getSmscode() {
		return smscode;
	}

	public void setSmscode(long smscode) {
		this.smscode = smscode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
