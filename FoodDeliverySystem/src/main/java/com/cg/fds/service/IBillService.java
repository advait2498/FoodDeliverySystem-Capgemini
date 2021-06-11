package com.cg.fds.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fds.entities.Bill;

@Service
@Transactional
public interface IBillService {

	public Bill addBill(Bill bill);
	public Bill updateBillTotalItem(Bill bill);
	public Bill updateBillTotalCost(Bill bill);
	public Bill removeBill(Bill bill);
	public Bill viewBill(Bill bill);
	public List<Bill> viewBills(LocalDate startDate,LocalDate endDate);
	public List<Bill> viewBills(String custId);
	
}
