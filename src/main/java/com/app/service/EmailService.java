package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.model.SecurityAlert;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendSecurityAlert(SecurityAlert alert) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("mqnyle@gmail.com"); // Email người dùng
            message.setSubject("🚨 CẢNH BÁO BẢO MẬT - Hệ thống cửa");
            message.setText(
                "CẢNH BÁO: Hệ thống cửa của bạn đã bị khóa!\n\n" +
                "Thời gian: " + alert.getTimestamp() + "\n" +
                "Lý do: " + alert.getMessage() + "\n" +
                "Số lần thử: >3"  + "\n\n" +
                "Vui lòng kiểm tra ngay!"
            );
            
            mailSender.send(message);
            alert.setEmailSent(true);
            
            System.out.println("Security alert email sent successfully");
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
}