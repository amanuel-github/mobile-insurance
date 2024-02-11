package com.medcoanalytics.lemobileyeservice.repository.mobiles;


import com.medcoanalytics.lemobileyeservice.entity.mobiles.Mobile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {
    Page<Mobile> findAllByMobileUuid(String mobileUuid , Pageable pageRequest);

    Mobile findByMobileUuid(String mobileUuid);
}