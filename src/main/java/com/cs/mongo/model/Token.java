package com.cs.mongo.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Token {

	Date expiredDate;
	Date createdDate;
	String key;
	String tokenValue;
	
	
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public String getAllowedIP() {
		// TODO Auto-generated method stub
		return "*";
	}
	
	
}
