package com.ssh.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.ssh.service.SearchPandaService;

@Controller("/deletePanda")
public class DeletePandaAction extends Action {
	
	@Autowired
	@Qualifier("pandaService")
	SearchPandaService searchPandaService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zid = request.getParameter("zid");
		boolean deleteResult = searchPandaService.deletePanda(zid);
		ActionMessages messages = new ActionMessages();
		ActionMessage message = null;
		
		if(deleteResult){
			message = new ActionMessage("panda.delete.success");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			request.setAttribute("message", messages);
			return mapping.findForward("showPanda");
		}
		message = new ActionMessage("panda.delete.failure");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		request.setAttribute("message", messages);
		return mapping.findForward("showPanda");
	}
}
