package com.cg.fds.repository;

import java.util.List;

import com.cg.fds.entities.Category;

public interface ICategoryRepository {

	//public Category addCategory(Category cat);
	public Category saveCategory(Category cat);
	//public Category updateCategory(Category cat);
	public Category delete(Category cat);
	public Category findByCategoryName(String categoryName);
	public List<Category> findAll();
}
