package com.cg.fds;

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

import com.cg.fds.entities.Address;
import com.cg.fds.entities.Category;
import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;
import com.cg.fds.entities.Item;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.repository.ICartRepository;
import com.cg.fds.service.impl.CustomerCartImpl;


@SpringBootTest
public class FoodCartTest {
	@InjectMocks
	private CustomerCartImpl icartService;
	
	@Mock
	private ICartRepository icartRepository;

	@Test
	public void addItemToCart()
	{
		Address address = new Address(101,"plaza","kphb","1478","hyderabad","telangana","india","500048");
		Category category = new Category(1,"South Indian");
		ArrayList<Item> itemList = new ArrayList<Item>();
		Restaurant restaurant = new Restaurant(12,"Raghavendra Tiffins", address, itemList,"Owla Gaadu","3546816551");
		ArrayList<Restaurant> resList = new ArrayList<>();
		resList.add(restaurant);
		Item items = new Item(1,"indian",category,1, 250, resList);
		itemList.add(items);
		Customer customer = new Customer("Syed","Raheem","male","22","9494920608","raheem345@gmail.com",address);
		FoodCart expCart = new FoodCart(1,customer,itemList);
		
		Mockito.when(icartRepository.save(expCart)).thenReturn(expCart);
		
		FoodCart actCart = icartService.addItemToCart(expCart,items);
		assertEquals(expCart.getCartId(),actCart.getCartId());
		
	   	
	}
	
	@Test
	public void increaseQuantityTest() {
		
		Address address = new Address(101,"plaza","kphb","1478","hyderabad","telangana","india","500048");
		Address address1 =new Address(102,"hima","miyapur","77/89","rangareddy","maharastra","india","500014");
		
		Category category = new Category(1,"South Indian");
		Category category1 = new Category(2,"north Indian");
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		Restaurant restaurant = new Restaurant(12,"Raghavendra Tiffins", address, itemList,"Owla Gaadu","3546816551");
		Restaurant restaurant1 = new Restaurant(156,"Mehfil", address1, itemList,"deliver","3546816551");
		
		ArrayList<Restaurant> resList = new ArrayList<>();
		resList.add(restaurant);
		resList.add(restaurant1);
		
		Item items = new Item(1,"indian",category,1, 250, resList);
		Item items1= new Item(2,"south", category1,2,620,resList);
		
		itemList.add(items);
		itemList.add(items1);
		
		Customer customer = new Customer("Syed","Raheem","male","22","9494920608","raheem345@gmail.com",address);
		Customer customer1 = new Customer("srikanth","avs","male","40","8989875448","AVS@gmail.com",address);
		
		FoodCart expCart = new FoodCart(1,customer,itemList);
		FoodCart expCart1 = new FoodCart(2,customer1,itemList);
		
		List<FoodCart> foodCart = new ArrayList<>();
		foodCart.add(expCart);
		foodCart.add(expCart1);
	
		
        Mockito.when(icartRepository.findAll()).thenReturn(foodCart);
		
		FoodCart actCart = icartService.increaseQuantity(expCart,items,2);
		assertEquals(expCart.getCartId(),actCart.getCartId());
		assertEquals(expCart.getItemList().size(),actCart.getItemList().size());
		
		
		}
	
	@Test
        public void reduceQuantityTest() {
		
		Address address = new Address(101,"plaza","kphb","1478","hyderabad","telangana","india","500048");
		Category category = new Category(1,"South Indian");
		ArrayList<Item> itemList = new ArrayList<Item>();
		Restaurant restaurant = new Restaurant(12,"Raghavendra Tiffins", address, itemList,"Owla Gaadu","3546816551");
		ArrayList<Restaurant> resList = new ArrayList<>();
		resList.add(restaurant);
		Item items = new Item(1,"indian",category,1, 250, resList);
		itemList.add(items);
		Customer customer = new Customer("Syed","Raheem","male","22","9494920608","raheem345@gmail.com",address);
		FoodCart expCart = new FoodCart(1,customer,itemList);
		
        Mockito.when(icartRepository.save(expCart)).thenReturn(expCart);
		
		FoodCart actCart = icartService.reduceQuantity(expCart,items,1);
		assertEquals(expCart.getCartId(),actCart.getCartId());
		
		}
	
	@Test
	
	   public void removeItemTest() {
		Address address = new Address(101,"plaza","kphb","1478","hyderabad","telangana","india","500048");
		Category category = new Category(1,"South Indian");
		ArrayList<Item> itemList = new ArrayList<Item>();
		Restaurant restaurant = new Restaurant(12,"Raghavendra Tiffins", address, itemList,"Owla Gaadu","3546816551");
		ArrayList<Restaurant> resList = new ArrayList<>();
		resList.add(restaurant);
		Item items = new Item(1,"indian",category,1, 250, resList);
		itemList.remove(items);
		Customer customer = new Customer("srikanth","Avs","male","24","9489898755","sri345@gmail.com",address);
		FoodCart expCart = new FoodCart(1002,customer,itemList);
		icartRepository.delete(expCart);
		
		FoodCart actCart = icartService.removeItem(expCart,items);
		assertEquals(expCart.getItemList(),actCart.getCartId());
		
		
	}
//	
//	@Test
//	
//	public void clearCartTest() {
//		
//		
//		Address address = new Address("101","plaza","kphb","1478","hyderabad","telangana","india","500048");
//		Address address1 =new Address("102","hima","miyapur","77/89","rangareddy","maharastra","india","500014");
//		
//		Category category = new Category(1,"South Indian");
//		Category category1 = new Category(2,"north Indian");
//		
//		ArrayList<Item> itemList = new ArrayList<Item>();
//		
//		Restaurant restaurant = new Restaurant(12,"Raghavendra Tiffins", address, itemList,"Owla Gaadu","3546816551");
//		Restaurant restaurant1 = new Restaurant(156,"Mehfil", address1, itemList,"deliver","3546816551");
//		
//		ArrayList<Restaurant> resList = new ArrayList<>();
//		resList.add(restaurant);
//		resList.add(restaurant1);
//		
//		Item items = new Item(1,"indian",category,1, 250, resList);
//		Item items1= new Item(2,"south", category1,2,620,resList);
//		
//		itemList.add(items);
//		itemList.add(items1);
//		
//		Customer customer = new Customer("Syed","Raheem","male","22","9494920608","raheem345@gmail.com",address);
//		Customer customer1 = new Customer("srikanth","avs","male","40","8989875448","AVS@gmail.com",address);
//		
//		FoodCart expCart = new FoodCart(1,customer,itemList);
//		FoodCart expCart1 = new FoodCart(2,customer1,itemList);
//		
////		List<FoodCart> foodCart = new ArrayList<>();
////		foodCart.add(expCart);
////		foodCart.add(expCart1);
//	
//		
//		 Mockito.when(icartRepository.findAll()).thenReturn(foodCart);
//	      
//	        FoodCart cart = icartService.clearCart(expCart);	        
//	        assertEquals(foodCart.size(), cart.getItemList().size());
//	        verify(icartRepository,times(1)).findAll();
//	    }
		
     }


