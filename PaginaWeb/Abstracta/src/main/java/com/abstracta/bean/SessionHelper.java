package com.abstracta.bean;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Helper for DB Sessions/Hibernate
 * @author jtrias
 */
public class SessionHelper {

	private static SessionFactory sessionFactory = getSessionFactory();
	public static ServiceRegistry serviceRegistry;
	private static final Log log = LogFactory.getLog(SessionHelper.class);
	
	/*
	 * Session Factory
	 */
	protected static SessionFactory getSessionFactory() {
		
		try {
			
			if(sessionFactory!=null)
				return sessionFactory;
			
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    
		    SessionFactory sess = configuration.buildSessionFactory(serviceRegistry);
		  
            return sess;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			log.error("Session Error:" + e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(
					e.getMessage());
		}
	}
	
	public void attachDirty(Object instance) {
		log.debug("attaching dirty Cart instance");
	
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	public void save(Object instance) {
		log.debug("attaching dirty Cart instance");
	
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			session.save(instance);
			log.debug("attach successful");
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	public void delete(Object instance) {
		log.debug("attaching dirty Cart instance");
	
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			session.delete(instance);
			log.debug("attach successful");
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object findById(Class targetClass, int id) {
		
		log.debug("getting Cart instance with id: " + id);
		
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			Object instance = session.get( targetClass, id);
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	public Integer getCartItemQty(Integer cartId) {
		
		log.debug("finding Cart instance by example");
		Transaction trx = null;
		Session session = null;
		Object qty = 0;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();

			qty =  session.createCriteria(CartLine.class)
					.add(Restrictions.eq("cartId", cartId))
					.setProjection(Projections.sum("qty")).uniqueResult();
			
			if(qty!=null)
				return ((Long)qty).intValue();
			else
				return 0;	
			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CartLine> getCartLines(Integer cartId) {
		
		log.debug("finding Cart instance by example");
		Transaction trx = null;
		Session session = null;
		List<CartLine> lines = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();

			lines = session.createCriteria(CartLine.class)
					.add(Restrictions.eq("cartId", cartId))
					.addOrder(Order.asc("created")).list();
			
			return lines;
			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find User entity by name
	 */
	public Customer findUserByUserName(String username) {
		
		log.debug("getting User instance with name: " + username);
		
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			Customer instance = (Customer) session.createCriteria(Customer.class)
							  .add(Restrictions.eq("username", username))
							  .add(Restrictions.eq("active", 'Y'))
							  .uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find User entity by name
	 */
	public Customer findInactiveCustomerByName(String username) {
		
		log.debug("getting User instance with name: " + username);
		
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			Customer instance = (Customer) session.createCriteria(Customer.class)
							  .add(Restrictions.eq("username", username))
							  .add(Restrictions.eq("active", 'N'))
							  .uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find newsletter entity by email
	 */
	public Newsletter findNewsletterSubscription(String email) {
		
		log.debug("getting User instance with name: " + email);
		
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			Newsletter instance = (Newsletter) session.createCriteria(Newsletter.class)
							  .add(Restrictions.eq("email", email))
							  .setMaxResults(1)
							  .uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Cart by Customer
	 */
	public Cart findCartByCustomer(Customer customer) {
		
		log.debug("getting Cart instance with Customer: " + customer.getName());
		
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			Cart instance = (Cart) session.createCriteria(Cart.class)
								  .add(Restrictions.eq("customerId", customer.getCustomerId()))
								  .add(Restrictions.eq("statusId", 2))
								  .setMaxResults(1)
								  .uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Wish List by Customer
	 */
	public WishList findWishListByCustomer(Customer customer) {
		
		log.debug("getting Wish List instance with Customer: " + customer.getName());
		
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			WishList instance = (WishList) session.createCriteria(WishList.class)
								  .add(Restrictions.eq("customerId", customer.getCustomerId()))
								  .setMaxResults(1)
								  .uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Wish List Line By Wish List & Product
	 */
	public WishListLine findWListLineByWList_Product(int wishListID, int productID) {
		
		log.debug("getting WishListLine instance with Wish List & Product: " + wishListID +" / "+ productID);
		
		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			WishListLine instance = (WishListLine) session.createCriteria(WishListLine.class)
									.add(Restrictions.eq("productId", productID))
									.add(Restrictions.eq("wishListId", wishListID))
									.add(Restrictions.eq("movedToCart", 'N'))
									.setMaxResults(1)
									.uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WishListLine> getWishListLines(Integer wishListId) {
		
		log.debug("finding Wish List Line instance by wish list Id");
		Transaction trx = null;
		Session session = null;
		List<WishListLine> lines = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();

			lines = session.createCriteria(WishListLine.class)
					.add(Restrictions.eq("wishListId", wishListId))
					.add(Restrictions.eq("movedToCart", 'N'))
					.addOrder(Order.desc("created")).list();
			
			return lines;
			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
}
