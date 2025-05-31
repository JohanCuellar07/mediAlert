package com.sena.mediAlert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {
    @Autowired
    private JavaMailSender JavaMailSender;

    public void sendEmail(String addressMail){
        try {
            String subject = "Asunto del correo";
            String bodyMail = ""
            + "<!DOCTYPE html>\n"
            + "<html lang=\"es\">\n"
            + "<head>\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "    <title>T´tulo del correo</title>\n"
            + estiloBase
            + "</head>\n"
            + "<body>\n"
            + "</body>\n"
            + "</html>";
            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {

        }
    }

    String estiloBase = ""
    + "<style>\n"
    + "    body {\n"
    + "        font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;\n"
    + "        background-color: #f2f4f8;\n"
    + "        margin: 0;\n"
    + "        padding: 0;\n"
    + "    }\n"
    + "    .correo-container {\n"
    + "        background-color: #ffffff;\n"
    + "        max-width: 600px;\n"
    + "        margin: 50px auto;\n"
    + "        padding: 40px;\n"
    + "        border-radius: 10px;\n"
    + "        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);\n"
    + "    }\n"
    + "    h2 {\n"
    + "        color: #2c3e50;\n"
    + "        font-size: 24px;\n"
    + "        margin-bottom: 20px;\n"
    + "    }\n"
    + "    p {\n"
    + "        font-size: 16px;\n"
    + "        color: #555555;\n"
    + "        line-height: 1.6;\n"
    + "    }\n"
    + "    .btn {\n"
    + "        display: inline-block;\n"
    + "        margin-top: 25px;\n"
    + "        padding: 14px 24px;\n"
    + "        background-color: #3498db;\n"
    + "        color: #ffffff;\n"
    + "        text-decoration: none;\n"
    + "        border-radius: 6px;\n"
    + "        font-size: 16px;\n"
    + "        transition: background-color 0.3s ease;\n"
    + "    }\n"
    + "    .btn:hover {\n"
    + "        background-color: #2980b9;\n"
    + "    }\n"
    + "    .footer {\n"
    + "        margin-top: 40px;\n"
    + "        font-size: 13px;\n"
    + "        color: #999999;\n"
    + "        text-align: center;\n"
    + "    }\n"
    + "</style>\n";

    public boolean emailSender(String addressMail, String subject, String bodyMail) throws MessagingException {
        try {
            MimeMessage message = JavaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(addressMail);
            helper.setSubject(subject);
            helper.setText(bodyMail, true);
            JavaMailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
