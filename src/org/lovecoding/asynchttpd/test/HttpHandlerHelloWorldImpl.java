package org.lovecoding.asynchttpd.test;

import org.lovecoding.asynchttpd.core.HttpConstant;
import org.lovecoding.asynchttpd.core.HttpHandler;
import org.lovecoding.asynchttpd.core.HttpRequest;
import org.lovecoding.asynchttpd.core.HttpResponse;

public class HttpHandlerHelloWorldImpl implements HttpHandler{

	@Override
	public HttpResponse service(HttpRequest request) {
		String msg = "<html><body><h1>Hello World !!!</h1>\n</body></html>\n";
		return new HttpResponse(HttpConstant.Status.HTTP_OK, HttpConstant.MIME.MIME_HTML, msg);
	}

}
