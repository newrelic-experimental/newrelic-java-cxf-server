package org.apache.cxf.interceptor;

import org.apache.cxf.message.Message;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.cxf.core.MessageInboundWrapper;

@Weave(type=MatchType.Interface)
public abstract class Interceptor<T extends Message> {

	@Trace(async=true)
	public void handleMessage(T message) {
		AgentBridge.getAgent().getTransaction(false).provideHeaders(new MessageInboundWrapper(message));
		Weaver.callOriginal();
	}
	
	@Trace(async=true)
	public void handleFault(T message) {
		AgentBridge.getAgent().getTransaction(false).provideHeaders(new MessageInboundWrapper(message));
		Weaver.callOriginal();
	}
}
