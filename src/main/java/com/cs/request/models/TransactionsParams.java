package com.cs.request.models;

public class TransactionsParams {

	private String slipNumber;
	private String packetTaken;
	private String amountPaid;
	private String buyer;
	private String pickupPrice;
	private String dropPrice;
	
	
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
	public String getPickupPrice() {
		return pickupPrice;
	}
	public void setPickupPrice(String pickupPrice) {
		this.pickupPrice = pickupPrice;
	}
	public String getDropPrice() {
		return dropPrice;
	}
	public void setDropPrice(String dropPrice) {
		this.dropPrice = dropPrice;
	}
	
}
