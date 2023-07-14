package com.townhouse.management.house.occupant;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.townhouse.management.appuser.AppUser;
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
public class Occupant {
    @SequenceGenerator(
        name = "occupant_sequence",
        sequenceName = "occupant_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "occupant_sequence"
    )
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name="house_id", nullable=false)
    private House house;

    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String email;
    private String contactNumber;

    public Occupant(House house, String firstName, String lastName, String middleName, Date birthday, String email,
            String contactNumber) {
        this.house = house;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.email = email;
        this.contactNumber = contactNumber;
    }
    
}
