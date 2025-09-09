package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Customer;
import com.app.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class AddCusService {
@Autowired
private CustomerRepository cusRe;

public void addNewCus(String email) {
	Customer cus =new Customer();
	cus.setEmail(email);
	cusRe.save(cus);return;
}
public List<String> retrieveALLCus(){
	return this.cusRe.findAll().stream().map(row->row.getEmail()).collect(Collectors.toList());
}
@Transactional
public void deleteByEmail(String email) {
	this.cusRe.deleteByEmail(email);
	return;
}
}
