package org.lovecoding.asynchttpd.test;

import java.util.HashMap;
import java.util.Map;

import org.lovecoding.asynchttpd.core.HttpHandler;
import org.lovecoding.asynchttpd.core.HttpHandlerDefaultImpl;
import org.lovecoding.asynchttpd.server.Asynchttpd;


public class Startup {
	
	  
	   public static void main(String[] args) throws Exception {
		   
		   	  Map<String,HttpHandler> handlers = new HashMap<String,HttpHandler>();
		   	  handlers.put("/", new HttpHandlerDefaultImpl());
		   	  handlers.put("/hello", new HttpHandlerHelloWorldImpl());
		   	  handlers.put("/json", new HttpHandlerHelloWorldJsonImpl());
		   	  Asynchttpd server = new Asynchttpd(8081,handlers);
		   	  server.startup();
	    }
  
}
