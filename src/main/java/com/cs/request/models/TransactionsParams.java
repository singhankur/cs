package com.cs.request.models;

public class TransactionsParams {

	private String slipNumber;
	private String packetTaken;
	private String totalAmount;
	private String amountPaid;
	private String buyer;
	public String getSlipNumber() {
		return slipNumber;
	}
	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}
	public String getPacketTaken() {
		return packetTaken;
	}
	public void setPacketTaken(String packetTaken) {
		this.packetTaken = packetTaken;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
}
