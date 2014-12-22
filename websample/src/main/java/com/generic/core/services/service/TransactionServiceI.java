package com.generic.core.services.service;

import com.generic.rest.constants.SessionAttributes;
import com.generic.rest.dto.TransactionDto;

public interface TransactionServiceI {

	String createTransaction(TransactionDto transactionDto, SessionAttributes sessionAttributes);
	
	String updateTransaction(String txnId);
}
