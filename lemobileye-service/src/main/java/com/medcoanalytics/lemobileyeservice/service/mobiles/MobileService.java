package com.medcoanalytics.lemobileyeservice.service.mobiles;

import com.medcoanalytics.lemobileyeservice.payload.request.mobiles.MobileRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.mobiles.MobileResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MobileService {
    public ResponseEntity<MobileResponse> createMobile(MobileRequest mobileRequest);
    public ResponseEntity<MobileResponse> updateMobile(String mobileUuid, MobileRequest mobileRequest);
    public ResponseEntity<?> deleteMobile(String mobileUuid);
    public List<MobileResponse> getMobiles(int page, int limit);
    public MobileResponse getMobile(String mobileUuid);
}
