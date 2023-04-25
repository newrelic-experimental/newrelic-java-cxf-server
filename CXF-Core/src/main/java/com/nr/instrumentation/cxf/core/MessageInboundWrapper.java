package com.nr.instrumentation.cxf.core;

import org.apache.cxf.message.Message;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.InboundHeaders;

public class MessageInboundWrapper implements InboundHeaders {
	
	private Message message;
	
	public MessageInboundWrapper(Message msg) {
		message = msg;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public String getHeader(String name) {
		return (String) message.get(name);
	}

}
