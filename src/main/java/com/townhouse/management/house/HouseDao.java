package com.townhouse.management.house;

import java.util.Collection;
import java.util.Optional;

public interface HouseDao<House> {
    Optional<House> get(Long id);
    Collection<House> getAll();
    House saveHouse(House house);
}
