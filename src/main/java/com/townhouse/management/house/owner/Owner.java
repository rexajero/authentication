package com.townhouse.management.house.owner;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.townhouse.management.appuser.AppUser;
import com.townhouse.management.appuser.AppUserRole;
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
public class Owner {
    @SequenceGenerator(
        name = "owner_sequence",
        sequenceName = "owner_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "owner_sequence"
    )
    private Long id;
    private boolean isOccupant;

    //to implement one to many
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    private House house;

    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String password;

    private Date birthday;
    private String nationality;
    private String email;

    private String zipCode;
    private String country;
    private String province;
    private String city;
    private String addrLineOne;
    private String addrLineTwo;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole = AppUserRole.OWNER;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUser appUser;
    
    
    
    //permanent address
    //One to many emergency contact
    //one to many vehicle

    // public Owner(House house, boolean isOccupant, Date birthday, String nationality, String email,
    //         String zipCode, String country, String province, String city, String addrLineOne, String addLineTwo) {
    //     this.house = house;
    //     this.isOccupant = isOccupant;
    //     this.birthday = birthday;
    //     this.nationality = nationality;
    //     this.email = email;
    //     this.zipCode = zipCode;
    //     this.country = country;
    //     this.province = province;
    //     this.city = city;
    //     this.addrLineOne = addrLineOne;
    //     this.addrLineTwo = addLineTwo;
    // }

    public Owner(House house, boolean isOccupant, String firstName, String lastName, String middleName,
            String phoneNumber, Date birthday, String nationality, String email, String zipCode,
            String country, String province, String city, String addrLineOne, String addrLineTwo) {
        this.house = house;
        this.isOccupant = isOccupant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.nationality = nationality;
        this.email = email;
        this.zipCode = zipCode;
        this.country = country;
        this.province = province;
        this.city = city;
        this.addrLineOne = addrLineOne;
        this.addrLineTwo = addrLineTwo;
        this.appUserRole = AppUserRole.OWNER;
    }
}
