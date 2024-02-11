package com.medcoanalytics.lemobileyeservice.controller.mobiles;

import com.medcoanalytics.lemobileyeservice.payload.request.mobiles.MobileRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.mobiles.MobileResponse;
import com.medcoanalytics.lemobileyeservice.service.mobiles.MobileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/mobile")
@SecurityRequirement(name = "bearerAuth")
public class MobileController {
    @Autowired
    MobileService mobileService;

    @PostMapping("/create-mobile")
    // @PreAuthorize("hasRole('Create-Claim')")
    public  ResponseEntity<MobileResponse> createMobile(@RequestParam(value = "insuredPersonUuid") String insuredPersonUuid,
                                                        @Valid @RequestBody MobileRequest claimRequest, @RequestParam(value = "userUuid") String userUuid) {
        return mobileService.createMobile(claimRequest);

    }

    @PutMapping(path = "/{mobileUuid}")
    @PreAuthorize("hasRole('Update-Mobile')")
    public ResponseEntity<?> updateMobile(@PathVariable String mobileUuid,
                                         @Valid @RequestBody MobileRequest claimRequest) {
        return mobileService.updateMobile(mobileUuid, claimRequest);
    }

    @GetMapping(path = "/{mobileUuid}")
    @PreAuthorize("hasRole('Read-Mobile')")
    public MobileResponse getMobile(@PathVariable String mobileUuid) {

        return mobileService.getMobile(mobileUuid);
    }

    @DeleteMapping(path = "/{mobileUuid}")
    @PreAuthorize("hasRole('Delete-Mobile')")
    public ResponseEntity<?> deleteMobile(@PathVariable String mobileUuid) {

        return mobileService.deleteMobile(mobileUuid);
    }

    @GetMapping()
    @PreAuthorize("hasRole('Read-Mobiles')")
    public List<MobileResponse> getMobiles(@PathVariable String claimUuid , @RequestParam(name = "search", required = false)  String searchKey,
                                                @RequestParam(value="page", defaultValue = "1") int page,
                                                @RequestParam(value="limit", defaultValue = "25") int limit) {
        return mobileService.getMobiles(page, limit);
    }
}