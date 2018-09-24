package com.ssh.POJO;

/**
 * Characteristics entity. @author MyEclipse Persistence Tools
 */

public class Characteristics implements java.io.Serializable {

	// Fields

	private Integer characterId;
	private String characteristics;
	private String remark;
	private String other;

	// Constructors

	
	
	/** default constructor */
	public Characteristics() {
	}

	
	
	
	/** minimal constructor */
	public Characteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	/** full constructor */
	public Characteristics(String characteristics, String remark, String other) {
		this.characteristics = characteristics;
		this.remark = remark;
		this.other = other;
	}

	// Property accessors

	public Integer getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public String getCharacteristics() {
		return this.characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	public void update(Characteristics charact){
		this.characteristics = charact.getCharacteristics();
		this.remark = charact.getRemark();
		this.other = charact.getOther();
	}

}