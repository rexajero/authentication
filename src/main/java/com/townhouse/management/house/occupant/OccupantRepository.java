package com.townhouse.management.house.occupant;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.townhouse.management.house.House;

@Repository
@Transactional(readOnly = true)
public interface OccupantRepository extends JpaRepository<Occupant, Long>{
    Optional<Occupant> findByEmail(String email);
    List<Occupant> findByHouse(House house);
}
