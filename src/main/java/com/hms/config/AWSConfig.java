package com.hms.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.access.key.id}")
    private String accessKey;

    @Value("${aws.secret.key.id}")
    private String secretKey;

    @Value("${aws.s3.region}")
    private String region;


    @Bean
    public AmazonS3 amazonS3() {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
       return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .withRegion(region).build();

    }

}
