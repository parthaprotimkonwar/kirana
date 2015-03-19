package com.generic.rest.mvc;

import static com.generic.core.utilities.Util.generateUserId;
import static com.generic.core.utilities.Util.getSessionStoreHouse;
import static com.generic.core.utilities.Util.isNullAndEmpty;
import static com.generic.core.utilities.Util.saveSessionStoreHouse;
import static com.generic.core.utilities.Util.userLoggedin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.core.services.serviceimpl.ServicesFactory;
import com.generic.core.utilities.UtilConstants;
import com.generic.rest.constants.CheckoutType;
import com.generic.rest.constants.Constants;
import com.generic.rest.constants.SessionAttributes;
import com.generic.rest.constants.SessionStoreConstants;
import com.generic.rest.dto.LoginDto;
import com.generic.rest.dto.LoginResponseDto;
import com.generic.rest.dto.ResponseDto;

@Controller
@RequestMapping(value="/rest/authenticate")
public class LoginController {

	@Resource
	ServicesFactory serviceFactory;
	
	/**
	 * User Login. Includes only for guest users
	 * URI : $contextConfigLocation/rest/authenticate/loggedin
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/loggedin", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto loggedIn(HttpSession session) {
		if(!userLoggedin(session)) {
			return new ResponseDto(Constants.USER_NOT_LOGGED_IN_CODE, Constants.USER_NOT_LOGGED_IN_MESSAGE);
		} else {
			return new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE);
		}
	}
	
	/**
	 * User Login. Includes only for guest users
	 * URI : $contextConfigLocation/rest/authenticate/login
	 * 
	 * @param session
	 * @param loginDto
	 * @return ResponseResource
	 */
	@RequestMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public @ResponseBody LoginResponseDto login(HttpSession session, @RequestBody LoginDto loginDto) {
		SessionAttributes sessionAttributes = getSessionStoreHouse(session);									// reading data from session
		if(userLoggedin(session)) {
			ResponseDto response = new ResponseDto(Constants.USER_LOGGED_IN_CODE, Constants.USER_LOGGED_IN_MESSAGE);
			return new LoginResponseDto(response, null);
		}
		if(isNullAndEmpty(loginDto.getCheckoutType())) {
			ResponseDto response =  new ResponseDto(Constants.FAILURE_RESPONSE_CODE, Constants.FAILURE_RESPONSE_MESSAGE); 
			return new LoginResponseDto(response, null);
		}
		if(loginDto.getCheckoutType().equals(CheckoutType.GUEST.getValue()) ||
				 loginDto.getCheckoutType().equals(CheckoutType.FACEBOOK.getValue()) ||
						loginDto.getCheckoutType().equals(CheckoutType.GPLUS.getValue())) {									// Login as a GUEST
																															// or as Facebook or GPLUS
			String userId= generateUserId(UtilConstants.GUEST_USER_PREFIX); 
			loginDto.setUserId(userId);
			
			userId = serviceFactory.getUserService().addGuestIfNotExist(loginDto);
			sessionAttributes.setUserId(userId);																// setting the userId
			sessionAttributes.setLoginDto(loginDto);
			session.setAttribute(SessionStoreConstants.USER_LOGGED_IN, new Boolean("true"));
			saveSessionStoreHouse(session, sessionAttributes);													// save data to session
			ResponseDto response = new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE); 
			return new LoginResponseDto(response, loginDto);
			
		} else if(loginDto.getCheckoutType().equals(CheckoutType.LOGIN.getValue())) {							// Login as a USER
			
			ResponseDto response = serviceFactory.getUserService().loginUser(loginDto);
			if(response.getStatusCode().equals(Constants.SUCCESS_RESPONSE_CODE)) {
				sessionAttributes.setUserId(loginDto.getUserId());												// setting the userId
				sessionAttributes.setLoginDto(loginDto);
				session.setAttribute(SessionStoreConstants.USER_LOGGED_IN, new Boolean("true"));
				saveSessionStoreHouse(session, sessionAttributes);												// save data to session
				ResponseDto responseDto =  new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE); 
				return new LoginResponseDto(responseDto, loginDto);
			}
			return new LoginResponseDto(response, null);
		}
		return new LoginResponseDto(new ResponseDto(Constants.FAILURE_RESPONSE_CODE, Constants.FAILURE_RESPONSE_MESSAGE), null);
	}
	
	
	@RequestMapping(value="/user/signup", produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public @ResponseBody ResponseDto userSignup(HttpSession session, @RequestBody LoginDto loginDto) {
		
		String userId= generateUserId(UtilConstants.USER_PREFIX); 
		loginDto.setUserId(userId);
		
		ResponseDto response = serviceFactory.getUserService().addUserIfNotExist(loginDto);
		
		if(response.getStatusCode().equals(Constants.SUCCESS_RESPONSE_CODE)) {
			SessionAttributes sessionAttributes = getSessionStoreHouse(session);								// reading data from session
			sessionAttributes.setUserId(userId);																// setting the userId
			sessionAttributes.setLoginDto(loginDto);
			session.setAttribute(SessionStoreConstants.USER_LOGGED_IN, new Boolean("true"));
			saveSessionStoreHouse(session, sessionAttributes);													// save data to session
		}
		return response;
	}
	
	/**
	 * User Logout
	 * URI : $contextConfigLocation/rest/authenticate/logout
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto logout(HttpSession session) {
		if(!userLoggedin(session))
			return new ResponseDto(Constants.USER_NOT_LOGGED_IN_CODE, Constants.USER_NOT_LOGGED_IN_MESSAGE);
		session.invalidate();
		return new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE);
	}
}
