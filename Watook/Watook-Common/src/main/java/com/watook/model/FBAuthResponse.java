package com.watook.model;

public class FBAuthResponse {
	private String category;
	private String link;
	private String name;
	private String id;
	private Boolean isAuthSuccess;
	
	private FBErrorResponse fBErrorResponse;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public FBErrorResponse getfBErrorResponse() {
		return fBErrorResponse;
	}
	public void setfBErrorResponse(FBErrorResponse fBErrorResponse) {
		this.fBErrorResponse = fBErrorResponse;
	}
	public Boolean getIsAuthSuccess() {
		return isAuthSuccess;
	}
	public void setIsAuthSuccess(Boolean isAuthSuccess) {
		this.isAuthSuccess = isAuthSuccess;
	}
	
}
