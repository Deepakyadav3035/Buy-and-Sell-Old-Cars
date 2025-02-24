package com.hms.repository;

import com.hms.entity.Cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            "SELECT c FROM Car c " +
                    "JOIN c.brand b " +
                    "JOIN c.model m "+
                    "JOIN c.transmission t "+
                    "JOIN c.year y "+
                   "JOIN c.kmDriven k "+
                   "JOIN c.fuelType f "+
                    "WHERE b.name = :details or m.name = :details or y.year = :details or t.type = :details or CAST(k.kms AS string) =:details or f.fueltype = :details "
    )
    List<Car> searchCar(
            @Param("details") String details
    );
}
