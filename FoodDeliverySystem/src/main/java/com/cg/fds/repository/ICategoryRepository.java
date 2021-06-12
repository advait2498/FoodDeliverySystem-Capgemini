package com.capgemini.fds.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.fds.entities.Category;

public interface ICategoryRepository extends CrudRepository<Category, Integer>{

	//public Category addCategory(Category cat);
	//public Category saveCategory(Category cat);
	//public Category updateCategory(Category cat);
//	public Category delete(Category cat);
	public Category findByCatId(int id);
	public Category findByCategoryName(String categoryName);
	public List<Category> findAll();
}