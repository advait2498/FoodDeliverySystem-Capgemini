package com.capgemini.fds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.fds.entities.Category;
import com.capgemini.fds.exception.CategoryNotFoundException;
import com.capgemini.fds.repository.ICategoryRepository;
import com.capgemini.fds.service.ICategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	ICategoryRepository repository;
	
	public CategoryServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Category addCategory(Category cat) {
		// TODO Auto-generated method stub
		return repository.save(cat);
	}

	@Override
	public Category updateCategory(Category cat) {
		// TODO Auto-generated method stub
		Category isCategoryAvailable = repository.findByCategoryName(cat.getCategoryName());
		if(isCategoryAvailable != null) {
			repository.save(cat);
			return cat;
		}else {
			throw new CategoryNotFoundException("Category is not availble.");
		}
	}

	@Override
	public Category removeCategory(Category cat) {
		// TODO Auto-generated method stub
		Category isCategoryAvailable = repository.findByCategoryName(cat.getCategoryName());
		if(isCategoryAvailable != null) {
			repository.delete(cat);
			return cat;
		}else {
			throw new CategoryNotFoundException("Category is not availble.");
		}
	}

	@Override
	public Category viewCategory(Category cat) {
		// TODO Auto-generated method stub
		Category isCategoryAvailable = repository.findByCategoryName(cat.getCategoryName());
		if(isCategoryAvailable != null) {
			return cat;
		}else {
			throw new CategoryNotFoundException("Category is not availble.");
		}
	}
	
	public Category viewCategory(int catId) {
		Category category = repository.findByCatId(catId);
		if(category != null) {
			return category;
		}else {
			throw new CategoryNotFoundException("Category is not availble.");
		}
	}

	@Override
	public List<Category> viewAllCategory() {
		return repository.findAll();
	}

	@Override
	public Category searchByCategoryName(String name) {
		return repository.findByCategoryName(name);
	}

}