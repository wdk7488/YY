package com.ssh.POJO;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * AbstractPanda entity provides the base persistence definition of the Panda
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPanda implements java.io.Serializable {

	// Fields

	private Integer pid;
	private String zid;
	private String name;
	private Integer sex;
	private Date birthday;
	private String father;
	private String mother;
	private String ancestryId;
	private String place;
	private Integer gid;
	private String photo;
	private Date dateOfDeath;
	private Integer generation;

	// Constructors

	/** default constructor */
	public AbstractPanda() {
	}

	/** minimal constructor */
	public AbstractPanda(String zid) {
		this.zid = zid;
	}

	/** full constructor */
	public AbstractPanda(String zid, String name, Integer sex, Date birthday,
			String father, String mother, String ancestryId, String place,
			Integer gid, String photo, Date dateOfDeath, Integer generation) {
		this.zid = zid;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.father = father;
		this.mother = mother;
		this.ancestryId = ancestryId;
		this.place = place;
		this.gid = gid;
		this.photo = photo;
		this.dateOfDeath = dateOfDeath;
		this.generation = generation;
	}
	
	public void update(Panda panda){
		this.zid = panda.getZid();
		this.name = panda.getName();
		this.sex = panda.getSex();
		this.birthday = panda.getBirthday();
		this.father = panda.getFather();
		this.mother = panda.getMother();
		this.ancestryId = panda.getAncestryId();
		this.place = panda.getPlace();
		this.gid = panda.getGid();
		this.photo = panda.getPhoto();
		this.dateOfDeath = panda.getDateOfDeath();
		this.generation = panda.getGeneration();
	}

	// Property accessors
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getZid() {
		return this.zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return this.mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getAncestryId() {
		return this.ancestryId;
	}

	public void setAncestryId(String ancestryId) {
		this.ancestryId = ancestryId;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDateOfDeath() {
		return this.dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public Integer getGeneration() {
		return this.generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

}