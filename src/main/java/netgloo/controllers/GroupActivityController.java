package netgloo.controllers;

import java.util.Date;

import netgloo.constants.Constants;
import netgloo.dto.StatusDTO;
import netgloo.models.GroupDetailDo;
import netgloo.models.GroupUserAcitivityDo;
import netgloo.repository.GroupDetailDAO;
import netgloo.repository.GroupUserActivityDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the class to manage activities for a given group. The group activity includes operations like add(Join), remove(UnJoin) from the group
 *
 * @author netgloo
 */
@RestController
@RequestMapping("/activity")
public class GroupActivityController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GroupActivityController.class);

	// ------------------------
	// PUBLIC METHODS
	// ------------------------
	/**
	 * /create --> Create a new user and save it in the database.
	 * 
	 * @param email
	 *            User's email
	 * @param name
	 *            User's name
	 * @return A string describing if the user is succesfully created or not.
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add")
	@ResponseBody
	public StatusDTO add(@RequestBody GroupUserAcitivityDo groupUserActivity) {
		try {
			groupUserActivity.setLast_modified(new Date());
			if(groupUserActivity.getUid()>0 && groupUserActivity.getGroupid() > 0){
				GroupDetailDo groupDetailDo = groupDetailDAO.findById(groupUserActivity.getGroupid());
				if(groupDetailDo!=null){
					GroupUserAcitivityDo activityDo = groupUserActivityDAO.findByUidAndGroupid(groupUserActivity.getUid(), groupUserActivity.getGroupid());
					if(activityDo!=null && activityDo.getJoined()==1){
						LOGGER.debug("User already part of the Group: " + groupUserActivity.getUid());
						return new StatusDTO(groupUserActivity.getGroupid(), Constants.RESPONSE_FAILURE_CODE, Constants.GROUP_USER_ALREADY_JOINED);
					}
					groupUserActivity.setJoined(1);
					groupUserActivityDAO.save(groupUserActivity);
				}else {
					LOGGER.debug("Invalid Group ID (doesnot exist): " +groupUserActivity.getGroupid());
					return new StatusDTO(groupUserActivity.getGroupid(),Constants.RESPONSE_FAILURE_CODE , Constants.GROUP_INVALID);
				}
				
			}
		} catch (Exception ex) {
			LOGGER.debug("Error creating the Group acitivity: " + ex.toString());
			new StatusDTO(groupUserActivity.getGroupid(),Constants.RESPONSE_FAILURE_CODE , Constants.GROUP_USER_ACTIVITY_FAILED);
		}
		LOGGER.debug("Group activity succesfully created! (id = " + groupUserActivity.getGroupid()
				+ ")");
		return new StatusDTO(groupUserActivity.getGroupid(),Constants.RESPONSE_SUCCESS_CODE , Constants.GROUP_USER_ACTIVITY_CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/remove")
	@ResponseBody
	public StatusDTO remove(@RequestBody GroupUserAcitivityDo groupUserActivity) {
		try {
			GroupUserAcitivityDo groupUserActivityDo = groupUserActivityDAO.findByUidAndGroupid(groupUserActivity.getUid(), groupUserActivity.getGroupid());
			if(groupUserActivityDo!= null){
				groupUserActivity.setLast_modified(new Date());
				groupUserActivity.setJoined(0);
				groupUserActivityDAO.save(groupUserActivity);
			}
		} catch (Exception ex) {
			LOGGER.debug("Error removing user from group: " + ex.toString());
			new StatusDTO(groupUserActivity.getGroupid(),Constants.RESPONSE_FAILURE_CODE , Constants.GROUP_USER_REMOVED);
		}
		
		LOGGER.debug("User successfully removed from group (id = " + groupUserActivity.getGroupid()
				+ ")");
		return new StatusDTO(groupUserActivity.getGroupid(),Constants.RESPONSE_SUCCESS_CODE , Constants.GROUP_USER_REMOVED);
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public GroupDetailDo findByGroupId(@PathVariable("id") int id) {
		GroupDetailDo gd = null;
		try {
			gd = groupUserActivityDAO.findById(id);
		} catch (Exception ex) {
			LOGGER.debug("Error fetching groups: " + ex.toString()); 
			return null;
		}
		return gd;
	}

	@Autowired
	private GroupUserActivityDAO groupUserActivityDAO;
	
	@Autowired
	private GroupDetailDAO groupDetailDAO;

} // class UserController
