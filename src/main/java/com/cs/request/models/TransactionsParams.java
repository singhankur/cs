package com.cs.request.models;

public class TransactionsParams {

	private String slipNumber;
	private String packetTaken;
	private String amountPaid;
	private String buyer;
	private String pickupPrice;
	private String dropPrice;
	private boolean settled;
	private double totalWeight;
	
	
	public double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public boolean isSettled() {
		return settled;
	}
	public void setSettled(boolean settled) {
		this.settled = settled;
	}
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
