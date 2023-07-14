package com.townhouse.management.house.owner;

import java.util.Date;

import com.townhouse.management.appuser.AppUserRole;

public record OwnerDTO (
    Long id,
    String firstName,
    String lastName,
    String middleName,
    String phoneNumber,
    Date birthday,
    String nationality,
    String email,
    String zipCode,
    String country,
    String province,
    String city,
    String addrLineOne,
    String addrLineTwo,
    AppUserRole appUserRole,
    Long houseId
) {
    
}
