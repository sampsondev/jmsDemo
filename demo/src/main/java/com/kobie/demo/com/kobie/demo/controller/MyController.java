package com.kobie.demo.com.kobie.demo.controller;

import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
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
    public ResponseEntity<String> sendMessage( ){
        MessageBuilder<String> message = MessageBuilder.withPayload("there2").setHeader("queue", "https://sqs.us-east-1.amazonaws.com/203743034184/datatrans");
        SendMessageRequest request = new SendMessageRequest("https://sqs.us-east-1.amazonaws.com/203743034184/datatrans", " there ");

        return new ResponseEntity<String>( new String("producer"), HttpStatus.OK ) ;
    }

}
