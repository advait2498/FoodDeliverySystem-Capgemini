package com.capgemini.fds;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.fds.entities.Category;
import com.capgemini.fds.entities.Item;
import com.capgemini.fds.repository.IItemRepository;
import com.capgemini.fds.service.impl.ItemServiceImpl;


@SpringBootTest
public class ItemTest 
{
	
	@InjectMocks
	private ItemServiceImpl itemService;
	
	@Mock
	private IItemRepository itemRepository;
	
	@Test
    public void findByItemNameStartsWithTest() throws Exception {
        List<Item> itemList = new ArrayList<>();
        Item i1 = new Item(1,"Chicken Biryani",null,1,200,null);
        Item i2 = new Item(2,"Chicken Dum Biryani",null,1,200,null);
        itemList.add(i1);
        itemList.add(i2);
        
        Mockito.when(itemRepository.findByItemNameStartsWith("Chicken")).thenReturn(itemList);
        List<Item> actitemList = itemService.findByItemNameStartsWith("Chicken");
        assertEquals(itemList.get(1),actitemList.get(1));
        verify(itemRepository,times(1)).findByItemNameStartsWith("Chicken");
   
    }
	
	@Test
	public void testByPrice()
	{
		List<Item> itemList=new ArrayList<>();
		Item i1 = new Item(1,"Manchuria",null,1,150,null);
		Item i2 = new Item(2,"Chicken Biryani",null,1,200,null);
		Item i3 = new Item(3,"Salad",null,1,110,null);
		itemList.add(i1);
		itemList.add(i2);
		itemList.add(i3);
		Mockito.when(itemRepository.findByCostGreaterThanEqual(150)).thenReturn(itemList);
		
		List<Item> actItem = itemService.findByCostGreaterThanEqual(150);
		assertEquals(itemList.get(1), actItem.get(1));
		verify(itemRepository,times(1)).findByCostGreaterThanEqual(150);
	}
	
	@Test
	public void sortByNameTest()
	{
		Category cat = new Category(1,"Starters");
		Category cat1 = new Category(2, "Biryani");
		List<Item> itemList=new ArrayList<>();
		Item i1 = new Item(1,"Manchuria",cat,1,150,null);
		Item i2 = new Item(2,"Chicken Biryani",cat1,1,200,null);
		Item i3 = new Item(3,"Salad",cat,1,110,null);
		itemList.add(i1);
		itemList.add(i2);
		itemList.add(i3);
		Mockito.when(itemRepository.findByCategoryOrderByItemName(cat)).thenReturn(itemList);
		
		List<Item> actItem = itemService.findByCategoryOrderByItemName(cat);
		assertEquals(itemList.get(1),actItem.get(1));
		verify(itemRepository,times(1)).findByCategoryOrderByItemName(cat);
	}

	@Test
    public void searchByPriceBetween() {
        List <Item> itemList = new ArrayList<>();
        Item i1 = new Item(1,"Hakka Noodles",null,1,200,null);
        Item i2 = new Item(1,"Schzwan Noodles",null,1,300,null);
        Item i3 = new Item(1,"Chilli Noodles",null,1,350,null);
        Item i4 = new Item(1,"Special Noodles",null,1,400,null);
       
        itemList.add(i4);
        itemList.add(i3);
        itemList.add(i2);
        itemList.add(i1);
       
        Mockito.when(itemRepository.findByCostBetween(450, 500)).thenReturn(itemList);
        
        List<Item> actItem = itemService.findByCostBetween(450, 500);
        assertEquals(itemList.get(1),actItem.get(1));
        verify(itemRepository,times(1)).findByCostBetween(450, 500);  
    }

}
