package com.app.service;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.app.model.Customer;
import com.app.repository.CustomerRepository;

@Service
public class ClockService {
private final String key="opendoor123";
@Autowired
private RestTemplate restTemplate;

@Autowired
private CustomerRepository cusRe;
private final String esp32Url="http://172.20.10.3/open_door";

public ResponseEntity<?> evaluateAndOpen(String email) {
	Customer cus=this.cusRe.findByEmail(email);
   if(cus==null)return null;
	

		System.out.print("1");
		return openDoor();
	
	
}

public ResponseEntity<?> openDoor() {
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        
        HttpEntity<String> request = new HttpEntity<>("opendoor123", headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(
            esp32Url, 
            request, 
            String.class
        );
        
        return ResponseEntity.ok(response.getBody());
        
    } catch (Exception e) {
        return ResponseEntity.status(500)
            .body("Error: " + e.getMessage());
    }
}
}
