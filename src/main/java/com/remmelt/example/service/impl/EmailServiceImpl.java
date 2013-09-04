package com.remmelt.example.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.remmelt.example.service.EmailService;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
	@Override
	public void sendMail(String emailAddress, String subject, String message) {
		log.info("EmailServiceImpl.sendMail({}, {}, {})", emailAddress, subject, message);
		log.warn("Actually sending email!");
	}
}
