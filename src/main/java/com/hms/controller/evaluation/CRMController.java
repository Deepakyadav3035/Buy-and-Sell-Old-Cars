package com.hms.controller.evaluation;

import com.hms.entity.evaluation.Agent;
import com.hms.entity.evaluation.Area;
import com.hms.entity.evaluation.CustomerVisit;
import com.hms.repository.AgentRepository;
import com.hms.repository.AreaRepository;
import com.hms.repository.CustomerVisitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/CRM")
public class CRMController {
    private AreaRepository areaRepository;
    private AgentRepository agentRepository;
    private CustomerVisitRepository customerVisitRepository;

    public CRMController(AreaRepository areaRepository, AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
        this.customerVisitRepository = customerVisitRepository;
    }

    @GetMapping
    public ResponseEntity<List<Area>>searchAgent(
            @RequestParam String pincode
    ){
        List<Area> areas = areaRepository.findBypincode(pincode);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PutMapping
    public String alocateAgent(
            @RequestParam long customerId,
            @RequestParam long agentId
    ){

        Optional<Agent> byId = agentRepository.findById(agentId);
        if (!byId.isPresent()){
            return "Agent not present";
        }
       Agent agent = byId.get();
        CustomerVisit customerVisit = customerVisitRepository.findById(customerId).get();
        customerVisit.setAgent(agent);
        customerVisitRepository.save(customerVisit);
        return "Agent is Allocated";

    }
}
