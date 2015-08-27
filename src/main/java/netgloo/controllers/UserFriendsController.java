package netgloo.controllers;

import java.util.Date;
import java.util.List;

import netgloo.constants.Constants;
import netgloo.dto.StatusDTO;
import netgloo.dto.UserFriendsRequestDTO;
import netgloo.models.UserFriendsDo;
import netgloo.repository.UserFriendsDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@RestController
@RequestMapping("/contacts")
public class UserFriendsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserFriendsController.class);

	// ------------------------
	// PUBLIC METHODS
	// ------------------------
	/**
	 * Invite operation to use the app
	 * @param userFriendDo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/invite")
	@ResponseBody
	public StatusDTO inviteToApp(@RequestBody UserFriendsDo userFriendDo) {
		try {
			UserFriendsDo userFriendDo2 = userFriendsDAO.findByUidAndFriendphoneno(Long.valueOf(userFriendDo.getUid()), userFriendDo.getFriendphoneno());
			if (userFriendDo2 != null && userFriendDo2.getInvited()==1) {
				LOGGER.debug("User already invited: " + userFriendDo2.getFriendphoneno());
				return new StatusDTO(-1, Constants.RESPONSE_FAILURE_CODE,
						Constants.USER_ALREADY_INVITED);
			} else {
				userFriendDo.setInvited(1);
				userFriendDo.setLastmodified(new Date());
				userFriendsDAO.save(userFriendDo);
			}

		} catch (Exception ex) {
			LOGGER.debug("Error creating the user-friendship: " + ex.toString());
			return new StatusDTO(-1, Constants.RESPONSE_FAILURE_CODE,
					Constants.RESPONSE_FAILURE_MSG);
		}
		
		if(userFriendDo.getId() > 0){
			LOGGER.debug("user-friendship succesfully created! (id = " + userFriendDo.getId() + ")");
			return new StatusDTO(userFriendDo.getId(), Constants.RESPONSE_SUCCESS_CODE,
					Constants.RESPONSE_SUCCESS_MSG);
		}
		return null;
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public UserFriendsDo findById(@PathVariable("id") int id) {
		UserFriendsDo user = null;
		try {
			user = userFriendsDAO.findById(id);
		} catch (Exception ex) {
			LOGGER.debug("Error fetching groups: " + ex.toString());
			return null;
		}
		return user;
	}

	//api to retrieve user info by uid
	@RequestMapping(method = RequestMethod.GET, value = "/uid")
	@ResponseBody
	public List<UserFriendsDo> findByUid(@RequestParam("uid") String uid) {
		List<UserFriendsDo> userFriendsDo = null;
		try {
			userFriendsDo = userFriendsDAO.findByUid(uid);
		} catch (Exception ex) {
			LOGGER.debug("Error fetching user info: " + ex.toString());
			return null;
		}
		return userFriendsDo;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/friend")
	@ResponseBody
	public StatusDTO confirmFriend(@RequestBody UserFriendsRequestDTO userFriendsRequestDTO) {
		UserFriendsDo userFriendDo = null;
		try {
			 userFriendDo = userFriendsDAO.findByUidAndFriendphoneno(Long.valueOf(userFriendsRequestDTO.getUid()), userFriendsRequestDTO.getFriendphoneno());
			 if(userFriendDo!=null && userFriendDo.getFriended()==1){
				 return new StatusDTO(-1, Constants.RESPONSE_FAILURE_CODE,
							Constants.USER_ALREADY_FRIENDED);
			 }else {
				 userFriendDo.setFriended(1); //confirm friendship
				 userFriendDo.setLastmodified(new Date());
				 userFriendsDAO.save(userFriendDo);
			 }
			
		} catch (Exception ex) {
			LOGGER.debug("Error confirming user-friendship: " + ex.toString());
			return new StatusDTO(userFriendDo.getId(), Constants.RESPONSE_FAILURE_CODE,
					Constants.RESPONSE_FAILURE_MSG);
		}
		
		return new StatusDTO(userFriendDo.getId(), Constants.RESPONSE_SUCCESS_CODE,
				Constants.RESPONSE_SUCCESS_MSG);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/status")
	@ResponseBody
	public UserFriendsDo listStatus(@RequestBody UserFriendsRequestDTO userFriendsRequestDTO) {
		UserFriendsDo userFriendDo = null;
		try {
			 userFriendDo = userFriendsDAO.findByUidAndFriendphoneno(Long.valueOf(userFriendsRequestDTO.getUid()), userFriendsRequestDTO.getFriendphoneno());
			 if(userFriendDo!=null){
				    return userFriendDo;
			 }
			
		} catch (Exception ex) {
			LOGGER.debug("Error confirming user-friendship: " + ex.toString());
			return null;
		}
		
		return null;
		
	}
	
	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private UserFriendsDAO userFriendsDAO;

}
