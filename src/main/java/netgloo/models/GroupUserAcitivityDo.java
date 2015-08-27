package netgloo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * An entity User composed by three fields (id, email, name).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 *
 * @author netgloo
 */
@Entity
@Table(name = "group_user_activity")
public class GroupUserAcitivityDo {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An autogenerated id (unique for each user in the db)
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @NotNull
  private int groupid;
  
  @NotNull
  private long uid;
  
  // last modified date
  @NotNull
  private Date last_modified;
  
// indicates if user has joined the group
  private int joined;
  
  

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public GroupUserAcitivityDo() { }

 

  // Getter and setter methods
  
  public GroupUserAcitivityDo(int groupid, long uid, Date last_modified, int joined) {
	this.groupid = groupid;
	this.uid = uid;
	this.last_modified = last_modified;
	this.joined = joined;
	
}

public GroupUserAcitivityDo(int id) {
	this.id = id;
}



public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}



public int getGroupid() {
	return groupid;
}



public void setGroupid(int groupid) {
	this.groupid = groupid;
}



public long getUid() {
	return uid;
}



public void setUid(int uid) {
	this.uid = uid;
}



public Date getLast_modified() {
	return last_modified;
}



public void setLast_modified(Date last_modified) {
	this.last_modified = last_modified;
}



public int getJoined() {
	return joined;
}



public void setJoined(int joined) {
	this.joined = joined;
}




 
  
} // class User