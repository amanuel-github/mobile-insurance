package com.medcoanalytics.lemobileyeservice.payload.request.mobiles;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileAttachmentRequest {
    @Size(min = 36, max = 40)
    private String mobileUuid ;

    @Size(min = 36, max = 40)
    private String attachmentUuid;

    @Size(max = 500)
    private String file;
}
