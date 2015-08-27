package netgloo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_friends")
public class UserFriendsDo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private long uid;
	
	@NotNull
	private long frienduid;
	
	@NotNull
	private String friendphoneno;
	
	private int invited;
	private int friended;
	private Date lastmodified;
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getFriendphoneno() {
		return friendphoneno;
	}
	public void setFriendphoneno(String friendphoneno) {
		this.friendphoneno = friendphoneno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getFrienduid() {
		return frienduid;
	}
	public void setFrienduid(long frienduid) {
		this.frienduid = frienduid;
	}
	public Date getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(Date since) {
		this.lastmodified = since;
	}
	public int getInvited() {
		return invited;
	}
	public void setInvited(int invited) {
		this.invited = invited;
	}
	public int getFriended() {
		return friended;
	}
	public void setFriended(int friended) {
		this.friended = friended;
	}
	
}
