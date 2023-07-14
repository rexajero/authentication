package com.townhouse.management.house.occupant;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OccupantRegistrationRequest {
    private Long houseId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String email;
    private String contactNumber;
}
