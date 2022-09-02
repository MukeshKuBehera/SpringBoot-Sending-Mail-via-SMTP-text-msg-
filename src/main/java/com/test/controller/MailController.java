package com.test.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.MailRequest;
import com.test.service.MailService;

@RestController
public class MailController {

	@Autowired
	private MailService mailservice;
	
	@RequestMapping("/run")
	public String run() {
		
		return "Application is running";
	}
	
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	public ResponseEntity<?> sendMail(@RequestBody MailRequest mailrequest) throws IOException{
		System.out.println(mailrequest);
		boolean result=this.mailservice.sendMail(mailrequest.getSubject(),mailrequest.getMessage(),mailrequest.getTo());
		
		if(result) {
		return ResponseEntity.ok("done");
		}else {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not Sent Successfully");
		}
	}
}
