package com.medcoanalytics.lemobileyeservice.service.impl.claims;


import com.medcoanalytics.lemobileyeservice.entity.claims.ClaimAttachments;
import com.medcoanalytics.lemobileyeservice.payload.request.claims.ClaimAttachmentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.MessageResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimAttachmentResponse;
import com.medcoanalytics.lemobileyeservice.repository.claims.ClaimAttachmentsRepository;
import com.medcoanalytics.lemobileyeservice.service.claims.ClaimAttachmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimAttachmentServiceImpl implements ClaimAttachmentService {

	@Value("${file.claim-server-ip}")
	private String server;

	@Value("${file.ftp-claim-server-port}")
	private int port;

	@Value("${file.ftp-claim-server-username}")
	private String username;

	@Value("${file.ftp-claim-server-password}")
	private String password;

	@Value("${file.ftp-claim-path-receipts}")
	private String uploadDirectory;

	@Autowired
	ClaimAttachmentsRepository claimAttachmentRepository;

	@Override
	public List<ClaimAttachmentResponse> getClaimAttachments(String claimUuid) {

		List<ClaimAttachments> claimAttachmentsList= claimAttachmentRepository.findAllByClaimUuid(claimUuid);

		List <ClaimAttachmentResponse> car = new ArrayList<>();
		for (ClaimAttachments ca : claimAttachmentsList ) {
			ClaimAttachmentResponse c = new ClaimAttachmentResponse();
			c.setCreatedAt(ca.getCreatedAt());
			c.setFileName(ca.getFileName());
			c.setClaimAttachmentUuid(ca.getClaimAttachmentUuid());
			c.setFilePath(ca.getFile());
			c.setFileSize(ca.getFileSize());
			car.add(c);
		}
		return car;
	}

	@Override

	public ResponseEntity<?> createClaimAttachment(List<ClaimAttachmentRequest> claimAttachmentRequests) throws IOException {
		List<ClaimAttachments> claimAttachment = new ArrayList<>();
		for (ClaimAttachmentRequest ca : claimAttachmentRequests) {
			ClaimAttachments cas = new ClaimAttachments();
			BeanUtils.copyProperties(ca, cas);
			claimAttachment.add(cas);
		}
		claimAttachmentRepository.saveAll(claimAttachment);
		return ResponseEntity.ok(new MessageResponse("Successfully uploaded"));
	}

	@Override
	public ResponseEntity<?> deleteClaimAttachment(String claimAttachmentUuid) {
		ClaimAttachments ca = claimAttachmentRepository.findByClaimAttachmentUuid(claimAttachmentUuid);
		if (ca == null)
			return ResponseEntity.ok(new MessageResponse("Claim attachment  not found."));
		String oldFileName = ca.getFile();
		File file = new File(oldFileName);

		file.delete();
		claimAttachmentRepository.delete(ca);
		return ResponseEntity.ok(new MessageResponse("Claim  attachment deleted successfully!"));
	}

	@Override
	public ResponseEntity<Resource> openAttachment(String claimAttachmentUuid) throws IOException {
		ClaimAttachments ca = claimAttachmentRepository.findByClaimAttachmentUuid(claimAttachmentUuid);
		String uploadDir = uploadDirectory + ca.getFile();
		File file = new File(uploadDir);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getName());
		headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.toPath()));
		headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
		return ResponseEntity.ok().headers(headers).body(resource);

	}

	@Override
	public ResponseEntity<?> updateClaimAttachment(List<ClaimAttachmentRequest> claimAttachementRequests)
			throws IOException {
		List<ClaimAttachments> claimAttachment = new ArrayList<>();
		for (ClaimAttachmentRequest ca : claimAttachementRequests) {
			String claimAttachmentUuid=ca.getClaimAttachmentUuid();
			ClaimAttachments dbCa = claimAttachmentRepository.findByClaimAttachmentUuid(ca.getClaimAttachmentUuid());
			if (dbCa !=null) {
				claimAttachmentUuid=dbCa.getClaimAttachmentUuid();
				BeanUtils.copyProperties(ca, dbCa);
			} else {
				dbCa=new ClaimAttachments();
				BeanUtils.copyProperties(ca, dbCa);
			}

			dbCa.setClaimAttachmentUuid(claimAttachmentUuid);
			claimAttachment.add(dbCa);
		}
		claimAttachmentRepository.saveAll(claimAttachment);
		return ResponseEntity.ok(new MessageResponse("Successfully uploaded"));


	}


}
