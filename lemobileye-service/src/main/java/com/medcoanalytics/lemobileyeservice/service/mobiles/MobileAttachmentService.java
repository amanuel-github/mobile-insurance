package com.medcoanalytics.lemobileyeservice.service.mobiles;

import com.medcoanalytics.lemobileyeservice.payload.request.mobiles.MobileAttachmentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.mobiles.MobileAttachmentResponse;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.List;

public interface MobileAttachmentService {
    public ResponseEntity<?> createMobileAttachment(@Valid List<MobileAttachmentRequest> mobileAttachmentRequest) throws IOException;
    public ResponseEntity<?> updateMobileAttachment(@Valid List<MobileAttachmentRequest> mobileAttachmentRequest)  throws IOException;
    public ResponseEntity<?> deleteMobileAttachment(String mobileAttachmentUuid);
    public List<MobileAttachmentResponse> getMobileAttachments(String mobileAttachmentUuid);
    ResponseEntity<Resource> openAttachment(String mobileAttachmentUuid) throws IOException;
}
