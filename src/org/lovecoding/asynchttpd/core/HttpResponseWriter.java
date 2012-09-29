package org.lovecoding.asynchttpd.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class HttpResponseWriter {


	public static void writer(String status, String mime,
			Map<String,String> header, InputStream data,OutputStream out) {
		try {
			if (status == null){
				throw new Error("writer(): Status can't be null.");
			}
			if(out == null){
				throw new Error("writer(): OutputStream can't be null.");
			}
				
			PrintWriter pw = new PrintWriter(out);
			pw.print("HTTP/1.0 " + status + " \r\n");

			if (mime != null)
				pw.print("Content-Type: " + mime + "\r\n");

			if (header == null || header.get("Date") == null)
				pw.print("Date: " + HttpConstant.DateFormat.GMT_FORMAT.format(new Date()) + "\r\n");

			if (header != null) {
				Set<String> keySet = header.keySet();
				for(String key:keySet) {
					String value = header.get(key);
					pw.print(key + ": " + value + "\r\n");
				}
			}
			pw.print("Server: "+HttpConstant.Server.NAME+"\r\n");
			pw.print("\r\n");
			pw.flush();

			if (data != null) {
				int pending = data.available();
				byte[] buff = new byte[HttpConstant.BufferSize.RESPONSE_SIZE];
				while (pending > 0) {
					int read = data.read(buff, 0,
							((pending > HttpConstant.BufferSize.RESPONSE_SIZE) ? HttpConstant.BufferSize.RESPONSE_SIZE
									: pending));
					if (read <= 0)
						break;
					out.write(buff, 0, read);
					pending -= read;
				}
			}
			out.flush();
			out.close();
			if (data != null)
				data.close();
		} catch (IOException ioe) {

		}
	}
}
