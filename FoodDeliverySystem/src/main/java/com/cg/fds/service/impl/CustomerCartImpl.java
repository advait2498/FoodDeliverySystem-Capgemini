package com.capgemini.fds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.fds.entities.Customer;
import com.capgemini.fds.entities.FoodCart;
import com.capgemini.fds.entities.Item;
import com.capgemini.fds.exception.EmptyCartException;
import com.capgemini.fds.exception.ItemNotFoundInCart;
import com.capgemini.fds.repository.ICartRepository;
import com.capgemini.fds.service.ICartService;

import java.util.List;

@Service
@Transactional
public class CustomerCartImpl implements ICartService {

	@Autowired
	ICartRepository repository;
	
	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) {
		// TODO Auto-generated method stub
		cart.getItemList().add(item);
		return cart;
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, int quantity) {
		List<Item> itemList = cart.getItemList();
		if(itemList.contains(item)) {
			item.setQuantity(item.getQuantity() + quantity);
			cart.setItemList(itemList);
			return cart;
		}else {
			throw new ItemNotFoundInCart("Item not found in cart. Failed to increase quantity.");
		}
	}

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) {
		// TODO Auto-generated method stub
		List<Item> itemList = cart.getItemList();
		if(itemList.contains(item)) {
			item.setQuantity(item.getQuantity() - quantity);
			cart.setItemList(itemList);
			return cart;
		}else {
			throw new ItemNotFoundInCart("Item not found in cart. Failed to decrease quantity.");
		}
	}

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) {
		// TODO Auto-generated method stub
		if(cart.getItemList().contains(item)) {
			cart.getItemList().remove(item);
			return cart;
		}else {
			throw new ItemNotFoundInCart("Item not found in cart...");
		}
	}

	@Override
	public FoodCart clearCart(FoodCart cart) {
		// TODO Auto-generated method stub
		if(cart.getItemList().size() == 0)
			throw new EmptyCartException("Cart is already empty. Please add some items in cart.");
		else {
			cart.getItemList().removeAll(cart.getItemList());
			return cart;
		}
	}
	@Override
	public FoodCart viewCart(Customer customer) {
		return repository.findByCustomer(customer);
	}
}