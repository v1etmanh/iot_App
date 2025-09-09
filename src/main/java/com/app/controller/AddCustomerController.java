package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AddCusService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AddCustomerController
{@Autowired
	private AddCusService addSer;
	@GetMapping("/addemail")
	public ResponseEntity<?> addEmail(@RequestParam("email")String email) {
		this.addSer.addNewCus(email);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@GetMapping("/getUsers")
	public ResponseEntity<?> retrieveAllEmail() {
		return ResponseEntity.status(HttpStatus.OK).body(this.addSer.retrieveALLCus());
	}
	
@DeleteMapping("/deleteByEmail")
public ResponseEntity<?>deleteByEmail(@RequestParam("email") String email){
	this.addSer.deleteByEmail(email);
	return ResponseEntity.status(HttpStatus.OK).build();
}
}
