package netgloo.dto;

import java.io.Serializable;

public class UserFriendsRequestDTO implements Serializable {

	// =================================================
	// Instance Variables
	// =================================================
	private static final long serialVersionUID = 1L;
	private String uid;
	private String frienduid;
	private String friendphoneno;
	

	// =================================================
	// Constructors
	// =================================================

	/**
	 * 
	 */
	public UserFriendsRequestDTO() {
		super();
	}

	/**
	 * @param code
	 * @param desc
	 */
	public UserFriendsRequestDTO(final String uid, final String frienduid, final String friendphoneno) {
		super();
		this.uid = uid;
		this.frienduid = frienduid;
		this.friendphoneno = friendphoneno;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFrienduid() {
		return frienduid;
	}

	public void setFrienduid(String frienduid) {
		this.frienduid = frienduid;
	}

	public String getFriendphoneno() {
		return friendphoneno;
	}

	public void setFriendphoneno(String friendphoneno) {
		this.friendphoneno = friendphoneno;
	}

	

}
