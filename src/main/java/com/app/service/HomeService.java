package com.app.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.app.model.Customer;
import com.app.repository.CustomerRepository;

@Service
public class HomeService {
	@Autowired
	private CustomerRepository cusRe;
	public Customer SavenewCus(@AuthenticationPrincipal Jwt jwt) {
		 String email=jwt.getClaimAsString("email");
	        String name =jwt.getClaimAsString("name");
	        String givenName=jwt.getClaimAsString("given_name");
	        String familyName=jwt.getClaimAsString("family_name");
	        Customer c=cusRe.findByEmail(email);
	        if(c==null) {
	        	return null;
	}
	        return c;
	}
}
