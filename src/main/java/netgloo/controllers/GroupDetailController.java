package netgloo.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import netgloo.models.GroupDetailDo;
import netgloo.repository.GroupDetailDAO;

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
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@RestController
@RequestMapping("/group")
public class GroupDetailController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GroupDetailController.class);

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
	@RequestMapping(method=RequestMethod.POST, value="")
	@ResponseBody
	public GroupDetailDo create(@RequestBody GroupDetailDo groupDetail) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String departuretime = dateFormat.format(date);
			groupDetail.setDeparturetime(departuretime);
			groupDetail.setRetired(0); //default is active group
			repository.save(groupDetail);
		} catch (Exception ex) {
			LOGGER.debug("Error creating the Group: " + ex.toString());
			return null;
		}
		LOGGER.debug("Group succesfully created! (id = " + groupDetail.getId()
				+ ")");
		return groupDetail;
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public GroupDetailDo findByGroupId(@PathVariable("id") int id) {
		GroupDetailDo gd = null;
		try {
			gd = repository.findById(id);
		} catch (Exception ex) {
			LOGGER.debug("Error fetching groups: " + ex.toString()); 
			return null;
		}
		return gd;
	}
	
	//api to retrieve user info by uid
//		@RequestMapping(method = RequestMethod.GET, value = "/nearby")
//		@ResponseBody
//		public UserDo findByUid(@RequestParam("uid") String uid,@RequestParam("lat") String latitude,@RequestParam("long") Double longitude) {
//			UserDo user = null;
//			try {
//				user = repository.findByOwnerid(uid);
//			} catch (Exception ex) {
//				LOGGER.debug("Error fetching user info: " + ex.toString());
//				return null;
//			}
//			return user;
//		}
	
	@RequestMapping(method=RequestMethod.GET, value="/owner")
	@ResponseBody
	public List<GroupDetailDo> findByOwnerId(@RequestBody GroupDetailDo groupDetailDo) {
		List<GroupDetailDo> gds;
		try {
			gds = repository.findByOwneridAndRetired(groupDetailDo.getOwnerid(), groupDetailDo.getRetired());
		} catch (Exception ex) {
			LOGGER.debug("Error fetching groups: " + ex.toString()); 
			return null;
		}
		return gds;
	}

	/**
	 * /delete --> Delete the user having the passed id.
	 * 
	 * @param id
	 *            The id of the user to delete
	 * @return A string describing if the user is succesfully deleted or not.
	 */
//	@RequestMapping("/delete")
//	@ResponseBody
//	public String delete(int id) {
//		try {
//			GroupDetail groupDetail = new GroupDetail(id);
//			repository.delete(groupDetail);
//		} catch (Exception ex) {
//			return "Error deleting the user:" + ex.toString();
//		}
//		return "User succesfully deleted!";
//	}

	/**
	 * /get-by-email --> Return the id for the user having the passed email.
	 * 
	 * @param email
	 *            The email to search in the database.
	 * @return The user id or a message error if the user is not found.
	 */
	// @RequestMapping("/get-by-venue")
	// @ResponseBody
	// public String getByVenueName(String venue_name) {
	// String id;
	// try {
	// GroupDetail groupDetail = groupDetailDao.findByVenueName(venue_name);
	// id = String.valueOf(groupDetail.getId());
	// }
	// catch (Exception ex) {
	// return "Group not found";
	// }
	// return "The group id is: " + id;
	// }

	/**
	 * /update --> Update the email and the name for the user in the database
	 * having the passed id.
	 * 
	 * @param id
	 *            The id for the user to update.
	 * @param email
	 *            The new email.
	 * @param name
	 *            The new name.
	 * @return A string describing if the user is succesfully updated or not.
	 */
//	@RequestMapping("/update")
//	@ResponseBody
//	public String updateGroup(long id, String venue_name, String name) {
//		try {
//			GroupDetail groupDetail = repository.findOne(id);
//			groupDetail.setVenue_name(venue_name);
//			repository.save(groupDetail);
//		} catch (Exception ex) {
//			return "Error updating the Group: " + ex.toString();
//		}
//		return "Group succesfully updated!";
//	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private GroupDetailDAO repository;

} // class UserController
