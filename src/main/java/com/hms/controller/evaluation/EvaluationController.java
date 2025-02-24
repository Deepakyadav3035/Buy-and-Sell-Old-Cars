package com.hms.controller.evaluation;

import com.hms.entity.evaluation.Agent;
import com.hms.entity.evaluation.Area;
import com.hms.entity.evaluation.CustomerVisit;
import com.hms.service.evaluation.AgentService;
import com.hms.service.evaluation.AreaService;
import com.hms.service.evaluation.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/evaluation")
public class EvaluationController {
    private AgentService agentService;
    private AreaService areaService;
    private CustomerService customerService;

    public EvaluationController(AgentService agentService, AreaService areaService, CustomerService customerService) {
        this.agentService = agentService;
        this.areaService = areaService;
        this.customerService = customerService;
    }

    @PostMapping("/addAgent")
    public ResponseEntity<?> addAgent(
            @RequestBody Agent agent
            ){
        ResponseEntity<?> Agent = agentService.addAgent(agent);
        return Agent;
    }

    @PostMapping("/addArea/{agentId}")
    public Object addArea(
            @RequestBody Area area,
            @PathVariable Long agentId
            ) {
        Optional<Agent> agentOptional = agentService.getAgentById(agentId);

        if (agentOptional.isPresent()) {
            Agent agent = agentOptional.get();
            area.setAgent(agent);
            ResponseEntity<?> createdArea = areaService.addArea(area);
            return createdArea;
        } else {
            return "Agent not found with ID: ";
        }
    }

    @PostMapping("/addCustomer/{agentId}")
    public Object addCustomerVisit(
            @RequestBody CustomerVisit customerVisit,
            @PathVariable long agentId
            ){
        Optional<Agent> agentOptional = agentService.getAgentById(agentId);

        if (agentOptional.isPresent()) {
            Agent agent = agentOptional.get();
            customerVisit.setAgent(agent);
            ResponseEntity<?> customer = customerService.addCustomerVisit(customerVisit);
            return customer;
        } else {
            return "Agent not found with ID: ";
        }
    }
}
