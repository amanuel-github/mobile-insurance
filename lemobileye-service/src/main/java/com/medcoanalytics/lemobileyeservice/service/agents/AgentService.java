package com.medcoanalytics.lemobileyeservice.service.agents;

import com.medcoanalytics.lemobileyeservice.payload.request.agents.AgentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.agents.AgentResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AgentService {
    public ResponseEntity<AgentResponse> createAgent(AgentRequest agentRequest);
    public ResponseEntity<AgentResponse> updateAgent(String agentUuid, AgentRequest agentRequest);
    public ResponseEntity<?> deleteAgent(String agentUuid);
    public List<AgentResponse> getAgents(int page, int limit);
    public AgentResponse getAgent(String agentUuid);

}
