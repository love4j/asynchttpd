package org.lovecoding.asynchttpd.test;

import org.lovecoding.asynchttpd.core.HttpConstant;
import org.lovecoding.asynchttpd.core.HttpHandler;
import org.lovecoding.asynchttpd.core.HttpRequest;
import org.lovecoding.asynchttpd.core.HttpResponse;

public class HttpHandlerHelloWorldJsonImpl implements HttpHandler{

	@Override
	public HttpResponse service(HttpRequest request) {
		String json = "{\"id\":1,\"text\":\"hello world!\"}";
		return new HttpResponse(HttpConstant.Status.HTTP_OK, HttpConstant.MIME.MIME_JSON, json);
	}

}
