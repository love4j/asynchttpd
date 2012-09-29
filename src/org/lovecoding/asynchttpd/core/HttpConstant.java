package org.lovecoding.asynchttpd.core;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class HttpConstant {

	public static class Status {
		
		public static final String HTTP_OK = "200 OK",
				HTTP_PARTIALCONTENT = "206 Partial Content",
				HTTP_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable",
				HTTP_REDIRECT = "301 Moved Permanently",
				HTTP_NOTMODIFIED = "304 Not Modified",
				HTTP_FORBIDDEN = "403 Forbidden",
				HTTP_NOTFOUND = "404 Not Found",
				HTTP_BADREQUEST = "400 Bad Request",
				HTTP_INTERNALERROR = "500 Internal Server Error",
				HTTP_NOTIMPLEMENTED = "501 Not Implemented";
	}
	
	public static class MIME {
		
		public static final String MIME_PLAINTEXT = "text/plain",
				MIME_HTML = "text/html",
				MIME_DEFAULT_BINARY = "application/octet-stream",
				MIME_XML = "text/xml",
				MIME_JSON = "application/json";
		
		public static Map<String, String> MAP = new HashMap<String, String>();
		static {
			MAP.put("css", "text/css");
			MAP.put("htm", "text/html");
			MAP.put("html", "text/html");
			MAP.put("xml", "text/xml");
			MAP.put("txt", "text/plain");
			MAP.put("asc", "text/plain");
			MAP.put("gif", "image/gif");
			MAP.put("jpg", "image/jpeg");
			MAP.put("jpeg", "image/jpeg");
			MAP.put("png", "image/png");
			MAP.put("mp3", "audio/mpeg");
			MAP.put("m3u", "audio/mpeg-url");
			MAP.put("mp4", "video/mp4");
			MAP.put("ogv", "video/ogg");
			MAP.put("flv", "video/x-flv");
			MAP.put("mov", "video/quicktime");
			MAP.put("swf", "application/x-shockwave-flash");
			MAP.put("js", "application/javascript");
			MAP.put("pdf ", "application/pdf");
			MAP.put("doc", "application/msword");
			MAP.put("ogg", "application/x-ogg");
			MAP.put("zip", "application/octet-stream");
			MAP.put("exe", "application/octet-stream");
			MAP.put("class", "application/octet-stream");
		}

	}
	
	public static class BufferSize {
		public static int RESPONSE_SIZE = 16 * 1024;
	}
	
	public static class DateFormat {
		
		public static SimpleDateFormat GMT_FORMAT;
		static {
			GMT_FORMAT = new SimpleDateFormat(
					"E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
			GMT_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
		}
	}
	public static class Server {
		public static final String NAME = "asynchttpd";
		public static final String VERSION = "1.0.0";
		public static final String AUTHOR = "alex.tsai2010@gmail.com";
	}
	
}
