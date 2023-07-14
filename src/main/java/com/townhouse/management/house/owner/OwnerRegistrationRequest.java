package com.townhouse.management.house.owner;

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
public class OwnerRegistrationRequest {
    House house;
    boolean isOccupant;
    String firstName;
    String lastName;
    String middleName;
    String phoneNumber;
    Date birthday;
    String nationality;
    String email;
    String zipCode;
    String country;
    String province;
    String city;
    String addrLineOne;
    String addrLineTwo;
}
