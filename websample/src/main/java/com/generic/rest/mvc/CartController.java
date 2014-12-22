package com.generic.rest.mvc;

import static com.generic.core.utilities.Util.getSessionStoreHouse;
import static com.generic.core.utilities.Util.saveSessionStoreHouse;
import static com.generic.core.utilities.Util.userLoggedin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.rest.constants.Constants;
import com.generic.rest.constants.SessionAttributes;
import com.generic.rest.dto.CartDto;
import com.generic.rest.dto.ResponseDto;

@Controller
@RequestMapping(value="/rest/cart")
public class CartController {

	/**
	 * Store the cart to the session only when the user is logged in
	 * @param cart
	 * @param session
	 * @return ResponseResource
	 */
	@RequestMapping(value="/addtocart", method=RequestMethod.POST)
	public @ResponseBody ResponseDto addToCart(@RequestBody List<CartDto> cart, HttpSession session) {
		if(!userLoggedin(session))
			return new ResponseDto(Constants.USER_NOT_LOGGED_IN_CODE, Constants.USER_NOT_LOGGED_IN_MESSAGE);
		SessionAttributes sessionAttributes = getSessionStoreHouse(session);
		sessionAttributes.setCart(cart);																		//find out how to retrieve the items
		saveSessionStoreHouse(session, sessionAttributes);
		return new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE);
	}
	
	//TODO : remove it
	@RequestMapping(value="/displaycart", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CartDto> displayCart() {
		
		List<CartDto> list = new ArrayList<CartDto>();
		list.add(new CartDto("001", "5"));
		list.add(new CartDto("002", "6"));
		list.add(new CartDto("003", "7"));
		list.add(new CartDto("004", "8"));
		list.add(new CartDto("005", "9"));
		list.add(new CartDto("006", "10"));
		return list;
	}
}
