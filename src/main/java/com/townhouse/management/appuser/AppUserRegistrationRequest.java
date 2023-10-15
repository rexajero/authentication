package com.townhouse.management.appuser;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppUserRegistrationRequest {
    String email;
    String password;
    AppUserRole appUserRole;
}
