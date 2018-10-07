package com.ssh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssh.DAO.PandaDAO;
import com.ssh.POJO.Panda;
import com.ssh.common.Constants;
import com.ssh.common.Global;

@Service("pandaService")
public class SearchPandaService {
	
	@Autowired
	@Qualifier("pandaDAO")
	private PandaDAO pandaDAO;
	/** 用注解替代bean中的property后可以不用setter注入
	public void setPandaDAO(PandaDAO pandaDAO) {
		log.debug("是否初始化创建pandaDAO");
		this.pandaDAO = pandaDAO;
	}
	*/
	
	private static final Logger log = LoggerFactory.getLogger(SearchPandaService.class);
	private List pandaArray;
	
	
	
	
	public List<?> searchPanda(Panda panda){
		if(pandaDAO == null){
			pandaDAO = (PandaDAO) Global.getBean("pandaDAO");
		}
		
		List<Panda> list = new ArrayList();
		if(!panda.getZid().isEmpty()){
			panda = pandaDAO.findByZid(panda.getZid());
			list.add(panda);
		}else if(!panda.getName().isEmpty()){
			list = pandaDAO.findByName(panda.getName());
		}else if(!panda.getFather().isEmpty()){
			list = pandaDAO.findByFather(panda.getFather());
		}else if(!panda.getMother().isEmpty()){
			list = pandaDAO.findByMother(panda.getMother());
		}else if(!panda.getAncestryId().isEmpty()){
			list = pandaDAO.findByAncestryId(panda.getAncestryId());
		}else{
			list = null;
		}
		return list;
	}
	
	public Panda findPandaByZid(String zid){
		if(zid == null || zid.isEmpty()){
			
			return null;
		}else{
			return pandaDAO.findByZid(zid);
		}
	}
	
	public boolean modifyPanda(Panda panda){
		if(null == panda || null == panda.getZid()){
			log.info("modifyPanda中panda为空，无操作");
			return false;
		}else{
			Panda tempPanda = pandaDAO.findByZid(panda.getZid());
			tempPanda.update(panda);
			pandaDAO.attachDirty(panda);
		}
		return true;
		
	}
	
	public boolean deletePanda(String zid){
		if(null == zid){
			log.info("deletePanda中zid为空，无操作");
			return false;
		}else{
			Panda panda = pandaDAO.findByZid(zid);
			pandaDAO.delete(panda);
		}
		
		return true;
	}
	
	public List findAll(){
		return pandaDAO.findAll();
	}
	
	public List whosyoursonByLineAndSex(String id,int line,Integer sex,boolean firstFlag){
		
		//StringBuffer message = new StringBuffer();
		String zid = "";
		if(id == null || id.isEmpty() || "99999".equals(id))
		{
			log.info("id:"+id+"空或者是野猫99999");
			return pandaArray;
		}
		Panda panda = new Panda();
		
		if(firstFlag)
			pandaArray = new ArrayList();
		while(true){
			//雌雄判断 可否引用参数
			List<?> pandaList = null;
			if(null != sex && sex == 0){
				pandaList = pandaDAO.findByMother(id);
			}
			else if(null != sex && sex == 1){
				pandaList = pandaDAO.findByFather(id);
			}
			else{
				pandaList = pandaDAO.findByFather(id);
			}
			
			if(pandaList == null){
				log.info("性别未知");
				break;
			}
			else if(pandaList.size() == 0 && firstFlag){
				line = line - 1;
				log.info("无子");
				break;
			}
			else if(pandaList.size() == 0){
				//line = line - 1;
				log.info("后继无人");
				break;
			}
			
			firstFlag = false;
			log.info("谱系号"+id+"熊猫孩子数量"+pandaList.size());
			zid = id;
			line++;
			for(int i = pandaList.size()-1; i >= 0; i--){
				panda = (Panda) pandaList.get(i);
				
				id = panda.getZid();
				sex = panda.getSex();
				
				//log.info("|");
				log.info(panda.getName()+"("+line+sex+zid+")"+"--");
				Map pandaMap = new HashMap();
				pandaMap.put("panda",panda);
				pandaMap.put("line", line);
				pandaMap.put("ancestry", zid);//father需要改为ancestry
				pandaArray.add(pandaMap);
				//message.append(whosyoursonByLine(id,line));
				 whosyoursonByLineAndSex(id,line,sex,firstFlag);
			}
			log.info("pandaArraysize:"+pandaArray.size());
			return pandaArray;
			
		}
		return null;
	}
	
	public List whosyourAncestryByLineAndSex(String zid,String sonId,int line, boolean firstFlag){
		if(null == zid || zid.isEmpty()){
			return null;
		}
		if(firstFlag){
			pandaArray = new ArrayList();
		}
		Panda panda = new Panda();
		panda = pandaDAO.findByZid(zid);
		if(panda == null){
			return pandaArray;
		}else{
			Map pandaMap = new HashMap();
			pandaMap.put("panda",panda);
			pandaMap.put("line", line);
			pandaMap.put("ancestry", "");//如果没有 js里不好判断查询祖先还是儿女
			pandaMap.put("son", sonId);
			pandaArray.add(pandaMap);
		}
		
		String mother = panda.getMother();
		String father = panda.getFather();
		line++;
		if(null != mother && !mother.isEmpty() && !Constants.PANDA.WILD.getValue().equals(mother)){
			
			whosyourAncestryByLineAndSex(mother,zid,line,false);
			
		}
		if(null != father && !father.isEmpty() && !Constants.PANDA.WILD.getValue().equals(father)){
			
			whosyourAncestryByLineAndSex(father,zid,line,false);
			
		}
			
		return pandaArray;
	}
}
