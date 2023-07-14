package com.townhouse.management.house.owner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.townhouse.management.house.House;
import com.townhouse.management.house.HouseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final HouseRepository houseRepository;
    private final OwnerDTOMapper ownerDTOMapper;

    public Owner addNewOwner(House house, boolean isOccupant, String firstName, String lastName, String middleName,
    String phoneNumber, Date birthday, String nationality, String email, String zipCode,
    String country, String province, String city, String addrLineOne, String addrLineTwo) {

        //check if house exist
        //check if house already have an owner
        Owner owner = new Owner(house, isOccupant, firstName, lastName, middleName, phoneNumber, 
        birthday, nationality, email, zipCode, country, province, city, addrLineOne, addrLineTwo);
        
        return ownerRepository.save(owner);
    }

    public Owner addOwner(House house, boolean isOccupant, String firstName, String lastName, String middleName,
        String phoneNumber, Date birthday, String nationality, String email, String zipCode,
        String country, String province, String city, String addrLineOne, String addrLineTwo) {
        
        House h = houseRepository.getById(house.getId());
        
        Owner owner = new Owner(h, isOccupant, firstName, lastName, middleName, phoneNumber, 
            birthday, nationality, email, zipCode, country, province, city, addrLineOne, addrLineTwo);
        owner = ownerRepository.save(owner);
        return owner;
    }

    //replace owner

    public Owner findOwnerById(Long id) throws Exception {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new Exception("Owner not found"));
        return owner;
    }

    public OwnerDTO add(OwnerRegistrationRequest request) throws Exception {
        House house = houseRepository.getById(request.getHouse().getId());

        Owner owner = new Owner(
            house, 
            request.isOccupant(), 
            request.getFirstName(), 
            request.getLastName(), 
            request.getMiddleName(), 
            request.getPhoneNumber(), 
            request.getBirthday(), 
            request.getNationality(), 
            request.getEmail(), 
            request.getZipCode(), 
            request.getCountry(), 
            request.getProvince(), 
            request.getCity(), 
            request.getAddrLineOne(), 
            request.getAddrLineTwo()
        );

        owner = ownerRepository.save(owner);
        // owner.setHouse(null);
        return ownerRepository.findById(owner.getId())
                .map(ownerDTOMapper)
                .orElseThrow(() -> new Exception("Owner not found."));
    }

    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(ownerDTOMapper)
                .collect(Collectors.toList());
    }

    public OwnerDTO getOwnerByHouseId(Long houseId) {
        House house = houseRepository.getById(houseId);
        Owner owner = ownerRepository.findByHouse(house);
        return ownerDTOMapper.apply(owner);
    }
}
