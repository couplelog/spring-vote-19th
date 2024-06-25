package com.ceos_19.vote.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private static final String ePw = createKey();

    private MimeMessage createMessage(String to) throws Exception {
        // Extract email address from JSON string
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = mapper.readTree(to);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to parse JSON: " + e.getMessage(), e);
        }

        if (!jsonNode.has("email")) {
            throw new IllegalArgumentException("The provided JSON does not contain an 'email' field.");
        }

        String emailAddress = jsonNode.get("email").asText();

        System.out.println("Recipient: " + emailAddress);
        System.out.println("Verification Code: " + ePw);

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress)); // Correct way to add recipient
        message.setSubject("Email Verification Test"); // Subject

        String msgg = "<div style='margin:20px;'>" +
                "<h1>CEOS Vote Email Verification Code</h1>" +
                "<p>Please copy and enter the code below:</p>" +
                "<p>Thank you.</p>" +
                "<div align='center' style='border:1px solid black; font-family:verdana';>" +
                "<h3 style='color:blue;'>Registration Verification Code</h3>" +
                "<div style='font-size:130%'>CODE: <strong>" + ePw + "</strong></div></div>";

        message.setText(msgg, "utf-8", "html"); // Content
        message.setFrom(new InternetAddress("rtssogang@google.com", "CEOS")); // Sender

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // Verification code with 8 characters
            int index = rnd.nextInt(3); // 0~2 random

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97)); // a~z
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65)); // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10))); // 0~9
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        // Print input JSON for debugging
        System.out.println("Input JSON: " + to);

        MimeMessage message;
        try {
            message = createMessage(to);
            emailSender.send(message);
        } catch (MailException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to send email: " + e.getMessage(), e);
        }
        return ePw;
    }

    private String mimeMessageToString(MimeMessage message) throws IOException, MessagingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);
        return outputStream.toString("UTF-8");
    }
}
