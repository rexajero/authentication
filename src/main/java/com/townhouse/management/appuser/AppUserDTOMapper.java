package com.townhouse.management.appuser;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.townhouse.management.house.House;
import com.townhouse.management.house.HouseDTO;

@Service
public class AppUserDTOMapper implements Function<AppUser, AppUserDTO>{

    @Override
    public AppUserDTO apply(AppUser appUser) {
        return new AppUserDTO(
            appUser.getId(), 
            appUser.getEmail(), 
            appUser.getLocked(), 
            appUser.getEnabled(), 
            appUser.getAppUserRole()
        );
    }
    
}
