package com.medcoanalytics.lemobileyeservice.controller.mobiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medcoanalytics.lemobileyeservice.payload.request.mobiles.MobileAttachmentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.mobiles.MobileAttachmentResponse;
import com.medcoanalytics.lemobileyeservice.service.mobiles.MobileAttachmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/mobile-attachment")
@SecurityRequirement(name = "bearerAuth")
public class MobileAttachmentController {
    @Autowired
    MobileAttachmentService mobileAttachmentService;

    @PostMapping()
    @JsonProperty("mobileAttachments")
    public ResponseEntity<?> createMobileAttachment(@Valid List<MobileAttachmentRequest> mobileAttachmentRequest) throws Exception {
        return mobileAttachmentService.createMobileAttachment(mobileAttachmentRequest);
    }

    @GetMapping(path="/{mobileUuid}")
    public List<MobileAttachmentResponse> getMobileAttachments(@PathVariable String mobileUuid) {
        return mobileAttachmentService.getMobileAttachments(mobileUuid);
    }

    @DeleteMapping(path="/{mobileAttachmentUuid}")
    public ResponseEntity<?> deleteMobileAttachment(@PathVariable String mobileAttachmentUuid) {
        return mobileAttachmentService.deleteMobileAttachment(mobileAttachmentUuid);
    }

    @GetMapping("/open")
    public ResponseEntity<Resource> openAttachment(@RequestParam("mobileAttachmentUuid")  String mobileAttachmentUuid) throws IOException {
        return mobileAttachmentService.openAttachment(mobileAttachmentUuid);
    }


    @PutMapping()
    public ResponseEntity<?> updateClaimAttachment(@Valid List<MobileAttachmentRequest> mobileAttachmentRequest) throws IOException {
        return mobileAttachmentService.updateMobileAttachment(mobileAttachmentRequest);
    }
}
