package com.medcoanalytics.lemobileyeservice.repository.claims;


import com.medcoanalytics.lemobileyeservice.entity.claims.ClaimLogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClaimLogsRepository extends JpaRepository<ClaimLogs, Long> {

	Page<ClaimLogs> findAllByClaimUuid(String claimUuid , Pageable pageRequest);

}
