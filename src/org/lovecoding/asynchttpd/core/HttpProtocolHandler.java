package org.lovecoding.asynchttpd.core;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;


import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.stream.StreamIoHandler;

public class HttpProtocolHandler extends StreamIoHandler {
	
	private  Map<String,HttpHandler> handlers;
	public HttpProtocolHandler(Map<String,HttpHandler> handlers){
		this.handlers = handlers;
	}
	
	@Override
	protected void processStreamIo(IoSession session, InputStream in,
			OutputStream out) {
		HttpWorker worker = new HttpWorker(in, out,handlers);
		worker.run();
	}


}
