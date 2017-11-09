package com.watook.model;

public class Request {

	private int requestFrom;
	private int requestTo;
	private int statusCode;
	
	public Request() {
		super();
	}
	
	public int getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(int requestFrom) {
		this.requestFrom = requestFrom;
	}
	public int getRequestTo() {
		return requestTo;
	}
	public void setRequestTo(int requestTo) {
		this.requestTo = requestTo;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "Request [requestFrom=" + requestFrom + ", requestTo=" + requestTo + ", statusCode=" + statusCode + "]";
	}
	
	
}
