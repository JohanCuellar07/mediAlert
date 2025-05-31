package com.sena.mediAlert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sena.mediAlert.service.emailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class emailController {
    @Autowired
    private emailService emailService;

    //@GetMapping("/email")
    //public String sendEmail(@PathVariable String email) {
    //    emailService.sendEmail(email);
    //    return "Mail sent successfully";
    //}
}
