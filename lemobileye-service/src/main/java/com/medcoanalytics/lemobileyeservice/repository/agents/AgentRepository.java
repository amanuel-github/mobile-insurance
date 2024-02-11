package com.medcoanalytics.lemobileyeservice.repository.agents;


import com.medcoanalytics.lemobileyeservice.entity.agents.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Page<Agent> findAllByAgentUuid(String agentUuid , Pageable pageRequest);

    Agent findByAgentUuid(String agentUuid);
}
