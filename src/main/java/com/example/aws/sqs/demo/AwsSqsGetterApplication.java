package com.example.aws.sqs.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.example.aws.sqs.demo.service.ApplicationPropertieseService;

@SpringBootApplication
public class AwsSqsGetterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSqsGetterApplication.class, args).close();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            new AwsSqsGetterApplication().getMessage(AmazonSQSClientBuilder.defaultClient());
        };
    }

    @Autowired
    ApplicationPropertieseService properties;

    private void getMessage(AmazonSQS sqs) {
        System.out.println("SQS URL : " + properties.getSqsUrl());
        List<Message> messages = sqs.receiveMessage(properties.getSqsUrl()).getMessages();
        messages.forEach(System.out::println);
        for (Message m : messages) {
            sqs.deleteMessage(properties.getSqsUrl(), m.getReceiptHandle());
        }
    }
}
