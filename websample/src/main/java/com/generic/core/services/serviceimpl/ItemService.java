package com.generic.core.services.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Items;
import com.generic.core.respository.ItemsRepository;
import com.generic.core.services.service.ItemServiceI;

@Service
@Transactional
public class ItemService implements ItemServiceI {

	@Resource
	ItemsRepository itemRepository;
	
	@Override
	public List<Items> findAllItems() {

		return itemRepository.findAll();
	}

	
}
