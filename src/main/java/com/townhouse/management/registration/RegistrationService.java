package com.townhouse.management.registration;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.townhouse.management.appuser.AppUser;
import com.townhouse.management.appuser.AppUserRepository;
import com.townhouse.management.appuser.AppUserRole;
import com.townhouse.management.appuser.AppUserService;
import com.townhouse.management.email.Email;
import com.townhouse.management.email.EmailSender;
import com.townhouse.management.house.House;
import com.townhouse.management.house.HouseRepository;
import com.townhouse.management.house.HouseService;
import com.townhouse.management.house.owner.Owner;
import com.townhouse.management.house.owner.OwnerService;
import com.townhouse.management.registration.token.ConfirmationToken;
import com.townhouse.management.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserRepository appUserRepository;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) throws Exception {

        // Validate email and check if already taken
        if(!emailValidator.test(request.getEmail()) || appUserService.findByEmail(request.getEmail())) {
            throw new IllegalStateException("Invalid email.");
        }

        return registerAppUser(request);
    }

    private String registerAppUser(RegistrationRequest request) {
        String pass = bCryptPasswordEncoder.encode(request.getPassword());
        AppUser appUser = new AppUser(request.getEmail(), pass, request.getAppUserRole());
    
        appUser = appUserRepository.save(appUser);

        return createToken(appUser);
    }

    // private String registerAdmin(RegistrationRequest request) {
    //     String pass = bCryptPasswordEncoder.encode(request.getPassword());
    //     AppUser appUser = new AppUser(request.getEmail(), pass, AppUserRole.ADMIN);
    //     appUserRepository.save(appUser);
    //     return createToken(appUser);
    // }

    private String createToken(AppUser appUser) {
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        Email email = new Email(emailSender);
        email.sendEmail(appUser, token);
        return token;
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token).orElseThrow(() -> new IllegalStateException("Token not found."));
        
        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already confirmed.");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token has expired.");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "Your account is confirmed.";
    }
}
