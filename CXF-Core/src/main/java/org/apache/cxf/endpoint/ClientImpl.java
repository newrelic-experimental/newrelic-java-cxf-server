package org.apache.cxf.endpoint;

import java.util.Map;

import org.apache.cxf.message.Exchange;
import org.apache.cxf.service.model.BindingOperationInfo;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ClientImpl {

	@Trace
	private Object[] doInvoke(ClientCallback callback, BindingOperationInfo oi, Object[] params, Map<String, Object> context,Exchange exchange) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","ClientImpl","doInvoke",oi.getName().getLocalPart()});
		if(callback.token == null) {
			callback.token = NewRelic.getAgent().getTransaction().getToken();
		}
		return Weaver.callOriginal();
	}
}
