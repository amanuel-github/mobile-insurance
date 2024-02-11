package com.medcoanalytics.lemobileyeservice.payload.response.mobiles;


import com.medcoanalytics.lemobileyeservice.shared.audit.Audit;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MobileAttachmentResponse extends Audit {

    private String mobileAttachmentUuid;

    private String fileName;
    private Long fileSize;
    private Instant createdAt;
    private String filePath;

    long totalPages;

}
