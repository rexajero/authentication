package com.mail.authentication.appuser;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> findAllUsers() {
        List<AppUser> appUsers = appUserService.findAllUsers();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }
    
}
