package com.abstracta.webstore;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abc.ws.bean.SingleClassInfo;
import com.abc.ws.bean.XxWabClassinfo;
import com.abc.ws.bean.XxWabCostumerservice;
import com.abc.ws.bean.XxWabHomepage;
import com.abc.ws.bean.XxWabProductinfo;
import com.abc.ws.controller.WebServiceHelperProxy;
import com.abstracta.bean.CartTotal;
import com.abstracta.bean.Newsletter;
import com.abstracta.bean.SessionHelper;
import com.abstracta.enums.Action;

/**
 * Handles requests for the application home page.
 * @author jtrias
 */
@Controller
public class StoreController {
	
	@Autowired
	ServletContext servletContext;
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	private List<XxWabClassinfo> globalMenu = null;
	private HashMap<Integer, HashMap<Integer, String>> menuMatrix = null;
	
	/**
	 * Shows the store's home.
	 */
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String home(Model model) {
		
		logger.info("Store: Home");
		
		//Color panels
		model.addAttribute("colorPanels", true);

		String slider = getSliderImages("h", 1);
		model.addAttribute("slider", slider);
		
		if(!loadMenu(model))
			return "Error";
		
		loadCostumerService(model);
		
		return "Store";
	}
	
	/**
	 * Search for products.
	 */
	@RequestMapping(value="/Search",method = RequestMethod.GET)
	public String search(Model model, @RequestParam(value = "query", defaultValue = "") String query) {
		
		logger.info("Store: search");
		String title = "búsqueda";
		
		ArrayList<String> path = new ArrayList<String>();
	    path.add("search");
		
		loadMenu(model);
		loadCostumerService(model);
		
		model.addAttribute("showRoom", true);
		
		int qtyFound = 0;
		//Show Room (search result)
		if(query!=null && query.trim().length()>2 && !query.trim().equals(""))
			qtyFound = getProductsForShowRoom(model, null, 0, query.trim(), null);
		
		if(qtyFound==0)
			model.addAttribute("noResults", true);
		
		//title of the store's page
		model.addAttribute("title", getTitle(title + " ("+qtyFound+")"));
		
		return "Store";
	}
	
	/**
	 * Promo products.
	 */
	@RequestMapping(value="/Promo", method = RequestMethod.GET)
	public String promo(Model model) {
		
		logger.info("Store: promo");
		String title = "Promoción";
		
		ArrayList<String> path = new ArrayList<String>();
	    path.add("promo");
		
		loadMenu(model);
		loadCostumerService(model);
		
		model.addAttribute("showRoom", true);
		
		int qtyFound = 0;
		qtyFound = getProductsForShowRoom(model, null, 2, null, null);
		
		if(qtyFound==0)
			model.addAttribute("noResults", true);
		
		//title of the store's page
		model.addAttribute("title", getTitle(title + " ("+qtyFound+")"));
		
		return "Store";
	}
	
	/**
	 * Shows the store by department level.
	 */
	@RequestMapping(value="/{dep}", method = RequestMethod.GET)
	public String storeLvl1(@PathVariable String dep, Model model) {
		
		logger.info("Store: Department: " + dep);
		int lvl = 1;
		ArrayList<String> path = new ArrayList<String>();
	    path.add(dep);

		//Loads the store's Menu
		loadMenu(model);
		loadCostumerService(model);
		
		//Validates the classification
		long lvlID = getLevelId(lvl, path);
		if(lvlID==0)
			return "pageNotFound";
		
		//title of the store's page
		model.addAttribute("title", getTitle(dep));
		
		//Loads the sub-categorys
		int subs = getSubCategorysForShowRoom(model, lvl, lvlID);
		
		if(subs>0){
			String slider = getSliderImages("d", 1);
			model.addAttribute("slider", slider);
		}
		else{
			//If no sub-categorys then it trys to get products from the requested one
			model.addAttribute("showRoom", true);
			
			//Show Room
			int qtyFound = getProductsForShowRoom(model, lvl, lvlID, null, path);

			if(qtyFound<4)
				model.addAttribute("bodySpace", true);
			
			if(qtyFound==0)
				model.addAttribute("noProducts", true);
		}
		
		return "Store";
	}
	
	/**
	 * Shows the store by section level.
	 */
	@RequestMapping(value="/{dep}/{sec}", method = RequestMethod.GET)
	public String storeLvl2(@PathVariable("dep") String dep, @PathVariable("sec") String sec, 
							Model model, String id) {
		
		logger.info("Store: Department + Section: " + dep +"-"+sec);
		int lvl = 2;
		ArrayList<String> path = new ArrayList<String>();
	    path.add(dep);
	    path.add(sec);
		
		//Loads the store's Menu
		loadMenu(model);
		loadCostumerService(model);
		
		//Validates the classification
		long lvlID = getLevelId(lvl, path);
		if(lvlID==0)
			return "pageNotFound";
		
		//title of the store's page
		model.addAttribute("title", getTitle(sec));
		
		//Loads the sub-categorys
		int subs = getSubCategorysForShowRoom(model, lvl, lvlID);
		
		if(subs>0){
			String slider = getSliderImages("d", 1);
			model.addAttribute("slider", slider);
		}
		else{
			//If no sub-categorys then it tries to get products from the requested one
			model.addAttribute("showRoom", true);
			
			//Show Room
			int qtyFound = getProductsForShowRoom(model, lvl, lvlID, null, path);

			if(qtyFound<4)
				model.addAttribute("bodySpace", true);
			
			if(qtyFound==0)
				model.addAttribute("noProducts", true);
		}
		
		return "Store";
	}
	
	
	/**
	 * Shows the store by type level. (last level of classification)
	 * @throws Exception 
	 */
	@RequestMapping(value="/{dep}/{sec}/{typ}", method = RequestMethod.GET)
	public String storeLvl3(@PathVariable("dep") String dep, @PathVariable("sec") String sec, 
							@PathVariable("typ") String typ,
							Model model) throws Exception {
		
		ArrayList<String> path = new ArrayList<String>();
	    path.add(dep);
	    path.add(sec);
	    path.add(typ);
	    
		logger.info("Store: Departmetn + Section + type : " + path);
		int lvl = 3;
		
		//Loads the store's menu
		loadMenu(model);
		loadCostumerService(model);
		
		//Validates the classification
		long lvlID = getLevelId(lvl, path);
		if(lvlID==0)
			return "pageNotFound";
		
		model.addAttribute("showRoom", true);
		
		//title of the store's page
		model.addAttribute("title", getTitle(typ));
		
		//Show Room
		int qtyFound = getProductsForShowRoom(model, lvl, lvlID, null, path);

		if(qtyFound<4)
			model.addAttribute("bodySpace", true);
		
		if(qtyFound==0)
			model.addAttribute("noProducts", true);
		
		return "Store";
	}
	
	
	/**
	 * Product Info
	 */
	@RequestMapping(value="/productos/{name}/{id}", method = RequestMethod.GET)
	public String product(@PathVariable("name") String name, @PathVariable Integer id,
				        Model model, HttpServletRequest request, HttpServletResponse response,
				        @RequestParam(value = "action", defaultValue = "") String action) {
		
		String path = "productos/" + name +"/"+ id;
		logger.info("productos: " + path);
		
		String redirect = productView(model, id, name);
		
		if(redirect==null){
			loadMenu(model);
			model.addAttribute("pageNotFound", true);
			loadCostumerService(model);
		}
		else if(!redirect.isEmpty())
			return redirect;
		
		if(action!=null){
			
			String actionMsg = "";
			
			if(action.equalsIgnoreCase(Action.WISHLIST_ADDED.getCode())){
				actionMsg = "Artículo agregado a tus favoritos";
				model.addAttribute("wishList", true);
			}
			else if(action.equalsIgnoreCase(Action.WISHLIST_UPDATED.getCode())){
				actionMsg = "Este artículo ya estaba en tus favoritos, lo colocamos al principio de la lista";
				model.addAttribute("wishList", true);
			}
			
			model.addAttribute("wishListMsg", actionMsg);
		}
		
		//title of the store's page
		model.addAttribute("title", getTitle(name));
		
		return "Store";
	}
	

	/**
	 * Page Not Found (error 404)  //TODO allways entering this
	 */
	@RequestMapping(value="/errors/404.html")
    public String handle404(Model model){
		
		model.addAttribute("pageNotFound", true);
		
		if(globalMenu!=null)
			model.addAttribute("menu", globalMenu);
		else
		{
			loadMenu(model);
			loadCostumerService(model);
		}
    	return "Store";
    }
	
	/**
	 * Info of the single product selected
	 */
	private String productView(Model model, long id, String name){
		
		WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabProductinfo product = null;
		try {
	
			product = ws.findProductById(id);
	
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		
		if(product==null)
			return null;
		
		if(!product.getNameFormatted().equalsIgnoreCase(name))
			return "redirect:" + "/productos/"+ product.getNameFormatted() +"/"+ id;
		
		//Loads menu and costumer Service
		loadMenu(model);
		loadCostumerService(model);
		
		//For the breadcrumb Path
		breadcrumb(product, model);
		
		//Product Photos
		int photoCount = getFolderItemCount("i", (int)id);
		
		//Trend Set (products in the same trend set)
		XxWabProductinfo[] trendSetProducts = null;
		try {
			trendSetProducts = ws.findTrendSetProducts((int)id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Complements (products in the same Complement Type)
		XxWabProductinfo[] complementProducts = null;
		try {
			complementProducts = ws.findComplementProducts((int)id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("trendSet", trendSetProducts);
		model.addAttribute("complements", complementProducts);
		model.addAttribute("productPhotos", photoCount);
		model.addAttribute("product", product);
		
		return "";
	}
	
	/*
	 * Path for breadcrumb (Navigation)
	 */
	private void breadcrumb(XxWabProductinfo product, Model model){
		
		//For Breadcrumb
		List<SingleClassInfo> subCategorys = new ArrayList<SingleClassInfo>();
		int subID = 0;
		String link = "";
		String target = "";
		String name = "";
		int lvl = 0;
		
		if(product.getClasslevel3Id()>0)
			lvl = 3;
		else if(product.getClasslevel2Id()>0)
			lvl = 2;
		else if(product.getClasslevel1Id()>0)
			lvl = 1;
		
		/*
		if(product.getClasslevel1Id()>0){
			
			name = menuMatrix.get(1).get((int)product.getClasslevel1Id());
			subID = (int)product.getClasslevel1Id();
			SingleClassInfo singleClass = new SingleClassInfo(name, subID, link, target);
			subCategorys.add(singleClass);
		}
		if(product.getClasslevel2Id()>0){
			name = menuMatrix.get(2).get((int)product.getClasslevel2Id());
			subID = (int)product.getClasslevel2Id();
			SingleClassInfo singleClass = new SingleClassInfo(name, subID, link, target);
			subCategorys.add(singleClass);
		}
		if(product.getClasslevel3Id()>0){
			name = menuMatrix.get(3).get((int)product.getClasslevel3Id());
			subID = (int)product.getClasslevel3Id();
			SingleClassInfo singleClass = new SingleClassInfo(name, subID, link, target);
			subCategorys.add(singleClass);
		}*/
		
		long id=0;
		SingleClassInfo singleClass = null;
		for(int i=1; i < (lvl+1); i++){
			
			if(i==1)
				id = product.getClasslevel1Id();
			if(i==2)
				id = product.getClasslevel2Id();
			if(i==3)
				id = product.getClasslevel3Id();
			
			name = menuMatrix.get(i).get((int)id);
			singleClass = new SingleClassInfo(name, subID, link, target);
			subCategorys.add(singleClass);
		}
	
		

		model.addAttribute("path", subCategorys);
	}
	
	/*
	 * Gets de ID for the level by name
	 */
	private long getLevelId(int lvl, List<String> path){
		
		Iterator<XxWabClassinfo> iterator = globalMenu.iterator();

		while (iterator.hasNext()) {
			   
			XxWabClassinfo element = iterator.next();
			
			if(lvl==1 && element.getClasslevel1NameFormatted().equalsIgnoreCase(path.get(lvl-1)))
				return element.getClasslevel1Id();
			
			if(lvl==2 && element.getClasslevel2NameFormatted().equalsIgnoreCase(path.get(lvl-1)))
				if(element.getClasslevel1NameFormatted().equalsIgnoreCase(path.get(lvl-2)))
					return element.getClasslevel2Id();
			
			if(lvl==3 && element.getClasslevel3NameFormatted().equalsIgnoreCase(path.get(lvl-1)))
				if(element.getClasslevel2NameFormatted().equalsIgnoreCase(path.get(lvl-2)))
					if(element.getClasslevel1NameFormatted().equalsIgnoreCase(path.get(lvl-3)))
					return element.getClasslevel3Id();
		}
		
		return 0;
	}

	/*
	 * Sub-Categorys Show Room
	 */
	private int getSubCategorysForShowRoom(Model model, int lvl, long id){
		
		WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabClassinfo[] subs = null;
		List<SingleClassInfo> subCategorys = new ArrayList<SingleClassInfo>();

		try {
			subs = ws.findClassByLevelID(lvl, id);
		} catch (RemoteException e) {
			logger.error("Sub-Categorys: " + e.getMessage());
		}
		
		if(subs==null || subs.length==0)
			return 0;
		
		//TODO validar esto por WS (en el sql)
		if(subs.length==1 && (subs[0].getClasslevel3Id()<0 || subs[0].getClasslevel2Id()<0))
			return 0;
		
		String link = "";
		
		String target = "";
		if((lvl+1) == 1)
			target = "d";
		else if((lvl+1) == 2)
			target = "s";
		else if((lvl+1) == 3)
			target = "t";

		long prevSub = 0;
		long subID = 0;
		String name = "";

		for(int i=0; i < subs.length; i++){
			

			if(lvl==1)
				subID = subs[i].getClasslevel2Id();
			
			if(lvl==2)
				subID = subs[i].getClasslevel3Id();
			
			if(prevSub == subID)
				continue;
			else{
				
				
				if(lvl==1){
					name = subs[i].getClasslevel2Name();
					id = subs[i].getClasslevel2Id();
					link = "/"+ subs[i].getClasslevel1NameFormatted() +"/"+ 
							subs[i].getClasslevel2NameFormatted();
				}
				
				if(lvl==2){
					name = subs[i].getClasslevel3Name();
					link = "/"+ subs[i].getClasslevel1NameFormatted() +"/"+ 
						   subs[i].getClasslevel2NameFormatted() +"/"+ 
						   subs[i].getClasslevel3NameFormatted();
				}
				
				SingleClassInfo singleClass = new SingleClassInfo(name, subID, link, target);
				subCategorys.add(singleClass);
			}
			
			if(lvl==1)
				prevSub = subs[i].getClasslevel2Id();
			if(lvl==2)
				prevSub = subs[i].getClasslevel3Id();
		}
		
		model.addAttribute("showRoomSubCategorys", subCategorys);
		return subCategorys.size();
	}
	
	/*
	 * The product Show Room
	 */
	private int getProductsForShowRoom(Model model, Integer lvl, long id, String search, ArrayList<String> path){
		
		WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabProductinfo[] products = null;
		try {
			
			if(id==1)
				products = ws.findProductsForGift();
			else if(id==2)
				products = ws.findPromoProducts();
			else if(lvl!=null)
				products = ws.findProductByLevelID(lvl, id);
			else
				products = ws.findProductByNameDesc(search);
		
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		
		//breadcrumbs (Navigation)
		if(id==1){
			//fake product to pass the search values to the breadcrumb
			XxWabProductinfo product = new XxWabProductinfo();
			product.setClasslevel1Id(1);
			breadcrumb(product, model);	
		}
		else if(lvl!=null)
			
			if(products!=null)
				breadcrumb(products[0], model);
			else{
				
				//fake product to pass the search values to the breadcrumb
				XxWabProductinfo product = new XxWabProductinfo();
				
				if(lvl==1)
					product.setClasslevel1Id(id);
				if(lvl==2){
					product.setClasslevel1Id(getLevelId(1, path));
					product.setClasslevel2Id(id);
				}
				if(lvl==3){
					product.setClasslevel1Id(getLevelId(1, path));
					product.setClasslevel2Id(getLevelId(2, path));
					product.setClasslevel3Id(id);
				}
				
				breadcrumb(product, model);	
			}
		
		else{
			//fake product to pass the search values to the breadcrumb
			XxWabProductinfo product = new XxWabProductinfo();
			product.setClasslevel1Id(10);
			breadcrumb(product, model);
		}
		
		if(products==null)
			return 0;
		
		model.addAttribute("showRoomProducts", products);
		
		return products.length; 
	}
	
	/*
	 * Get the images to put in the slide show
	 */
	private String getSliderImages(String target, int id){
		
		String images = "";
		int weith = 1900;
		int height = 700;
		String path = "/Images/"+target+"/"+weith+"/"+height;
		
		if(target.equals("h")){
		
			WebServiceHelperProxy ws = new WebServiceHelperProxy();
			XxWabHomepage[] homeCovers = null;
			
			try {
				homeCovers = ws.findHomePageCovers();
			} catch (RemoteException e) {
				logger.error("findHomePageCovers -> getSliderImages");
				e.printStackTrace();
			}

			for(int i=0; i < homeCovers.length; i++){
				
				images += "<li>";
				images += "<img src=\""+ path + "/"+ homeCovers[i].getXxWabHomepageId() + "/1\" /> ";
									 
				if(homeCovers[i].getUrl()!=null){
					images += "<a href=\""+ homeCovers[i].getUrl() +"\">"; 
					images += "<img class=\"orbit-cover\" src=\""+ path + "/"+ homeCovers[i].getXxWabHomepageId() + "/2\" /> ";
					images += "</a>";
				}	 		 
				
				images += "</li>";
			}

		}else{
		
			//Goes for the photos to show
			int photos = getFolderItemCount(target, id);
			for(int i=1; i <= photos; i++){
				
				images += "<li>";
				images += "<img src=\""+ path + "/"+ id + "/" + (i) +"\" /> ";
				images += "</li>";
			}
		}
	
		return images;
	}
	
	/*
	 * Formatts the title of the view
	 */
	private String getTitle(String myTitle){
		
		myTitle = myTitle.replace("-", " ");
		
		String []words = myTitle.split("\\s+");
        String textoFormateado = "";
        
        for(String word : words){
            textoFormateado += word.substring(0,1).toUpperCase()
        	    	.concat( word.substring(1, word.length())
        		.toLowerCase()).concat(" ");
        }
		
		return textoFormateado;
	}
	
	/*
	 * Goes for the menu data and constructs the menu
	 */
	
	protected boolean loadMenu(Model model){
	
		WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabClassinfo[] classes = null;
		List<XxWabClassinfo> list = null;
		try {
			classes = ws.findAll();
			list = new LinkedList<XxWabClassinfo>(Arrays.asList(classes));
			
			//TODO menu global vs instanciacion Constante
			globalMenu = list;
			
			//Gift
			XxWabClassinfo gift = new XxWabClassinfo();
			gift.setClasslevel1Id(1);
			gift.setClasslevel1Name("regalo");
			list.add(gift);
			
			//All the values in the matrix (helper)
			putInMatrix();
			
		} catch (RemoteException e) {
			logger.error("Menu: " + e.getMessage());
			return false;
		}
		
		model.addAttribute("menu", list);
		
		return true;
	}
	
	/*
	 * Goes for all the costumer service data 
	 */
	
	protected boolean loadCostumerService(Model model){
	
		WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabCostumerservice[] services = null;
	
		try {
			services = ws.findAllCostumerService();
			
		} catch (RemoteException e) {
			logger.error("Costumer Services Load: " + e.getMessage());
			return false;
		}
		
		model.addAttribute("costumerservice", services);
		
		return true;
	}
	
	protected String unformatName(String formatted){
		
		return formatted.replace("-", " ");
	}
	
	/*
	 * Put the menu in a Matrix to have at hand the values of the categorization.
	 */
	private void putInMatrix(){
		
		if(globalMenu==null)
			return;
	
		HashMap<Integer, String> lvl1 = new HashMap<Integer, String>();
		HashMap<Integer, String> lvl2 = new HashMap<Integer, String>();
		HashMap<Integer, String> lvl3 = new HashMap<Integer, String>();
		
		// while loop
		int i = 0;
		while (i < globalMenu.size()) {
			
			lvl1.put((int)globalMenu.get(i).getClasslevel1Id(), globalMenu.get(i).getClasslevel1Name());
			lvl2.put((int)globalMenu.get(i).getClasslevel2Id(), globalMenu.get(i).getClasslevel2Name());
			if(globalMenu.get(i).getClasslevel3Id()>0)
				lvl3.put((int)globalMenu.get(i).getClasslevel3Id(), globalMenu.get(i).getClasslevel3Name());
			i++;
		}
		
		//we have to add the "search" category (just to breadcrumb purpose)
		lvl1.put(10, "busqueda");
		
		menuMatrix = new HashMap<Integer, HashMap<Integer, String>>();
		menuMatrix.put(1, lvl1);
		menuMatrix.put(2, lvl2);
		menuMatrix.put(3, lvl3);
	}
	
	/*
	 * How many items in a folder
	 */
	
	public int getFolderItemCount(String target, int id){
		
		String folder = "resources/web/images/catalog/";
		
		//Could be N targets like: Item, Department, Section, Home (folders to look up for photos)
		if(target.equalsIgnoreCase("i"))
			folder += "item";
		else if(target.equalsIgnoreCase("h"))
			folder += "home";
		else if(target.equalsIgnoreCase("d"))
			folder += "department";
		else if(target.equalsIgnoreCase("s"))
			folder += "section";
		else if(target.equalsIgnoreCase("t"))
			folder += "type";
		else if(target.equalsIgnoreCase("p"))
			folder += "promo";
		
		//Se obtiene el directorio donde estan las imagenes
		File directory = new File(servletContext.getRealPath(folder+"/"+id));
		
		if(directory.list()==null || directory.list().length == 0)
			return 0;
		else
			return directory.list().length;
	}
	
	/*
	 * Add email to the newsletter list
	 */
	@RequestMapping(value="/Newsletter/Subscribe", method=RequestMethod.POST)
	public @ResponseBody String[] subscribeNewsletter(HttpServletRequest request, HttpServletResponse response, Model model){
	    
		logger.info("Newsletter Subscription");
		
		String email = request.getParameter("email");
		email = email.trim();
	    
		if(!isValidEmailAddress(email)){
			String[] message = {"errorNewsletter",
								"Por favor ingresa una direccion de correo válida."
							  + "<br>(ejmp: nombre@dominio.com)"};
	    	return message;
		}
		
		SessionHelper sh = new SessionHelper();
		Newsletter subscription = sh.findNewsletterSubscription(email);
       	
       	if(subscription!=null){
       		subscription.setActive('Y');
           	sh.attachDirty(subscription);
     
           	String[] message = {"successNewsletter",
			           			"¡Bienvenido de vuelta!"
			           			+ "<br>"
			           			+ "Ya teníamos tu dirección de correo registrada, "
			           			+ "así que seguirás siendo el primero en enterarte "
			           			+ "de nuestros nuevos productos y ofertas especiales."};
           	
           	return message;	
       	}
       	else{	
	        subscription = new Newsletter( email, true, new Date());
	        sh.attachDirty(subscription);
	        String[] message = {"successNewsletter",
	        					"¡Gracias!"
	        				  + "<br>"
	        				  + "Tu email ha sido agregado a nuestra lista."};
	        return message;
       	}
	}
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/*
	 * Validate Email Address
	 */
	public boolean isValidEmailAddress(String email){
		Pattern p = Pattern.compile(EMAIL_PATTERN);
		Matcher m = p.matcher(email);
	    return m.matches();
	}
}
