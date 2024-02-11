package com.medcoanalytics.lemobileyeservice.service.impl.mobiles;


import com.medcoanalytics.lemobileyeservice.entity.mobiles.MobileAttachment;
import com.medcoanalytics.lemobileyeservice.payload.request.mobiles.MobileAttachmentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.MessageResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.mobiles.MobileAttachmentResponse;
import com.medcoanalytics.lemobileyeservice.repository.mobiles.MobileAttachmentRepository;
import com.medcoanalytics.lemobileyeservice.service.mobiles.MobileAttachmentService;
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
public class MobileAttachmentServiceImpl implements MobileAttachmentService {


    @Value("${file.claim-server-ip}")
    private String server;

    @Value("${file.ftp-mobile-server-port}")
    private int port;

    @Value("${file.ftp-mobile-server-username}")
    private String username;

    @Value("${file.ftp-claim-server-password}")
    private String password;

    @Value("${file.ftp-mobile-path-attachments}")
    private String uploadDirectory;

    @Autowired
    MobileAttachmentRepository mobileAttachmentRepository;


    @Override
    public ResponseEntity<?> createMobileAttachment(List<MobileAttachmentRequest> mobileAttachmentRequest) throws IOException {
        List<MobileAttachment> mobileAttachment = new ArrayList<>();
        for (MobileAttachmentRequest mar : mobileAttachmentRequest) {
            MobileAttachment ma = new MobileAttachment();
            BeanUtils.copyProperties(mar, ma);
            mobileAttachment.add(ma);
        }
        mobileAttachmentRepository.saveAll(mobileAttachment);
        return ResponseEntity.ok(new MessageResponse("Successfully created"));
    }

    @Override
    public ResponseEntity<?> updateMobileAttachment(List<MobileAttachmentRequest> mobileAttachmentRequest) throws IOException {
        List<MobileAttachment> mobileAttachment = new ArrayList<>();
        for (MobileAttachmentRequest ma : mobileAttachmentRequest) {
            String mobileAttachmentUuid=ma.getAttachmentUuid();
            MobileAttachment dbMa = mobileAttachmentRepository.findByAttachmentUuid(ma.getAttachmentUuid());
            if (dbMa !=null) {
                mobileAttachmentUuid=dbMa.getAttachmentUuid();
                BeanUtils.copyProperties(ma, dbMa);
            } else {
                dbMa=new MobileAttachment();
                BeanUtils.copyProperties(ma, dbMa);
            }

            dbMa.setAttachmentUuid(mobileAttachmentUuid);
           mobileAttachment.add(dbMa);
        }
        mobileAttachmentRepository.saveAll(mobileAttachment);
        return ResponseEntity.ok(new MessageResponse("Successfully uploaded"));
    }

    @Override
    public ResponseEntity<?> deleteMobileAttachment(String mobileAttachmentUuid) {
        MobileAttachment ma = mobileAttachmentRepository.findByAttachmentUuid(mobileAttachmentUuid);
        if (ma == null)
            return ResponseEntity.ok(new MessageResponse("Mobile attachment  not found."));
        String oldFileName = ma.getFile();
        File file = new File(oldFileName);

        file.delete();
        mobileAttachmentRepository.delete(ma);
        return ResponseEntity.ok(new MessageResponse("Mobile  attachment deleted successfully!"));
    }

    @Override
    public List<MobileAttachmentResponse> getMobileAttachments(String mobileAttachmentUuid) {
        List<MobileAttachment> mobileAttachmentsList = mobileAttachmentRepository.findAllByMobileUuid(mobileAttachmentUuid);

        List<MobileAttachmentResponse> mar = new ArrayList<>();
        for (MobileAttachment ma : mobileAttachmentsList) {
            MobileAttachmentResponse m = new MobileAttachmentResponse();
            m.setCreatedAt(ma.getCreatedAt());
            m.setFileName(ma.getFileName());
            m.setMobileAttachmentUuid(ma.getAttachmentUuid());
            m.setFilePath(ma.getFile());
            m.setFileSize(ma.getFileSize());
            mar.add(m);
        }
            return mar;



    }

    @Override
    public ResponseEntity<Resource> openAttachment(String mobileAttachmentUuid) throws IOException {
        MobileAttachment ma = mobileAttachmentRepository.findByAttachmentUuid(mobileAttachmentUuid);
        String uploadDir = uploadDirectory + ma.getFile();
        File file = new File(uploadDir);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.toPath()));
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
