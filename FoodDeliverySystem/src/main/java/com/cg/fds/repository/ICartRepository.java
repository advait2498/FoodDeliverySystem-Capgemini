package com.cg.fds.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cg.fds.entities.Customer;
import com.cg.fds.entities.FoodCart;

public interface ICartRepository extends CrudRepository<FoodCart, Integer>{

	//public FoodCart addItemToCart(FoodCart cart,Item item);
	//public FoodCart increaseQuantity(FoodCart cart,Item item,int quantity) ;
	//public FoodCart reduceQuantity(FoodCart cart,Item item,int quantity);
	//public FoodCart removeItem(FoodCart cart,Item item);
	//public FoodCart clearCart(FoodCart cart);
	@Query("select c.items from FoodCart c where c = : cart")
	public FoodCart findAllItems(@Param("cart") FoodCart cart);
	
	public FoodCart findByCustomer(Customer customer);
}
