package com.ssh.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.DAO.CharacteristicsDAO;
import com.ssh.DAO.PandacharacterDAO;
import com.ssh.POJO.Characteristics;
import com.ssh.POJO.Genealogy;
import com.ssh.POJO.Pandacharacter;
import com.ssh.POJO.PandacharacterId;
import com.ssh.common.MessageAndFlag;

@Service("pandaCharacterService")
public class PandaCharacterService {

	private Logger log = LoggerFactory.getLogger(PandaCharacterService.class);
	
	@Autowired  
	@Qualifier("pandacharacterDAO")
	private PandacharacterDAO pandacharacterDAO;
	
	@Autowired	
	@Qualifier("characteristicsDAO")
	private CharacteristicsDAO characteristicsDAO;
	
	
	//@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,readOnly = false)
	public MessageAndFlag save(Characteristics characteristics,Pandacharacter pandaCharacter){
		
		//save中id应该没用
		//Integer id = characteristics.getCharacterId();
		
		String charact = characteristics.getCharacteristics();
		PandacharacterId pandacharacterId = pandaCharacter.getId();
		
		//charact不为空，就要判断characteristics中是否已经存在，如果有了取出characterId，
		if(null != charact && !charact.isEmpty()){
			if(characteristicsDAO.characteristicsIsExist(charact)){
				Characteristics characteristicsTemp = (Characteristics) characteristicsDAO.findByCharacteristics(charact).get(0);
				//事务  characteristicsDAO.getSessionFactory()
				Integer characterIdTemp = characteristicsTemp.getCharacterId();
				//Integer idTemp = pandacharacterId.getId();
				
				if(null != pandacharacterId){
					pandacharacterId.setCharacterId(characterIdTemp);
					//id即熊猫zid  通过action传递过来的
					if(null != pandacharacterId.getId() && null != characterIdTemp){
						
						
						//Pandacharacter pandacharacterTemp = new Pandacharacter();
						//pandacharacterTemp.setId(pandacharacterId);
						
						Pandacharacter pandacharacterTemp = pandacharacterDAO.findById(pandacharacterId);
						if(null != pandacharacterTemp){
							Long count = new Long(1);//
							pandacharacterTemp.setNumber(pandacharacterTemp.getNumber()+count);
							//pandacharacterDAO.save(pandacharacterTemp); 
							pandacharacterDAO.attachDirty(pandacharacterTemp);
							return new MessageAndFlag("charact is exist,  pandacharacter number+1 success",true);
							
						}
						else{//pandacharacterTemp 为空  先创建
							pandacharacterTemp = new Pandacharacter();
							pandacharacterTemp.setId(pandacharacterId);
							Long count = new Long(1);
							pandacharacterTemp.setNumber(count);
							pandacharacterDAO.save(pandacharacterTemp); 
							return new MessageAndFlag("charact is exist,  add pandacharacter success",true);
						}
						
						
					}
					
				}
				
				
			}//charact不为空，不过charact在表中找不到  需要插入characteristics表中先  用到事务
			else{
				//add characteristics
				Characteristics characteristicsTemp = new Characteristics();
				//Integer characterIdTemp = characteristicsDAO.getNewCharacterId();
				//characteristicsTemp.setCharacterId(characterIdTemp);
				characteristicsTemp.update(characteristics);
				characteristicsDAO.save(characteristicsTemp);
				
				//add pandaCharacter
				if(null != pandacharacterId){
					pandacharacterId.setCharacterId(characteristicsTemp.getCharacterId());
					//id即熊猫zid  通过action传递过来的
					if(null != pandacharacterId.getId() && null != characteristicsTemp.getCharacterId()){
						
						
						Pandacharacter pandacharacterTemp = new Pandacharacter();
						pandacharacterTemp.setId(pandacharacterId);
						Long count = new Long(1);//
						pandacharacterTemp.setNumber(count);
						pandacharacterDAO.save(pandacharacterTemp); 
						return new MessageAndFlag("charact is not exist, add pandacharacter and characteristics  success",true);
					}
					
				}
				
				
			}
			
		}
		
		return new MessageAndFlag("标签不能为空",false);
	}
	
	public MessageAndFlag update(Characteristics characteristics,Pandacharacter pandacharacter){
		//Integer id = characteristics.getCharacterId();
		String charact = characteristics.getCharacteristics();
		PandacharacterId pcId = pandacharacter.getId();
		
		//既然是更新，id和characterId必然不为空
		if(null == pcId || null == pcId.getCharacterId() || null == pcId.getId()){
			return new MessageAndFlag("id为空，",false);
		}
		Pandacharacter pandacharacterTemp = pandacharacterDAO.findById(pcId);
		//有人支持了这个标签  累加加1   有利于排序
		pandacharacterTemp.setNumber((pandacharacterTemp.getNumber())+1);
			
		return new MessageAndFlag("",false);
		
	}
	
	
	
	/**
	 * 通过id是否存在判断
	 * @param characteristics
	 * @return
	 */
	public MessageAndFlag saveCharacteristics(Characteristics characteristics){
		Integer id = characteristics.getCharacterId();
		String charact = characteristics.getCharacteristics();
		
		if(updateFlag(id, charact)){
			Characteristics charactTemp = characteristicsDAO.findById(id);
			charactTemp.update(characteristics);
			return new MessageAndFlag("更新成功 id:("+id+") charact:("+charact+")",true);
				//持久化对象改变后退出session即可，会自动保存？
			//characteristicsDAO.attachDirty(instance);
			
			
		}//id为空表示是从add界面调用方法,
		else{
			id = characteristicsDAO.getNewCharacterId();
			if(saveFlag(id, charact)){
				
				return new MessageAndFlag("新增成功 id:("+id+") charact:("+charact+")",true);
			}
			
			
			return new MessageAndFlag("不能更新 id:("+id+") charact:("+charact+")",false);
		}
	}
	
	public MessageAndFlag updateCharacteristics(Characteristics characteristics){
		Integer id = characteristics.getCharacterId();
		String charact = characteristics.getCharacteristics();
		
		if(updateFlag(id, charact)){
			Characteristics charactTemp = characteristicsDAO.findById(id);
			charactTemp.update(characteristics);
			return new MessageAndFlag("更新成功 id:("+id+") charact:("+charact+")",true);
				//持久化对象改变后退出session即可，会自动保存？
			//characteristicsDAO.attachDirty(instance);
			
			
		}//id为空表示是从add界面调用方法,
		else{
			id = characteristicsDAO.getNewCharacterId();
			if(saveFlag(id, charact)){
				
				return new MessageAndFlag("新增成功 id:("+id+") charact:("+charact+")",true);
			}
			
			
			return new MessageAndFlag("不能更新 id:("+id+") charact:("+charact+")",false);
		}
	}
	
	
	
	
	public boolean deleteCharacteristics(Integer characterId){
		Characteristics characteristics = characteristicsDAO.findById(characterId);
		if(null != characteristics){
			characteristicsDAO.delete(characteristics);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Characteristics> getCharacteristicses(Characteristics characteristics){
		List<Characteristics> list = new ArrayList();
		//这里为null可以考虑去掉，因为通过form表单的String类型为null会转为=""  
		if(characteristics.getCharacterId() != null && characteristics.getCharacterId() != 0){
			Characteristics characteristicsTemp = characteristicsDAO.findById(characteristics.getCharacterId());
			//这里如果为空，list就是{null}  == null 和size()==0都判断不出来
			if(characteristicsTemp == null){
				return list;
			}
			list.add(characteristicsTemp);
		}
		//表单  一个用户可以创建多个表单记录不同族谱  需要和userId合用
		else if(characteristics.getCharacteristics() != null && !characteristics.getCharacteristics().isEmpty()){  
			list = characteristicsDAO.findByCharacteristics(characteristics.getCharacteristics());
		}
		
		
		return list;
	}
	
	
	
	
	public List<?> getListByZid(String id){
		String sql = "select c.characterId cid,c.characteristics ccharacter,p.number num from characteristics c, pandacharacter p where c.characterId=p.characterId and p.id ="+id+" order by num desc";
		List<?> list = pandacharacterDAO.queryBySql(sql);
		
		return list;
	}
	
	
	
	public MessageAndFlag saveOrUpdatePandaCharacter(Pandacharacter pandaCharacter){
		
		return null;
	}
	
	public MessageAndFlag deletePandaCharacter(Pandacharacter pandaCharacter){
		
		return null;
	}
	
	public MessageAndFlag getPandaCharacters(Pandacharacter pandaCharacter){
		
		return null;
	}
	
	
	private boolean saveFlag(Integer id,String charact){
		if(null == id || id == 0 || null == charact || charact.isEmpty()){
			return false;
		}
		
		if(characteristicsDAO.characteristicsIsExist(charact)){
			return false;
		}
		
		return true;
		
		
	}
	
	private boolean updateFlag(Integer id,String charact){
		if(null == id || id == 0 || null == charact || charact.isEmpty()){
			return false;
		}
		if(characteristicsDAO.characteristicsIsExist(charact)){
			return false;
		}
		if(characteristicsDAO.characterIdIsExist(id)){
			return true;
		}
		return false;
	}
}
