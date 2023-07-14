package com.townhouse.management.registration;

import java.util.Date;

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
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final Date birthday;
    private final String phoneNumber;
    private final String email;
    private final String role;
    private final Long houseId;
}
