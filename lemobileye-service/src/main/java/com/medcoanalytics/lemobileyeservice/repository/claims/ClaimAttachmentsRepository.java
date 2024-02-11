package com.medcoanalytics.lemobileyeservice.repository.claims;



import com.medcoanalytics.lemobileyeservice.entity.claims.ClaimAttachments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClaimAttachmentsRepository extends JpaRepository<ClaimAttachments, Long> {

	ClaimAttachments findByClaimAttachmentUuid(String claimAttachmentUuid);

	Page<ClaimAttachments> findALlByClaimUuid(String claimUuid , Pageable pageRequest);

    List<ClaimAttachments> findAllByClaimUuid(String claimUuid);
}
