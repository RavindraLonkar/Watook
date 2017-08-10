package com.watook.model;

public class FBErrorResponse {	 
    private String message;
    private String type;
    private String code;
    private String fbtrace_id;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFbtrace_id() {
		return fbtrace_id;
	}
	public void setFbtrace_id(String fbtrace_id) {
		this.fbtrace_id = fbtrace_id;
	}
        
}
