/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.ssh.struts.action;

import java.util.ArrayList;
import java.util.List;

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

import com.ssh.DAO.PandaDAO;
import com.ssh.POJO.Panda;
import com.ssh.common.Global;
import com.ssh.service.SearchPandaService;
import com.ssh.struts.form.SearchPandaForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-07-2018
 * 
 * XDoclet definition:
 * @struts.action path="/searchPanda" name="searchPandaForm" input="/jsp/searchPanda.jsp" scope="request" validate="true"
 */
@Controller("/searchPanda")
public class SearchPandaAction extends Action {
	/*
	 * Generated Methods
	 */
	private static final Logger log = LoggerFactory.getLogger(SearchPandaAction.class);							
	
	@Autowired
	@Qualifier("pandaService")
	private SearchPandaService searchPandaService;
	
	public SearchPandaService getSearchPandaService() {
		return searchPandaService;
	}

	public void setSearchPandaService(SearchPandaService searchPandaService) {
		log.info("set初始化创建searchPandaService");
		this.searchPandaService = searchPandaService;
	}



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
		SearchPandaForm searchPandaForm = (SearchPandaForm) form;// TODO Auto-generated method stub
		String zid = searchPandaForm.getZid();
		String name = searchPandaForm.getName().trim();
		String father = searchPandaForm.getFather().trim();
		String mother = searchPandaForm.getMother().trim();
		String ancestryId = searchPandaForm.getAnsestryId().trim();
		
		Panda panda = new Panda();
		panda.setZid(zid);
		panda.setName(name);
		panda.setFather(father);
		panda.setMother(mother);
		panda.setAncestryId(ancestryId);
		
		List<?> list = searchPandaService.searchPanda(panda);
		
		
		//list如果为空，显式的总是｛null} 而不是null ，所以造成判断必须加上list.get(0)==null 想办法解决
		if(list == null || list.size() == 0 || list.get(0) == null){
			request.setAttribute("notnull", "0");
		}else{
			request.setAttribute("notnull", "1");
			request.setAttribute("list", list);
		}
		
		return mapping.findForward("showPanda");
	}
}