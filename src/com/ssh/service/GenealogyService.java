package com.ssh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssh.DAO.GenealogyDAO;
import com.ssh.POJO.Genealogy;
import com.ssh.POJO.Panda;
import com.ssh.common.Constants;
import com.ssh.common.MessageAndFlag;

@Service("genealogyService")
public class GenealogyService {
	
	private static final Logger log = LoggerFactory.getLogger(GenealogyService.class);
	
	@Autowired
	@Qualifier("genealogyDAO")
	private GenealogyDAO genealogyDAO;
	
	List<Map<String,Object>> genealogyList;
	
	public MessageAndFlag saveOrUpdateGenealogy(Genealogy genealogy){
		String zid = genealogy.getZid();
		String father = genealogy.getFather();
		String mother = genealogy.getMother();
		if(null == genealogy.getSex()){//不然会和数据库的sex is null 冲突
			log.info(" [saveOrUpdateGenealogy] sex is null,setSex = 2");
			genealogy.setSex(2);
		}
		//这里父母是否已经存在的判断和null比较难潘达un
		if(updateFlag(zid,father,mother)){
			
			Genealogy genealogyTemp = genealogyDAO.findByZid(zid);
			genealogyTemp.update(genealogy);
			genealogyDAO.attachDirty(genealogyTemp);
			log.info(" [saveOrUpdateGenealogy] update genealogy :"+genealogy);
			return new MessageAndFlag("更新zid("+zid+")成功",true);
		}
		else{
			//如果zid为空，找到不重复的zid
			zid = genealogyDAO.getNewZid();
			if(saveFlag(zid,father,mother)){
				genealogy.setZid(zid);
				genealogyDAO.save(genealogy);
				log.info(" [saveOrUpdateGenealogy] save genealogy zid("+zid+") :"+genealogy);
				return new MessageAndFlag("创建一个新zid("+zid+")，保存成功",true);
			}
		}
		log.info(" [saveOrUpdateGenealogy] saveFlag:zid数据异常/父母id不存在，先插入父母id");
		return new MessageAndFlag("zid/父母id不存在，请先插入父母信息",false);
	}
	
	
	public void saveGenealogyList(List list){
		if(null != list){
			for (Object object : list) {
				saveOrUpdateGenealogy((Genealogy)object);
			}
		}
	}
	
	public boolean deleteGenealogy(String zid){
		Genealogy genealogy = genealogyDAO.findByZid(zid);
		if(null != genealogy){
			genealogyDAO.delete(genealogy);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean findGenealogy(String zid){
		Genealogy genealogy = genealogyDAO.findByZid(zid);
		if(null != genealogy){
			genealogyDAO.findByZid(zid);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Genealogy> getGenealogys(Genealogy genealogy){
		List<Genealogy> list = new ArrayList();
		//这里为null可以考虑去掉，因为通过form表单的String类型为null会转为=""  
		if(genealogy.getZid() != null && !genealogy.getZid().isEmpty()){
			Genealogy genealogyTemp = genealogyDAO.findByZid(genealogy.getZid());
			//这里如果为空，list就是{null}  == null 和size()==0都判断不出来
			if(genealogyTemp == null){
				return list;
			}
			list.add(genealogyTemp);
		}
		//表单  一个用户可以创建多个表单记录不同族谱  需要和userId合用
		else if(genealogy.getSheetName() != null && !genealogy.getSheetName().isEmpty()){  
			list = genealogyDAO.findBySheetName(genealogy.getSheetName());
		}
		else if(genealogy.getFather() != null && !genealogy.getFather().isEmpty()){
			list = genealogyDAO.findByFather(genealogy.getFather());
		}
		else if(genealogy.getMother() != null && !genealogy.getMother().isEmpty()){
			list = genealogyDAO.findByMother(genealogy.getMother());
		}else if(genealogy.getUserId() != null ){
			list = genealogyDAO.findByUserId(genealogy.getUserId());
		}
		
		return list;
	}
	
	
	public List<Map<String,Object>> whosYourSonByLineAndSex(String id,int line,Integer sex,boolean firstFlag){
		
		
		//StringBuffer message = new StringBuffer();
		String zid = "";
		if(id == null || id.isEmpty() || "99999".equals(id))
		{
			log.info("id:"+id+"空或者是野猫99999");
			return genealogyList;
		}
		Genealogy genealogy = new Genealogy();
		
		if(firstFlag)
			genealogyList = new ArrayList();
		while(true){
			//雌雄判断 可否引用参数
			List<?> genealogyArray = null;
			if(sex == 0){
				genealogyArray = genealogyDAO.findByMother(id);
			}
			else if(sex == 1){
				genealogyArray = genealogyDAO.findByFather(id);
			}
			else{//
				genealogyArray = genealogyDAO.findByFather(id);
			}
			
			if(genealogyArray == null){
				log.info("性别未知");
				break;
			}
			else if(genealogyArray.size() == 0 && firstFlag){
				line = line - 1;
				log.info("无子");
				break;
			}
			else if(genealogyArray.size() == 0){
				//line = line - 1;
				log.info("后继无人");
				break;
			}
			
			firstFlag = false;
			log.info("谱系号"+id+"孩子数量"+genealogyArray.size());
			zid = id;
			line++;
			for(int i = genealogyArray.size()-1; i >= 0; i--){
				genealogy = (Genealogy) genealogyArray.get(i);
				
				id = genealogy.getZid();
				sex = genealogy.getSex();
				
				log.info(genealogy.getName()+"("+line+sex+zid+")"+"--");
				Map<String,Object> genealogyMap = new HashMap();
				genealogyMap.put("genealogy",genealogy);
				genealogyMap.put("line", line);
				genealogyMap.put("ancestry", zid);//father需要改为ancestry
				genealogyList.add(genealogyMap);
				//message.append(whosyoursonByLine(id,line));
				whosYourSonByLineAndSex(id,line,sex,firstFlag);
			}
			log.info("pandaArraysize:"+genealogyList.size());
			return genealogyList;
			
		}
		return genealogyList;
	}
	
	public List<Map<String, Object>> whosYourAncestryByLineAndSex(String zid,String sonId,int line, boolean firstFlag){
		if(null == zid || zid.isEmpty()){
			return null;
		}
		if(firstFlag){
			genealogyList = new ArrayList();
		}
		Genealogy genealogy = new Genealogy();
		genealogy = genealogyDAO.findByZid(zid);
		if(genealogy == null){
			return genealogyList;
		}else{
			Map<String,Object> genealogyMap = new HashMap();
			genealogyMap.put("genealogy",genealogy);
			genealogyMap.put("line", line);
			genealogyMap.put("son", sonId);
			genealogyList.add(genealogyMap);
		}
		
		String mother = genealogy.getMother();
		String father = genealogy.getFather();
		line++;
		if(null != mother && !mother.isEmpty() && !Constants.GENEALOGY.UNKOWN.getValue().equals(mother)){
			
			whosYourAncestryByLineAndSex(mother,zid,line,false);
			
		}
		if(null != father && !father.isEmpty() && !Constants.GENEALOGY.UNKOWN.getValue().equals(father)){
			
			whosYourAncestryByLineAndSex(father,zid,line,false);
			
		}
			
		return genealogyList;
	}
	
	private boolean updateFlag(String zid, String father, String mother){
		if(null != zid && !zid.isEmpty()){
			boolean zidIsExist =  genealogyDAO.zidIsExists(zid);
			boolean fatherIsExist;
			boolean motherIsExist;
			
			
			if(Constants.GENEALOGY.UNKOWN.getValue().equals(father)){
				fatherIsExist = true;
			}
			else if(null != father && !father.isEmpty()){
				fatherIsExist =  genealogyDAO.zidIsExists(father);
			}
			else{
				fatherIsExist = true;
			}
			
			
			if(Constants.GENEALOGY.UNKOWN.getValue().equals(mother)){
				motherIsExist = true;
			}
			else if(null != mother && !mother.isEmpty()){
				motherIsExist =  genealogyDAO.zidIsExists(mother);
			}
			else{
				motherIsExist = true;
			}
			return zidIsExist && fatherIsExist && motherIsExist;
		}
		
		
		return false;
	}
	
	private boolean saveFlag(String zid, String father, String mother){
		if(null != zid ){
			//boolean zidIsExist =  genealogyDAO.zidIsExists(zid);
			boolean fatherIsExist;
			boolean motherIsExist;
			
			//father为0准许，father为空也准许，father不为空和0的时候  表内必须有zid==father
			if(Constants.GENEALOGY.UNKOWN.getValue().equals(father)){
				fatherIsExist = true;
			}
			else if(null != father && !father.isEmpty()){
				fatherIsExist =  genealogyDAO.zidIsExists(father);
			}
			else{
				fatherIsExist = true;
			}
			
			//mother为0准许，mother为空也准许，mother不为空和0的时候  表内必须有zid==mother
			if(Constants.GENEALOGY.UNKOWN.getValue().equals(mother)){
				motherIsExist = true;
			}
			else if(null != mother && !mother.isEmpty()){
				motherIsExist =  genealogyDAO.zidIsExists(mother);
			}
			else{
				motherIsExist = true;
			}
			return  fatherIsExist && motherIsExist;
		}
		
		
		return false;
	}
}
