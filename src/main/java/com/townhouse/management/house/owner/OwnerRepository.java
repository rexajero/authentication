package com.townhouse.management.house.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.townhouse.management.appuser.AppUser;
import com.townhouse.management.house.House;
import java.util.List;



@Repository
@Transactional(readOnly = true)
public interface OwnerRepository extends JpaRepository<Owner, Long>{
    Owner findByHouse(House house);
    Owner findByAppUser(AppUser appUser);
}
