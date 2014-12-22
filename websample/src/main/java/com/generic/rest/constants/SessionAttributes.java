package com.generic.rest.constants;

import static com.generic.core.utilities.Util.isNullAndEmpty;

import java.io.Serializable;
import java.util.List;

import com.generic.rest.dto.CartDto;
import com.generic.rest.dto.LoginDto;
import com.generic.rest.dto.TransactionDto;

public class SessionAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String shopId;
	private String locationId;
	private String txnId;
	private String items;
	private LoginDto loginDto;
	private List<CartDto> cart;					//items will be set when setCart is called
	private TransactionDto transactionDto;
	
	
	public void copyTransactionDto(TransactionDto original, TransactionDto current) {
	
		if(!isNullAndEmpty(current.getDeliveryAddress()))
			original.setDeliveryAddress(current.getDeliveryAddress());
		
		if(!isNullAndEmpty(current.getDeliveryStatus()))
			original.setDeliveryStatus(current.getDeliveryStatus());
		
		if(!isNullAndEmpty(current.getPaymentMode()))
			original.setPaymentMode(current.getPaymentMode());
		
		if(!isNullAndEmpty(current.getPaymentStatus()))
			original.setPaymentStatus(current.getPaymentStatus());
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public LoginDto getLoginDto() {
		return loginDto;
	}

	public void setLoginDto(LoginDto loginDto) {
		this.loginDto = loginDto;
	}

	public List<CartDto> getCart() {
		return cart;
	}

	public void setCart(List<CartDto> cart) {
		StringBuffer sb = new StringBuffer();
		for(CartDto cartDto : cart) {
			sb.append(cartDto.getItemId());
			sb.append(":");
			sb.append(cartDto.getQuantity());
			sb.append("|");
		}
		setItems(sb.substring(0, sb.length() - 1));				//setting @Items
		this.cart = cart;
	}

	public TransactionDto getTransactionDto() {
		return transactionDto;
	}

	public void setTransactionDto(TransactionDto transactionDto) {
		if(this.transactionDto == null)
			this.transactionDto = transactionDto;
		else if(transactionDto != null)
			copyTransactionDto(this.transactionDto, transactionDto);
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
}
