package com.abc.ws.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.abc.ws.bean.XxWabClassinfo;
import com.abc.ws.bean.XxWabCostumerservice;
import com.abc.ws.bean.XxWabHomepage;
import com.abc.ws.bean.XxWabProductinfo;

/*
 * DAO Helper
 */
public class WebServiceHelper{
	
	private static SessionFactory sessionFactory = getSessionFactory();
	public static ServiceRegistry serviceRegistry;
	private static final Log log = LogFactory.getLog(WebServiceHelper.class);
	
	
	public static void main(String[] args) {
		
		WebServiceHelper wsh = new WebServiceHelper();
		
		List<XxWabCostumerservice> services = Arrays.asList(wsh.findAllCostumerServiceReturn());
		XxWabCostumerservice service;
		
		for(int i=0; i < services.size(); i++){
			
			service = services.get(i);
			
			System.out.println(service.getName());
			System.out.println(service.getTitle());
			System.out.println(service.getXxWabContentbrief());
			System.out.println(service.getContenttext());
			System.out.println(service.getXxWabReturnandchanges());
			System.out.println(service.getSeqno());

			System.out.println("**********");
		}
		
		if(1==1)
			return;
		
		List<XxWabProductinfo> products5 = Arrays.asList(wsh.findNewProducts());
		XxWabProductinfo product5;
		
		for(int i=0; i < products5.size(); i++){
			
			product5 = products5.get(i);
			
			System.out.println(product5.getName());
			System.out.println(product5.getDescription());
			System.out.println(product5.getValue());
			System.out.println(product5.getGift());
			System.out.println(product5.getNewproduct());
			System.out.println("PROMO: "+product5.getPromo());
			System.out.println(product5.getFinalprice());
			System.out.println(product5.getOriginalprice());
			System.out.println(product5.getOverview());
			System.out.println(product5.getColor());
			System.out.println("*****************************");
			if(i==5)
				break;
		}
		
		if(1==1)
			return;
		
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");

		
		List<XxWabProductinfo> products4 = Arrays.asList(wsh.findProductsForGift());
		XxWabProductinfo product4;
		
		for(int i=0; i < products4.size(); i++){
			
			product4 = products4.get(i);
			
			System.out.println(product4.getName());
			System.out.println(product4.getDescription());
			System.out.println(product4.getValue());
			System.out.println(product4.getGift());
			System.out.println(product4.getNewproduct());
			System.out.println("*****************************");
			if(i==5)
				break;
		}
		
		System.out.println("XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXX");
		
		List<XxWabProductinfo> products3 = Arrays.asList(wsh.findTrendSetProducts(1078197));
		XxWabProductinfo product3;
		
		for(int i=0; i < products3.size(); i++){
			
			product3 = products3.get(i);
			
			System.out.println(product3.getName());
			System.out.println(product3.getDescription());
			System.out.println(product3.getValue());
			System.out.println(product3.getGift());
			System.out.println("*****************************");
			if(i==1)
				break;
		}
		
		System.out.println("XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXX");
		
		List<XxWabProductinfo> products2 = Arrays.asList(wsh.findProductByNameDesc("silla"));
		XxWabProductinfo product2;
		
		for(int i=0; i < products2.size(); i++){
			
			product2 = products2.get(i);
			
			System.out.println(product2.getName());
			System.out.println(product2.getDescription());
			System.out.println(product2.getValue());
			System.out.println("*****************************");
			if(i==1)
				break;
		}
		
		System.out.println("------------------------------------------------------------------*****************************");
		
		//Producto Individual
		XxWabProductinfo myProduct = wsh.findProductById(1091364);
		System.out.println("Producto Individual: " + myProduct.getName());
		
		System.out.println("--------------------------------------");
		
		//Productos por level
		List<XxWabProductinfo> products = Arrays.asList(wsh.findProductByLevelID(3, 3389));
		XxWabProductinfo product;
		
		for(int i=0; i < products.size(); i++){
			
			product = products.get(i);
			
			System.out.println(product.getName());
			System.out.println(product.getValue());
			System.out.println(product.getFinalprice());
			System.out.println(product.getQty());
			System.out.println(product.getClasslevel3Id());
			
			if(i==0)
				break;
		}
		
		System.out.println("--------------------------------------");
		
		
		System.out.println("\nCLASES");
		//Clases por level
		List<XxWabClassinfo> classes = Arrays.asList(wsh.findClassByLevelID(1, 1000593));
		XxWabClassinfo myClass;
		for(int i=0; i < classes.size(); i++){
			
			myClass = classes.get(i);
			
			System.out.println(myClass.getClasslevel2Name());
			System.out.println(myClass.getClasslevel3Name());
			System.out.println("********************************");
		}
		
	}
	

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
			throw new IllegalStateException(
					e.getMessage());
		}
	}
	
	
	/*
	 * Find all the Classes
	 */
	@SuppressWarnings("unchecked")
	public XxWabClassinfo[] findAll() {

		Transaction trx = null;
		Session session = null;
		try {
			
			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabClassinfo> result = session.createCriteria(XxWabClassinfo.class).list();  
			   
		    XxWabClassinfo[] array = new XxWabClassinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("get failed allClasses", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Object By Classification Level and ID 
	 */
	@SuppressWarnings("unchecked")
	public XxWabProductinfo[] findProductByLevelID(int classlvl, long id) {

		Transaction trx = null;
		Session session = null;
		try {
			
			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabProductinfo> result = session.createCriteria(XxWabProductinfo.class)
											 .add(Restrictions.eq("classlevel"+classlvl+"Id", id)).list();
		    
		    XxWabProductinfo[] array = new XxWabProductinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findByLevelID failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Object By Classification Level and ID 
	 */
	@SuppressWarnings("unchecked")
	public XxWabClassinfo[] findClassByLevelID(int classlvl, long id) {

		Transaction trx = null;
		Session session = null;
		try {
			
			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabClassinfo> result = session.createCriteria(XxWabClassinfo.class)
											 .add(Restrictions.eq("classlevel"+classlvl+"Id", id)).list();
		    
		    XxWabClassinfo[] array = new XxWabClassinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findClassByLevelID failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Object By id
	 */
	public XxWabProductinfo findProductById(long id) {
		
		Transaction trx = null;
		Session session = null;
		try{ 

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			XxWabProductinfo instance = (XxWabProductinfo) session
							  .createCriteria(XxWabProductinfo.class)
							  .add(Restrictions.idEq(id)).uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} 
			
			return instance;
			
		} catch (RuntimeException re) {
			log.error("findProductById failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Product By Name/Description
	 */
	@SuppressWarnings("unchecked")
	public XxWabProductinfo[] findProductByNameDesc(String search) {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabProductinfo> result = session.createCriteria(XxWabProductinfo.class)
					.add(
						Restrictions.disjunction()
						.add(Restrictions.like("name", search.toUpperCase(), MatchMode.ANYWHERE))
					     .add(Restrictions.like("description", search.toUpperCase(), MatchMode.ANYWHERE))
					 ).list();
		    
		    XxWabProductinfo[] array = new XxWabProductinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findProductByNameDesc failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	
	/*
	 * Find Product for Gift 
	 */
	@SuppressWarnings("unchecked")
	public XxWabProductinfo[] findProductsForGift() {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabProductinfo> result = session.createCriteria(XxWabProductinfo.class)
					.add(
						Restrictions.disjunction()
						.add(Restrictions.eq("gift", "Y"))
					 ).list();
		    
		    XxWabProductinfo[] array = new XxWabProductinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findProductsForGift failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find new Products 
	 */
	@SuppressWarnings("unchecked")
	public XxWabProductinfo[] findNewProducts() {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabProductinfo> result = session.createCriteria(XxWabProductinfo.class)
					.add(
						Restrictions.disjunction()
						.add(Restrictions.eq("newproduct", "Y"))
					 ).list();
		    
		    XxWabProductinfo[] array = new XxWabProductinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findNewProducts failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find promo Products
	 */
	@SuppressWarnings("unchecked")
	public XxWabProductinfo[] findPromoProducts() {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabProductinfo> result = session.createCriteria(XxWabProductinfo.class)
					.add(
						Restrictions.disjunction()
						.add(Restrictions.eq("promo", "Y"))
					 ).list();
		    
		    XxWabProductinfo[] array = new XxWabProductinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findNewProducts failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find TrendSet Products by productID
	 */
	@SuppressWarnings("unchecked")
	public XxWabProductinfo[] findTrendSetProducts(int product) {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			String query = "SELECT pro FROM XxWabTrendsetProduct tsp, XxWabProductinfo pro " +
					"where " +
					"tsp.MProductId = pro.MProductId " +
					"and tsp.xxWabTrendset = ( "+
						"select xxWabTrendset " +
					    "from XxWabTrendsetProduct tspa "+
					    "where tspa.MProductId = " + product +
					 ") " +
					 "and tsp.MProductId <> " + product;
			
			List<XxWabProductinfo> result = session.createQuery(query).list();
		    
		    XxWabProductinfo[] array = new XxWabProductinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findTrendSetProducts failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Complement Products by productID
	 */
	@SuppressWarnings("unchecked")
	public XxWabProductinfo[] findComplementProducts(int product) {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			String query = "SELECT pro FROM XxWabComplement a, XxWabProductinfo pro " +
					"where " +
					"a.MProductId = pro.MProductId " +
					"and a.xxWabComplementtype = ( "+
						"select b.xxWabComplementtype " +
					    "from XxWabComplement b "+
					    "where b.MProductId = " + product +
					 ") " +
					 "and a.MProductId <> " + product;
			
			List<XxWabProductinfo> result = session.createQuery(query).list();
		    
		    XxWabProductinfo[] array = new XxWabProductinfo[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findComplementProducts failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Home Page Photos
	 */
	@SuppressWarnings("unchecked")
	public XxWabHomepage[] findHomePageCovers() {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabHomepage> result = session.createCriteria(XxWabHomepage.class)
					.add(
						Restrictions.disjunction()
						.add(Restrictions.eq("isactive", "Y"))
						
					 ).addOrder(Order.asc("seqno")).list();
		    
		    XxWabHomepage[] array = new XxWabHomepage[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findHomePageCovers failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Costumer Service By Name
	 */
	public XxWabCostumerservice findCostumerServiceByName(String name) {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			XxWabCostumerservice result = (XxWabCostumerservice) session.createCriteria(XxWabCostumerservice.class)
												.add(Restrictions.eq("name", name).ignoreCase()
												 ).add(Restrictions.eq("isactive", "Y").ignoreCase()).uniqueResult();

		    return result;  
			
		} catch (RuntimeException re) {
			log.error("findCostumerServiceByName failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Costumer Service By Name
	 */
	@SuppressWarnings("unchecked")
	public XxWabCostumerservice[] findAllCostumerService() {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabCostumerservice> result = session.createCriteria(XxWabCostumerservice.class)
												.add(Restrictions.eq("isactive", "Y"))
												.addOrder(Order.asc("seqno")).list();
			
		    XxWabCostumerservice[] array = new XxWabCostumerservice[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findCostumerServiceByName failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
	
	/*
	 * Find Costumer Service By Name
	 */
	@SuppressWarnings("unchecked")
	public XxWabCostumerservice[] findAllCostumerServiceReturn() {

		Transaction trx = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();	
			trx = session.beginTransaction();
			
			List<XxWabCostumerservice> result = session.createCriteria(XxWabCostumerservice.class)
												.add(Restrictions.eq("isactive", "Y"))
												.add(Restrictions.eq("xxWabReturnandchanges", "Y")).list();
			
		    XxWabCostumerservice[] array = new XxWabCostumerservice[result.size()];
		    array = result.toArray(array);
		    
		    return array;  
			
		} catch (RuntimeException re) {
			log.error("findCostumerServiceByName failed", re);
			throw re;
		}
		finally{
			trx.commit();
		    session.close();
		}
	}
}
