package com.app.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.AccessLog;
import com.app.model.Customer;
import com.app.model.SecurityAlert;
import com.app.repository.AccessLogRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.SecurityAlertRepository;
import com.app.service.EmailService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Cho React
public class DoorSystemController {
    @Autowired
    private  CustomerRepository cusRe;
    @Autowired
    private AccessLogRepository accessLogRepo;
    
    @Autowired
    private SecurityAlertRepository securityAlertRepo;
    
    @Autowired
    private EmailService emailService;
    
    @PostMapping("/access-logs")
    public ResponseEntity<String> receiveAccessLogs(@RequestBody Map<String, Object> request) {
    	System.out.print("helo"+System.currentTimeMillis());
    	
        try {
            List<Map<String, Object>> logs = (List<Map<String, Object>>) request.get("logs");
            
            for (Map<String, Object> logData : logs) {
                AccessLog log = new AccessLog();
                log.setTimestamp((String) LocalDateTime.now().toString());
                log.setEvent((String) logData.get("event"));
                log.setPeopleIn((Integer) logData.get("peopleIn"));
                log.setPeopleOut((Integer) logData.get("peopleOut"));
                
                accessLogRepo.save(log);
            }
            
            return ResponseEntity.ok("Logs saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/security-alert")
    public ResponseEntity<String> receiveSecurityAlert(@RequestBody SecurityAlert alert) {
        try {
            SecurityAlert savedAlert = securityAlertRepo.save(alert);
            
            // Gửi email cảnh báo
            emailService.sendSecurityAlert(savedAlert);
            securityAlertRepo.save(savedAlert); // Cập nhật trạng thái email
            
            return ResponseEntity.ok("Alert processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/access-logs")
    public List<AccessLog> getAccessLogs(@AuthenticationPrincipal Jwt jwt ) {
    	String email=jwt.getClaimAsString("email");
    	Customer cus=this.cusRe.findByEmail(email);
    	if(cus==null)return null;
        return accessLogRepo.findTop100ByOrderByCreatedAtDesc();
    }
    
    @GetMapping("/security-alerts")
    public List<SecurityAlert> getSecurityAlerts(@AuthenticationPrincipal Jwt jwt) {
    	Customer cus=this.cusRe.findByEmail(jwt.getClaimAsString("email"));
    	if(cus==null)return null;
        return securityAlertRepo.findTop50ByOrderByCreatedAtDesc();
    }
}