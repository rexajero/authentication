package com.townhouse.management.registration;

import java.util.Date;

import com.townhouse.management.appuser.AppUserRole;
import com.townhouse.management.house.House;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String email;
    private final String password;
    private final AppUserRole appUserRole;
}
