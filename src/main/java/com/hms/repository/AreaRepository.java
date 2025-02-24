package com.hms.repository;

import com.hms.entity.evaluation.Agent;
import com.hms.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findBypincode(String pincode);


}











