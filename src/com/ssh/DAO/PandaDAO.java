package com.ssh.DAO;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ssh.POJO.Panda;

/**
 * A data access object (DAO) providing persistence and search support for Panda
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.ssh.POJO.Panda
 * @author MyEclipse Persistence Tools
 */
public class PandaDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(PandaDAO.class);
	// property constants
	public static final String ZID = "zid";
	public static final String NAME = "name";
	public static final String SEX = "sex";
	public static final String FATHER = "father";
	public static final String MOTHER = "mother";
	public static final String ANCESTRY_ID = "ancestryId";
	public static final String PLACE = "place";
	public static final String GID = "gid";
	public static final String PHOTO = "photo";
	public static final String GENERATION = "generation";

	protected void initDao() {
		// do nothing
	}

	public void save(Panda transientInstance) {
		log.debug("saving Panda instance");
		Transaction tran=getSession().beginTransaction();
		try {
			
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		finally{
			tran.commit();
			getSession().flush();
			getSession().close();
		}
	}

	public void delete(Panda persistentInstance) {
		log.debug("deleting Panda instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Panda findById(java.lang.Integer id) {
		log.debug("getting Panda instance with id: " + id);
		try {
			Panda instance = (Panda) getHibernateTemplate().get(
					"com.ssh.POJO.Panda", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Panda instance) {
		log.debug("finding Panda instance by example");
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

	public void flush(){
		getHibernateTemplate().flush();
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Panda instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Panda as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Panda findByZid(Object zid) {
		log.debug("getting Panda instance with zid: " + zid);
		try {
			String queryString = "from Panda as model where model.zid= ?";
			List list = getHibernateTemplate().find(queryString, zid);
			if(list != null && list.size()>0){
				return (Panda)getHibernateTemplate().find(queryString, zid).get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
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

	public List findByAncestryId(Object ancestryId) {
		return findByProperty(ANCESTRY_ID, ancestryId);
	}

	public List findByPlace(Object place) {
		return findByProperty(PLACE, place);
	}

	public List findByGid(Object gid) {
		return findByProperty(GID, gid);
	}

	public List findByPhoto(Object photo) {
		return findByProperty(PHOTO, photo);
	}

	public List findByGeneration(Object generation) {
		return findByProperty(GENERATION, generation);
	}

	public List findAll() {
		log.debug("finding all Panda instances");
		try {
			String queryString = "from Panda";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Panda merge(Panda detachedInstance) {
		log.debug("merging Panda instance");
		try {
			Panda result = (Panda) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Panda instance)  {
		log.debug("attaching dirty Panda instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Panda instance) {
		log.debug("attaching clean Panda instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PandaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PandaDAO) ctx.getBean("PandaDAO");
	}
}