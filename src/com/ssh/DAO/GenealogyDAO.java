package com.ssh.DAO;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.POJO.Genealogy;
import com.ssh.POJO.Panda;

/**
 * A data access object (DAO) providing persistence and search support for
 * Genealogy entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ssh.POJO.Genealogy
 * @author MyEclipse Persistence Tools
 */
public class GenealogyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(GenealogyDAO.class);
			
	// property constants
	public static final String ZID = "zid";
	public static final String USER_ID = "userId";
	public static final String NAME = "name";
	public static final String SHEET_NAME = "sheetName";
	public static final String SEX = "sex";
	public static final String FATHER = "father";
	public static final String MOTHER = "mother";
	public static final String CHARACTERISTI = "characteristi";
	public static final String PLACE2 = "place2";
	public static final String ANCESTRY_ID = "ancestryId";
	public static final String PHOTO = "photo";
	public static final String RESUME = "resume";
	public static final String REMARK = "remark";
	public static final String OTHER = "other";

	protected void initDao() {
		// do nothing
	}

	public void save(Genealogy transientInstance) {
		log.debug("saving Genealogy instance");
		
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
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
	
	
	public void delete(Genealogy persistentInstance) {
		log.debug("deleting Genealogy instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Genealogy findById(java.lang.Integer id) {
		log.debug("getting Genealogy instance with id: " + id);
		try {
			Genealogy instance = (Genealogy) getHibernateTemplate().get(
					"com.ssh.POJO.Genealogy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Genealogy findByZid(Object zid) {
		log.debug("getting Panda instance with zid: " + zid);
		try {
			String queryString = "from Genealogy as model where model.zid= ?";
			List list = getHibernateTemplate().find(queryString, zid);
			if(list != null && list.size()>0){
				return (Genealogy)getHibernateTemplate().find(queryString, zid).get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(Genealogy instance) {
		log.debug("finding Genealogy instance by example");
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
		log.debug("finding Genealogy instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Genealogy as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findBySheetName(Object sheetName) {
		return findByProperty(SHEET_NAME, sheetName);
	}

	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findByFather(Object father) {
		return findByProperty(FATHER, father);
	}

	public List findByMother(Object mother) {
		return findByProperty(MOTHER, mother);
	}

	public List findByCharacteristi(Object characteristi) {
		return findByProperty(CHARACTERISTI, characteristi);
	}

	public List findByPlace2(Object place2) {
		return findByProperty(PLACE2, place2);
	}

	public List findByAncestryId(Object ancestryId) {
		return findByProperty(ANCESTRY_ID, ancestryId);
	}

	public List findByPhoto(Object photo) {
		return findByProperty(PHOTO, photo);
	}

	public List findByResume(Object resume) {
		return findByProperty(RESUME, resume);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByOther(Object other) {
		return findByProperty(OTHER, other);
	}

	public List findAll() {
		log.debug("finding all Genealogy instances");
		try {
			String queryString = "from Genealogy";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	
	public String getNewZid(){
		String sql = "select max(zid) from genealogy";
		Object obj = queryUniqueBySql(sql);
		
		if(obj == null){
			return "1";
		}
		else{
			try {
				Integer zid = Integer.valueOf(obj.toString());
				log.info(" [getNewZid] getNewZid:"+(zid+1));
				return (zid+1)+"";
			} catch (ClassCastException e) {
				// TODO: handle exception
				log.info("zid("+obj+")数据异常，类型转化为Integer失败");
			}
		}
		return null;
		
	}
	
	public boolean zidIsExists(String zid){
		String sql = "select count(*) from genealogy where zid = '"+zid+"'";
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
	
	public Genealogy merge(Genealogy detachedInstance) {
		log.debug("merging Genealogy instance");
		try {
			Genealogy result = (Genealogy) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Genealogy instance) {
		log.debug("attaching dirty Genealogy instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Genealogy instance) {
		log.debug("attaching clean Genealogy instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	
	
	
	

	public static GenealogyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GenealogyDAO) ctx.getBean("GenealogyDAO");
	}
}