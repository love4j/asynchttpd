package org.lovecoding.asynchttpd.core;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpWorker {

	private final Map<String,HttpHandler> handlers;
	private final InputStream in;
	private final OutputStream out;
	
	public HttpWorker(InputStream in, OutputStream out,Map<String,HttpHandler> handlers) {
		this.in = in;
		this.out = out;
		this.handlers = handlers;
	}
	
	public void run() {
		try {
			InputStream is = this.in;
			if (is == null){
				return;
			}

			int bufsize = 8192;
			byte[] buf = new byte[bufsize];
			int rlen = is.read(buf, 0, bufsize);
			if (rlen <= 0){
				return;
			}
		
			BufferedReader hin = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buf, 0,rlen)));
			Map<String,String> status = new HashMap<String,String>(3);
			Map<String,String> parms = new HashMap<String,String>(3);
			Map<String,String> header = new LinkedHashMap<String,String>(8);
			Map<String,String> files = new HashMap<String,String>(2);

			//decode header
			String decodeHeaderError = HttpDecode.decodeHeader(hin, status, parms, header);
			if(decodeHeaderError!=null){
				HttpError.sendError(decodeHeaderError,decodeHeaderError,this.out);
			}
			String method = status.get("method");
			String uri = status.get("uri");
			HttpResponse resp = null;
			HttpRequest req = new HttpRequest(uri, method, header, parms, files);
			if(this.handlers==null || this.handlers.isEmpty()){
				HttpHandler defaultHandler = new HttpHandlerDefaultImpl();
				resp = defaultHandler.service(req);
			}else{
				HttpHandler httpHandler = this.handlers.get(uri);
				if(httpHandler != null){
					resp = httpHandler.service(req);
				}else{
					resp = HttpError.service404(req);
				}
			}
			if (resp == null){
				HttpError.sendError(HttpConstant.Status.HTTP_INTERNALERROR,HttpConstant.Status.HTTP_INTERNALERROR,this.out);
			}else{
				HttpResponseWriter.writer(resp.status, resp.mimeType, resp.header, resp.data, this.out);
			}
			in.close();
			is.close();
		} catch (IOException ioe) {
			try {
				HttpError.sendError(HttpConstant.Status.HTTP_INTERNALERROR,HttpConstant.Status.HTTP_INTERNALERROR,this.out);
			} catch (Throwable t) {
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	
	
}
