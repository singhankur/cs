package com.cs.request.models;

public class LedgerSearchParams {

	private String ledger_id;
	private String name;
	private String fathersName;
	private String address;
	private String mobile;
	private String session_id;
	
	
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getLedger_id() {
		return ledger_id;
	}
	public void setLedger_id(String ledger_id) {
		this.ledger_id = ledger_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFathersName() {
		return fathersName;
	}
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	
}
