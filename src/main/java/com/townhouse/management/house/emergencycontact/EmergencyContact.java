package com.townhouse.management.house.emergencycontact;

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
public class EmergencyContact {
    @SequenceGenerator(
        name = "emergencycontact_sequence",
        sequenceName = "emergencycontact_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "emergencycontact_sequence"
    )
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "house_id", referencedColumnName = "id")
    @ManyToOne
    @JoinColumn(name="house_id", nullable=false)
    House house;

    public EmergencyContact(String firstName, String lastName, String phoneNumber, String email, House house) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.house = house;
    }
}
