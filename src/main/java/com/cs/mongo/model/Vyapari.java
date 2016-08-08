package com.cs.mongo.model;

import java.util.Date;

import javax.annotation.Nonnull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vyapari")
public class Vyapari implements User{
	

	//For Future Reference
	private String vypariid;
	private String name;
	private String fatherName; 
	@Nonnull@Id
	private String slipNumber;
	private String lotNumber;
	private String pickupPrice;
	private String dropPrice;
	private String noOfPacket;
	private String mobile;
	private String address;
	private Date createdDate;
	private Date lastModifiedDate;
	private String maintainedBy;
	private String session_id;
	private String totalWeight;
	private String typeOfPotato;
	private String profileType;
	
	
	
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	public String getVypariid() {
		return vypariid;
	}
	public void setVypariid(String vypariid) {
		this.vypariid = vypariid;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSlipNumber() {
		return slipNumber;
	}
	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
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
	public String getNoOfPacket() {
		return noOfPacket;
	}
	public void setNoOfPacket(String noOfPacket) {
		this.noOfPacket = noOfPacket;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getTypeOfPotato() {
		return typeOfPotato;
	}
	public void setTypeOfPotato(String typeOfPotato) {
		this.typeOfPotato = typeOfPotato;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	
}
