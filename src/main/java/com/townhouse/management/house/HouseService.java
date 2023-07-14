package com.townhouse.management.house;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
    private final HouseDTOMapper houseDTOMapper;

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
            request.getOwner().getZipCode(),
            request.getOwner().getCountry(),
            request.getOwner().getProvince(),
            request.getOwner().getCity(),
            request.getOwner().getAddrLineOne(),
            request.getOwner().getAddrLineTwo()
        );
        owner = ownerRepository.save(owner);

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

    public String registerHouse(HouseRegistrationRequest request) {

        // House house = new House(
        //     request.getBlock(),
        //     request.getLot()
        // );

        // houseRepository.save(house);
        // return "House Block-" + house.getBlock() + " Lot-" + house.getLot() + " created."; 
        return "deprecated";
    }

    public void addOccupantToHouse(Occupant occupant, House house) {
        Set<Occupant> occupants = house.getOccupants();
        occupants.add(occupant);
        houseRepository.save(house);
        return;
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

    //update house owner
    //add occupant
    //remove occupant
    //add pet
    //remove pet
}
