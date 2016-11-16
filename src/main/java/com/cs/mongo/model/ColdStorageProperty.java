package com.cs.mongo.model;

import javax.annotation.Nonnull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coldStorageProperty")
public class ColdStorageProperty {
	
	@Nonnull@Id
	private String yearorDate;
	private Integer packetIn;
	private String packetOf;
	private String takenBy;
	private Integer numberOfPacket;
	private Integer RemainingPacket;
	private String actionPerformed;
	
	
	
	public String getActionPerformed() {
		return actionPerformed;
	}
	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}
	public String getYearorDate() {
		return yearorDate;
	}
	public void setYearorDate(String yearorDate) {
		this.yearorDate = yearorDate;
	}
	public Integer getPacketIn() {
		return packetIn;
	}
	public void setPacketIn(Integer packetIn) {
		this.packetIn = packetIn;
	}
	public String getPacketOf() {
		return packetOf;
	}
	public void setPacketOf(String packetOf) {
		this.packetOf = packetOf;
	}
	public String getTakenBy() {
		return takenBy;
	}
	public void setTakenBy(String takenBy) {
		this.takenBy = takenBy;
	}
	public Integer getNumberOfPacket() {
		return numberOfPacket;
	}
	public void setNumberOfPacket(Integer numberOfPacket) {
		this.numberOfPacket = numberOfPacket;
	}
	public Integer getRemainingPacket() {
		return RemainingPacket;
	}
	public void setRemainingPacket(Integer remainingPacket) {
		RemainingPacket = remainingPacket;
	}
	

}
