package com.cg.fds;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Category;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.repository.IItemRepository;
import com.cg.fds.service.IItemService;
import com.cg.fds.service.impl.ItemServiceImpl;
import java.util.Optional;

@SpringBootTest
public class ItemSeriviceTest {
	
	@InjectMocks
	ItemServiceImpl itemService;
	@Mock
	IItemRepository itemRepository;
	
	@Test
	public void addItemTest() {
		//Category category = new Category(101,"North Indian");
		Category category = new Category();
		category.setCatId(101);
		category.setCategoryName("Chinese");
		Address address = new Address();
		//Address address = new Address("901","Vaishali Tower","BR Road","Mulund","Mumbai","Maharashtra","India","400080");
		address.setAddressId(1010);
		address.setBuildingName("Vaishali Tower");
		address.setArea("BR Road");
		address.setStreetNo("Street 3");
		address.setCity("Mulund");
		address.setState("Maharashtra");
		address.setPincode("411002");
		ArrayList<Item> itemList = new ArrayList<Item>();
		ArrayList<Restaurant> resList = new ArrayList<>();
		resList.addAll(resList);
		Restaurant restaurants = new Restaurant(301,"Dwarka",address,itemList,"Raj","8767230066");
		
		Item expected = new Item();
		expected.setCategory(category);
		expected.setItemId(1);
		expected.setItemName("Paneer");
		expected.setQuantity(3);
		expected.setCost(500.00);
		expected.setRestaurants(resList);
		Mockito.when(itemRepository.save(expected)).thenReturn(expected);
		
		Item actual = itemService.addItem(expected);
		assertEquals(expected.getItemName(),actual.getItemName());

	}
	
	@Test
    public void updateItem() {
        Category category = new Category(101, "North Indian");
        Address address = new Address(901,"Vaishali Tower","BR Road","Mulund","Mumbai","Maharashtra","India","400080");
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<Restaurant> resList = new ArrayList<>();
        resList.addAll(resList);
        Restaurant restaurants = new Restaurant(301,"Dwarka",address,itemList,"Raj","8767230066");
        Item expected = new Item(1,"Paneer",category,1,200,resList);
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(expected));
//        expected.setQuantity(2);
//        expected.setCost(500);
//        System.out.println(i1.getQuantity());
//        Mockito.when(itemRepository.save(i1)).thenReturn(i1);
//        assertThat(itemService.updateItem(i1));
//        verify(itemRepository,times(1).save(i1));
        Item actual = itemService.updateItem(1,expected);
   
   
    }
	
	@Test
	public void removeItem() {
		Category category = new Category(101, "North Indian");
		Address address = new Address(901,"Vaishali Tower","BR Road","Mulund","Mumbai","Maharashtra","India","400080");
		ArrayList<Item> itemList = new ArrayList<Item>();
		Restaurant restaurants = new Restaurant(101,"Ashirwad",address,itemList,"Satyam","23428942991");
		ArrayList<Restaurant> resList = new ArrayList<>();
		resList.addAll(resList);
		Item expectedItem = new Item(1,"Paneer",category,1,200,resList);
		itemService.removeItem(expectedItem.getItemId(),expectedItem);
		verify(itemRepository,times(1)).deleteById(1);
//		
//		Item actualItem = itemService.removeItem(expectedItem.getItemId());
//		
//		assertEquals(expectedItem.getItemId(), actualItem.getItemId());
	}

	
	@Test
	public void viewItem() {
		List<Item> item = new ArrayList<Item>();
		Category category = new Category(101, "North Indian");
		Address address = new Address(901,"Vaishali Tower","BR Road","Mulund","Mumbai","Maharashtra","India","400080");
		ArrayList<Item> itemList = new ArrayList<Item>();
		ArrayList<Restaurant> resList = new ArrayList<>();
		resList.addAll(resList);
		Restaurant restaurants = new Restaurant(301,"Dwarka",address,itemList,"Raj","8767230066");
		Item add = new Item(1,"Paneer",category,1,200,resList);
		
		item.add(add);
		
		//Mockito.when(itemRepository.findById(101)).thenReturn(Optional.of(add));
		Mockito.when(itemRepository.findById(101)).thenReturn(Optional.of(add));
		assertEquals(1,item.size());
//		assertEquals(101,add.getItemId());
//		assertEquals("Paneer",add.getItemName());
		
	}
	
		@Test
	    public void viewAllItemByCategory() {
		Category category = new Category(1,"South Indian");
		Category category1 = new Category(2,"North Indian");
		Category category2 = new Category(2,"Chinese");
	    List<Item> item_list = new ArrayList<>();
	    
	    List<Restaurant> r1 = new ArrayList<Restaurant>();
	    List<Restaurant> r2 = new ArrayList<Restaurant>();
	    List<Restaurant> r3 = new ArrayList<Restaurant>();
	    Item i1 = new Item(1, "Dosa",category,1,200,r1);
	    Item i2 = new Item(2, "Paneer",category1,1,200,r2);
	    Item i3 = new Item(3, "Noodles",category2,1,200,r3);
	    item_list.add(i1);
	    item_list.add(i2);
	    item_list.add(i3);
	   
	   
	    Mockito.when(itemRepository.findByCategory(category)).thenReturn(item_list);
	    List<Item> list = itemService.viewAllItems(category);
	    assertEquals(item_list.size(),list.size());
	    assertEquals(1,i1.getItemId());
	    assertEquals("Dosa",i1.getItemName());
	    assertEquals(category,i1.getCategory());
	    assertEquals(1,i1.getQuantity());
	    assertEquals(200,i1.getCost());
	   
	   
	}
	
//		@Test
//	    public void findByItemName() {
//	        ArrayList<Item> itemList = new ArrayList<Item>();
//	        ArrayList<Restaurant> resList = new ArrayList<>();
//	        
//	        Restaurant restaurant1 = new Restaurant(301,"Dwarka",null,itemList,"Raj","8767230066");
//	        Restaurant restaurant2 = new Restaurant(302,"China Garden",null,null,"Raj","8767230066");
//	        Restaurant restaurant3 = new Restaurant(303,"Subway",null,null,"Raj","8767230066");
//	        resList.add(restaurant1);
//	        resList.add(restaurant2);
//	        resList.add(restaurant3);
//	        Item i1 = new Item(1, "Dosa",null,1,200,resList);
//	        itemList.add(i1);
//	       
//	        Mockito.when(itemRepository.findByRestaurantsItemName(i1.getItemName())).thenReturn(resList);
//	        List<Restaurant> list = itemService.findRestaurantsByItemName(i1.getItemName());
//	        assertEquals(itemList.size(),list.size());
//	    }
}
