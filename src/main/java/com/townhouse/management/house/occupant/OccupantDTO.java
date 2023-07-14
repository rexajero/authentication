package com.townhouse.management.house.occupant;

import java.util.Date;

public record OccupantDTO (
    Long id,
    String firstName, 
    String lastName, 
    String middleName, 
    Date birthday, 
    String email,
    String contactNumber,
    Long houseId
) {
    
}
