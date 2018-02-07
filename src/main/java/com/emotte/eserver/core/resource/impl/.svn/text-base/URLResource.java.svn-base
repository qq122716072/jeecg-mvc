package com.emotte.eserver.core.resource.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.emotte.eserver.core.resource.Resource;

public class URLResource implements Resource {
	private URL url;
	
	public URLResource(String path) throws MalformedURLException {
		this.url = new URL(path);
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		URLConnection con = this.url.openConnection();
		try {
			return con.getInputStream();
		} catch (IOException ex) {
			// Close the HTTP connection (if applicable).
			if (con instanceof HttpURLConnection) {
				((HttpURLConnection) con).disconnect();
			}
			throw ex;
		}
	}
}
