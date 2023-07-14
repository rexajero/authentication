package com.townhouse.management.house;

import java.util.Set;

import com.townhouse.management.house.emergencycontact.EmergencyContact;
import com.townhouse.management.house.occupant.Occupant;
import com.townhouse.management.house.owner.Owner;
import com.townhouse.management.house.pet.Pet;
import com.townhouse.management.house.vehicle.Vehicle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class HouseRegistrationRequest {
    private final int block;
    private final int lot;
    // private final House house;
    private final Owner owner;
    private final Set<Occupant> occupants;
    private final Set<Pet> pets;
    private final Set<EmergencyContact> emergencyContacts;
    private final Set<Vehicle> vehicles;
}
