package com.medcoanalytics.lemobileyeservice.repository.mobiles;


import com.medcoanalytics.lemobileyeservice.entity.mobiles.MobileAttachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobileAttachmentRepository extends JpaRepository<MobileAttachment, Long> {
    Page<MobileAttachment> findAllByAttachmentUuid(String attachmentUuid , Pageable pageRequest);

    Page<MobileAttachment> findALlByMobileUuid(String claimUuid, Pageable pageRequest);

    MobileAttachment findByAttachmentUuid(String attachmentUuid);

    List<MobileAttachment> findAllByMobileUuid(String mobileUuid);
}
