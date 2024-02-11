package com.medcoanalytics.lemobileyeservice.payload.response.agents;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentResponse {
    @Size(min = 36, max = 40)
    private String agentUuid;

    @Size(max = 50)
    private String agentName;

    @Size(max = 40)
    private String address1;

    @Size(max = 40)
    private String address2;

    @Size(max = 40)
    private String address3;

    @Size(max = 40)
    private String address4;

    @Size(max = 100)
    private String phone;

    @Size(max = 100)
    private String email;

    long totalPages;

}
