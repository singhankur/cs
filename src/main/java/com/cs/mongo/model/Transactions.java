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
	private Double pickupPrice;
	private Double dropPrice;
	private Double totalWeight;
	private boolean settled;
		
	
	
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
//	public Double getTotalAmount() {
//		return totalAmount;
//	}
//	public void setTotalAmount(Double totalAmount) {
//		this.totalAmount = totalAmount;
//	}
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
	public Double getPickupPrice() {
		return pickupPrice;
	}
	public void setPickupPrice(Double pickupPrice) {
		this.pickupPrice = pickupPrice;
	}
	public Double getDropPrice() {
		return dropPrice;
	}
	public void setDropPrice(Double dropPrice) {
		this.dropPrice = dropPrice;
	}
	
	
	
}
