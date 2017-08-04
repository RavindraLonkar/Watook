package com.watook.model;

public class Response {
    private String status;
    private String reasonCode;
    private Object data;
     
    public Response(){
         
    }
     
    public Response(String status, Object data,String resonCode){
        this.status = status;
        this.data = data;
        this.reasonCode=resonCode;       
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
	
}
