package com.app.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table( name ="access_logs")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AccessLog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long acessLogId;
private String timestamp;
private String event;
private Integer peopleIn;
private Integer peopleOut;

@CreationTimestamp
private Date createdAt;
}
