package com.beben.tomasz.restaurant.commons.mail;

public interface EmailService {

    void send(String to, String subject, String text);
}
