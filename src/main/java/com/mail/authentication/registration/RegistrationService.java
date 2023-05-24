package com.mail.authentication.registration;

import org.springframework.stereotype.Service;

import com.mail.authentication.appuser.AppUser;
import com.mail.authentication.appuser.AppUserRole;
import com.mail.authentication.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail) {
            throw new IllegalStateException("Email not valid.");
        }

        return appUserService.signUpUser(
            new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
            )
        );
    }
    
}
