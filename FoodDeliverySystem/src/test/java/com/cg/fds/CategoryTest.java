package com.capgemini.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.fds.entities.Category;
import com.capgemini.fds.repository.ICategoryRepository;
import com.capgemini.fds.repository.IItemRepository;
import com.capgemini.fds.service.impl.CategoryServiceImpl;

@SpringBootTest
class CategoryTest {
	@InjectMocks
	private CategoryServiceImpl iCategoryService;
	@Mock
	private ICategoryRepository iCategoryRepository;
	@Mock
	private IItemRepository iRepository;

	@Test
	public void addCategoryTest() {
		Category expCategory = new Category(1, "South Indian");
		Category expCategory1 = new Category(2, "North Indian");
		Mockito.when(iCategoryRepository.save(expCategory)).thenReturn(expCategory);

		Category actCategory = iCategoryService.addCategory(expCategory);
		assertEquals(expCategory.getCategoryName(), actCategory.getCategoryName());
		
		Mockito.when(iCategoryRepository.save(expCategory1)).thenReturn(expCategory1);

		Category actCategory1 = iCategoryService.addCategory(expCategory1);
		assertEquals(expCategory1.getCategoryName(), actCategory1.getCategoryName());
	}

	
	@Test 
	public void updateCategoryTest() throws Exception
	{ 
		Category expCategory = new Category(2, "South Indian");
		Mockito.when(iCategoryRepository.findById(2)).thenReturn(Optional.of(expCategory));
	  
		Category actCategory = iCategoryService.updateCategory(expCategory);
		assertEquals(actCategory.getCategoryName(), expCategory.getCategoryName());
		verify(iCategoryRepository,times(1)).save(expCategory); 
	}
	  
	@Test 
	public void removeCategoryTest() throws Exception
	{
		Category expCategory = new Category(1, "Starters");
		iCategoryRepository.delete(expCategory);
		verify(iCategoryRepository,times(1)).delete(expCategory);
	}
	
	@Test
	public void viewCategoryTest() throws Exception
	{
		Category expCategory = new Category(1, "Starters");
		Mockito.when(iCategoryRepository.findById(1)).thenReturn(Optional.of(expCategory));
	  
		Category actCategory = iCategoryService.viewCategory(expCategory);
		assertEquals(actCategory.getCategoryName(), expCategory.getCategoryName());
		verify(iCategoryRepository,times(1)).findById(1);
	}
	
	@Test
	public void viewAllCategoryTest()
	{
		Category expCategory = new Category(1, "Starters");
		Category expCategory1 = new Category(2, "South Indian");
		Category expCategory2 = new Category(3, "North Indian");
		Category expCategory3 = new Category(4, "Chineese");
		Category expCategory4 = new Category(5, "Thai");
		Category expCategory5 = new Category(6, "Italian");
		Category expCategory6 = new Category(7, "Beverages");
		List<Category> expCatList = new ArrayList<>();
		expCatList.add(expCategory);
		expCatList.add(expCategory1);
		expCatList.add(expCategory2);
		expCatList.add(expCategory3);
		expCatList.add(expCategory4);
		expCatList.add(expCategory5);
		expCatList.add(expCategory6);

		Mockito.when(iCategoryRepository.findAll()).thenReturn(expCatList);
	  
		List<Category> actCatList = iCategoryService.viewAllCategory();
		assertEquals(expCatList.size(), actCatList.size());
		verify(iCategoryRepository,times(1)).findAll();
	}
}
