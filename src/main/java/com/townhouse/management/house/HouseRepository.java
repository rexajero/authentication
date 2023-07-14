package com.townhouse.management.house;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.townhouse.management.house.occupant.Occupant;
import com.townhouse.management.house.owner.Owner;

@Repository
@Transactional(readOnly = true)
public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findById(Long id);

    House getById(Long id);

    Optional<House> findByBlockAndLot(int block, int lot);

    @Transactional
    @Modifying
    @Query("UPDATE House h " + "SET h.owner = ?2 WHERE h.id = ?1")
    int updateOwner(Long houseId, Owner owner);
}
