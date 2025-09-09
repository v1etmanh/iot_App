package com.app.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "security_alerts")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SecurityAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String timestamp;
    private String alertType;
    private Integer attempts;
    private String message;
    private Boolean emailSent = false;
    
    @CreationTimestamp
    private Date createdAt;
    
    // getters và setters
}