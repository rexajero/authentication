package com.townhouse.management.house.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.townhouse.management.house.House;


@Repository
@Transactional(readOnly = true)
public interface OwnerRepository extends JpaRepository<Owner, Long>{
    Owner findByHouse(House house);
}
