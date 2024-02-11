package com.medcoanalytics.lemobileyeservice.service.impl.mobiles;

import com.medcoanalytics.lemobileyeservice.entity.mobiles.Mobile;
import com.medcoanalytics.lemobileyeservice.exception.ResourceNotFoundException;
import com.medcoanalytics.lemobileyeservice.payload.request.mobiles.MobileRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.MessageResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.mobiles.MobileResponse;
import com.medcoanalytics.lemobileyeservice.repository.mobiles.MobileRepository;
import com.medcoanalytics.lemobileyeservice.service.mobiles.MobileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MobileServiceImpl implements MobileService {
    @Autowired
    MobileRepository mobileRepository;

    @Override
    public ResponseEntity<MobileResponse> createMobile(MobileRequest mobileRequest)
    {
        Mobile mobile = new Mobile();
        BeanUtils.copyProperties(mobileRequest, mobile);
        mobileRepository.save(mobile);
        MobileResponse mobileResponse= new MobileResponse();
        BeanUtils.copyProperties(mobile, mobileResponse);

        return new ResponseEntity<>(mobileResponse, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<MobileResponse> updateMobile(String mobileUuid, MobileRequest mobileRequest) {
        Mobile mobile = mobileRepository.findByMobileUuid(mobileUuid);
        if(mobile == null)
            throw  new ResourceNotFoundException("Mobile", "mobileUuid", mobileUuid);
        BeanUtils.copyProperties(mobileRequest,mobile);
        mobileRepository.save(mobile);
        MobileResponse mobileResponse= new MobileResponse();
        BeanUtils.copyProperties(mobile, mobileResponse);

        return new ResponseEntity<>(mobileResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteMobile(String mobileUuid) {
        Mobile mobile = mobileRepository.findByMobileUuid(mobileUuid);
        if(mobile == null)
            throw  new ResourceNotFoundException("Mobile", "mobileUuid", mobileUuid);
        mobile.setDeleted(true);
        mobileRepository.save(mobile);
        return ResponseEntity.ok(new MessageResponse("Mobile soft deleted successfully!"));

    }

    @Override
    public List<MobileResponse> getMobiles(int page, int limit)
    {
        if(page > 0) page = page - 1;
        Pageable pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<Mobile> mobilePage = mobileRepository.findAll(pageRequest);
        long totalPages = mobilePage.getTotalPages();
        List<Mobile> mobileList = mobilePage.getContent();
        List<MobileResponse> mobileResponse = new ArrayList<>();
        for (Mobile m : mobileList) {
            MobileResponse mr = new MobileResponse();
            if(mobileResponse.size() == 0)
                mr.setTotalPages(totalPages);
            BeanUtils.copyProperties(m, mr);
           mobileResponse.add(mr);
        }
        return mobileResponse;

    }

    @Override
    public MobileResponse getMobile(String mobileUuid) {
        Mobile mobile = mobileRepository.findByMobileUuid(mobileUuid);
        if(mobile == null)
            throw  new ResourceNotFoundException("Mobile", "mobileUuid", mobileUuid);
        MobileResponse mobileResponse = new MobileResponse();
        BeanUtils.copyProperties(mobile, mobileResponse);
        return mobileResponse;

    }
}
