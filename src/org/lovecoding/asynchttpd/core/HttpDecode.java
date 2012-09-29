package org.lovecoding.asynchttpd.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;


public class HttpDecode {
	
	public static String decodeHeader(BufferedReader in, Map<String,String> status,
			Map<String,String> parms, Map<String,String> headers)
			throws InterruptedException {
		try {
			// Read the request line
			String firstLine = in.readLine();
			if (firstLine == null){
				return HttpConstant.Status.HTTP_BADREQUEST;
			}
			String[] data = firstLine.split(" ");
			if(data==null || data.length<3){
				 return HttpConstant.Status.HTTP_BADREQUEST;
			}
			String method = data[0];
			String uri = data[1];
			String version = data[2];
			int index = uri.indexOf("?");
			if(index != -1){
				decodeParms(uri.substring(index + 1), parms);
				uri = decodePercent(uri.substring(0, index));
			}else{
				uri = decodePercent(uri);
			}
			status.put("method", method);
			status.put("uri", uri);
			status.put("version", version);
			//read header
			String headerLine;
		    while ((headerLine = in.readLine()) != null && !headerLine.equals("")) {
			   String[] tokens = headerLine.split(": ");
			   headers.put(tokens[0], tokens[1]);
			 }
		    //read content
		    int length = 0;
		    if(headers.containsKey("Content-Length")){
		    	length = Integer.valueOf(headers.get("Content-Length"));
		    }
		    if("POST".equals(method) && length > 0){
		    	char[] body = new char[length];
		    	int size = 0;
		    	while(true){
		    		int n = in.read(body);
		    		size += n;
		    		if(size == length){
		    			break;
		    		}
		    	}
		    	String contentType = headers.get("Content-Type");
		    	if(contentType.equalsIgnoreCase("multipart/form-data")){
		    		//TODO
		    	}else{
		    		String postParameter = new String(body);
		    		HttpDecode.decodeParms(postParameter.trim(), parms);
		    	}
		    	
		    }
			
		} catch (IOException ioe) {
			 ioe.printStackTrace();
			 return HttpConstant.Status.HTTP_BADREQUEST;
		}
		return null;
	}
	
	
	
	private static String decodePercent(String str) throws InterruptedException {
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				switch (c) {
				case '+':
					sb.append(' ');
					break;
				case '%':
					sb.append((char) Integer.parseInt(
							str.substring(i + 1, i + 3), 16));
					i += 2;
					break;
				default:
					sb.append(c);
					break;
				}
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	
	public static void decodeParms(String parms, Map<String,String> p)
			throws InterruptedException {
		if (parms == null) {
			return;
		}

		StringTokenizer st = new StringTokenizer(parms, "&");
		while (st.hasMoreTokens()) {
			String e = st.nextToken();
			int sep = e.indexOf('=');
			if (sep >= 0) {
				p.put(decodePercent(e.substring(0, sep)).trim(),decodePercent(e.substring(sep + 1)));
			}
		}
	}

	

	
	


}
