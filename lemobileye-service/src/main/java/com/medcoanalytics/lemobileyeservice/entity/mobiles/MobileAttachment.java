package com.medcoanalytics.lemobileyeservice.entity.mobiles;

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
@Table(name = "mobile-attachments")
public class MobileAttachment extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 36, max = 40)
    private String attachmentUuid = UUID.randomUUID().toString();

    @Size(min = 36, max = 40)
    private String mobileUuid ;

    @Size(max = 500)
    private String file;

    private String fileName;
    private Long fileSize;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}
