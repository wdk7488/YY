package com.ssh.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.POJO.Pandacharacter;
import com.ssh.POJO.PandacharacterId;

/**
 * A data access object (DAO) providing persistence and search support for
 * Pandacharacter entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ssh.POJO.Pandacharacter
 * @author MyEclipse Persistence Tools
 */
public class PandacharacterDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PandacharacterDAO.class);
	// property constants
	public static final String NUMBER = "number";
	public static final String REMARK = "remark";
	public static final String OTHER = "other";

	protected void initDao() {
		// do nothing
	}

	public void save(Pandacharacter transientInstance) {
		log.debug("saving Pandacharacter instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Pandacharacter persistentInstance) {
		log.debug("deleting Pandacharacter instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	
	public List<?> queryBySql(String sql){
		Session session =  getSession();
		log.info(" [queryBySql] queryBySql:"+sql);
		List<?> list = session.createSQLQuery(sql).list();
		log.info(" [queryBySql] query result size:"+list.size());
		session.close();
		return list;
		
	}
	
	public Object queryUniqueBySql(String sql){
		Session session =  getSession();
		//log.info("[queryUniqueBySql]  queryBySql:"+sql);
		Object object = session.createSQLQuery(sql).uniqueResult();
		log.info(" [queryUniqueBySql] querySql:("+sql+") result object:"+object);
		session.close();
		return object;
	}
	
	public int excuteBySql(String sql){
		Session session =  getSession();
		log.info(" [excuteBySql] queryBySql:"+sql);
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		int result = sqlQuery.executeUpdate();//跟新的条数
		log.info(" [excuteBySql] query result int:"+result);
		session.close();
		return result;
		
	}
	
	
	public Pandacharacter findById(com.ssh.POJO.PandacharacterId id) {
		log.debug("getting Pandacharacter instance with id: " + id);
		try {
			Pandacharacter instance = (Pandacharacter) getHibernateTemplate()
					.get("com.ssh.POJO.Pandacharacter", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Pandacharacter instance) {
		log.debug("finding Pandacharacter instance by example");
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
		log.debug("finding Pandacharacter instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Pandacharacter as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNumber(Object number) {
		return findByProperty(NUMBER, number);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByOther(Object other) {
		return findByProperty(OTHER, other);
	}

	public List findAll() {
		log.debug("finding all Pandacharacter instances");
		try {
			String queryString = "from Pandacharacter";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Pandacharacter merge(Pandacharacter detachedInstance) {
		log.debug("merging Pandacharacter instance");
		try {
			Pandacharacter result = (Pandacharacter) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Pandacharacter instance) {
		log.debug("attaching dirty Pandacharacter instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pandacharacter instance) {
		log.debug("attaching clean Pandacharacter instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PandacharacterDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PandacharacterDAO) ctx.getBean("PandacharacterDAO");
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
	
	
	
	
	
	
}