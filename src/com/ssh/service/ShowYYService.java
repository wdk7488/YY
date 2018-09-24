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

import com.ssh.DAO.PandaDAO;
import com.ssh.POJO.Panda;
import com.ssh.common.Constants;
import com.ssh.common.Constants.RELATION;
import com.ssh.common.Global;

@Service("showYYService")
public class ShowYYService {
	PandaDAO pandaDAO;//如果出现bug  改回= (PandaDAO)Global.getDAO("pandaDAO")
	
	@Autowired
	@Qualifier("pandaDAO")
	public void setPandaDAO(PandaDAO pandaDAO) {
		this.pandaDAO = pandaDAO;
	}

	private static final Logger log = LoggerFactory.getLogger(PandaDAO.class);
	List<Panda> list = new ArrayList();
	
	
	
	public List<Panda> whosYourSon(Panda panda){
		//通过panda对象中不为空的条件找出这只猫
		String zid = panda.getZid();
		Integer sex = panda.getSex();
		if(null == zid || zid.length() == 0 || sex == null){
			return null;
		}
		
		//根据性别查找儿女 
		if(Constants.SEX.MAN.getValue().equals(sex)){
			list = pandaDAO.findByFather(zid);
		}
		else if(Constants.SEX.WOMAN.getValue().equals(sex)){
			list = pandaDAO.findByMother(zid);
		}
		else{
			return null;
		}
		return list;
	}
	
	//好像没有必要 
	public List<?> whosYourDaddy(Panda panda){
		String zid = findIdByPanda(panda);
		
		panda = pandaDAO.findByZid(zid);
		/**
		if(list == null){
			return null;
		}
		if(list.size() != 1){
			//list不等于1 代表通过名字或者生日查找出的熊猫有多个，需要选择
			return null;
		}
		*/
		//panda = (Panda) list.get(0);
		String fatherId = panda.getFather();
		panda =  pandaDAO.findByZid(fatherId);
		return list;
	}
	
	public List<?> whosYourMother(Panda panda){
		String zid = findIdByPanda(panda);
		/**
		list = pandaDAO.findByZid(zid);
		
		if(list == null){
			return null;
		}
		if(list.size() != 1){
			//list不等于1 代表通过名字或者生日查找出的熊猫有多个，需要选择
			return null;
		}
		panda = (Panda) list.get(0);
		*/
		panda = pandaDAO.findByZid(zid);
		String motherId = panda.getMother();
		list =  pandaDAO.findByMother(motherId);
		return list;
	}
	
	
	public List<?> whosYourCouple(Panda panda){
		//通过panda对象确定族谱号zid  需要判断zid为空吗？
		whosMe(panda);
		if(list == null || list.size() == 0){
			return null;
		}
		panda = list.get(0);
		String zid = panda.getZid();
		Integer sex = panda.getSex();
		if(zid.isEmpty() || sex == null){
			return null;
		}
		
		
		//直系血亲not in(孙女,孙子,儿子,女儿,fatheid,motherid,爷爷id,奶奶id,祖父id,祖母id)
			//能否找到6个直系Id，找到对象6个直系Id，双for循环对比不能相同？
		
		
		//旁系血亲 
		//排除三代血亲 可能需要直接通过sql实现？    not in(爷爷id,奶奶id,祖父id,祖母id)
		//寻找孕龄对象 sql?  where birthday between **** and **** ;
		
		
		return null;
	}
	
	public String isCouple(String id,String otherId){
		//找到对应熊猫
		
		Panda panda = pandaDAO.findByZid(id);
		Panda otherPanda = pandaDAO.findByZid(otherId);
		if(panda == null || otherPanda == null){
			log.info(id+"或者"+otherId+"有问题，查不到对应熊猫");
			//return需要考虑换成状态信息类，包括boolean状态和message信息成员。
			return "id有问题";
		}
		//同性不能组cp=
		if(panda.getSex().equals(otherPanda.getSex())){
			log.info(id+"或者"+otherId+"性别相同，不能组成cp");
			return "性别相同";
		}
		/**
		//不需要判断关系的情侣判断
		List<String> zhixi = whosYourZhixi(panda);
		List<String> otherzhixi = whosYourZhixi(otherPanda);
		System.out.println("zhixi:");
		for(String zid:zhixi){
			System.out.println(zid);
			if(otherzhixi.contains(zid)){
				return false;
			}
		}
		System.out.println("otherzhixi:");
		for(String zid:otherzhixi){
			System.out.println(zid);
		}
		*/
		//需要判断关系的情侣判断  这里map的key不能重复，所以不能实现，考虑使用zid为key，relation为value试试。留下zid重复的问题
		Map<String, Constants.RELATION> zhixi = whosYourZhiXi(panda);
		//直系亲属直接返回关系	
		if(!otherId.isEmpty() && zhixi.keySet().contains(otherId)){
			log.info("关联人："+otherId+"是"+id+"的"+zhixi.get(otherId).getName());
			return otherId+"是"+id+"的"+zhixi.get(otherId).getName();
		}
				
		Map<String, Constants.RELATION> otherzhixi = whosYourZhiXi(otherPanda);
		log.info("zhixi:");
		for(String zid:zhixi.keySet()){
			log.info(zhixi.get(zid)+zid);
			if(!zid.isEmpty() && otherzhixi.keySet().contains(zid)){
				log.info("关联人："+zid+"是1的"+zhixi.get(zid).getName());
				log.info("关联人："+zid+"是2的"+otherzhixi.get(zid).getName());
				//判断爷爷后不会再判断父亲层次，造成错误，加了判断也有问题
				//if(zhixi.get(zid) == otherzhixi.get(zid) &&  zhixi.get(zid).getValue() >= 3 && zhixi.get(zid).getValue()<=6){
				//	continue;
				//}
				return findRealtionship(zhixi,otherzhixi,zid);
				
			}
		}
		log.info("otherzhixi:");
		for(String zid:otherzhixi.keySet()){
			log.info(otherzhixi.get(zid)+zid);
		}
		//能否找到6个直系Id，找到对象6个直系Id，双for循环对比不能相同？
	
	//旁系血亲 
	//排除三代血亲 可能需要直接通过sql实现？    not in(爷爷id,奶奶id,祖父id,祖母id)
	//寻找孕龄对象 sql?  where birthday between **** and **** ;
		
		return "";
	}
	
	public RELATION relationship(String id,String otherId){
		Panda panda = pandaDAO.findByZid(id);
		Panda otherPanda = pandaDAO.findByZid(otherId);
		Map<String, Constants.RELATION> zhixi = whosYourZhiXi(panda);
		if(zhixi.containsKey(otherId)){
			log.info(otherId+"是"+id+"的"+zhixi.get(otherId).getName());
			return zhixi.get(otherId);
		}
		
		Map<String, RELATION> otherzhixi = whosYourZhiXi(otherPanda);
		for(String zid: zhixi.keySet()){
			log.info(zid);
			if(otherzhixi.keySet().contains(zid)){
				log.info("关联人："+zid+"是1的"+zhixi.get(zid).getName());
				log.info("关联人："+zid+"是2的"+otherzhixi.get(zid).getName());
				findRealtionship(zhixi,otherzhixi,zid);
			}
		}
		return Constants.RELATION.UNKOWN;
	}
	
	
	public Panda whosYourAncestry(String zid){
		//考虑以后添加一个ancestryId，如果有ancestryId直接取用返回。
		Panda panda = pandaDAO.findByZid(zid);
		
		if(panda == null){
			log.debug("whosYourAncestry中zid="+zid+"查找的panda为空");
			return new Panda();
		}
		return whosYourAncestry(panda,Constants.RACESYSTEM.MATERNAL);
		//如果没有ancestryId，根据父系/母系倒叙遍历fatherId/motherId
	}
	
	public Panda whosYourAncestry(Panda panda){
		//考虑以后添加一个ancestryId，如果有ancestryId直接取用返回。
		return whosYourAncestry(panda,Constants.RACESYSTEM.MATERNAL);
		//如果没有ancestryId，根据父系/母系倒叙遍历fatherId/motherId
	}
	
	
	
	public Panda whosYourAncestry(Panda panda,Constants.RACESYSTEM raceSystem){
		//考虑以后添加一个ancestryId，如果有ancestryId直接取用返回。
		String ancestryId = panda.getAncestryId();
		Panda tempPanda = new Panda();
		//Panda tempPanda = pandaDAO.findByZid(ancestryId);
		//如果没有ancestryId，根据父系/母系倒叙遍历fatherId/motherId
		if(ancestryId == null || ancestryId.isEmpty()){
			ancestryId = findAncestry(panda);
		}
		if(!ancestryId.isEmpty()){//如果找出老祖宗则填写到表中方便下次查询
			tempPanda = pandaDAO.findByZid(ancestryId); 
			panda.setAncestryId(ancestryId);
			pandaDAO.attachDirty(panda);//使用save不能保存数据纠结了几小时
			log.info("查到熊猫"+panda.getName()+"的ancestryId="+ancestryId+"并插入数据库");
		}
		return tempPanda;
	}
	
	private String findAncestry(Panda panda){
		String motherId = panda.getMother();
		if(motherId.isEmpty() || Constants.PANDA.WILD.getValue().equals(motherId)){
			
			return panda.getZid();
		}
		panda = pandaDAO.findByZid(motherId);
		
		return findAncestry(panda);//递归，之前返回“”出错
	}
	
	public List<String> whosYourZhixi(Panda panda){
		List zhixi = new ArrayList();
		//直系血亲not in(孙女,孙子,儿子,女儿,fatheid,motherid,爷爷id,奶奶id,祖父id,祖母id)
		String fatherId = panda.getFather();
		if(fatherId != null && !fatherId.equals("99999") ){
			Panda fatherPanda = pandaDAO.findByZid(fatherId);
			String grandfatherId = fatherPanda.getFather().toString();
			String grandmotherId = fatherPanda.getMother().toString();
			zhixi = zhixiArray(zhixi,fatherId);
			zhixi = zhixiArray(zhixi,grandfatherId);
			zhixi = zhixiArray(zhixi,grandmotherId);
		}
		String motherId = panda.getMother();
		if(motherId != null && !motherId.equals("99999") ){
			Panda motherPanda = pandaDAO.findByZid(motherId);
			String mGrandfatherId = motherPanda.getFather().toString();
			String mGrandmotherId = motherPanda.getMother().toString();
			zhixi = zhixiArray(zhixi,motherId);
			zhixi = zhixiArray(zhixi,mGrandfatherId);
			zhixi = zhixiArray(zhixi,mGrandmotherId);
		}
		//找儿女
		list = whosYourSon(panda);
		List list2 = new ArrayList();
		if(list != null){
			for(int i = 0; i < list.size(); i++){
				panda = list.get(i);
				zhixi.add(panda.getZid());
				list2 = whosYourSon(panda);
				if(list != null){
					for(int j = 0; j < list.size(); j++){
						panda = list.get(i);
						zhixi.add(panda.getZid());
					}
				}
			}
		}
		
		return zhixi;
	}
	
	public Map<String, Constants.RELATION> whosYourZhiXi(Panda panda){
		Map<String, Constants.RELATION> zhixi = new HashMap<String, Constants.RELATION>();
		//直系血亲not in(孙女,孙子,儿子,女儿,fatheid,motherid,爷爷id,奶奶id,祖父id,祖母id)
		String fatherId = panda.getFather();
		if(fatherId != null && !fatherId.isEmpty() && !fatherId.equals("99999") ){
			Panda fatherPanda = pandaDAO.findByZid(fatherId);
			String grandfatherId = fatherPanda.getFather();
			String grandmotherId = fatherPanda.getMother();
			zhixi = zhixiMap(zhixi,fatherId,Constants.RELATION.FATHER);
			
			zhixi = zhixiMap(zhixi,grandfatherId,Constants.RELATION.GRANDFATHER);
			zhixi = zhixiMap(zhixi,grandmotherId,Constants.RELATION.GRANDMOTHER);
			
		}
		String motherId = panda.getMother();
		if(motherId != null && !motherId.isEmpty() && !motherId.equals("99999") ){
			Panda motherPanda = pandaDAO.findByZid(motherId);
			String mGrandfatherId = motherPanda.getFather();
			String mGrandmotherId = motherPanda.getMother();
			zhixi = zhixiMap(zhixi,motherId,Constants.RELATION.MOTHER);
			zhixi = zhixiMap(zhixi,mGrandfatherId,Constants.RELATION.MGRANDFATHER);
			zhixi = zhixiMap(zhixi,mGrandmotherId,Constants.RELATION.MGRANDMOTHER);
		}
		//找儿女  需要想办法做成一个方法吗  重复太多
		List<Panda> list1 = whosYourSon(panda);
		List<Panda> list2 = new ArrayList();
		Panda panda2 = new Panda();
		
		
		if(list1 != null){
			for(int i = 0; i < list1.size(); i++){
				panda = list1.get(i);
				if(Constants.SEX.WOMAN.getValue().equals(panda.getSex())){
					zhixi.put(panda.getZid(), Constants.RELATION.DAUGHTER);
					list2 = whosYourSon(panda);
					if(list2 != null){
						for(int j = 0; j < list2.size(); j++){
							panda2 = list2.get(j);
							if(panda2.getSex() == Constants.SEX.WOMAN.getValue()){
								zhixi.put(panda2.getZid(), Constants.RELATION.GRANDDAUGHTER);
							}
							else{
								zhixi.put(panda2.getZid(), Constants.RELATION.GRANDSON);
							}
						}
					}
				}else if(Constants.SEX.MAN.getValue().equals(panda.getSex())){
					zhixi.put(panda.getZid(), Constants.RELATION.SON);
					list2 = whosYourSon(panda);
					if(list2 != null){
						for(int j = 0; j < list2.size(); j++){
							panda2 = list2.get(j);
							if(panda2.getSex() == Constants.SEX.WOMAN.getValue()){
								zhixi.put(panda2.getZid(), Constants.RELATION.MGRANDDAUGHTER);
							}
							else{
								zhixi.put(panda2.getZid(),Constants.RELATION.MGRANDSON);
							}
						}
					}
				}
				
			}
		}
		
		return zhixi;
	}
	
	public String findRealtionship(Map<String, RELATION> zhixi, Map<String, RELATION> otherzhixi,String zid){
		StringBuffer sb = new StringBuffer();
		switch(zhixi.get(zid)){
		case FATHER://为id的父亲  为男性所以otherid关系只取单数
			switch(otherzhixi.get(zid)){
			case FATHER://为otherId的父亲  性别判断
				sb.append("1是2的兄弟");
				break;
			case GRANDFATHER://为otherId的爷爷
				sb.append("1是2的伯伯");
				break;
			case MGRANDFATHER://为otherId的外公
				sb.append("1是2的姨母/舅舅");
				break;
			case SON://为otherId的儿子
				sb.append("1是2的孙子");//应该在直系关系判断里就已经被筛选，本条无效
				break;
			case GRANDSON://为otherId的孙子
				sb.append("1是2的曾孙");
				break;
			case MGRANDSON://为otherId的外孙
				sb.append("1是2的曾外孙");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case MOTHER:
			switch(otherzhixi.get(zid)){
			case MOTHER:
				sb.append("1是 2个兄弟");
				break;
			case GRANDMOTHER:
				sb.append("1是 2个伯伯");
				break;
			case MGRANDMOTHER:
				sb.append("1是 2个舅舅");
				break;
			case DAUGHTER:
				sb.append("1是 2个外孙");
				break;
			case GRANDDAUGHTER:
				sb.append("1是 2个曾外孙");
				break;
			case MGRANDDAUGHTER:
				sb.append("1是 2个曾外孙");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case GRANDFATHER://为id的父亲  为男性所以otherid关系只取单数
			switch(otherzhixi.get(zid)){
			case FATHER://为otherId的父亲  性别判断
				sb.append("1是2的侄子");
				break;
			case GRANDFATHER://为otherId的爷爷
				sb.append("1是2的兄弟或者堂兄弟");
				break;
			case MGRANDFATHER://为otherId的外公
				sb.append("1是2的表兄弟");
				break;
			case SON://为otherId的儿子
				sb.append("1是2的曾孙");//应该在直系关系判断里就已经被筛选，本条无效
				break;
			case GRANDSON://为otherId的孙子
				sb.append("1是2的玄孙");
				break;
			case MGRANDSON://为otherId的外孙
				sb.append("1是2的玄外孙");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case GRANDMOTHER:
			switch(otherzhixi.get(zid)){
			case MOTHER:
				sb.append("1是 2个侄子");
				break;
			case GRANDMOTHER:
				sb.append("1是 2个兄弟或者堂兄弟");
				break;
			case MGRANDMOTHER:
				sb.append("1是 2个表兄弟");
				break;
			case DAUGHTER:
				sb.append("1是 2个曾孙");
				break;
			case GRANDDAUGHTER:
				sb.append("1是 2个玄孙");
				break;
			case MGRANDDAUGHTER:
				sb.append("1是 2的玄外孙");
				break;
			default:
				sb.append("未知关系");
			}	
			break;
		case MGRANDFATHER://为id的父亲  为男性所以otherid关系只取单数
			switch(otherzhixi.get(zid)){
			case FATHER://为otherId的父亲  性别判断
				sb.append("1是2的外甥");
				break;
			case GRANDFATHER://为otherId的爷爷
				sb.append("1是2的表兄弟");
				break;
			case MGRANDFATHER://为otherId的外公
				sb.append("1是2的兄弟或者表兄弟");
				break;
			case SON://为otherId的儿子
				sb.append("1是2的曾外孙");//
				break;
			case GRANDSON://为otherId的孙子
				sb.append("1是2的玄外孙");
				break;
			case MGRANDSON://为otherId的外孙
				sb.append("1是2的玄外孙");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case MGRANDMOTHER:
			switch(otherzhixi.get(zid)){
			case MOTHER:
				sb.append("1是 2的外甥");
				break;
			case GRANDMOTHER:
				sb.append("1是 2的表兄弟");
				break;
			case MGRANDMOTHER:
				sb.append("1是 2的兄弟或者表兄弟");
				break;
			case DAUGHTER:
				sb.append("1是 2的曾外孙");
				break;
			case GRANDDAUGHTER:
				sb.append("1是 2的玄外孙");
				break;
			case MGRANDDAUGHTER:
				sb.append("1是 2的玄外孙");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case SON://为id的儿子  为男性所以otherid关系只取单数
			switch(otherzhixi.get(zid)){
			case FATHER://为otherId的父亲  性别判断
				sb.append("1是2的爷爷");
				break;
			case GRANDFATHER://为otherId的爷爷
				sb.append("1是2的曾祖父");
				break;
			case MGRANDFATHER://为otherId的外公
				sb.append("1是2的外曾祖父");
				break;
			case SON://为otherId的儿子
				sb.append("1是2的本人或者夫妻");//本人？***
				break;
			case GRANDSON://为otherId的孙子
				sb.append("1是2的儿子或者媳妇");
				break;
			case MGRANDSON://为otherId的外孙
				sb.append("1是2的女儿或者女婿");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case DAUGHTER:
			switch(otherzhixi.get(zid)){
			case MOTHER:
				sb.append("1是 2的外公或者外婆");
				break;
			case GRANDMOTHER:
				sb.append("1是 2的曾外祖");
				break;
			case MGRANDMOTHER:
				sb.append("1是 2的曾外祖");
				break;
			case DAUGHTER:
				sb.append("1是 2的本人或者配偶");
				break;
			case GRANDDAUGHTER:
				sb.append("1是 2的儿子或者媳妇");
				break;
			case MGRANDDAUGHTER:
				sb.append("1是 2个女儿或者女婿");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case GRANDSON://为id的孙子  为男性所以otherid关系只取单数
			switch(otherzhixi.get(zid)){
			case FATHER://为otherId的父亲  性别判断
				sb.append("1是2的曾祖父");
				break;
			case GRANDFATHER://为otherId的爷爷
				sb.append("1是2的玄祖父");
				break;
			case MGRANDFATHER://为otherId的外公
				sb.append("1是2的外玄祖父");
				break;
			case SON://为otherId的儿子
				sb.append("1是2的父母");//父母，妻子父母
				break;
			case GRANDSON://为otherId的孙子
				sb.append("1是2的本人或者配偶");
				break;
			case MGRANDSON://为otherId的外孙
				sb.append("1是2的亲家");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case GRANDDAUGHTER:
			switch(otherzhixi.get(zid)){
			case MOTHER:
				sb.append("1是 2的外曾祖父母");
				break;
			case GRANDMOTHER:
				sb.append("1是 2的外玄祖父母");
				break;
			case MGRANDMOTHER:
				sb.append("1是 2的外玄祖父母");
				break;
			case DAUGHTER:
				sb.append("1是 2的父母");
				break;
			case GRANDDAUGHTER:
				sb.append("1是 2的本人或者配偶");
				break;
			case MGRANDDAUGHTER:
				sb.append("1是 2的亲家");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case MGRANDSON://为id的孙子  为男性所以otherid关系只取单数
			switch(otherzhixi.get(zid)){
			case FATHER://为otherId的父亲  性别判断
				sb.append("1是2的曾外祖父母");
				break;
			case GRANDFATHER://为otherId的爷爷
				sb.append("1是2的外玄祖父母");
				break;
			case MGRANDFATHER://为otherId的外公
				sb.append("1是2的外玄祖父母");
				break;
			case SON://为otherId的儿子
				sb.append("1是2的配偶父母");//妻子父母
				break;
			case GRANDSON://为otherId的孙子
				sb.append("1是2的亲家");
				break;
			case MGRANDSON://为otherId的外孙
				sb.append("1是2的本人或者配偶");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		case MGRANDDAUGHTER:
			switch(otherzhixi.get(zid)){
			case MOTHER:
				sb.append("1是 2的外曾外祖父母");
				break;
			case GRANDMOTHER:
				sb.append("1是 2的外玄祖父母");
				break;
			case MGRANDMOTHER:
				sb.append("1是 2的外玄祖父母");
				break;
			case DAUGHTER:
				sb.append("1是 2的父母");
				break;
			case GRANDDAUGHTER:
				sb.append("1是 2的亲家");
				break;
			case MGRANDDAUGHTER:
				sb.append("1是 2的本人或者配偶");
				break;
			default:
				sb.append("未知关系");
			}
			break;
		default:
			sb.append("未知关系");
		}
		log.info(sb.toString());
		return sb.toString();
	}
	
	public List<?> whosMe(Panda panda){
		
		if(panda == null){
			return null;
			//panda信息有误，提示错误
		}
		if(panda.getZid() != null)
		{
			panda = pandaDAO.findByZid(panda.getZid());
			list.add(panda);
		}
		else if(panda.getName() != null)
		{
			list = pandaDAO.findByName(panda.getName());
		}
		else
		{
			list = null;
		}
		return list;
	}
	
	//感觉好多余  有空继续想
	public String findIdByPanda(Panda panda){
		//list = whosMe(panda);
		whosMe(panda);
		if(list == null){
			return null;
		}
		if(list.size() != 1){
			//list不等于1 代表通过名字或者生日查找出的熊猫有多个，需要选择
			return null;
		}
		panda = list.get(0);
		return panda.getZid();
	}
	
	
	public List checkList(List list){
		if(list == null){
			return null;
		}
		if(list.size() != 1){
			//list不等于1 代表通过名字或者生日查找出的熊猫有多个，需要选择
			return null;
		}
		return list;
	}
	
	public List zhixiArray(List zhixiArray,String id){
		if(id == null || "99999".equals(id)){
			return list;
		}
		zhixiArray.add(id);
		return zhixiArray;
	}
	
	public Map zhixiMap(Map map, String id, Constants.RELATION relation){
		if(id == null || "99999".equals(id)){
			//map.put("", relation);
			return map;
		}
		map.put(id, relation);
		return map;
	}
	
	
	
}
