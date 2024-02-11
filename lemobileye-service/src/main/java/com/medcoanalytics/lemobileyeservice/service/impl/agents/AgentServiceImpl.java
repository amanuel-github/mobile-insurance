package com.medcoanalytics.lemobileyeservice.service.impl.agents;


import com.medcoanalytics.lemobileyeservice.entity.agents.Agent;
import com.medcoanalytics.lemobileyeservice.exception.ResourceNotFoundException;
import com.medcoanalytics.lemobileyeservice.payload.request.agents.AgentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.MessageResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.agents.AgentResponse;
import com.medcoanalytics.lemobileyeservice.repository.agents.AgentRepository;
import com.medcoanalytics.lemobileyeservice.service.agents.AgentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    AgentRepository agentRepository;

    @Override
    public ResponseEntity<AgentResponse> createAgent(AgentRequest agentRequest) {
        Agent agent = new Agent();
        BeanUtils.copyProperties(agentRequest, agent);
        agentRepository.save(agent);
        AgentResponse agentResponse= new AgentResponse();
        BeanUtils.copyProperties(agent, agentResponse);

        return new ResponseEntity<>(agentResponse, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<AgentResponse> updateAgent(String agentUuid, AgentRequest agentRequest) {
        Agent agent = agentRepository.findByAgentUuid(agentUuid);
        if(agent == null)
            throw  new ResourceNotFoundException("Agent", "agentUuid", agentUuid);
        BeanUtils.copyProperties(agentRequest,agent);
        agentRepository.save(agent);
        AgentResponse agentResponse= new AgentResponse();
        BeanUtils.copyProperties(agent, agentResponse);

        return new ResponseEntity<>(agentResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteAgent(String agentUuid) {
        Agent agent = agentRepository.findByAgentUuid(agentUuid);
        if(agent == null)
            throw  new ResourceNotFoundException("Agent", "agentUuid", agentUuid);
        agent.setDeleted(true);
        agentRepository.save(agent);
        return ResponseEntity.ok(new MessageResponse("Agent soft deleted successfully!"));

    }

    @Override
    public List<AgentResponse> getAgents(int page, int limit) {
        if(page > 0) page = page - 1;
        Pageable pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<Agent> agentPage = agentRepository.findAll(pageRequest);
        long totalPages = agentPage.getTotalPages();
        List<Agent> agentList = agentPage.getContent();
        List<AgentResponse> agentResponse = new ArrayList<>();
        for (Agent a : agentList) {
            AgentResponse ar = new AgentResponse();
            if(agentResponse.size() == 0)
                ar.setTotalPages(totalPages);
            BeanUtils.copyProperties(a, ar);
            agentResponse.add(ar);
        }
        return agentResponse;

    }

    @Override
    public AgentResponse getAgent(String agentUuid) {
        Agent agent = agentRepository.findByAgentUuid(agentUuid);
        if(agent == null)
            throw  new ResourceNotFoundException("Agent", "agentUuid", agentUuid);
        AgentResponse agentResponse = new AgentResponse();
        BeanUtils.copyProperties(agent, agentResponse);
        return agentResponse;

    }
}
