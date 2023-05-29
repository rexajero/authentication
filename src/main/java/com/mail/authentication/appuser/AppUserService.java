package com.mail.authentication.appuser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mail.authentication.registration.token.ConfirmationToken;
import com.mail.authentication.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG = "User with email is not found.";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) 
        throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        String token = UUID.randomUUID().toString();

        if(userExist) {
            AppUser au = appUserRepository.findByEmail(appUser.getEmail())
                            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, appUser.getEmail())));
            if(au.isEnabled()) {
                throw new IllegalStateException("Email is already taken.");
            }

            //Update Token details here
            ConfirmationToken ct = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), au
            );
            confirmationTokenService.saveConfirmationToken(ct);
            
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);

            appUserRepository.save(appUser);

            ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser
            );

            confirmationTokenService.saveConfirmationToken(confirmationToken);
        }
        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public Long findByEmail(String email) {
        AppUser au = appUserRepository.findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        Long id = au.getId();
        return id;
    }

    public void resetPassword(Long id, String password) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        return;
    }

    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }
    
}
