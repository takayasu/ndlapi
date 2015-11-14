package com.yurugee.lib.ndlapi.sru;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.yurugee.lib.ndlapi.exception.ConfigurationException;
import com.yurugee.lib.ndlapi.sru.request.SruRequest;
import com.yurugee.lib.ndlapi.sru.response.SruResponse;
import com.yurugee.lib.ndlapi.util.HttpUtil;

public class SruAccess {
	
	private static final String URL = "http://iss.ndl.go.jp/api/sru";
	
	
	public static SruResponse send(SruRequest request) throws UnsupportedOperationException, UnsupportedEncodingException, IOException, ConfigurationException{

		
		String result = HttpUtil.send(getUrlString(request));
		
		SruResponse res = new SruResponse(result);
		
		return res;
	}
	
	private static String getUrlString(SruRequest request) throws UnsupportedEncodingException, ConfigurationException{
		StringBuilder build = new StringBuilder();
		build.append(URL);
		build.append("?");
		build.append(request.getQueryString());
		
		return build.toString();
	}
	
	
	
	
	

}
