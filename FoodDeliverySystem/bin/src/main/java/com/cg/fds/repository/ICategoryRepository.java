package com.cg.fds.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.fds.entities.Category;

public interface ICategoryRepository extends CrudRepository<Category, Integer> {

	//public Category addCategory(Category cat);
	//public Category saveCategor(Category cat);
	//public Category updateCategory(Category cat);
	//public Category delete(Category cat);
	//public Category findByCatId(int id);
	public Category findByCategoryName(String categoryName);
	//public List<Category> findAll();
}
