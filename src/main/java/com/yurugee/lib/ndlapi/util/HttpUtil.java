package com.yurugee.lib.ndlapi.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static String send(String url) throws UnsupportedOperationException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
	 
		HttpResponse response = client.execute(request);
	 	 
		return EntityUtils.toString(response.getEntity());
		
	}
	

}
