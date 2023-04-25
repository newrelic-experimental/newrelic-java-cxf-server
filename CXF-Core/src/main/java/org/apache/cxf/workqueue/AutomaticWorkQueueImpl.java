package org.apache.cxf.workqueue;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class AutomaticWorkQueueImpl {

	@Trace(dispatcher=true)
	public void execute(Runnable command) {
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public void execute(Runnable command, long timeout) {
		Weaver.callOriginal();
	}

}
