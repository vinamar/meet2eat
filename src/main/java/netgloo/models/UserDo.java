package netgloo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserDo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String fname;
	
	@NotNull
	private String lname;
	
	@NotNull
	private String email;
	
	@NotNull
	private String phoneno;
	
	private String gender;
	private String birthday;
	private String city;
	private String profilepic;
	private String coverpic;
	private Date registereddate;
	private String oauthprovider;
	private String uid;
	private Date lastseendate;
	//status
	private int registered;
	private int active;
	
	private String recentlocation;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}
	public String getCoverpic() {
		return coverpic;
	}
	public void setCoverpic(String coverpic) {
		this.coverpic = coverpic;
	}
	public Date getRegistereddate() {
		return registereddate;
	}
	public void setRegistereddate(Date registereddate) {
		this.registereddate = registereddate;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getOauthprovider() {
		return oauthprovider;
	}
	public void setOauthprovider(String oauthprovider) {
		this.oauthprovider = oauthprovider;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Date getLastseendate() {
		return lastseendate;
	}
	public void setLastseendate(Date lastseendate) {
		this.lastseendate = lastseendate;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public int getRegistered() {
		return registered;
	}
	public void setRegistered(int registered) {
		this.registered = registered;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getRecentlocation() {
		return recentlocation;
	}
	public void setRecentlocation(String recentlocation) {
		this.recentlocation = recentlocation;
	}

}
