package com.generic.core.services.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Shops;
import com.generic.core.model.entities.Transactions;
import com.generic.core.model.entities.Users;
import com.generic.core.respository.TransactionsRepository;
import com.generic.core.services.service.TransactionServiceI;
import com.generic.core.utilities.Util;
import com.generic.core.utilities.UtilConstants;
import com.generic.rest.constants.SessionAttributes;
import com.generic.rest.constants.Status;
import com.generic.rest.dto.TransactionDto;

@Service
@Transactional
public class TransactionService implements TransactionServiceI{

	@Resource
	TransactionsRepository transactionsRepository;
	
	
	@Override
	public String createTransaction(TransactionDto transactionDto, SessionAttributes sessionAttributes) {
		Transactions transaction = new Transactions();
		Date now = new Date();
		Shops shop = new Shops(sessionAttributes.getShopId());
		Users user = new Users(sessionAttributes.getUserId());
		
		transaction.setTxnId(generateTransactionId());
		transaction.setTxnCreatedTime(now);
		transaction.setTxnUpdatedTime(now);
		
		Date preferredDeliveryDate = Util.getPreferredDeliveryDate(transactionDto.getPreferredDeliveryDate());
		transaction.setPreferredDeliveryDate(preferredDeliveryDate);
		transaction.setPreferredDeliveryTimeSlot(transactionDto.getPreferreddeliveryTimeSlot());
		transaction.setDeliveryAddress(transactionDto.getDeliveryAddress());
		transaction.setPaymentMode(transactionDto.getPaymentMode());
		transaction.setPaymentStatus(transactionDto.getPaymentStatus());
		transaction.setDeliveryStatus(Status.PENDING.getValue());
		transaction.setItems(sessionAttributes.getItems());
		transaction.setShop(shop);
		transaction.setUser(user);
		
		transactionsRepository.saveAndFlush(transaction);
		return transaction.getTxnId();
	}
	

	private String generateTransactionId() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		return UtilConstants.TRANSACTION_PREFIX + sdf.format(d);
	}

	@Override
	public String updateTransaction(String txnId) {
		// TODO Auto-generated method stub
		/*} else {
		transaction.setTxnId(sessionAttributes.getTxnId());
		transaction.setTxnUpdatedTime(now);
		transaction.setDeliveryAddress(transactionDto.getDeliveryAddress());
		transaction.setPaymentStatus(transactionDto.getPaymentStatus());
		transaction.setDeliveryStatus(transactionDto.getDeliveryStatus());
	}*/
		return null;
	}

}
