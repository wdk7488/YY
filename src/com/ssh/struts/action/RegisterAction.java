/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.ssh.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ssh.DAO.UserDAO;
import com.ssh.POJO.User;
import com.ssh.common.Global;
import com.ssh.struts.form.RegisterForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-01-2018
 * 
 * XDoclet definition:
 * @struts.action path="/register" name="registerForm" input="/jsp/register.jsp" scope="request" validate="true"
 */
public class RegisterAction extends Action {
	/*
	 * Generated Methods
	 */

	
	
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
		RegisterForm registerForm = (RegisterForm) form;// TODO Auto-generated method stub
		String username = registerForm.getUsername();
		String password = registerForm.getPassword();
		String nickname = registerForm.getNicename();
		
		UserDAO userDAO = (UserDAO)Global.getBean("UserDAO");
		List<?> list = userDAO.findByUsername(username);
		
		if(list.size() > 0){
            request.setAttribute("message", "该用户名已被注册");
            return mapping.findForward("registerFail");
        }
		
		 //插入新用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        userDAO.save(user);
        
        request.setAttribute("message", "注册成功,请登录:" + nickname);
        return mapping.findForward("registerSuccess");
    
	}
}