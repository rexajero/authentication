package com.townhouse.management.house;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.townhouse.management.house.owner.Owner;

@Service
public class HouseDTOMapper implements Function<House, HouseDTO> {

    @Override
    public HouseDTO apply(House house) {
        return new HouseDTO(
                    house.getId(), 
                    house.getBlock(), 
                    house.getLot()
                );
    }
    
}
