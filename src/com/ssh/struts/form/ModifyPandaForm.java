/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.ssh.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.ssh.POJO.Panda;
import com.ssh.common.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 09-07-2018
 * 
 * XDoclet definition:
 * @struts.form name="modifyPandaForm"
 */
public class ModifyPandaForm extends ActionForm {
	/*
	 * Generated fields
	 */
	
	/** birthday property */
	private String birthday;

	/** sex property */
	private String sex;

	/** mother property */
	private String mother;

	/** zid property */
	private String zid;

	/** ancestryId property */
	private String ancestryId;

	/** name property */
	private String name;

	/** pid property */
	private String pid;

	/** dateOfDeath property */
	private String dateOfDeath;

	/** place property */
	private String place;

	/** father property */
	private String father;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors error = new ActionErrors();
		if(name == null || name.isEmpty()){
			error.add("name",new ActionMessage("panda.modify.no.name.error"));
			return error;
		}
		if(sex == null || sex.isEmpty()){
			error.add("sex",new ActionMessage("panda.modify.no.sex.error"));
			return error;
		}
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		//设置默认radio值  this.sex=“1”;
		
	}

	/** 
	 * Returns the birthday.
	 * @return Date
	 */
	public String getBirthday() {
		return birthday;
	}

	/** 
	 * Set the birthday.
	 * @param birthday The birthday to set
	 */
	public void setBirthday(String birthday) {
		
		this.birthday = birthday;
	}

	/** 
	 * Returns the sex.
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/** 
	 * Set the sex.
	 * @param sex The sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/** 
	 * Returns the mother.
	 * @return String
	 */
	public String getMother() {
		return mother;
	}

	/** 
	 * Set the mother.
	 * @param mother The mother to set
	 */
	public void setMother(String mother) {
		this.mother = mother;
	}

	/** 
	 * Returns the zid.
	 * @return String
	 */
	public String getZid() {
		return zid;
	}

	/** 
	 * Set the zid.
	 * @param zid The zid to set
	 */
	public void setZid(String zid) {
		this.zid = zid;
	}

	/** 
	 * Returns the ancestryId.
	 * @return String
	 */
	public String getAncestryId() {
		return ancestryId;
	}

	/** 
	 * Set the ancestryId.
	 * @param ancestryId The ancestryId to set
	 */
	public void setAncestryId(String ancestryId) {
		this.ancestryId = ancestryId;
	}

	/** 
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/** 
	 * Set the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * Returns the pid.
	 * @return String
	 */
	public String getPid() {
		return pid;
	}

	/** 
	 * Set the pid.
	 * @param pid The pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/** 
	 * Returns the dateOfDeath.
	 * @return Date
	 */
	public String getDateOfDeath() {
		return dateOfDeath;
	}

	/** 
	 * Set the dateOfDeath.
	 * @param dateOfDeath The dateOfDeath to set
	 */
	public void setDateOfDeath(String dateOfDeath) {
		
		this.dateOfDeath = dateOfDeath;
	}

	/** 
	 * Returns the place.
	 * @return String
	 */
	public String getPlace() {
		return place;
	}

	/** 
	 * Set the place.
	 * @param place The place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/** 
	 * Returns the father.
	 * @return String
	 */
	public String getFather() {
		return father;
	}

	/** 
	 * Set the father.
	 * @param father The father to set
	 */
	public void setFather(String father) {
		this.father = father;
	}
	
	public Panda getPanda(){
		
		Date birthdayDate = null;
		try {
			birthdayDate = DateUtil.stringToDateCommon(this.birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dateOfDeathDate = null;
		try {
			dateOfDeathDate = DateUtil.stringToDateCommon(this.dateOfDeath);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Panda panda = new Panda(zid, name, Integer.valueOf(sex), birthdayDate, father, mother, ancestryId, place, null, "", dateOfDeathDate,null);
		
		return panda;
	}
	
	public void update(Panda panda){
		this.zid = panda.getZid();
		this.name = panda.getName();
		this.sex = panda.getSex()+"";
		this.birthday = DateUtil.dateToString(panda.getBirthday(), DateUtil.YYYY_MM_DD);
		this.dateOfDeath = DateUtil.dateToString(panda.getDateOfDeath(), DateUtil.YYYY_MM_DD);
		
		this.father = panda.getFather();
		this.mother = panda.getMother();
		this.ancestryId = panda.getAncestryId();
		this.place = panda.getPlace();
	}
}