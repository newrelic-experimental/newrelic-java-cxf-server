package org.apache.cxf.endpoint;

import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ClientCallback {
	
	@NewField
	public Token token = null;

	@Trace(async=true)
	public void handleResponse(Map<String, Object> ctx, Object[] res) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
	
	@Trace(async=true)
	public void handleException(Map<String, Object> ctx, Throwable ex) {
		NewRelic.noticeError(ex);
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
}
