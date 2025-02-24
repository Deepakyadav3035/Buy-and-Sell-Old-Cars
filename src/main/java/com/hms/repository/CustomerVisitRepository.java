package com.hms.repository;

import com.hms.entity.evaluation.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Long> {

    Optional<CustomerVisit> findByMobile(String mobile);
    Optional<CustomerVisit>findById(long id);
}