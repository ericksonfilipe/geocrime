package com.ufcg.sig.geocrime.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GeoCrimeServiceAsync {
	
	void greetServer(String input, AsyncCallback<String> callback,
			AsyncCallback<Void> callback2);

}
