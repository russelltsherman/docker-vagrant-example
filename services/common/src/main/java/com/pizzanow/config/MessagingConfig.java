package com.pizzanow.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class MessagingConfig {

	public static final String KitchenQueueName = "kitchen";
	public static final String DeliveryQueueName = "delivery";
	
	@Bean
    List<Queue> queues() {
        return Arrays.asList(new Queue(KitchenQueueName, false), new Queue(DeliveryQueueName, false));
    }
}
