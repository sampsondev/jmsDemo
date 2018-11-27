package com.kobie.demo.com.kobie.demo.controller;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.profile.internal.AwsProfileNameLoader;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class MyController {

    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> sendMessage( ){        // Create the connection factory based on the config

        final AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion("us-east-1").build();
        final CreateQueueRequest createQueueRequest =
                new CreateQueueRequest("datatrans");
        sqs.sendMessage(new SendMessageRequest("https://sqs.us-east-1.amazonaws.com/203743034184/datatrans", "my message"));
        return new ResponseEntity<String>( new String("producer"), HttpStatus.OK ) ;
    }

}
