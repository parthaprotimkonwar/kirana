package com.generic.core.services.serviceimpl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Users;
import com.generic.core.respository.UsersRepository;
import com.generic.core.services.service.UsersServiceI;
import com.generic.rest.constants.Constants;
import com.generic.rest.dto.LoginDto;
import com.generic.rest.dto.ResponseDto;

@Service
@Transactional
public class UserService implements UsersServiceI{

	@Resource
	UsersRepository userRepository;
	
	@Override
	public String addGuestIfNotExist(LoginDto loginDto) { 
		
		Users aGuest = userRepository.findByEmail(loginDto.getEmailId());
		if(aGuest == null) {
			addUser(loginDto);
		} else {
			return aGuest.getUserId();
		}
		return loginDto.getUserId();
	}

	@Override
	public ResponseDto addUserIfNotExist(LoginDto loginDto) {
		
		Users aUser = userRepository.findByEmail(loginDto.getEmailId());
		if(aUser == null) {
			addUser(loginDto);
		} else {
			return new ResponseDto(Constants.USER_EXIST_CODE, Constants.USER_EXIST_MESSAGE);
		}
		return new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE);
	}
	
	@Override
	public ResponseDto loginUser(LoginDto loginDto) {
		
		Users aUser = userRepository.findByEmailAndPassword(loginDto.getEmailId(), loginDto.getPassword());
		if(aUser == null) {
			return new ResponseDto(Constants.INVALID_LOGIN_CODE, Constants.INVALID_LOGIN_MESSAGE);
		} else {
			loginDto.setUserId(aUser.getUserId());
			loginDto.setEmailId(aUser.getEmail());
			loginDto.setPhoneNo(aUser.getPhone());
			loginDto.setUserName(aUser.getUserName());
		}
		return new ResponseDto(Constants.SUCCESS_RESPONSE_CODE, Constants.SUCCESS_RESPONSE_MESSAGE);
	}
	@Override
	public void addUser(LoginDto loginDto) {
		
		Date now = new Date();
		Users users = new Users();
		
		users.setUserId(loginDto.getUserId());
		users.setUserName(loginDto.getUserName());
		users.setPassword(loginDto.getPassword());
		users.setEmail(loginDto.getEmailId());
		users.setPhone(loginDto.getPhoneNo());
		users.setCreationTime(now);
		users.setLastAccessedTime(now);
		userRepository.saveAndFlush(users);
	}
}
