package com.ssh.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

@Controller("/index")
public class YYAction extends Action {
	
	@Autowired
	@Qualifier("pandaService")
	private SearchPandaService searchPandaService;
	private static final Logger log = LoggerFactory.getLogger(YYAction.class);
	private static int i = 0;
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String more = request.getParameter("more");
		String display = request.getParameter("display");
		String ancestryId = request.getParameter("ancestryId");
		List list = new ArrayList(); 
		List list2 = new ArrayList();
		if(!"1".equals(more)){
			i = 0;
		}
		if("ANCESTRY".equals(display) && !ancestryId.isEmpty()){
			log.info("getlist by ANCESTRY");
			Panda panda = searchPandaService.findPandaByZid(ancestryId);
			if(panda != null){

				Map pandaMap = new HashMap();
				pandaMap.put("panda",panda);
				pandaMap.put("line", 1);
				pandaMap.put("father", panda.getMother());//这里key应该改为nextNode或者ancestryId
				
				list2.add(pandaMap);
				list = searchPandaService.whosyoursonByLineAndSex(ancestryId,1,panda.getSex() == null?2:panda.getSex(),true);
				if(list != null ){
					for (Object object : list) {
						list2.add(object);
					}
				}
				
				
				
			}
			
			
			
		}
		else if("SON".equals(display) && !ancestryId.isEmpty()){
			log.info("getlist by SON");
			int line = 0;
			list = searchPandaService.whosyourAncestryByLineAndSex(ancestryId, "0", 1, true);
			if(null != list2 && list.size() >1){
				//为了不加个js方法，这里需要先取出line，然后line全部取反
				for (Object object : list) {
					if((Integer)((Map)object).get("line") > line){
						line = (Integer)((Map)object).get("line");
					}
				}
				
				line += 1;
				for(Object object : list){
					
					int li = (Integer)((Map)object).get("line");
					if(line <= 5){
						((Map)object).put("line", line-li);
						list2.add(object);
					}
					else if(line > 5){
						//转类型都这么麻烦 自动解包  太复杂了  考虑用泛型
						//倒叙数4个加入list2列表
						if(li < 5){
							((Map)object).put("line", 5-li);
							list2.add(object);
						}
						
						
					}
					
					
					
				}
			}
		
		
		}
		else{
			log.info("getlist by test");
			list = searchPandaService.findAll();
			for (int j = 0; j < 5; j++) {
				
				
				
				list2.add(list.get(i));
				i++;
			}
		}
		
		
		
		//JSONObject json = JSONObject.fromObject(list2);
		JSONArray jsonA = JSONArray.fromObject(list2);
		log.info(jsonA.toString());
		
		response.getWriter().write(jsonA.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	

}
