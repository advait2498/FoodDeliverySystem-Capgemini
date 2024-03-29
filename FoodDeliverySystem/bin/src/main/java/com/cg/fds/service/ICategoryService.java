package com.cg.fds.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fds.entities.Category;

@Service
@Transactional
public interface ICategoryService {

	public Category addCategory(Category cat);
	public Category updateCategory(int catId, Category cat);
	public Category removeCategory(Category cat);
	public Category viewCategory(int catId);
	public List<Category> viewAllCategory();
	public Category searchByCategoryName(String name);
}
