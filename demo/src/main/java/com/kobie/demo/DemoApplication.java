package com.kobie.demo;

import com.amazonaws.auth.profile.internal.AwsProfileNameLoader;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@SpringBootApplication
@IntegrationComponentScan
public class 	DemoApplication {

	public static void main(String[] args) {
		System.out.println("starting");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	SubscribableChannel getPublishChannel( ){
		return null;
	}

	@Bean
	AmazonSQS getAmazonSQS(){
		return AmazonSQSClientBuilder.standard().withRegion("us-east-1").build();
	}
}
