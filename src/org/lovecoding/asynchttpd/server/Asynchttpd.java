package org.lovecoding.asynchttpd.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.lovecoding.asynchttpd.core.HttpHandler;
import org.lovecoding.asynchttpd.core.HttpProtocolHandler;

public class Asynchttpd {

	 private Map<String,HttpHandler> handlers = new HashMap<String,HttpHandler>();
	 private int port = 8080;
	 private int threadPoolSize = 100;
	 
	 public Asynchttpd(){
		 
	 }
	 public Asynchttpd(int port,Map<String,HttpHandler> handlers){
		 this.port = port;
		 this.handlers = handlers;
	 }
	 public Asynchttpd(int port,Map<String,HttpHandler> handlers,int threadPoolSize){
		 this.port = port;
		 this.handlers = handlers;
		 this.threadPoolSize = threadPoolSize;
	 }
	 
	 public void startup() {
		 IoAcceptor acceptor = new NioSocketAcceptor();
		 Executor threadPool = Executors.newFixedThreadPool(threadPoolSize);   
	     acceptor.getFilterChain().addLast("exector", new ExecutorFilter(threadPool)); 
	     LoggingFilter filter = new LoggingFilter();   
	     filter.setExceptionCaughtLogLevel(LogLevel.DEBUG);   
	     filter.setMessageReceivedLogLevel(LogLevel.DEBUG);   
	     filter.setMessageSentLogLevel(LogLevel.DEBUG);   
	     filter.setSessionClosedLogLevel(LogLevel.DEBUG);   
	     filter.setSessionCreatedLogLevel(LogLevel.DEBUG);   
	     filter.setSessionIdleLogLevel(LogLevel.DEBUG);   
	     filter.setSessionOpenedLogLevel(LogLevel.DEBUG);   
	     acceptor.getFilterChain().addLast("logger", filter); 
         acceptor.setHandler(new HttpProtocolHandler(handlers));
         try {
			acceptor.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}
         System.out.println("asynchttpd startup on port " + port);
	 }
}
