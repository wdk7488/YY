package com.ssh.POJO;

/**
 * Pandacharacter entity. @author MyEclipse Persistence Tools
 */

public class Pandacharacter implements java.io.Serializable {

	// Fields

	private PandacharacterId id;
	private Long number;
	private String remark;
	private String other;

	// Constructors

	/** default constructor */
	public Pandacharacter() {
	}

	/** minimal constructor */
	public Pandacharacter(PandacharacterId id) {
		this.id = id;
	}

	/** full constructor */
	public Pandacharacter(PandacharacterId id, Long number, String remark,
			String other) {
		this.id = id;
		this.number = number;
		this.remark = remark;
		this.other = other;
	}

	// Property accessors

	public PandacharacterId getId() {
		return this.id;
	}

	public void setId(PandacharacterId id) {
		this.id = id;
	}

	public Long getNumber() {
		return this.number;
	}

	public void setNumber(Long number) {
		this.number = number;
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

}