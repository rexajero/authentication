package com.mail.authentication.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final String REGISTER_MESSAGE = "Authentication token sent to your email.\n";

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        return new ResponseEntity<String>(REGISTER_MESSAGE + registrationService.register(request), HttpStatus.OK);
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return new ResponseEntity<>(registrationService.confirmToken(token), HttpStatus.CREATED);
    }

    @PostMapping(path = "reset")
    public ResponseEntity<Long> reset(@RequestParam("email") String email) {
        return new ResponseEntity<Long>(registrationService.confirmEmail(email), HttpStatus.OK);
    }

    @PostMapping(path = "resetpassword")
    public ResponseEntity<String> reset(@RequestParam("id") Long id, @RequestParam("password") String password) {
        return new ResponseEntity<>(registrationService.resetPassword(id, password), HttpStatus.OK);
    }
    
}
