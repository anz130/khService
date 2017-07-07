package com.zhy.zhylib.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyong
 */
public class Entry {

	private String key;

	private Map<String, String> headers = new HashMap<String, String>();

	private byte[] body;

	private long date;

	private long duration;

	public Entry() {

	}
	public Entry(String key, byte[] body) {
		this.key = key;
		this.body = body;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public boolean isExpired() {
		return (date + duration) < System.currentTimeMillis();
	}

	public void addHeader(String name, String value) {
		headers.put(name, value);
	}

	public void removeHeader(String name) {
		headers.remove(name);
	}

	public String getHeader(String name) {
		return headers.get(name);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public boolean containsHeader(String name) {
		return headers.containsKey(name);
	}

	public void clearHeaders() {
		headers.clear();
	}
}
