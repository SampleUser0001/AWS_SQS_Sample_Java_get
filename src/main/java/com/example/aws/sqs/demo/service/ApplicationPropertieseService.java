package com.example.aws.sqs.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class ApplicationPropertieseService {
    @Value("${aws.sqs.url}")
    private String sqsUrl;

    @Value("${aws.sqs.groupid}")
    private String sqsGroupId;


}
