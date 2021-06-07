package com.cg.fds;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;

import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.repository.IItemRepository;
import com.cg.fds.service.IItemService;

@SpringBootTest
public class ItemSeriviceTest {
	
	@InjectMocks
	IItemService itemService;
	@Mock
	IItemRepository itemRepository;
	
	@Test
	public void addItemTest(List<Restaurant> Restaurant) {
		Category category = new Category("101", "North Indian");
		Item expected = new Item("1","Paneer",category,1,200, Restaurant);
		Mockito.when(itemRepository.save(expected)).thenReturn(expected);
		
		Item actual = itemService.addItem(expected);
		assertEquals(expected.getItemName(),actual.getItemName());
	}
	

}
