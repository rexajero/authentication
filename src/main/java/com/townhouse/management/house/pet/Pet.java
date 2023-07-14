package com.townhouse.management.house.pet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.townhouse.management.house.House;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Pet {
    @SequenceGenerator(
        name = "pet_sequence",
        sequenceName = "pet_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pet_sequence"
    )
    Long id;
    String petType;
    int quantity;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "house_id", referencedColumnName = "id")
    @ManyToOne
    @JoinColumn(name="house_id", nullable=false)
    House house;

    public Pet(String petType, int quantity, House house) {
        this.petType = petType;
        this.quantity = quantity;
        this.house = house;
    }

}
