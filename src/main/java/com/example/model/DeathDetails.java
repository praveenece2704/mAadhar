package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class DeathDetails {
	
	@Id
	private String aadharNo;
	private Date dateOfDeath;
	private String fullName;
	public DeathDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeathDetails(String aadharNo, Date dateOfDeath, String fullName) {
		super();
		this.aadharNo = aadharNo;
		this.dateOfDeath = dateOfDeath;
		this.fullName = fullName;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		return "DeathDetails [aadharNo=" + aadharNo + ", dateOfDeath=" + dateOfDeath + ", fullName=" + fullName + "]";
	}
	
	

}
