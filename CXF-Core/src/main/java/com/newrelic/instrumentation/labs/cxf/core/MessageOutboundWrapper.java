package com.newrelic.instrumentation.labs.cxf.core;

import org.apache.cxf.message.Message;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.OutboundHeaders;

public class MessageOutboundWrapper implements OutboundHeaders {

	private Message message;
	
	public MessageOutboundWrapper(Message msg) {
		message = msg;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public void setHeader(String name, String value) {
		message.put(name, value);
	}

}
