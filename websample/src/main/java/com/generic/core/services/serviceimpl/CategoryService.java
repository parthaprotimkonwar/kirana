package com.generic.core.services.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generic.core.model.entities.Categories;
import com.generic.core.respository.CategoriesRepository;
import com.generic.core.services.service.CategoriesServiceI;

@Service
@Transactional
public class CategoryService implements CategoriesServiceI{

	@Resource
	CategoriesRepository categoryRepository;
	
	@Override
	public List<Categories> findAllCategories() {
		
		return categoryRepository.findAll();
	}

	
}
