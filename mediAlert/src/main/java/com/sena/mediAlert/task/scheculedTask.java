package com.sena.mediAlert.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sena.mediAlert.service.emailService;

@Component
public class scheculedTask {
    @Autowired
    private emailService email;
    // tiempo en milisegundo
    // 1 segundo = 1000 milisegundos

    //@Scheduled(fixedRate = 1000)
    //public void sendEmail() {
    //    email.sendEmail("correo");
    //}

    // cron = "* * * * * *"


    // "Second Minute Hour DayOfMonth Month DayOfWeek"

    //@Scheduled(cron = "0 0 0 * * *")
    //public void taskEmail() {
    //    email.sendEmail("correo");
    //    System.out.println("email enviado");
    //y}
}
