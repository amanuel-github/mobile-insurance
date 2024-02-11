package com.medcoanalytics.lemobileyeservice.entity.agents;


import com.medcoanalytics.lemobileyeservice.shared.audit.Audit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agents")
public class Agent extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 36, max = 40)
    private String agentUuid = UUID.randomUUID().toString();

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

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}
