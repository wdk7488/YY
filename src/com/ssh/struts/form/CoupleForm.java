/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.ssh.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-22-2018
 * 
 * XDoclet definition:
 * @struts.form name="coupleForm"
 */
public class CoupleForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** id property */
	private String id;

	/** otherName property */
	private String otherName;

	/** name property */
	private String name;

	/** otherId property */
	private String otherId;

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
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the id.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/** 
	 * Set the id.
	 * @param id The id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * Returns the otherName.
	 * @return String
	 */
	public String getOtherName() {
		return otherName;
	}

	/** 
	 * Set the otherName.
	 * @param otherName The otherName to set
	 */
	public void setOtherName(String otherName) {
		this.otherName = otherName;
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
	 * Returns the otherId.
	 * @return String
	 */
	public String getOtherId() {
		return otherId;
	}

	/** 
	 * Set the otherId.
	 * @param otherId The otherId to set
	 */
	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
}