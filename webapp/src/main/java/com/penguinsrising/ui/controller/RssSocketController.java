package com.penguinsrising.ui.controller;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Service;

//@Service
//public class RssSocketController implements ApplicationListener<BrokerAvailabilityEvent>  {
//	private final MessageSendingOperations<String> messagingTemplate;
//
//	@Autowired
//	public RssSocketController(MessageSendingOperations<String> messagingTemplate) {
//		this.messagingTemplate = messagingTemplate;
//	}
//
//	private AtomicBoolean brokerAvailable = new AtomicBoolean();
//
//	@Override
//	public void onApplicationEvent(BrokerAvailabilityEvent event) {
//		this.brokerAvailable.set(event.isBrokerAvailable());
//	}
//
//
//}
