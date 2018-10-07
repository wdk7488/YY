package com.ssh.struts.form;

import java.util.Date;

import com.ssh.POJO.Genealogy;
import com.ssh.POJO.Panda;
import com.ssh.common.DateUtil;

public class PandaForm {
	private Integer pid;
	private String zid;
	private String name;
	private Integer sex;
	private String birthday;
	private String father;
	private String mother;
	private String ancestryId;
	private String place;
	private Integer gid;
	private String photo;
	private String dateOfDeath;
	private Integer generation;

	// Constructors

	/** default constructor */
	public PandaForm() {
	}

	/** minimal constructor */
	public PandaForm(String zid) {
		this.zid = zid;
	}

	/** full constructor */
	public PandaForm(String zid, String name, Integer sex, String birthday,
			String father, String mother, String ancestryId, String place,
			Integer gid, String photo, String dateOfDeath, Integer generation) {
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
	
	
	public PandaForm(Panda panda){
		
		this.zid = panda.getZid();
		this.name = panda.getName();
		this.sex = panda.getSex();
		String birthdayStr =DateUtil.dateToString(panda.getBirthday(), DateUtil.YYYY_MM_DD);
		this.birthday = birthdayStr;
		this.gid = panda.getGid();
		this.father = panda.getFather();
		this.mother = panda.getMother();
		this.place = panda.getPlace();
		this.ancestryId = panda.getAncestryId();
		this.photo = panda.getPhoto();
		
		String dateOfDeathStr = DateUtil.dateToString(panda.getDateOfDeath(), DateUtil.YYYY_MM_DD);
		this.dateOfDeath = dateOfDeathStr;
		
		this.generation = panda.getGeneration();
		
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getZid() {
		return zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getAncestryId() {
		return ancestryId;
	}

	public void setAncestryId(String ancestryId) {
		this.ancestryId = ancestryId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}
	
	public void pandaToPandaForm(Panda panda){
		this.zid = panda.getZid();
		this.name = panda.getName();
		this.sex = panda.getSex();
		String birthdayStr =DateUtil.dateToString(panda.getBirthday(), DateUtil.YYYY_MM_DD);
		this.birthday = birthdayStr;
		this.gid = panda.getGid();
		this.father = panda.getFather();
		this.mother = panda.getMother();
		this.place = panda.getPlace();
		this.ancestryId = panda.getAncestryId();
		this.photo = panda.getPhoto();
		
		String dateOfDeathStr = DateUtil.dateToString(panda.getDateOfDeath(), DateUtil.YYYY_MM_DD);
		this.dateOfDeath = dateOfDeathStr;
		this.generation = panda.getGeneration();
	}
}
