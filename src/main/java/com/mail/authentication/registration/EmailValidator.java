package com.mail.authentication.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String>{
    @Override
    public boolean test(String s) {
        //TO DO: Reex to validate email
        return true;
    }
}


