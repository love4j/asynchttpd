package org.lovecoding.asynchttpd.core;

public interface HttpHandler {

	public HttpResponse service(HttpRequest request);
}
