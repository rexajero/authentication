package com.townhouse.management.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.townhouse.management.appuser.AppUser;
import com.townhouse.management.appuser.AppUserRole;
import com.townhouse.management.appuser.AppUserService;
import com.townhouse.management.email.EmailSender;
import com.townhouse.management.house.House;
import com.townhouse.management.house.HouseRepository;
import com.townhouse.management.house.HouseService;
import com.townhouse.management.house.owner.Owner;
import com.townhouse.management.house.owner.OwnerService;
import com.townhouse.management.registration.token.ConfirmationToken;
import com.townhouse.management.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    private final HouseRepository houseRepository;
    private final HouseService houseService;
    private final OwnerService ownerService;

    public String register(RegistrationRequest request) throws Exception {

        // Validate email and check if already taken
        if(!emailValidator.test(request.getEmail()) || appUserService.findByEmail(request.getEmail())) {
            throw new IllegalStateException("Invalid email.");
        }

        AppUser appUser = new AppUser(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getMiddleName(),
                    request.getBirthday(),
                    request.getPhoneNumber(),
                    request.getEmail(),
                    AppUserRole.valueOf(request.getRole())
        );
        
        String token = null;

        switch (appUser.getAppUserRole()) {
            case OWNER:

                //Check if house exist
                // if(houseService.getHouseById(request.getHouseId())) {
                //     House house = houseRepository.findById(request.getHouseId()).orElseThrow(() -> new Exception("House does not exist"));
                    
                //     // To implement: Check if House hasNoOwner with exception
                //     token = appUserService.registeAppUser(appUser);
                //     // ownerService.createNewOwner(appUser, house, false);
                // } else {
                //     System.out.println("House does not exist!");
                //     // To implement: exception
                // }
                break;
            case ACCOUNTING:
                break;
            case ADMIN:
                break;
            case MAINTENANCE:
                break;
            case OCCUPANT:
                // Occupant occupant = occupantService.createNewOccupant(appUser, house, false);
                // houseService.addOccupantToHouse(occupant, house);
                break;
            case SECURITY:
                break;
            default:
                break;
        }
        return token;
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token).orElseThrow(() -> new IllegalStateException("Token not found."));
        
        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already confirmed.");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token has expired.");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "Your account is confirmed.";
    }

    // public Long confirmEmail(String email) {
    //     boolean isValidEmail = emailValidator.test(email);
    //     Long id;

    //     if(!isValidEmail) {
    //         throw new IllegalStateException("Email is not valid.");
    //     } else {
    //         // id  = appUserService.findByEmail(email);
    //         //Send email reset here with id
    //     }
        
    //     return id;
    // }

    

    

    
    
}
