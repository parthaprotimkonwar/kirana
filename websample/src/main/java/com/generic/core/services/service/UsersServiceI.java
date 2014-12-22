package com.generic.core.services.service;

import com.generic.rest.dto.LoginDto;
import com.generic.rest.dto.ResponseDto;

public interface UsersServiceI {

	String addGuestIfNotExist(LoginDto loginDto);
	
	void addUser(LoginDto loginDto);
	
	public ResponseDto addUserIfNotExist(LoginDto loginDto);
	
	public ResponseDto loginUser(LoginDto loginDto);
	
}
