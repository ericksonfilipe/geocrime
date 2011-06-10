package com.ufcg.sig.geocrime.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GeoCrimeService extends RemoteService {
	public class Util {
		public static GeoCrimeServiceAsync getInstance() {
			return GWT.create(GeoCrimeService.class);
		}
	}
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
