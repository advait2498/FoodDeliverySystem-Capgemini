package com.cg.fds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.fds.entities.Item;
//import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.repository.IRestaurantRepository;
import com.cg.fds.service.impl.RestaurantServiceImpl;



@SpringBootTest
public class RestaurantServiceTest {
	
	
@InjectMocks
RestaurantServiceImpl restaurantServiceImpl;
//IRestaurantService restaurantService;
@Mock
IRestaurantRepository restaurantRepository;

	@Test
	public void addRestaurant() {
		List<Item> items = new ArrayList<Item>();
		Restaurant expected=new Restaurant(201,"Pariwaar", null, items,"Shankar","34324299");
		Mockito.when(restaurantRepository.save(expected)).thenReturn(expected);
		
		Restaurant actual = restaurantServiceImpl.addRestaurant(expected);
		assertEquals(expected.getRestaurantId(), actual.getRestaurantId());
	}
	
	@Test
	public void updateRestaurant() {
		 List<Item> items = new ArrayList<Item>();
		 Restaurant expected=new Restaurant(101,"Pariwaar", null, items,"Shankar","34324299");
		 Mockito.when(restaurantRepository.findById(101)).thenReturn(Optional.of(expected));
		 
		 Restaurant actual=restaurantServiceImpl.updateRestaurant(101,expected);
		 assertEquals(expected.getRestaurantId(),actual.getRestaurantId());
	 }
 
	@Test
	 public void removeRestaurant() {
		 List<Item> items = new ArrayList<Item>();
		 Restaurant expected=new Restaurant(101,"Pariwaar", null, items,"Shankar","34324299");
		 Mockito.when(restaurantRepository.save(expected)).thenReturn(expected);
	
		 Restaurant actual = restaurantServiceImpl.removeRestaurant(101);
		 assertEquals(expected.getRestaurantId(),actual.getRestaurantId());
	 }
		
	@Test
	public void viewRestaurant() {
		List<Item> items = new ArrayList<Item>();
		Restaurant expected=new Restaurant(201,"Pariwaar", null, items,"Shankar","34324299");
		Mockito.when(restaurantRepository.findById(201)).thenReturn(Optional.of(expected));
		
		Restaurant actual = restaurantServiceImpl.viewRestaurant(201);
		assertEquals(expected.getRestaurantName(),actual.getRestaurantName()); 
	}
	
	@Test
    public void findByRestaurantName() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ArrayList<Restaurant> resList = new ArrayList<>();
        resList.addAll(resList);
        Restaurant restaurant1 = new Restaurant(301,"Dwarka",null,itemList,"Raj","8767230066");
        Restaurant restaurant2 = new Restaurant(302,"China Garden",null,itemList,"Raj","8767230066");
        Restaurant restaurant3 = new Restaurant(303,"Subway",null,itemList,"Raj","8767230066");
       
   
        Item i1 = new Item(1, "Dosa",null,1,200,resList);
        itemList.add(i1);
       
        Mockito.when(restaurantRepository.findItemsByRestaurantName(null)).thenReturn(itemList);
        List<Item> list = restaurantServiceImpl.findByRestaurantName(restaurant3.getRestaurantName());
        assertEquals(itemList.size(),list.size());
    }
}
