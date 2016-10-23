package com.cs.mongo.model;

import javax.annotation.Nonnull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adminConstants")
public class AdminConstants {

	private String storagePricePerPacketKisan;
	private String dropPricePerPacketKisan;
	private String storagePricePerPacketVypari;
	private String dropPricePerPacketVypari;
	
	private String storagePricePerSmallPacketVypari;
	private String storagePricePerSmallPacketKisan;
	
	
	
	public String getStoragePricePerSmallPacketVypari() {
		return storagePricePerSmallPacketVypari;
	}
	public void setStoragePricePerSmallPacketVypari(String storagePricePerSmallPacketVypari) {
		this.storagePricePerSmallPacketVypari = storagePricePerSmallPacketVypari;
	}
	public String getStoragePricePerSmallPacketKisan() {
		return storagePricePerSmallPacketKisan;
	}
	public void setStoragePricePerSmallPacketKisan(String storagePricePerSmallPacketKisan) {
		this.storagePricePerSmallPacketKisan = storagePricePerSmallPacketKisan;
	}
	@Id @Nonnull
	private String year;
	private String createdDate;
	public String getStoragePricePerPacketKisan() {
		return storagePricePerPacketKisan;
	}
	public void setStoragePricePerPacketKisan(String storagePricePerPacketKisan) {
		this.storagePricePerPacketKisan = storagePricePerPacketKisan;
	}
	public String getDropPricePerPacketKisan() {
		return dropPricePerPacketKisan;
	}
	public void setDropPricePerPacketKisan(String dropPricePerPacketKisan) {
		this.dropPricePerPacketKisan = dropPricePerPacketKisan;
	}
	public String getStoragePricePerPacketVypari() {
		return storagePricePerPacketVypari;
	}
	public void setStoragePricePerPacketVypari(String storagePricePerPacketVypari) {
		this.storagePricePerPacketVypari = storagePricePerPacketVypari;
	}
	public String getDropPricePerPacketVypari() {
		return dropPricePerPacketVypari;
	}
	public void setDropPricePerPacketVypari(String dropPricePerPacketVypari) {
		this.dropPricePerPacketVypari = dropPricePerPacketVypari;
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
