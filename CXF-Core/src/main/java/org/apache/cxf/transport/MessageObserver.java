package org.apache.cxf.transport;

import javax.xml.namespace.QName;

import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.service.Service;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class MessageObserver {

	@Trace(dispatcher=true)
	public void onMessage(Message message) {
		Exchange exchange = message.getExchange();
		String serviceName = null;
		if(exchange != null) {
			Service service = message.getExchange().getService();
			if(service != null) {
				QName serviceQname = service.getName();
				serviceName = serviceQname.toString();
			}
		}
		if(serviceName != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","MessageObserver",getClass().getName(),serviceName});
		} else {
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","MessageObserver",getClass().getName()});
		}
		Weaver.callOriginal();
	}
}
