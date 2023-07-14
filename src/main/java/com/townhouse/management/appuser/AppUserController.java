package com.townhouse.management.appuser;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/mytownhouse/user")
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

    @GetMapping("/find")
    public ResponseEntity<AppUser> findUser(@RequestParam("email") String email) {
        AppUser appUser = appUserService.findUser(email);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping(path = "resetpassword")
    public ResponseEntity<String> reset(@RequestParam("id") Long id, @RequestParam("password") String password) {
        return new ResponseEntity<>(appUserService.resetPassword(id, password), HttpStatus.OK);
    }
    
}
