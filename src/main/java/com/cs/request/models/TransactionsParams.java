package com.cs.request.models;

public class TransactionsParams {

	private String slipNumber;
	private String packetTaken;
	private String amountPaid;
	private String buyer;
	private String seller;
	private boolean smallPacket;
	private double totalWeight;
	private String totalSmallPaket;
	private double totalWeightAmount;
	private String fromWhichKisanSlipNumber;
	private String session_id;
	
	
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public boolean isSmallPacket() {
		return smallPacket;
	}
	public void setSmallPacket(boolean smallPacket) {
		this.smallPacket = smallPacket;
	}
	public String getTotalSmallPaket() {
		return totalSmallPaket;
	}
	public void setTotalSmallPaket(String totalSmallPaket) {
		this.totalSmallPaket = totalSmallPaket;
	}
	public double getTotalWeightAmount() {
		return totalWeightAmount;
	}
	public void setTotalWeightAmount(double totalWeightAmount) {
		this.totalWeightAmount = totalWeightAmount;
	}
	public String getFromWhichKisanSlipNumber() {
		return fromWhichKisanSlipNumber;
	}
	public void setFromWhichKisanSlipNumber(String fromWhichKisanSlipNumber) {
		this.fromWhichKisanSlipNumber = fromWhichKisanSlipNumber;
	}
	public double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
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

	
}
