package org.lovecoding.asynchttpd.core;

import java.util.Map;

public class HttpRequest {

	public String uri;
	
	public String method;
	
	public Map<String,String> header;
	
	public Map<String,String> parms;
	
	public Map<String,String> files;

	public HttpRequest(String uri, String method, Map<String,String> header,
			Map<String,String> parms, Map<String,String> files) {
		this.uri = uri;
		this.method = method;
		this.header = header;
		this.parms = parms;
		this.files = files;
	}

	public HttpRequest(String uri, String method, Map<String,String> header,
			Map<String,String> parms) {
		this.uri = uri;
		this.method = method;
		this.header = header;
		this.parms = parms;
	}

	public HttpRequest(String uri, String method, Map<String,String> header) {
		this.uri = uri;
		this.method = method;
		this.header = header;
	}
	
	
	
	
}
