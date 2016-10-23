package com.cs.mongo.model;

import java.util.Date;

import javax.annotation.Nonnull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transactions {
	
	@Nonnull @Id
	private Integer transactionID;
	
	private String slipNumber;
	private Integer packetTaken;
	private Double amountPaid;
	private String buyer;
	private String buyerID;
	private Date createdDate;
	private Date lastModifiedDate;
	private String maintainedBy;
	private Boolean isDeleted;
	private Double totalWeight;
	private boolean settled;
	private Double totalDropPrice;
	private Double totalWeightAmount;
	private boolean isTransactionComplete;
	private boolean haveSmallPacket;
	private Integer totalSmallPacket;
	private Double smallPacketAmount;
	private Double amount;
	private Double totalAmount;
		
	
	
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public boolean isHaveSmallPacket() {
		return haveSmallPacket;
	}
	public void setHaveSmallPacket(boolean haveSmallPacket) {
		this.haveSmallPacket = haveSmallPacket;
	}
	public Double getTotalDropPrice() {
		return totalDropPrice;
	}
	public void setTotalDropPrice(Double totalDropPrice) {
		this.totalDropPrice = totalDropPrice;
	}
	public Double getTotalWeightAmount() {
		return totalWeightAmount;
	}
	public void setTotalWeightAmount(Double totalWeightAmount) {
		this.totalWeightAmount = totalWeightAmount;
	}
	public boolean isTransactionComplete() {
		return isTransactionComplete;
	}
	public void setTransactionComplete(boolean isTransactionComplete) {
		this.isTransactionComplete = isTransactionComplete;
	}
	public Integer getTotalSmallPacket() {
		return totalSmallPacket;
	}
	public void setTotalSmallPacket(Integer totalSmallPacket) {
		this.totalSmallPacket = totalSmallPacket;
	}
	public Double getSmallPacketAmount() {
		return smallPacketAmount;
	}
	public void setSmallPacketAmount(Double smallPacketAmount) {
		this.smallPacketAmount = smallPacketAmount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public boolean isSettled() {
		return settled;
	}
	public void setSettled(boolean settled) {
		this.settled = settled;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getSlipNumber() {
		return slipNumber;
	}
	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}
	
	public Integer getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}
	public Integer getPacketTaken() {
		return packetTaken;
	}
	public void setPacketTaken(Integer packetTaken) {
		this.packetTaken = packetTaken;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(String buyerID) {
		this.buyerID = buyerID;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getMaintainedBy() {
		return maintainedBy;
	}
	public void setMaintainedBy(String maintainedBy) {
		this.maintainedBy = maintainedBy;
	}
	

	
	
}
