package com.yurugee.lib.ndlapi.oaipmh.request;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class OAIRequest {

	private static String[] VERBS = { "GetRecord", "Identify",
			"ListIdentifiers", "ListMetadataFormats", "ListRecords", "ListSets" };
	private static final List<String> verbList = Arrays.asList(VERBS);

	private String baseUrl;
	private String host;

	private String verb;
	private String from;
	private String until;
	private String metadataPrefix;
	private String set;
	private String resumptionToken;

	private String userId;

	private String password;


	public OAIRequest(String url) {
		baseUrl = url;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUntil() {
		return until;
	}

	public void setUntil(String until) {
		this.until = until;
	}

	public String getMetadataPrefix() {
		return metadataPrefix;
	}

	public void setMetadataPrefix(String metadataPrefix) {
		this.metadataPrefix = metadataPrefix;
	}

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public String getResumptionToken() {
		return resumptionToken;
	}

	public void setResumptionToken(String resumptionToken) {
		this.resumptionToken = resumptionToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRequestUrl() {

		if (StringUtils.isNotEmpty(resumptionToken)) {
			return getRequestUrlWithResumptionToken();
		} else {
			return getRequestUrlWithoutResumptionToken();
		}

	}

	private String getRequestUrlWithResumptionToken() {

		if (!checkVerb()) {
			throw new RuntimeException("Parameter is wrong:" + toString());
		}

		StringBuilder builder = new StringBuilder();
		builder.append(baseUrl);
		builder.append("?verb=");
		builder.append(verb);
		builder.append("&resumptionToken=");
		builder.append(resumptionToken);

		return builder.toString();

	}

	private String getRequestUrlWithoutResumptionToken() {

		if (!checkParams()) {
			throw new RuntimeException("Parameter is wrong:" + toString());
		}

		StringBuilder builder = new StringBuilder();
		builder.append(baseUrl);

		builder.append("?verb=");
		builder.append(verb);

		builder.append("&metadataPrefix=");
		builder.append(metadataPrefix);

		builder.append("&from=");
		builder.append(from);

		if (StringUtils.isNotEmpty(until)) {
			builder.append("&until=");
			builder.append(until);
		}

		if (StringUtils.isNotEmpty(set)) {
			builder.append("&set=");
			builder.append(set);
		}

		return builder.toString();
	}

	private boolean checkParams() {
		if (StringUtils.isEmpty(verb) || StringUtils.isEmpty(metadataPrefix)
				|| StringUtils.isEmpty(from)) {
			return false;
		}

		return checkVerb();
	}

	private boolean checkVerb() {
		if (!verbList.contains(verb)) {
			return false;
		}

		return true;
	}

	private boolean isResumptionTokenUse() {
		return StringUtils.isNotEmpty(resumptionToken);
	}

	@Override
	public String toString() {
		return "OAIRequest [baseUrl=" + baseUrl + ", verb=" + verb + ", from="
				+ from + ", until=" + until + ", metadataPrefix="
				+ metadataPrefix + ", set=" + set + ", resumptionToken="
				+ resumptionToken + "]";
	}

}
