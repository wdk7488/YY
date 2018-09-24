package com.ssh.POJO;

import java.util.Date;

/**
 * Panda entity. @author MyEclipse Persistence Tools
 */
public class Panda extends AbstractPanda implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Panda() {
	}

	/** minimal constructor */
	public Panda(String zid) {
		super(zid);
	}

	/** full constructor */
	public Panda(String zid, String name, Integer sex, Date birthday,
			String father, String mother, String ancestryId, String place,
			Integer gid, String photo, Date dateOfDeath, Integer generation) {
		super(zid, name, sex, birthday, father, mother, ancestryId, place, gid,
				photo, dateOfDeath, generation);
	}
	
	

}
