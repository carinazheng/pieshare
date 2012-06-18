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

import com.pieshare.util.PSHttpClient;

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

        
        btnLogin.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
                EditText txtUsername = (EditText)findViewById(R.id.editTextUsername);
                EditText txtPassword = (EditText)findViewById(R.id.editTextPassword);
                
        		HttpClient httpClient = PSHttpClient.getHttpClient();
        		if (PSHttpClient.login(LOGIN_URL, 
        				txtUsername.getText().toString(),
        				txtPassword.getText().toString()))
        		{
        			Intent intent = new Intent(Intent.ACTION_VIEW,
            				Uri.parse("http://www.google.com"));
            		startActivity(intent);
        		}
        		else
        		{
        			Intent intent = new Intent(Intent.ACTION_VIEW,
            				Uri.parse("http://www.facebook.com"));
            		startActivity(intent);
        		}
        		
        		
//        		Intent intent = new Intent(Intent.ACTION_VIEW,
//        				Uri.parse("http://www.google.com"));
//        		startActivity(intent);
        	}
        });
    }

}
