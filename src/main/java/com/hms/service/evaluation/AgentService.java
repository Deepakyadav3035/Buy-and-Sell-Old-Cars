package com.hms.service.evaluation;

import com.hms.entity.evaluation.Agent;
import com.hms.repository.AgentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

   private AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public ResponseEntity<?> addAgent(Agent agent) {
        Optional<Agent> byName = agentRepository.findByName(agent.getName());
        if(byName.isPresent()){
            return new ResponseEntity<>("agent already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Agent saveAgent = agentRepository.save(agent);
        return new ResponseEntity<>(saveAgent,HttpStatus.OK);
    }

    public Optional<Agent> getAgentById(Long agentId) {
      Optional<Agent> agent = agentRepository.findById(agentId);
      return agent;
    }
}
