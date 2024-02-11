package com.medcoanalytics.lemobileyeservice.payload.request.claims;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimAttachmentRequest {


    @Size(min = 24, max = 40)
    private String claimAttachmentUuid ;

    @Size(min = 36, max = 40)
    private String claimUuid;


    private String file;

    private String fileName;

    private Long fileSize;

}
