package com.medcoanalytics.lemobileyeservice.repository.claims;


import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimDetailResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimListResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimLogsResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.PaidClaimsListResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClaimListRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ClaimListResponse> findAllRequestedClaims(String personUuid, String searchKey, int page, int limit) {
		if (page > 0)
			page = page - 1;
		if (searchKey != null) {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid AND (claims.insurance_comment LIKE :searchKey claims.person_comment LIKE :searchKey claims.transaction_number LIKE :searchKey) Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		} else {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid  Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		}
	}

	public List<ClaimListResponse> findAllPreparedClaimsByPerson(String personUuid, String searchKey, int page,
			int limit) {
		if (page > 0)
			page = page - 1;
		if (searchKey != null) {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid AND (claims.insurance_comment LIKE :searchKey claims.person_comment LIKE :searchKey claims.transaction_number LIKE :searchKey) Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		} else {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid  Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		}
	}

	public List<ClaimListResponse> findAllPaidClaimsByInsurance(String  personUuid, String searchKey, int page,
			int limit) {
		if (page > 0)
			page = page - 1;
		if (searchKey != null) {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid AND (claims.insurance_comment LIKE :searchKey claims.person_comment LIKE :searchKey claims.transaction_number LIKE :searchKey) Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		} else {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid  Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		}
	}

	public List<ClaimListResponse> findAllApprovedClaimsByInsurance(String personUuid, String searchKey, int page,
			int limit) {
		if (page > 0)
			page = page - 1;
		if (searchKey != null) {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid AND (claims.insurance_comment LIKE :searchKey claims.person_comment LIKE :searchKey claims.transaction_number LIKE :searchKey) Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		} else {
			Query query = entityManager.createNativeQuery(
					"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.transaction_number as transactionNumber, claims.payment_number as paymentNumber,mobiles.person_uuid as personUuid FROM claims INNER JOIN mobiles ON claims.mobile_uuid=mobiles.mobile_uuid WHERE  claims.is_deleted=0 and mobiles.person_uuid=:personUuid  Group By claims.claim_uuid, insured_persons.insured_uuid  ORDER BY  claims.created_at ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimListResponse.class));
			List<ClaimListResponse> list = query.getResultList();
			return list;
		}
	}

	public List<ClaimLogsResponse> findClaimLogs(String claimUuid, String searchKey, int page, int limit) {
		if (page > 0)
			page = page - 1;
		if (searchKey != null) {
			Query query = entityManager.createNativeQuery(
					"SELECT claim_logs.comment as comment, claim_logs.previous_status as previousStatus, claim_logs.action_date as actionDate, claim_logs.action_status as status FROM claim_logs INNER JOIN claims on claim_logs.claim_uuid=claims.claim_uuid  WHERE claim_logs.claim_uuid=:claimUuid AND (claim_logs.comment LIKE :searchKey OR  claim_logs.previous_status LIKE :searchKey OR claim_logs.action_status LIKE :searchKey ) ORDER BY  claim_logs.action_date DESC ");

			query.setParameter("claimUuid", claimUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");
			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimLogsResponse.class));
			List<ClaimLogsResponse> list = query.getResultList();
			return list;
		} else {
			Query query = entityManager.createNativeQuery(
					"SELECT claim_logs.comment as comment, claim_logs.previous_status as previousStatus, claim_logs.action_date as actionDate, claim_logs.action_status as status FROM claim_logs INNER JOIN claims on claim_logs.claim_uuid=claims.claim_uuid  WHERE claim_logs.claim_uuid=:claimUuid  ORDER BY  claim_logs.action_date DESC ");

			query.setParameter("claimUuid", claimUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");
			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimLogsResponse.class));
			List<ClaimLogsResponse> list = query.getResultList();
			return list;
		}
	}

	public ClaimDetailResponse findClaimDetails(String claimUuid) {

		Query query = entityManager.createNativeQuery(
				"SELECT claims.claim_uuid AS claimUuid, claims.person_comment as personComment, claims.insurance_comment as insuranceComment, claims.payment_number as paymentNumber, claims.transaction_number as transactionNumber, FROM claims WHERE  claims.claim_uuid=:claimUuid  Group by claims.claim_uuid");
		query.setParameter("claimUuid", claimUuid);
		query.unwrap(NativeQuery.class)
				.setResultTransformer(new AliasToBeanResultTransformer<>(ClaimDetailResponse.class));
		List<ClaimDetailResponse> list = query.getResultList();
		for (ClaimDetailResponse l : list)
			return l;
		return null;

	}

	public List<PaidClaimsListResponse> findPaidClaimsByInsurance(String personUuid, String searchKey, int page,
																  int limit) {
		if (page > 0)
			page = page - 1;
		if (searchKey != null) {
			Query query = entityManager.createNativeQuery(
					" SELECT  claims.claim_number as claimNumber, claims.check_number as checkNumber, claims.payment_number as paymentNumber, claims.total_amount as totalAmount, claims.paid_date as paymentDate,claims.bank_transaction_code as transactionCode FROM claims  WHERE claims.person_uuid=:personrUuid  AND (claims.bank_transaction_code LIKE :searchKey ) GROUP By claims.payment_number  ORDER BY  claims.paid_date ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");
			query.setFirstResult(page * limit);
			query.setMaxResults(limit);

			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(PaidClaimsListResponse.class));
			List<PaidClaimsListResponse> list = query.getResultList();
			return list;
		} else {
			Query query = entityManager.createNativeQuery(
					" SELECT  claims.claim_number as claimNumber, claims.check_number as checkNumber, claims.payment_number as paymentNumber, claims.total_amount as totalAmount, claims.paid_date as paymentDate,claims.bank_transaction_code as transactionCode FROM claims  WHERE claims.person_uuid=:personrUuid GROUP By claims.payment_number  ORDER BY  claims.paid_date ASC");

			query.setParameter("personUuid", personUuid);
			query.setParameter("searchKey", "%" + searchKey + "%");
			query.setFirstResult(page * limit);
			query.setMaxResults(limit);

			query.unwrap(NativeQuery.class)
					.setResultTransformer(new AliasToBeanResultTransformer<>(PaidClaimsListResponse.class));
			List<PaidClaimsListResponse> list = query.getResultList();
			return list;
		}
	}

}
