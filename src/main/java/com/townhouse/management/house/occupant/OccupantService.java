package com.townhouse.management.house.occupant;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.townhouse.management.house.House;
import com.townhouse.management.house.HouseRepository;
import com.townhouse.management.house.owner.OwnerDTOMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OccupantService {
    private final OccupantRepository occupantRepository;
    private final HouseRepository houseRepository;
    private final OccupantDTOMapper occupantDTOMapper;

    public Occupant addNewOccupant(House house, String firstName, String lastName, String middleName, 
                                    Date birthday, String email, String contactNumber) {
        //check if house exist

        Occupant occupant = new Occupant(house, firstName, lastName, middleName, birthday, email, contactNumber);
        return occupantRepository.save(occupant);
    }

    public Occupant findOccupantById(Long id) throws Exception {
        Occupant occupant = occupantRepository.findById(id).orElseThrow(() -> new Exception("Occupant not found"));
        return occupant;
    }

    public Optional<Occupant> findOccupantByEmail(String email) throws Exception {
        return occupantRepository.findByEmail(email);
    }

    public List<OccupantDTO> getOccupantsByHouseId(Long houseId) {
        House house = houseRepository.getById(houseId);
        return occupantRepository.findByHouse(house).stream().map(occupantDTOMapper).collect(Collectors.toList());
    }

    public List<OccupantDTO> getAllOccupants() {
        return occupantRepository.findAll().stream().map(occupantDTOMapper).collect(Collectors.toList());
    }

    public OccupantDTO getOccupantsById(Long id) throws Exception {
        return occupantRepository.findById(id)
                .map(occupantDTOMapper)
                .orElseThrow(() -> new Exception("Occupant does not exist."));
    }

    public String add(List<OccupantRegistrationRequest> request) {
        for(OccupantRegistrationRequest reg_req : request) {
            House house = houseRepository.getById(reg_req.getHouseId());

            Occupant occupant = new Occupant(
                        house, 
                        reg_req.getFirstName(), 
                        reg_req.getLastName(), 
                        reg_req.getMiddleName(), 
                        reg_req.getBirthday(), 
                        reg_req.getEmail(), 
                        reg_req.getContactNumber());
            occupant = occupantRepository.save(occupant);
        }
        
        return "Occupants added.";
    }
}
