package com.generic.core.services.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.respository.AreaRepository;
import com.generic.core.services.service.AreaServiceI;

@Service
@Transactional
public class AreaService implements AreaServiceI{

	@Resource
	AreaRepository areaRepository;
}
