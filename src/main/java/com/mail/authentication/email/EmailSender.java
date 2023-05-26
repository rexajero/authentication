package com.mail.authentication.email;

public interface EmailSender {
    void send(String to, String email);
}
