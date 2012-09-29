package org.lovecoding.asynchttpd.core;

public class HttpHandlerDefaultImpl implements HttpHandler{

	@Override
	public HttpResponse service(HttpRequest request) {
		return new HttpResponse(HttpConstant.Status.HTTP_OK, HttpConstant.MIME.MIME_HTML, "<html><body><h1>It works...</h1>\n</body></html>\n");
	}

}
