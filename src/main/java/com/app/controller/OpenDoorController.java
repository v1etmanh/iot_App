package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ClockService;

@RestController
public class OpenDoorController {
@Autowired     
private ClockService clockSer;
@GetMapping("/openDoor")
public ResponseEntity<?>open(@AuthenticationPrincipal Jwt jwt ){
	
	String email=jwt.getClaimAsString("email");
	return this.clockSer.evaluateAndOpen(email);
}
}
