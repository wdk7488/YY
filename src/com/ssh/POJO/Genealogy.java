package com.ssh.POJO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Genealogy entity. @author MyEclipse Persistence Tools
 */

public class Genealogy implements java.io.Serializable {

	// Fields

	private Integer id;
	private String zid;
	private Integer userId;
	private String name;
	private String sheetName;
	private Integer sex;
	private Date birthday;
	private String father;
	private String mother;
	private String characteristi;
	private String place;
	private String ancestryId;
	private String photo;
	private Date dateOfDeath;
	private String resume;
	private String remark;
	private String other;

	// Constructors

	/** default constructor */
	public Genealogy() {
	}

	
	
	
	/** minimal constructor */
	public Genealogy(String zid, Integer sex, String father, String mother) {
		this.zid = zid;
		this.sex = sex;
		this.father = father;
		this.mother = mother;
	}
	
	/** minimal constructor */
	public Genealogy(String zid, Integer userId,String sheetName, String name, String father, String mother) {
		this.zid = zid;
		this.userId = userId;
		this.sheetName = sheetName;
		this.name = name;
		this.father = father;
		this.mother = mother;
	}

	/** full constructor */
	public Genealogy(String zid, Integer userId, String name,
			String sheetName, Integer sex, Date birthday, String father,
			String mother, String characteristi, String place,
			String ancestryId, String photo, Date dateOfDeath, String resume,
			String remark, String other) {
		this.zid = zid;
		this.userId = userId;
		this.name = name;
		this.sheetName = sheetName;
		this.sex = sex;
		this.birthday = birthday;
		this.father = father;
		this.mother = mother;
		this.characteristi = characteristi;
		this.place = place;
		this.ancestryId = ancestryId;
		this.photo = photo;
		this.dateOfDeath = dateOfDeath;
		this.resume = resume;
		this.remark = remark;
		this.other = other;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZid() {
		return this.zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSheetName() {
		return this.sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
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
		
		
		if(null == birthday){//这里非常奇妙  在search查询sql中  因为数据被持久化  这里会修改所有为null的date为new Date()
			birthday = new Date(0);
		}
		
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

	public String getCharacteristi() {
		return this.characteristi;
	}

	public void setCharacteristi(String characteristi) {
		this.characteristi = characteristi;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAncestryId() {
		return this.ancestryId;
	}

	public void setAncestryId(String ancestryId) {
		this.ancestryId = ancestryId;
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
		/*
		if(null == dateOfDeath){
			dateOfDeath = new Date(0);
		}
		*/
		this.dateOfDeath = dateOfDeath;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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
	
	public void update(Genealogy genealogy){
		this.userId = genealogy.getUserId();
		this.name = genealogy.getName();
		this.sheetName = genealogy.getSheetName();
		this.sex = genealogy.getSex();
		this.birthday = genealogy.getBirthday();
		this.father = genealogy.getFather();
		this.mother = genealogy.getMother();
		this.characteristi = genealogy.getCharacteristi();
		this.place = genealogy.getPlace();
		this.ancestryId = genealogy.getAncestryId();
		this.photo = genealogy.getPhoto();
		this.dateOfDeath = genealogy.getDateOfDeath();
		this.resume = genealogy.getResume();
		this.remark = genealogy.getRemark();
		this.other = genealogy.getOther();
	}

}