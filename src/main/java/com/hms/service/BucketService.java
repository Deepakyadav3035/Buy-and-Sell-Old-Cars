package com.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import com.amazonaws.services.s3.AmazonS3;

@Service
public class BucketService {
    @Autowired
    private AmazonS3 amazonS3;


    public String uploadFile(MultipartFile file, String bucketName) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        try {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);

            try {
                amazonS3.putObject(bucketName, convFile.getName(), convFile);
                return amazonS3.getUrl(bucketName, file.getOriginalFilename()).toString();
            } catch (Exception e) {
                throw new RuntimeException("Error while uploading file to S3", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error converting multipart file to file", e);
        }
    }
}