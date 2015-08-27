package netgloo.controllers;

import netgloo.repository.UserFriendsDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@RestController
@RequestMapping("/suggested")
public class SuggestedGroupsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SuggestedGroupsController.class);


	//api to retrieve user info by uid
//		@RequestMapping(method = RequestMethod.GET, value = "/nearby")
//		@ResponseBody
//		public UserDo findByUid(@RequestParam("uid") String uid,@RequestParam("lat") String latitude,@RequestParam("long") Double longitude) {
//			List<UserFriendsDo> userFriendsDo = null;
//			try {
//				userFriendsDo = userFriendsDAO.findByUid(uid);
//			} catch (Exception ex) {
//				LOGGER.debug("Error fetching user info: " + ex.toString());
//				return null;
//			}
//			return user;
//		}
	
//	@RequestMapping(method=RequestMethod.GET, value="")
//	@ResponseBody
//	public List<GroupDetail> findByOwnerId(@RequestBody GroupDetail groupDetail) {
//		List<GroupDetail> gds;
//		try {
//			gds = repository.findByOwnerAndRetired(groupDetail.getOwner(), groupDetail.getRetired());
//		} catch (Exception ex) {
//			LOGGER.debug("Error fetching groups: " + ex.toString()); 
//			return null;
//		}
//		return gds;
//	}

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
	private UserFriendsDAO userFriendsDAO;

} // class UserController
