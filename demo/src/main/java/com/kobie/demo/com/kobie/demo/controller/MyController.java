package com.kobie.demo.com.kobie.demo.controller;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.profile.internal.AwsProfileNameLoader;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AmazonSQS sqs;

    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> sendMessage( ){        // Create the connection factory based on the config


        AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));


        String msg = "My text published to SNS topic with email endpoint";
        PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-east-1:203743034184:messagingTopic", msg);

        PublishResult publishResult = snsClient.publish(publishRequest);
//        final AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion("us-east-1").build();
        final CreateQueueRequest createQueueRequest =
                new CreateQueueRequest("datatrans");
        String messageInJson = "{ \"theMessage\" : \"hello there\" } ";
        sqs.sendMessage(new SendMessageRequest("https://sqs.us-east-1.amazonaws.com/203743034184/datatrans", messageInJson));
        return new ResponseEntity<String>( new String("producer"), HttpStatus.OK ) ;
    }

}
