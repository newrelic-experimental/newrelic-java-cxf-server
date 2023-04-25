package org.apache.cxf.binding;

import org.apache.cxf.message.Message;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class Binding {

	public Message createMessage() {
		Message msg = Weaver.callOriginal();
		return msg;
	}
	
	public Message createMessage(Message m) {
		Message msg = Weaver.callOriginal();
		return msg;
	}
}
