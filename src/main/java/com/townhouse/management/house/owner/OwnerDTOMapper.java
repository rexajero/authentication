package com.townhouse.management.house.owner;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class OwnerDTOMapper implements Function<Owner, OwnerDTO>{

    @Override
    public OwnerDTO apply(Owner owner) {
        return new OwnerDTO(
            owner.getId(), 
            owner.getFirstName(), 
            owner.getLastName(), 
            owner.getMiddleName(), 
            owner.getPhoneNumber(), 
            owner.getBirthday(), 
            owner.getNationality(), 
            owner.getEmail(), 
            owner.getZipCode(), 
            owner.getCountry(), 
            owner.getProvince(), 
            owner.getCity(), 
            owner.getAddrLineOne(), 
            owner.getAddrLineTwo(), 
            owner.getAppUserRole(), 
            owner.getHouse().getId()
        );
    }
    
}
