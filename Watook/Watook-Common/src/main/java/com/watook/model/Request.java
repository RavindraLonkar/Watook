package com.watook.model;

public class Request {

	private int requestId;
	private int requestBy;
	private int requestTo;
	private int reqstatus;
	private int rejectattemtcount;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(int requestBy) {
		this.requestBy = requestBy;
	}

	public int getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(int requestTo) {
		this.requestTo = requestTo;
	}

	public int getReqstatus() {
		return reqstatus;
	}

	public void setReqstatus(int reqstatus) {
		this.reqstatus = reqstatus;
	}

	public int getRejectattemtcount() {
		return rejectattemtcount;
	}

	public void setRejectattemtcount(int rejectattemtcount) {
		this.rejectattemtcount = rejectattemtcount;
	}

}
