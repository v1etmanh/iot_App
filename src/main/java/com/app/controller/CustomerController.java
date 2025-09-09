package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.service.HomeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
@Autowired
private HomeService homeSer;

@GetMapping("/account")
public ResponseEntity< Customer> getUserDetailsAfterLogin(@AuthenticationPrincipal Jwt jwt)
{Customer cus=this.homeSer.SavenewCus(jwt);
if(cus==null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
return ResponseEntity.status(HttpStatus.OK).body(cus);
	}
}
