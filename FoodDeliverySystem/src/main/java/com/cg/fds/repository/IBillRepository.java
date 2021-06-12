package com.capgemini.fds.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.fds.entities.Bill;

public interface IBillRepository extends CrudRepository<Bill, Integer> {

	//public Bill addBill(Bill bill);
	@Modifying
	@Query("update Bill bill set bill.totalItem = ?1")
	public Bill updateTotalItems(int items);
	
	@Modifying
	@Query("update Bill bill set bill.totalCost = ?1")
	public Bill updateTotalCost(double cost);
	
	@Modifying
	@Query("delete from Bill bill where bill.billId = ?1")
	public Bill removeBill(int billId);
	
//	public Bill findById(int billId);
	
	public List<Bill> findByBillDateBetween(LocalDate startDate,LocalDate endDate);
//	public List<Bill> findAll(String custId);
	
}