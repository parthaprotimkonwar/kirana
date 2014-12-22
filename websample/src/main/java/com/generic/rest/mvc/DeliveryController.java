package com.generic.rest.mvc;

import static com.generic.core.utilities.Util.getSessionStoreHouse;
import static com.generic.core.utilities.Util.saveSessionStoreHouse;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.core.services.serviceimpl.ServicesFactory;
import com.generic.rest.constants.Constants;
import com.generic.rest.constants.SessionAttributes;
import com.generic.rest.dto.ResponseDto;
import com.generic.rest.dto.TransactionDto;

@Controller
@RequestMapping("/rest/delivery")
public class DeliveryController {

	@Resource
	ServicesFactory serviceFactory;
	
	@RequestMapping(value="proceed/for/delivery", consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody ResponseDto proceedForDelivery(@RequestBody TransactionDto transactionDto, HttpSession session) {
	
		SessionAttributes sessionAttributes = getSessionStoreHouse(session);
		String txnId = serviceFactory.getTransactionService().createTransaction(transactionDto, sessionAttributes);
		sessionAttributes.setTransactionDto(transactionDto);
		sessionAttributes.setTxnId(txnId);
		saveSessionStoreHouse(session, sessionAttributes);
		return new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE);
	}
}
