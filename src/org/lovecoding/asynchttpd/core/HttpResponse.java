package org.lovecoding.asynchttpd.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {
	
	public HttpResponse() {
		this.status = HttpConstant.Status.HTTP_OK;
	}

	
	public HttpResponse(String status, String mimeType, InputStream data) {
		this.status = status;
		this.mimeType = mimeType;
		this.data = data;
	}

	
	public HttpResponse(String status, String mimeType, String txt) {
		this.status = status;
		this.mimeType = mimeType;
		try {
			this.data = new ByteArrayInputStream(txt.getBytes("UTF-8"));
		} catch (java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
	}
	
	public void addHeader(String name, String value) {
		header.put(name, value);
	}

	public String status;

	
	public String mimeType;

	
	public InputStream data;

	
	public Map<String,String> header = new LinkedHashMap<String,String>();
}
