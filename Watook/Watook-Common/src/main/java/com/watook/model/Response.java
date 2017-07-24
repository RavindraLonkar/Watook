package com.watook.model;

public class Response {
    private String status;
    private String reasonCode;
    private Object data;
	private String token;
     
    public Response(){
         
    }
     
    public Response(String status, Object data,String resonCode,String token){
        this.status = status;
        this.data = data;
        this.reasonCode=resonCode;
        this.token=token;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public Object getData() {
        return data;
    }
 
    public void setData(Object data) {
        this.data = data;
    }

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
