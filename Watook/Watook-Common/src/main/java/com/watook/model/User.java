package com.watook.model;

import com.google.gson.annotations.SerializedName;

public class User {
	@SerializedName("userid") private String userID;
	@SerializedName("fbid") private String fbID;
	@SerializedName("lastname") private String lastName;
	@SerializedName("firstname") private String firstName;
	@SerializedName("dob") private String dOB;
	@SerializedName("age") private String age;
	@SerializedName("genderid") private String genderID;
	@SerializedName("contactmobile") private String contactMobile;
	@SerializedName("contactmobile2") private String contactMobile2;
	@SerializedName("emailid") private String emailID;
	@SerializedName("deviceid") private String deviceID;
	@SerializedName("isactive") private String isActive;
	@SerializedName("createdby") private String createdBy;
	@SerializedName("createddate") private String createdDate;
	@SerializedName("lastmodifiedby") private String lastModifiedBy;
	@SerializedName("lastmodifieddate") private String lastModifiedDate;
	@SerializedName("encryptkey") private String encryptKey;
	@SerializedName("fbToken") private String fbToken;
	@SerializedName("applicationId") private String applicationId;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFbID() {
		return fbID;
	}
	public void setFbID(String fbID) {
		this.fbID = fbID;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getdOB() {
		return dOB;
	}
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGenderID() {
		return genderID;
	}
	public void setGenderID(String genderID) {
		this.genderID = genderID;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getContactMobile2() {
		return contactMobile2;
	}
	public void setContactMobile2(String contactMobile2) {
		this.contactMobile2 = contactMobile2;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getEncryptKey() {
		return encryptKey;
	}
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}
	public String getFbToken() {
		return fbToken;
	}
	public void setFbToken(String fbToken) {
		this.fbToken = fbToken;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
}


