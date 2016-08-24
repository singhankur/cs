package com.cs.mongo.model;

import javax.annotation.Nonnull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adminConstants")
public class AdminConstants {

	private String storagePricePerPacket;
	private String pickupPricePerPacket;
	private String dropPricePerPacket;
	@Id @Nonnull
	private String year;
	private String createdDate;
	
	public String getStoragePricePerPacket() {
		return storagePricePerPacket;
	}
	public void setStoragePricePerPacket(String storagePricePerPacket) {
		this.storagePricePerPacket = storagePricePerPacket;
	}
	public String getPickupPricePerPacket() {
		return pickupPricePerPacket;
	}
	public void setPickupPricePerPacket(String pickupPricePerPacket) {
		this.pickupPricePerPacket = pickupPricePerPacket;
	}
	public String getDropPricePerPacket() {
		return dropPricePerPacket;
	}
	public void setDropPricePerPacket(String dropPricePerPacket) {
		this.dropPricePerPacket = dropPricePerPacket;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
