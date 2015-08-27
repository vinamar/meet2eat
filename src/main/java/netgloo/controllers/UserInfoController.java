package netgloo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import netgloo.commons.util.CommonsUtil;
import netgloo.constants.Constants;
import netgloo.dto.ContactDTO;
import netgloo.dto.SmsVerifyRespDTO;
import netgloo.dto.StatusDTO;
import netgloo.models.SmsVerifyDo;
import netgloo.models.UserDo;
import netgloo.repository.SmsVerifyDAO;
import netgloo.repository.UserDAO;
import netgloo.response.dto.ContactRespDTO;

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
@RequestMapping("/user")
public class UserInfoController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserInfoController.class);

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
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseBody
	public StatusDTO create(@RequestBody UserDo user) {
		try {
			// phone no is one of the PK
			UserDo temp = repository.findByPhoneno(user.getPhoneno());
			if (temp == null) {
				user.setRegistereddate(new Date());
				repository.save(user);

				if (user.getId() > 0) { // if user is created successfully then
										// generate sms code and save
					long smscode = CommonsUtil.generate6DigitRandCode();
					SmsVerifyDo smsVerify = new SmsVerifyDo(user.getPhoneno(),
							smscode);
					smsVerify.setLastmodified(new Date());
					smsVerifyrepository.save(smsVerify);
				}

			} else if (temp.getPhoneno() != null && temp.getUid() != null) {
				LOGGER.debug("User already exists: " + temp.getId());
				return new StatusDTO(temp.getId(),
						Constants.RESPONSE_FAILURE_CODE,
						Constants.USER_EXIST_MSG);
			}

		} catch (Exception ex) {
			LOGGER.debug("Error creating the user: " + ex.toString());
			return new StatusDTO(-1, Constants.RESPONSE_FAILURE_CODE,
					Constants.RESPONSE_FAILURE_MSG);
		}
		LOGGER.debug("Group succesfully created! (id = " + user.getId() + ")");
		return new StatusDTO(user.getId(), Constants.RESPONSE_SUCCESS_CODE,
				Constants.RESPONSE_SUCCESS_MSG);
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public UserDo findById(@PathVariable("id") int id) {
		UserDo user = null;
		try {
			user = repository.findById(id);
		} catch (Exception ex) {
			LOGGER.debug("Error fetching groups: " + ex.toString());
			return null;
		}
		return user;
	}

	//api to retrieve user info by uid
	@RequestMapping(method = RequestMethod.GET, value = "/uid")
	@ResponseBody
	public UserDo findByUid(@RequestParam("uid") String uid) {
		UserDo user = null;
		try {
			user = repository.findByUid(uid);
		} catch (Exception ex) {
			LOGGER.debug("Error fetching user info: " + ex.toString());
			return null;
		}
		return user;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/validatePhones")
	@ResponseBody
	public List<ContactRespDTO> findByPhonenos(@RequestBody ContactDTO[] contacts) {
		List<ContactRespDTO> respList = new ArrayList<ContactRespDTO>();
		try {
			List<String> phonenos = new ArrayList<String>();
			for(ContactDTO contact: contacts){
				phonenos.add(contact.getPhoneno());
			}
			List<UserDo> userDos = repository.findByPhonenos(phonenos);
			
			for(UserDo userdo: userDos){
				ContactRespDTO resp = new ContactRespDTO();
				resp.setPhoneno(userdo.getPhoneno());
				resp.setAppRegistered(userdo.getRegistered()==1?true:false);
				respList.add(resp);
			}
		} catch (Exception ex) {
			LOGGER.debug("Error fetching user info: " + ex.toString());
			return null;
		}
		return respList;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/isregistered")
	@ResponseBody
	public boolean isRegistered(@RequestParam("phoneno") String phoneno) {
		UserDo user = null;
		try {
			user = repository.findByPhoneno(phoneno);
			return (user.getRegistered()==1?true:false);
		} catch (Exception ex) {
			LOGGER.debug("Error fetching user info: " + ex.toString());
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/resend")
	@ResponseBody
	public SmsVerifyRespDTO resendSMSCode(
			@RequestBody SmsVerifyDo smsverifyReqDo) {
		try {
			// findby uid (fb uid)
			SmsVerifyDo smsVerify = smsVerifyrepository
					.findByPhoneno(smsverifyReqDo.getPhoneno());
			if (smsVerify != null) {
				if (smsVerify.getVerified() != 1) {
					long smscode = CommonsUtil.generate6DigitRandCode();
					smsVerify.setSmscode(smscode);
					smsVerify.setDeviceid(smsverifyReqDo.getDeviceid());
					smsVerify.setResendcount(smsVerify.getResendcount() + 1);
					smsVerifyrepository.save(smsVerify);

					LOGGER.debug("resend confirm code: "
							+ smsverifyReqDo.getSmscode() + " success for: "
							+ smsverifyReqDo.getPhoneno());
					return new SmsVerifyRespDTO(smsVerify.getPhoneno(),
							smsVerify.getSmscode(),
							Constants.RESPONSE_SUCCESS_CODE,
							Constants.SMS_RESEND_SUCCESS_MSG);
				} else if (smsVerify.getVerified() == 1) {
					LOGGER.debug("This phone no is already SMS verified for: "
							+ smsverifyReqDo.getPhoneno());
					return new SmsVerifyRespDTO(smsVerify.getPhoneno(),
							smsVerify.getSmscode(),
							Constants.RESPONSE_FAILURE_CODE,
							Constants.SMS_ALREADY_VERIFIED_MSG);
				}
			}

		} catch (Exception ex) {
			LOGGER.debug("resend confirm code failure: " + ex.toString());
			return new SmsVerifyRespDTO(smsverifyReqDo.getPhoneno(),
					smsverifyReqDo.getSmscode(),
					Constants.RESPONSE_FAILURE_CODE,
					Constants.RESPONSE_FAILURE_MSG);
		}

		LOGGER.debug("SMS code not resent for: " + smsverifyReqDo.getPhoneno());
		return new SmsVerifyRespDTO(smsverifyReqDo.getPhoneno(),
				smsverifyReqDo.getSmscode(), Constants.RESPONSE_FAILURE_CODE,
				Constants.SMS_RESEND_FAIL_MSG);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getsmscode")
	@ResponseBody
	public SmsVerifyRespDTO getSMSCode(
			@RequestBody SmsVerifyDo smsverifyReqDo) {
		try {
			// findby uid (fb uid)
			SmsVerifyDo smsVerify = smsVerifyrepository
					.findByPhoneno(smsverifyReqDo.getPhoneno());
			if (smsVerify != null) {
					return new SmsVerifyRespDTO(smsVerify.getPhoneno(),
							smsVerify.getSmscode(),
							Constants.RESPONSE_SUCCESS_CODE,
							Constants.SMS_GET_SUCCESS_MSG);
			}

		} catch (Exception ex) {
			LOGGER.debug("Error getting sms code for the given ph. no: " + ex.toString());
			return new SmsVerifyRespDTO(smsverifyReqDo.getPhoneno(),
					smsverifyReqDo.getSmscode(),
					Constants.RESPONSE_FAILURE_CODE,
					Constants.SMS_GET_FAIL_MSG);
		}

		LOGGER.debug("Error getting sms code for the given ph. no: " + smsverifyReqDo.getPhoneno());
		return new SmsVerifyRespDTO(smsverifyReqDo.getPhoneno(),
				smsverifyReqDo.getSmscode(),
				Constants.RESPONSE_FAILURE_CODE,
				Constants.SMS_GET_FAIL_MSG);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/confirm")
	@ResponseBody
	public SmsVerifyRespDTO confirmSMSCode(
			@RequestBody SmsVerifyDo smsverifyReqDo) {
		try {
			// findby uid (fb uid)
			SmsVerifyDo smsVerify = smsVerifyrepository
					.findByPhoneno(smsverifyReqDo.getPhoneno());
			if (smsVerify != null) {

				if (smsVerify.getVerified() != 1) {
					if (smsVerify.getSmscode() == smsverifyReqDo.getSmscode()) {
						smsVerify.setVerified(1);
						smsVerify.setLastmodified(new Date());
						smsVerify.setDeviceid(smsverifyReqDo.getDeviceid());
						smsVerifyrepository.save(smsVerify);
						LOGGER.debug("verified confirm code: "
								+ smsverifyReqDo.getSmscode()
								+ " success for: "
								+ smsverifyReqDo.getPhoneno());

						UserDo user = repository.findByPhoneno(smsverifyReqDo
								.getPhoneno());
						user.setRegistered(1);
						user.setRegistereddate(new Date());

						repository.save(user);
						LOGGER.debug("confirmed user registrationfor : "
								+ user.getEmail() + " with phoneno: "
								+ user.getPhoneno());

						return new SmsVerifyRespDTO(smsVerify.getPhoneno(),
								smsVerify.getSmscode(),
								Constants.RESPONSE_SUCCESS_CODE,
								Constants.SMS_CONFIRM_SUCCESS_MSG);

					}

				} else if (smsVerify.getVerified() == 1) {
					LOGGER.debug("This phone no is already SMS verified for: "
							+ smsverifyReqDo.getPhoneno());
					return new SmsVerifyRespDTO(smsverifyReqDo.getPhoneno(),
							smsverifyReqDo.getSmscode(),
							Constants.RESPONSE_FAILURE_CODE,
							Constants.SMS_ALREADY_VERIFIED_MSG);
				}
			}

		} catch (Exception ex) {
			LOGGER.debug("Error verifying/confirming the sms code: "
					+ ex.toString());
			return new SmsVerifyRespDTO(smsverifyReqDo.getPhoneno(),
					smsverifyReqDo.getSmscode(),
					Constants.RESPONSE_FAILURE_CODE,
					Constants.RESPONSE_FAILURE_MSG);
		}
		LOGGER.debug("This is not a valid SMS code for: "
				+ smsverifyReqDo.getPhoneno());
		return new SmsVerifyRespDTO(smsverifyReqDo.getPhoneno(),
				smsverifyReqDo.getSmscode(), Constants.RESPONSE_FAILURE_CODE,
				Constants.SMSCODE_DOESNT_MATCH_MSG);
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private UserDAO repository;

	@Autowired
	private SmsVerifyDAO smsVerifyrepository;

}
