package org.apache.cxf.service.invoker;

import org.apache.cxf.message.Exchange;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class Invoker {

	@Trace(dispatcher=true)
	public Object invoke(Exchange exchange, Object o) {
		
		return Weaver.callOriginal();
	}
}
