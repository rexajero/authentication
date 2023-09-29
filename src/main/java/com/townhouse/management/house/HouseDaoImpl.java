package com.townhouse.management.house;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HouseDaoImpl implements HouseDao<House>{

    private final HouseRepository houseRepository;

    @Override
    public Optional<House> get(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public Collection<House> getAll() {
        return houseRepository.findAll();
    }

    @Override
    public House saveHouse(House house) {
        return houseRepository.save(house);
    }
    
}
