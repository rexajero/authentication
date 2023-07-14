package com.townhouse.management.house.occupant;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class OccupantDTOMapper implements Function<Occupant, OccupantDTO>{

    @Override
    public OccupantDTO apply(Occupant occupant) {
        return new OccupantDTO(
            occupant.getId(), 
            occupant.getFirstName(), 
            occupant.getLastName(), 
            occupant.getMiddleName(), 
            occupant.getBirthday(), 
            occupant.getEmail(), 
            occupant.getContactNumber(), 
            occupant.getHouse().getId()
        );
    }
    
}
