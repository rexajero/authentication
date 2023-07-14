package com.townhouse.management.house.vehicle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Vehicle {
    @SequenceGenerator(
        name = "vehicle_sequence",
        sequenceName = "vehicle_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "vehicle_sequence"
    )
    Long id;
    String vehicleType;
    String model;
    int year;
    String licensePlate;
    @ManyToOne
    @JoinColumn(name="house_id", nullable=false)
    House house;

    public Vehicle(String vehicleType, String model, int year, String licensePlate, House house) {
        this.vehicleType = vehicleType;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.house = house;
    }
}
