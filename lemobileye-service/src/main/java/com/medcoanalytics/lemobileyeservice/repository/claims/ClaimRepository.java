package com.medcoanalytics.lemobileyeservice.repository.claims;


import com.medcoanalytics.lemobileyeservice.entity.claims.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	
/*
	@Query(value = "SELECT services.item, services.description, services.category, service_price.value as amount FROM services INNER JOIN  eligible_services ON eligible_services.service_list_uuid= services.service_uuid INNER JOIN  services_provided ON eligible_services.eligible_service_uuid=services_provided.eligible_service_uuid INNER JOIN service_price ON eligible_services.service_list_uuid=service_price.service_uuid INNER JOIN claims ON claims.contract_uuid = service_price.service_contract_uuid WHERE claims.claim_uuid=:claimUuid and services_provided.is_deleted=0 ORDER BY  services.item ASC ", nativeQuery = true)
	List<Object[]> findServicesProvidedPerClaim(@Param("claimUuid") String claimUuid);

	@Query(value = "SELECT  insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone, claims.*, payers.payer_name, payers.telephone FROM claims INNER JOIN payers ON payers.payer_uuid=claims.payer_uuid INNER JOIN insured_persons ON claims.insured_person_uuid=insured_persons.insured_uuid WHERE claims.prepared_by_provider_status='Pending'  and claims.is_deleted=0 and claims.provider_uuid=:providerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllSavedClaims(@Param("providerUuid") String providerUuid);

	@Query(value = "SELECT insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone, claims.*, payers.payer_name, payers.telephone FROM claims INNER JOIN payers ON payers.payer_uuid=claims.payer_uuid  INNER JOIN insured_persons ON claims.insured_person_uuid=insured_persons.insured_uuid WHERE claims.prepared_by_provider_status='Prepared' and claims.checked_by_provider_status='Pending' and claims.is_deleted=0 and claims.provider_uuid=:providerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllPreparedClaims(@Param("providerUuid") String providerUuid);

	@Query(value = "SELECT insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone, claims.*, payers.payer_name, payers.telephone FROM claims INNER JOIN payers ON payers.payer_uuid=claims.payer_uuid INNER JOIN insured_persons ON claims.insured_person_uuid=insured_persons.insured_uuid WHERE claims.prepared_by_provider_status='Prepared' AND claims.checked_by_provider_status='Reviewed' AND claims.audited_by_provider_status='Pending'  and claims.is_deleted=0 and claims.provider_uuid=:providerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllReviewedClaims(@Param("providerUuid") String providerUuid);

	@Query(value = "SELECT insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone, claims.*, payers.payer_name, payers.telephone FROM claims INNER JOIN payers ON payers.payer_uuid=claims.payer_uuid INNER JOIN insured_persons ON claims.insured_person_uuid=insured_persons.insured_uuid  WHERE claims.prepared_by_provider_status='Prepared' AND claims.checked_by_provider_status='Reviewed' AND claims.audited_by_provider_status='Audited' AND claims.approved_by_provider_status='Pending'  and claims.is_deleted=0 and claims.provider_uuid=:providerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllAuditedClaims(@Param("providerUuid") String providerUuid);

	@Query(value = "SELECT  insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone, claims.*, payers.payer_name, payers.telephone FROM claims INNER JOIN payers ON payers.payer_uuid=claims.payer_uuid INNER JOIN insured_persons ON claims.insured_person_uuid=insured_persons.insured_uuid WHERE claims.approved_by_provider_status='Approved'  and claims.is_deleted=0 and claims.provider_uuid=:providerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllSentClaims(@Param("providerUuid") String providerUuid);

	// for payers belows

	@Query(value = "SELECT insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone,  claims.*, providers.provider_name, providers.telephone FROM claims INNER JOIN providers ON providers.provider_uuid=claims.provider_uuid INNER JOIN insured_persons ON claims.insured_person_uuid=insured_persons.insured_uuid  WHERE claims.approved_by_provider_status='Approved' And claims.checked_by_payer_status='Pending'  and claims.is_deleted=0 and claims.payer_uuid=:payerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllRequestedClaims(@Param("payerUuid") String payerUuid);

	@Query(value = "SELECT insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone,  claims.*, providers.provider_name, providers.telephone FROM claims INNER JOIN providers ON providers.provider_uuid=claims.provider_uuid INNER JOIN insured_persons on claims.insured_person_uuid=insured_persons.insured_uuid  WHERE claims.approved_by_provider_status='Approved' And claims.checked_by_payer_status='Reviewed' AND claims.audited_by_payer_status='Pending' and  claims.is_deleted=0 and claims.payer_uuid=:payerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllReviewedClaimsByPayer(@Param("payerUuid") String payerUuid);

	@Query(value = "SELECT insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone,  claims.*, providers.provider_name, providers.telephone FROM claims INNER JOIN providers ON providers.provider_uuid=claims.provider_uuid INNER JOIN insured_persons on claims.insured_person_uuid=insured_persons.insured_uuid  WHERE claims.approved_by_provider_status='Approved' And claims.checked_by_payer_status='Reviewed' AND claims.audited_by_payer_status='Audited' and claims.approved_by_payer_status='Pending' and  claims.is_deleted=0 and claims.payer_uuid=:payerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllAuditedClaimsByPayer(@Param("payerUuid") String payerUuid);

	@Query(value = "SELECT insured_persons.title, insured_persons.first_name, insured_persons.father_name, insured_persons.grand_father_name, insured_persons.id_number, insured_persons.insurance_id, insured_persons.phone,  claims.*, providers.provider_name, providers.telephone FROM claims INNER JOIN providers ON providers.provider_uuid=claims.provider_uuid INNER JOIN insured_persons on claims.insured_person_uuid=insured_persons.insured_uuid  WHERE claims.approved_by_provider_status='Approved' And claims.checked_by_payer_status='Reviewed' AND claims.audited_by_payer_status='Audited' and claims.approved_by_payer_status='Approved' and  claims.is_deleted=0 and claims.payer_uuid=:payerUuid ORDER BY  claims.created_at ASC ", nativeQuery = true)
	List<Object[]> findAllApprovedClaimsByPayer(@Param("payerUuid") String payerUuid);

	 */
	@Query(value = "SELECT contract_uuid From payer_provider_contracts WHERE payer_provider_contracts.payer_uuid=:payerUuid AND payer_provider_contracts.provider_uuid=:providerUuid AND payer_provider_contracts.status='Active' ", nativeQuery = true)
	String findActiveContractUuidForClaim(@Param("providerUuid") String providerUuid, @Param("payerUuid") String payerUuid);
  /*
	@Query(value ="SELECT MAX(c.payment_number) FROM claims c WHERE c.payer_uuid = :payerUuid", nativeQuery = true)
    Long findMaxPaymentNumberByPayerUuid(@Param("payerUuid") String payerUuid);
	*/
	@Query(value ="SELECT c.* FROM claims c WHERE c.payer_uuid = :payerUuid AND c.claim_number = (SELECT MAX(c2.claim_number) FROM claims c2 WHERE c2.payer_uuid = :payerUuid) group by c.claim_number", nativeQuery = true)
	
	Claim findClaimByPayerUuidAndMaxClaimNumber(@Param("payerUuid") String payerUuid);
	
	@Query(value ="SELECT c.* FROM claims c WHERE c.payer_uuid = :payerUuid AND c.payment_number = (SELECT MAX(c2.payment_number) FROM claims c2 WHERE c2.payer_uuid = :payerUuid) group by c.payment_number", nativeQuery = true)
	Claim findClaimByPayerUuidAndMaxPaymentNumber(@Param("payerUuid") String payerUuid);
	
	Claim findByClaimUuid(String claimUuid);
	
	/*@Query(value = "<<<EOF SELECT JSON_OBJECT(
	           'claimNumber', claims.claim_number,
	           'claimDate', claims.approved_by_provider_date,
	           'checkNumber', claims.check_number,
	           'paymentNumber', claims.payment_number,
	           'paidDate', claims.paid_date,
	           'totalAmount', claims.total_amount,
	           'mrnNumber', claims.mrn_number,
	           'visitDate', claims.visit_date,
	           'payerName', payers.payer_name,
	           'payerPhone', payers.telephone,
	           'providerName', providers.provider_name,
	           'providerPhone', providers.telephone, 
	           'title', insured_persons.title,
	     'title', insured_persons.title,
	     'firstName', insured_persons.first_name,
	     'fatherName', insured_persons.father_name,
	     'grandFatherName', insured_persons.grand_father_name,
	     'gender', insured_persons.gender,
	      'insuranceId', insured_persons.institution_uuid,
	      'insuredPhone', insured_persons.phone,
	     'idNumber', insured_persons.id_number,
	     'institutionName', institutions.institution_name,
	     'toBank', bank_accounts.bank_name,
	     'toAccount', bank_accounts.bank_account,
	      'serviceProvided', ( SELECT JSON_ARRAYAGG( JSON_OBJECT(
	                                                 'item', services.item,
	                                                 'category', services.category,
	                                                 'description', services.description,
	                                                 'amount', services_provided.amount
	                                             )
	                                         )
	                                FROM services_provided
	                                INNER JOIN services
	                                ON services_provided.eligible_service_uuid = services.service_uuid
	                                WHERE services_provided.claim_uuid = claims.claim_uuid
	                            )
	       ) AS result
	       FROM claims INNER JOIN payers ON claims.payer_uuid=payers.payer_uuid INNER JOIN providers ON claims.provider_uuid =providers.provider_uuid INNER JOIN insured_persons ON claims.insured_person_uuid =insured_persons.insured_uuid INNER JOIN institutions ON insured_persons.institution_uuid=institutions.institution_uuid LEFT JOIN bank_accounts ON claims.to_bank_uuid =bank_accounts.bank_account_uuid WHERE  claims.payer_uuid=:payerUuid AND  claims.provider_uuid=:providerUuid  AND  claims.paymentNumber=:paymentNumber GROUP BY claims.claim_uuid  ", nativeQuery = true)
	*/
	/*
	@Query(value = "SELECT JSON_OBJECT(" +
	           "'claimNumber', claims.claim_number," +
	           "'claimDate', claims.approved_by_provider_date," +
	           "'checkNumber', claims.check_number," +
	           "'paymentNumber', claims.payment_number," +
	           "'paidDate', claims.paid_date," +
	           "'totalAmount', claims.total_amount," +
	           "'mrnNumber', claims.mrn_number," +
	           "'visitDate', claims.visit_date," +
	           "'payerName', payers.payer_name," +
	           "'payerPhone', payers.telephone," +
	           "'providerName', providers.provider_name," +
	           "'providerPhone', providers.telephone," +
	           "'title', insured_persons.title," +
	           "'firstName', insured_persons.first_name," +
	           "'fatherName', insured_persons.father_name," +
	           "'grandFatherName', insured_persons.grand_father_name," +
	           "'gender', insured_persons.gender," +
	           "'insuranceId', insured_persons.institution_uuid," +
	           "'insuredPhone', insured_persons.phone," +
	           "'idNumber', insured_persons.id_number," +
	           "'institutionName', institutions.institution_name," +
	           "'toBank', bank_accounts.bank_name," +
	           "'toAccount', bank_accounts.bank_account," +
	           "'serviceProvided', (" +
	                                "SELECT JSON_ARRAYAGG( JSON_OBJECT(" +
	                                                 "'item', services.item," +
	                                                 "'category', services.category," +
	                                                 "'description', services.description," +
	                                                 "'amount', services_provided.amount" +
	                                             ")" +
	                                         ")" +
	                                "FROM services_provided" +
	                                "INNER JOIN services" +
	                                "ON services_provided.eligible_service_uuid = services.service_uuid" +
	                                "WHERE services_provided.claim_uuid = claims.claim_uuid" +
	                            ")" +
	       ") AS result" +
	"FROM claims INNER JOIN payers ON claims.payer_uuid=payers.payer_uuid INNER JOIN providers ON claims.provider_uuid =providers.provider_uuid INNER JOIN insured_persons ON claims.insured_person_uuid =insured_persons.insured_uuid INNER JOIN institutions ON insured_persons.institution_uuid=institutions.institution_uuid LEFT JOIN bank_accounts ON claims.to_bank_uuid =bank_accounts.bank_account_uuid" +
	" WHERE  claims.payer_uuid=:payerUuid AND  claims.provider_uuid=:providerUuid  AND  claims.payment_number=:paymentNumber GROUP BY claims.claim_uuid;")
*/
	//List<Object[]> findPaidClaimDetails(@Param("providerUuid") String providerUuid, @Param("payerUuid") String payerUuid, @Param("paymentNumber") Long paymentNumber );
	

}
