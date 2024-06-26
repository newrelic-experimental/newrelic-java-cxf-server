
package org.apache.cxf.interceptor;

import org.apache.cxf.message.Message;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.cxf.core.MessageHeaders;

@Weave(type=MatchType.Interface)
public abstract class Interceptor<T extends Message> {

	@Trace(async=true)
	public void handleMessage(T message) {
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(new MessageHeaders(message));
		Weaver.callOriginal();
	}
	
	@Trace(async=true)
	public void handleFault(T message) {
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(new MessageHeaders(message));
		Weaver.callOriginal();
	}
}
