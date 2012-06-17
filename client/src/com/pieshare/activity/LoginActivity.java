package com.pieshare.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.pieshare.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class LoginActivity extends Activity {
	private static final String TAG = "LoginActivity";
	
	private static final String LOGIN_URL = "http://192.168.56.1:8000/accounts/login/";
	private static final String LOGIN_USER_FIELD = "username";
	private static final String LOGIN_PASS_FIELD = "password";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Button btnLogin = (Button)findViewById(R.id.buttonLogin);
        EditText txtUsername = (EditText)findViewById(R.id.editTextUsername);
        EditText txtPassword = (EditText)findViewById(R.id.editTextPassword);
        
        btnLogin.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		DefaultHttpClient httpclient = new DefaultHttpClient();

        	    try {
        	        HttpGet httpget = new HttpGet(LOGIN_URL);
        	      try{
        	        HttpResponse response = httpclient.execute(httpget);
        	        HttpEntity entity = response.getEntity();

        	        //  System.out.println("Login form get: " + response.getStatusLine());
        	        //  EntityUtils.consume(entity);

        	        // System.out.println("Initial set of cookies:");
        	        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        	        if (cookies.isEmpty()) {
        	            Log.d(TAG, "None");
        	        } else {
        	            for (int i = 0; i < cookies.size(); i++) {
        	            	Log.d(TAG, "- " + cookies.get(i).toString());
        	            }
        	        }
        	        
        	        HttpPost httpost = new HttpPost(LOGIN_URL);

        	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        	        nvps.add(new BasicNameValuePair(LOGIN_USER_FIELD, "sb"));
        	        nvps.add(new BasicNameValuePair(LOGIN_PASS_FIELD, "sb"));

        	        try {
        	            httpost.setEntity(new UrlEncodedFormEntity(nvps));
        	        } catch (UnsupportedEncodingException e) {
        	            // TODO Auto-generated catch block
        	            e.printStackTrace();
        	        }

        	        try {
        	            response = httpclient.execute(httpost);
        	        } catch (ClientProtocolException e) {
        	            // TODO Auto-generated catch block
        	            e.printStackTrace();
        	        } catch (IOException e) {
        	            // TODO Auto-generated catch block
        	            e.printStackTrace();
        	        }
        	        entity = response.getEntity();
        	        Toast.makeText(getBaseContext(), "done",Toast.LENGTH_LONG);
        	        Log.d(TAG, "Login form get: " + response.getStatusLine());
        	    //   EntityUtils.consume(entity);

        	        Log.d(TAG, "Post logon cookies:");
        	        cookies = httpclient.getCookieStore().getCookies();
        	        if (cookies.isEmpty()) {
        	        	Log.d(TAG, "None");
        	        } else {
        	            for (int i = 0; i < cookies.size(); i++) {
        	            	Log.d(TAG, "- " + cookies.get(i).toString());
        	            }
        	        }

        	    }finally {} }catch (Exception e) {
        	        // TODO: handle exception
        	    	Log.d(TAG, e.toString());
        	    }finally {
        	        // When HttpClient instance is no longer needed,
        	        // shut down the connection manager to ensure
        	        // immediate deallocation of all system resources
        	        httpclient.getConnectionManager().shutdown();
        	        Toast.makeText(getBaseContext(), "done",Toast.LENGTH_LONG);
        	    }
//        		Intent intent = new Intent(Intent.ACTION_VIEW,
//        				Uri.parse("http://www.google.com"));
//        		startActivity(intent);
        	}
        });
    }

}
