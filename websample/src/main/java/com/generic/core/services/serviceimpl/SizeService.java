package com.generic.core.services.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Size;
import com.generic.core.respository.SizeRepository;
import com.generic.core.services.service.SizeServiceI;

@Service
@Transactional
public class SizeService implements SizeServiceI{

	@Resource
	SizeRepository quantityRepository;


	@Override
	public List<Size> findAllQuantity() {
		
		return quantityRepository.findAll();
	}
}

