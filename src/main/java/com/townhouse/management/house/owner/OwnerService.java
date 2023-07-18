package com.townhouse.management.house.owner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.townhouse.management.appuser.AppUser;
import com.townhouse.management.appuser.AppUserRepository;
import com.townhouse.management.appuser.AppUserRole;
import com.townhouse.management.appuser.AppUserService;
import com.townhouse.management.email.Email;
import com.townhouse.management.email.EmailSender;
import com.townhouse.management.house.House;
import com.townhouse.management.house.HouseRepository;
import com.townhouse.management.registration.token.ConfirmationToken;
import com.townhouse.management.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final HouseRepository houseRepository;
    private final AppUserRepository appUserRepository;
    private final OwnerDTOMapper ownerDTOMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

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
            generateInitialPassword(request.getBirthday()),
            request.getZipCode(), 
            request.getCountry(), 
            request.getProvince(), 
            request.getCity(), 
            request.getAddrLineOne(), 
            request.getAddrLineTwo()
        );

        AppUser appUser = registeAppUserFromOwner(owner);
        owner.setAppUser(appUser);
        owner = ownerRepository.save(owner);
        createToken(appUser);
        
        return ownerRepository.findById(owner.getId())
                .map(ownerDTOMapper)
                .orElseThrow(() -> new Exception("Owner not found."));
    }

    public AppUser registeAppUserFromOwner(Owner owner) {
        return appUserRepository.save(new AppUser(owner.getEmail(), generateInitialPassword(owner.getBirthday()), AppUserRole.OWNER));
    }

    private String generateInitialPassword(Date birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String pass = sdf.format(birthday);
        pass = bCryptPasswordEncoder.encode(pass);
        return pass;
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

    private void createToken(AppUser appUser) {
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        Email email = new Email(emailSender);
        email.sendEmail(appUser, token);
    }
}
