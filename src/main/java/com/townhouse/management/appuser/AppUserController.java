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
    public ResponseEntity<List<AppUserDTO>> findAllUsers() {
        List<AppUserDTO> appUsers = appUserService.findAllUsers();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }

    @GetMapping("/find/Email")
    public ResponseEntity<AppUserDTO> findUser(@RequestParam("email") String email) {
        return new ResponseEntity<>(appUserService.findAppUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/find/Id")
    public ResponseEntity<AppUserDTO> findUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(appUserService.findAppUserById(id), HttpStatus.OK);
    }

    @PostMapping(path = "resetpassword")
    public ResponseEntity<String> reset(@RequestParam("id") Long id, @RequestParam("password") String password) {
        return new ResponseEntity<>(appUserService.resetPassword(id, password), HttpStatus.OK);
    }
    
}
