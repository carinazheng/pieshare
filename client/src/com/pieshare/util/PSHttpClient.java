package com.pieshare.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
	 
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class PSHttpClient {
	public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds

	private static HttpClient mHttpClient;

    private static HttpClient getHttpClient() {
        if (mHttpClient == null) {
	        mHttpClient = new DefaultHttpClient();
	        
	        final HttpParams params = mHttpClient.getParams();
	        HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
	        HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
	        ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
	    }
	    return mHttpClient;
	}

}
