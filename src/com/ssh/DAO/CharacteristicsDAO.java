package com.ssh.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.POJO.Characteristics;

/**
 * A data access object (DAO) providing persistence and search support for
 * Characteristics entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ssh.POJO.Characteristics
 * @author MyEclipse Persistence Tools
 */
public class CharacteristicsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CharacteristicsDAO.class);
	// property constants
	public static final String CHARACTERISTICS = "characteristics";
	public static final String REMARK = "remark";
	public static final String OTHER = "other";

	protected void initDao() {
		// do nothing
	}

	public void save(Characteristics transientInstance) {
		log.debug("saving Characteristics instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Characteristics persistentInstance) {
		log.debug("deleting Characteristics instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List<?> queryBySql(String sql){
		
		log.info(" [queryBySql] queryBySql:"+sql);
		List<?> list = getSession().createSQLQuery(sql).list();
		log.info(" [queryBySql] query result size:"+list.size());
		return list;
		
	}
	
	public Object queryUniqueBySql(String sql){
		
		//log.info("[queryUniqueBySql]  queryBySql:"+sql);
		Object object = getSession().createSQLQuery(sql).uniqueResult();
		log.info(" [queryUniqueBySql] querySql:("+sql+") result object:"+object);
		return object;
	}
	
	public int excuteBySql(String sql){
		
		log.info(" [excuteBySql] queryBySql:"+sql);
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		int result = sqlQuery.executeUpdate();//跟新的条数
		log.info(" [excuteBySql] query result int:"+result);
		return result;
		
	}
	
	
	public Characteristics findById(java.lang.Integer id) {
		log.debug("getting Characteristics instance with id: " + id);
		try {
			Characteristics instance = (Characteristics) getHibernateTemplate()
					.get("com.ssh.POJO.Characteristics", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Characteristics instance) {
		log.debug("finding Characteristics instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Characteristics instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Characteristics as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCharacteristics(Object characteristics) {
		return findByProperty(CHARACTERISTICS, characteristics);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByOther(Object other) {
		return findByProperty(OTHER, other);
	}

	public List findAll() {
		log.debug("finding all Characteristics instances");
		try {
			String queryString = "from Characteristics";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Characteristics merge(Characteristics detachedInstance) {
		log.debug("merging Characteristics instance");
		try {
			Characteristics result = (Characteristics) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Characteristics instance) {
		log.debug("attaching dirty Characteristics instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Characteristics instance) {
		log.debug("attaching clean Characteristics instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CharacteristicsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CharacteristicsDAO) ctx.getBean("CharacteristicsDAO");
	}
	
	public Integer getNewCharacterId(){
			String sql = "select max(characterId) from characteristics";
			Object obj = queryUniqueBySql(sql);
			
			if(obj == null){
				return 1;
			}
			else{
				try {
					Integer zid = (Integer)obj;
					log.info(" [getNewCharacterId] getNewZid:"+(zid+1));
					return zid+1;
				} catch (ClassCastException e) {
					// TODO: handle exception
					log.info(" [getNewCharacterId] zid("+obj+")数据异常，类型转化为Integer失败");
				}
			}
			return null;
			
		}
		
		public boolean characterIdIsExist(Integer zid){
			String sql = "select count(*) from characteristics where characterId = '"+zid+"'";
			
			Object obj = queryUniqueBySql(sql);
			if(obj == null){
				return false;
			}
			else{
				try {
					Integer zidCount = Integer.valueOf(obj.toString());
					if(zidCount == 1){
						return true;
					}
					else if(zidCount == 0){
						return false;
					}
					else{
						log.info("error by zid count is: "+zid);
						log.error("error by zid count is: "+zid);
						return true;
						
					}
				} catch (ClassCastException e) {
					// TODO: handle exception
					log.info("zid("+obj+")数据异常，类型转化为Integer失败");
				}
			}
			
			return true;//不知道填什么 联系管理员
		}
		
		public boolean characteristicsIsExist(String characteristics){
			String sql = "select count(*) from characteristics where characteristics = '"+characteristics+"'";
			Object obj = queryUniqueBySql(sql);
			if(obj == null){
				return false;
			}
			else{
				try {
					Integer zidCount = Integer.valueOf(obj.toString());
					if(zidCount == 1){
						return true;
					}
					else if(zidCount == 0){
						return false;
					}
					else{
						log.info("error by characteristics count is: "+characteristics);
						log.error("error by characteristics count is: "+characteristics);
						return true;
						
					}
				} catch (ClassCastException e) {
					// TODO: handle exception
					log.info("zid("+obj+")数据异常，类型转化为Integer失败");
				}
			}
			
			return true;//不知道填什么 联系管理员
		}
		
		
}