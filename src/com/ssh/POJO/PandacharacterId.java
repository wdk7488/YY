package com.ssh.POJO;

/**
 * PandacharacterId entity. @author MyEclipse Persistence Tools
 */

public class PandacharacterId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer characterId;

	// Constructors

	/** default constructor */
	public PandacharacterId() {
	}

	/** full constructor */
	public PandacharacterId(Integer id, Integer characterId) {
		this.id = id;
		this.characterId = characterId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PandacharacterId))
			return false;
		PandacharacterId castOther = (PandacharacterId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getCharacterId() == castOther.getCharacterId()) || (this
						.getCharacterId() != null
						&& castOther.getCharacterId() != null && this
						.getCharacterId().equals(castOther.getCharacterId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getCharacterId() == null ? 0 : this.getCharacterId()
						.hashCode());
		return result;
	}

}