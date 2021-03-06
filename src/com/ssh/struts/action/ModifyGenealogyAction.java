/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.ssh.struts.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.ssh.POJO.Genealogy;
import com.ssh.common.DateUtil;
import com.ssh.common.MessageAndFlag;
import com.ssh.service.GenealogyService;
import com.ssh.struts.form.ModifyGenealogyForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-21-2018
 * 
 * XDoclet definition:
 * @struts.action path="/modifyGenealogy" name="modifyGenealogyForm" input="/jsp/modifyGenealogy.jsp" scope="request" validate="true"
 * @struts.action-forward name="searchGenealogy" path="/jsp/searchGenealogy.jsp"
 */
@Controller("/modifyGenealogy")
public class ModifyGenealogyAction extends Action {
	/*
	 * Generated Methods
	 */

	Logger log = LoggerFactory.getLogger(ModifyGenealogyAction.class);
	
	@Autowired
	@Qualifier("genealogyService")
	private GenealogyService genealogyService;
	
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ModifyGenealogyForm genealogyForm = (ModifyGenealogyForm) form;// TODO Auto-generated method stub
		
		String zid = genealogyForm.getZid().trim();//modify和add不同之处
		String sheetName = genealogyForm.getSheetName().trim();
		String name = genealogyForm.getName().trim();
		Integer userId = 1;//以后从session护着application中获取
		Integer sex = genealogyForm.getSex();
		String father = genealogyForm.getFather().trim();
		String mother = genealogyForm.getMother().trim();
		String ancestryId = genealogyForm.getAncestryId().trim();
		String characteristi = genealogyForm.getCharacteristi().trim();
		String place = genealogyForm.getPlace().trim();
		String photo = genealogyForm.getPhoto().trim();
		String birthdayStr = genealogyForm.getBirthday().trim();
		String dateOfDeathStr = genealogyForm.getDateOfDeath().trim();
		String resume = genealogyForm.getResume();
		String remark = genealogyForm.getRemark();
		String other = genealogyForm.getOther();
		
		if(null == userId || userId == 0){
			userId = 1;//后续改为从session中获取
		}
		
		//字符串转事件格式
		
		Date birthday = null;
		try {
			birthday = DateUtil.stringToDateCommon(birthdayStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.info("birthdayStr("+birthdayStr+") 格式在DateUtil中不存在  输入正确格式yyyy-MM-dd" );
			e.printStackTrace();
		}
		Date dateOfDeath = null;
		try {
			dateOfDeath = DateUtil.stringToDateCommon(dateOfDeathStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.info("dateOfDeathStr("+dateOfDeathStr+") 格式在DateUtil中不存在  输入正确格式yyyy-MM-dd" );
			e.printStackTrace();
		}
		
		Genealogy genealogy = new Genealogy(zid,userId,name,sheetName,sex,birthday,father,mother,characteristi,place,ancestryId,photo,dateOfDeath,resume,remark,other);
		
		MessageAndFlag mf = genealogyService.saveOrUpdateGenealogy(genealogy);
		request.setAttribute("message", mf.getMessage());
		if(mf.isFlag()){
			
			log.info("modify Genealogy success");
			return mapping.findForward("searchGenealogy");
			
		}
		//以后改为本页
		log.info("modify Genealogy fail:"+mf.getMessage());
		return mapping.findForward("searchGenealogy");
	}
}