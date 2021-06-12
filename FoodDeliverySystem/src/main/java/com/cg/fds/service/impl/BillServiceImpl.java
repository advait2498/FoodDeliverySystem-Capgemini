package com.capgemini.fds.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.fds.entities.Bill;
import com.capgemini.fds.exception.NoBillFoundException;
import com.capgemini.fds.exception.SameDateFoundException;
import com.capgemini.fds.repository.IBillRepository;
import com.capgemini.fds.service.IBillService;

@Service
@Transactional
public class BillServiceImpl implements IBillService {
	
	@Autowired
	IBillRepository repository;

	@Override
	public Bill addBill(Bill bill) {
		// TODO Auto-generated method stub
		return repository.save(bill);
	}

	@Override
	public Bill removeBill(Bill bill) {
		// TODO Auto-generated method stub
		Optional<Bill> isBillAvailable = repository.findById(bill.getBillId());
		if(isBillAvailable.isPresent()) {
			repository.delete(isBillAvailable.get());
			return bill;
		}
		else
			throw new NoBillFoundException("No bill found");
	}

	@Override
	public Bill viewBill(Bill bill) {
		// TODO Auto-generated method stub
		Optional<Bill> isBillAvailable = repository.findById(bill.getBillId());
		if(isBillAvailable.isPresent())
			return isBillAvailable.get();
		else
			throw new NoBillFoundException("No bill found");
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		if(!startDate.equals(endDate))
			return repository.findByBillDateBetween(startDate, endDate);
		else
			throw new SameDateFoundException("Same date found in filter. Please provide valid interval.");
	}

	@Override
	public List<Bill> viewBills(String custId) {
		// TODO Auto-generated method stub
		return (List<Bill>) repository.findAll();
	}

	@Override
	public Bill updateBillTotalItem(Bill bill) {
		// TODO Auto-generated method stub
		Optional<Bill> isBillAvailable = repository.findById(bill.getBillId());
		if(isBillAvailable.isPresent()) {
			Bill billToUpdate = isBillAvailable.get();
			repository.updateTotalItems(bill.getTotalItem());
			return bill;
		}else {
			throw new NoBillFoundException("No bill found...");
		}
	}

	@Override
	public Bill updateBillTotalCost(Bill bill) {
		// TODO Auto-generated method stub
		Optional<Bill> isBillAvailable = repository.findById(bill.getBillId());
		if(isBillAvailable.isPresent()) {
			Bill billToUpdate = isBillAvailable.get();
			repository.updateTotalCost(bill.getTotalCost());
			return bill;
		}else {
			throw new NoBillFoundException("No bill found...");
		}
	}

}