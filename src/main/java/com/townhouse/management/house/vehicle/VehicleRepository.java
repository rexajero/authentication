package com.townhouse.management.house.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
}
