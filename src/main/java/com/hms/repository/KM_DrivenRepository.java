package com.hms.repository;

import com.hms.entity.Cars.KM_Driven;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KM_DrivenRepository extends JpaRepository<KM_Driven, Long> {
    Optional<KM_Driven> findByKms(int kms);
}