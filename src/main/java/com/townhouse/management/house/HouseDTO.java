package com.townhouse.management.house;

import com.townhouse.management.house.owner.Owner;

public record HouseDTO (
    Long id,
    int block,
    int lot
) {
    
}
