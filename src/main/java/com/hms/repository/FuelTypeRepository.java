package com.hms.repository;

import com.hms.entity.Cars.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> { ;

    Optional<FuelType> findByFueltype(String fueltype);
}