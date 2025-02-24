package com.hms.service.evaluation;

import com.hms.entity.evaluation.CustomerVisit;
import com.hms.repository.CustomerVisitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerVisitRepository customerVisitRepository;

    public CustomerService(CustomerVisitRepository customerVisitRepository) {
        this.customerVisitRepository = customerVisitRepository;
    }

    public ResponseEntity<?> addCustomerVisit(CustomerVisit customerVisit) {
        Optional<CustomerVisit> byMobile = customerVisitRepository.findByMobile(customerVisit.getMobile());
        if(byMobile.isPresent()){
           return new ResponseEntity<>("Mobile is exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CustomerVisit save = customerVisitRepository.save(customerVisit);
        return new ResponseEntity<>(save,HttpStatus.OK);
    }
}
