package com.pieshare.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.util.Log;
import android.widget.Toast;

public class PSHttpClient {
	private static final String TAG = "PSHttpClient";
	
	public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds
	private static final String LOGIN_USER_FIELD = "username";
	private static final String LOGIN_PASS_FIELD = "password";

	private static DefaultHttpClient mHttpClient;
	private static List<Cookie> mCookies;

	public static HttpClient getHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
		}
		return mHttpClient;
	}
	
	private static String convertStreamToString(java.io.InputStream is) {
	    try {
	        return new java.util.Scanner(is).useDelimiter("\\A").next();
	    } catch (java.util.NoSuchElementException e) {
	        return "";
	    }
	}

	public static boolean login(String url, String username, String password) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		boolean ret = false;

		try {
			HttpGet httpget = new HttpGet(url);

				HttpPost httpost = new HttpPost(url);

				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair(LOGIN_USER_FIELD, username));
				nvps.add(new BasicNameValuePair(LOGIN_PASS_FIELD, password));

				try {
					httpost.setEntity(new UrlEncodedFormEntity(nvps));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				HttpResponse	response = httpclient.execute(httpost);
				HttpEntity entity = response.getEntity();
				String res = convertStreamToString(entity.getContent());
				Log.d(TAG, "Response: " + res);
				ret = res.contains("Welcome");

//				Log.d(TAG, "Post logon cookies:");
//				List<Cookie> cookies = mHttpClient.getCookieStore().getCookies();
//				if (cookies.isEmpty()) {
//					Log.d(TAG, "None");
//				} else {
//					for (int i = 0; i < cookies.size(); i++) {
//						Log.d(TAG, "- " + cookies.get(i).toString());
//					}
//				}
				
//				mCookies = cookies;
		} catch (ClientProtocolException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		
		return ret;
	}

}
