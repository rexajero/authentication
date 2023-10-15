package com.townhouse.management.house.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.townhouse.management.appuser.AppUser;
import com.townhouse.management.house.House;
import java.util.List;
import java.util.Optional;



@Repository
@Transactional(readOnly = true)
public interface OwnerRepository extends JpaRepository<Owner, Long>{
    Optional<Owner> findById(Long id);
    Owner findByHouse(House house);
    Owner findByAppUser(AppUser appUser);
}
