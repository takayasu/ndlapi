package com.yurugee.lib.ndlapi.oaipmh;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.xml.sax.SAXException;

import com.yurugee.lib.ndlapi.oaipmh.request.OAIRequest;
import com.yurugee.lib.ndlapi.oaipmh.response.OAIResponse;


public class OAIPMHHarvestor {

	public OAIResponse execute(OAIRequest request) throws UnsupportedEncodingException, XPathExpressionException, ParserConfigurationException, SAXException, IOException{

		String response = getHttpContent(request.getRequestUrl(),request.getHost(),request.getUserId(),request.getPassword());

		return new OAIResponse(response);
	}

	private String getHttpContent(String url,String host,String userid,String password) throws IOException{

		CloseableHttpClient httpclient = HttpClients.createDefault();

		CredentialsProvider credsProvider = new BasicCredentialsProvider();

		HttpGet httpGet = new HttpGet(url);

		HttpClientContext localContext = HttpClientContext.create();


		if(StringUtils.isNotEmpty(userid)){
			UsernamePasswordCredentials creds = new UsernamePasswordCredentials(userid, password);
			credsProvider.setCredentials(
				    new AuthScope(host, 80), creds);

			localContext.setCredentialsProvider(credsProvider);
		}

		CloseableHttpResponse response1 = httpclient.execute(httpGet,localContext);

		HttpEntity entity1 = response1.getEntity();

		return EntityUtils.toString(entity1);
	}


}
