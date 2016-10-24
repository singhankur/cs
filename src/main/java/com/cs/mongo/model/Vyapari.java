package com.cs.mongo.model;



import javax.annotation.Nonnull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vyapari")
public class Vyapari implements User{
	

	//For Future Reference
	@Nonnull@Id
	private String vypariid;
	private String name;
	private String fatherName; 
	private String slipNumber;
	private String lotNumber;
	private String noOfPacket;
	private String mobile;
	private String address;
	private String createdDate;
	private String lastModifiedDate;
	private String maintainedBy;
	private String session_id;
	private String typeOfPotato;
	private String profileType;
	private boolean dropPricesettled;
	private boolean havePotato;
	
	
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public boolean isDropPricesettled() {
		return dropPricesettled;
	}
	public void setDropPricesettled(boolean dropPricesettled) {
		this.dropPricesettled = dropPricesettled;
	}
	public boolean isHavePotato() {
		return havePotato;
	}
	public void setHavePotato(boolean havePotato) {
		this.havePotato = havePotato;
	}
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
//	public String getPickupPrice() {
//		return pickupPrice;
//	}
//	public void setPickupPrice(String pickupPrice) {
//		this.pickupPrice = pickupPrice;
//	}
//	public String getDropPrice() {
//		return dropPrice;
//	}
//	public void setDropPrice(String dropPrice) {
//		this.dropPrice = dropPrice;
//	}
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
