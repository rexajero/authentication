package com.townhouse.management.house;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.townhouse.management.house.emergencycontact.EmergencyContact;
import com.townhouse.management.house.occupant.Occupant;
import com.townhouse.management.house.owner.Owner;
import com.townhouse.management.house.pet.Pet;
import com.townhouse.management.house.vehicle.Vehicle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class House {
    @SequenceGenerator(
        name = "house_sequence",
        sequenceName = "house_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "house_sequence"
    )
    private Long id;
    private int block;
    private int lot;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @OneToOne(mappedBy = "house")
    private Owner owner;

    @OneToMany(mappedBy="house")
    private Set<Occupant> occupants = new HashSet<Occupant>();
    // private Set<Occupant> occupants;

    @OneToMany(mappedBy="house")
    private Set<EmergencyContact> emergencyContacts = new HashSet<EmergencyContact>();

    @OneToMany(mappedBy="house")
    private Set<Pet> pets = new HashSet<Pet>();

    @OneToMany(mappedBy="house")
    private Set<Vehicle> vehicles = new HashSet<Vehicle>();

    public House(int block, int lot) {
        this.block = block;
        this.lot = lot;
    }

}
