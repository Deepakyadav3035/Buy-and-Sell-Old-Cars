package com.hms.service.evaluation;

import com.hms.entity.evaluation.Area;
import com.hms.repository.AreaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AreaService {

    private AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {

        this.areaRepository = areaRepository;
    }

    public ResponseEntity<?> addArea(Area area) {
        Area save = areaRepository.save(area);
        return new ResponseEntity<>(save,HttpStatus.OK);
    }
}
