package com.medcoanalytics.lemobileyeservice.controller.agents;


import com.medcoanalytics.lemobileyeservice.payload.request.agents.AgentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.agents.AgentResponse;
import com.medcoanalytics.lemobileyeservice.service.agents.AgentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/agent")
@SecurityRequirement(name = "bearerAuth")
public class AgentController {
    @Autowired
    AgentService agentService;

    @PostMapping("/create-agent")
    // @PreAuthorize("hasRole('Create-Claim')")
    public ResponseEntity<AgentResponse> createAgent(@RequestParam(value = "insuredPersonUuid") String insuredPersonUuid,
                                                     @Valid @RequestBody AgentRequest claimRequest, @RequestParam(value = "userUuid") String userUuid) {
        return agentService.createAgent(claimRequest);

    }

    @PutMapping(path = "/{agentUuid}")
    @PreAuthorize("hasRole('Update-Claim')")
    public ResponseEntity<?> updateAgent(@PathVariable String agentUuid,
                                         @Valid @RequestBody AgentRequest claimRequest) {
        return agentService.updateAgent(agentUuid, claimRequest);
    }

    @GetMapping(path = "/{agentUuid}")
    @PreAuthorize("hasRole('Read-Agent')")
    public AgentResponse getAgent(@PathVariable String agentUuid) {

        return agentService.getAgent(agentUuid);
    }

    @DeleteMapping(path = "/{agentUuid}")
    @PreAuthorize("hasRole('Delete-Claim')")
    public ResponseEntity<?> deleteAgent(@PathVariable String agentUuid) {

        return agentService.deleteAgent(agentUuid);
    }

    @GetMapping()
    @PreAuthorize("hasRole('Read-Agents')")
    public List<AgentResponse> getAgents(@PathVariable String agentUuid , @RequestParam(name = "search", required = false)  String searchKey,
                                                @RequestParam(value="page", defaultValue = "1") int page,
                                                @RequestParam(value="limit", defaultValue = "25") int limit) {
        return agentService.getAgents( page, limit);
    }
}
