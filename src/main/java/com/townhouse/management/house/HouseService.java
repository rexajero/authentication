package com.townhouse.management.house;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.townhouse.management.appuser.AppUser;
import com.townhouse.management.appuser.AppUserRepository;
import com.townhouse.management.email.Email;
import com.townhouse.management.email.EmailSender;
import com.townhouse.management.house.emergencycontact.EmergencyContact;
import com.townhouse.management.house.emergencycontact.EmergencyContactRepository;
import com.townhouse.management.house.occupant.Occupant;
import com.townhouse.management.house.occupant.OccupantRepository;
import com.townhouse.management.house.owner.Owner;
import com.townhouse.management.house.owner.OwnerRepository;
import com.townhouse.management.house.pet.Pet;
import com.townhouse.management.house.pet.PetRepository;
import com.townhouse.management.house.vehicle.Vehicle;
import com.townhouse.management.house.vehicle.VehicleRepository;
import com.townhouse.management.registration.token.ConfirmationToken;
import com.townhouse.management.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final PetRepository petRepository;
    private final EmergencyContactRepository emergencyContactRepository;
    private final OwnerRepository ownerRepository;
    private final OccupantRepository occupantRepository;
    private final VehicleRepository vehicleRepository;
    private final AppUserRepository appUserRepository;
    private final HouseDTOMapper houseDTOMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public House registerNewHouse(HouseRegistrationRequest request) {

        House house = new House(request.getBlock(), request.getLot());
        house = houseRepository.save(house);

        Owner owner = new Owner(
            house,
            request.getOwner().isOccupant(),
            request.getOwner().getFirstName(),
            request.getOwner().getLastName(),
            request.getOwner().getMiddleName(),
            request.getOwner().getPhoneNumber(),
            request.getOwner().getBirthday(),
            request.getOwner().getNationality(),
            request.getOwner().getEmail(),
            generateInitialPassword(request.getOwner().getBirthday()),
            request.getOwner().getZipCode(),
            request.getOwner().getCountry(),
            request.getOwner().getProvince(),
            request.getOwner().getCity(),
            request.getOwner().getAddrLineOne(),
            request.getOwner().getAddrLineTwo()
        );
        AppUser appUser = registeAppUserFromOwner(owner);
        owner.setAppUser(appUser);
        owner = ownerRepository.save(owner);
        createToken(appUser);

        // Set house occupant
        for(Occupant oc : request.getOccupants()) {
            Occupant occupant = new Occupant(
                house,
                oc.getFirstName(),
                oc.getLastName(),
                oc.getMiddleName(),
                oc.getBirthday(),
                oc.getEmail(),
                oc.getContactNumber()
            );
            occupantRepository.save(occupant);
        }

        for(EmergencyContact ec : request.getEmergencyContacts()) {
            EmergencyContact e_contact = new EmergencyContact(
                ec.getFirstName(),
                ec.getLastName(),
                ec.getPhoneNumber(),
                ec.getEmail(),
                house
            );
            emergencyContactRepository.save(e_contact);
        }

        for(Pet p : request.getPets()) {
            Pet pet = new Pet(
                p.getPetType(),
                p.getQuantity(),
                house
            );
            petRepository.save(pet);
        }

        for(Vehicle v : request.getVehicles()) {
            Vehicle vehicle = new Vehicle(
                        v.getVehicleType(), 
                        v.getModel(), v.getYear(), 
                        v.getLicensePlate(), 
                        house
            );
            vehicleRepository.save(vehicle);
        }

        return house;
    }

    public House registerOnlyHouse(int block, int lot) throws Exception {
        boolean exist = houseRepository.findByBlockAndLot(block, lot).isPresent();

        if(exist) {
            throw new Exception("House already exist.");
        }

        return houseRepository.save(new House(block, lot));
    }

    public HouseDTO getHouseById(Long houseId) throws Exception {
        return houseRepository.findById(houseId)
                .map(houseDTOMapper)
                .orElseThrow(() -> new Exception("House not found."));
    }

    public List<HouseDTO> getAllHouse() {
        return houseRepository.findAll()
                .stream()
                .map(houseDTOMapper)
                .collect(Collectors.toList());
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

    public AppUser registeAppUserFromOwner(Owner owner) {
        return appUserRepository.save(new AppUser(owner.getEmail(), owner.getPassword(), owner.getAppUserRole()));
    }

    private String generateInitialPassword(Date birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String pass = sdf.format(birthday);
        pass = bCryptPasswordEncoder.encode(pass);
        return pass;
    }
}
