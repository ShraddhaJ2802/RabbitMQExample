package com.bridgelab.rabbitexample.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.bridgelab.rabbitexample.config.MessagingConfig;
import com.bridgelab.rabbitexample.dto.Order;
import com.bridgelab.rabbitexample.dto.OrderStatus;
import com.bridgelab.rabbitexample.util.EmailService;

@Component
public class User {
	@Autowired
	private EmailService emailService;
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus)
	{
		//System.out.println("Message received from queue: "+orderStatus);
		emailService.sendEmail(orderStatus.getOrder().getEmail(),"Welcome in our restaurant nd your order is :"+orderStatus.getOrder().getName() ,"Message received from queue" +orderStatus);
	}

}
