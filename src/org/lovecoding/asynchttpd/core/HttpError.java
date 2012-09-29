package org.lovecoding.asynchttpd.core;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

public class HttpError {
	
	public static void sendError(String status, String msg,OutputStream out)
			throws InterruptedException {
		HttpResponseWriter.writer(status, HttpConstant.MIME.MIME_PLAINTEXT, null,
				new ByteArrayInputStream(msg.getBytes()),out);
		throw new InterruptedException();
	}
	
	public static HttpResponse service404(HttpRequest request) {
		return new HttpResponse(HttpConstant.Status.HTTP_OK, HttpConstant.MIME.MIME_HTML, "<html><body><h1>404 NOT FOUND</h1>\n</body></html>\n");
	}
	
	public static HttpResponse service500(HttpRequest request) {
		return new HttpResponse(HttpConstant.Status.HTTP_OK, HttpConstant.MIME.MIME_HTML, "<html><body><h1>500 Server Error</h1>\n</body></html>\n");
	}
}
