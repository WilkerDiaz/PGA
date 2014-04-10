package com.abstracta.webstore;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.ws.bean.XxWabProductinfo;
import com.abc.ws.controller.WebServiceHelperProxy;
import com.abstracta.bean.Cart;
import com.abstracta.bean.CartLine;
import com.abstracta.bean.CartTotal;
import com.abstracta.bean.Customer;
import com.abstracta.bean.EmailReset;
import com.abstracta.bean.Newsletter;
import com.abstracta.bean.SessionHelper;
import com.abstracta.bean.WishList;
import com.abstracta.bean.WishListItemDetail;
import com.abstracta.bean.WishListLine;
import com.abstracta.enums.Action;
import com.abstracta.webstore.service.MailService;

/**
 * Handles Account Stuff
 * @author jtrias
 */
@Controller
public class AccountController{

	@Autowired
	ServletContext servletContext;
	int cookieExp = 5259487;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	
	@RequestMapping(value="/Cuenta/", method = RequestMethod.GET)
	public String account(Model model, Principal loggedUser) {
 
		logger.info("Cuenta");
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		SessionHelper sh = new SessionHelper();
		Customer user = sh.findUserByUserName(loggedUser.getName());
		
		model.addAttribute("userInfo", user);
		model.addAttribute("userName", user.getName());
		
		return "Account";
	}
	
	@RequestMapping(value="/Cuenta/Ordenes", method = RequestMethod.GET)
	public String orders(Model model, Principal loggedUser) {
 
		logger.info("Account / Orders");
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		SessionHelper sh = new SessionHelper();
		Customer user = sh.findUserByUserName(loggedUser.getName());
		
		model.addAttribute("userName", user.getName());
		model.addAttribute("orders", true);
		
		return "Account";
	}
	
	@Autowired
    MailService mailService;
	
	@RequestMapping(value="/Cuenta/Login")
	public String accountLogin(Model model) {
 
		logger.info("Login");
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		return "Login";
	}
	
	/*
	 * Failed to login
	 */
	@RequestMapping(value="/Cuenta/LoginFallido")
	public String loginError(Model model) {
 
		logger.info("Login fallido");
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		model.addAttribute("error", "true");
		return "Login";
	}

	@RequestMapping(value="/Cuenta/Logout", method = RequestMethod.GET)
	public String myLogout(Model model, HttpServletResponse response) {
 
		logger.info("redirect To Real Logout");
		
		return "redirect:/j_spring_security_logout";
	}
	
	/*
	 * Password forgotten
	 */
	@RequestMapping(value="/Cuenta/OlvideMiClave", method = RequestMethod.GET)
	public String forgotPassword(Model model, Principal user) {
 
		logger.info("Olvidé mi contraseña");
		
		if(user!=null)
			return "redirect:/Cuenta/";
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		return "ForgotPassword";
	}
	
	/*
	 * Customer Forgot Password
	 */
	@RequestMapping(value = "/Cuenta/OlvideMiClave", method = RequestMethod.POST)
    public String forgotPassword(
    		@ModelAttribute("newEmailReset") @Valid EmailReset emailReset,
            BindingResult result, Model model){
		
		logger.info("Forgot password POST - user: " + emailReset.getEmail());
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		if(result.hasErrors()) {
			return "ForgotPassword";
	    }
		
		SessionHelper sh = new SessionHelper();
		String message  = "";
		Customer customer = sh.findUserByUserName(emailReset.getEmail());
		if(customer!=null){
			
	        Map<String, Object> miniModel = new HashMap<String, Object>();
	        
	        if(customer.getSex().equals(Customer.Sex.FEMALE.getCode()))
	        	miniModel.put("greetings", "Estimada");
	        else
	        	miniModel.put("greetings", "Estimado");
			
	        miniModel.put("userName", customer.getName() + " " + customer.getLastname());
			miniModel.put("userMail", customer.getEmail());
			
			//Generates unique token for the password reset and the date when it expires.
	        String confirmToken = generateConfirmToken(32);
	        customer.setPasswordResetToken(confirmToken);
	        
	        Date dt = new Date();
	        Calendar c = Calendar.getInstance(); 
	        c.setTime(dt); 
	        c.add(Calendar.DATE, 1);
	        dt = c.getTime();
	        
	        customer.setPasswordResetExp(dt);
	        sh.attachDirty(customer);
			
	        miniModel.put("confirmToken", confirmToken);
			
	        //Mail Send for the customer with instructions
			mailService.sendMail(miniModel, "webstore@beco.com.ve", customer.getEmail(), "Restablecer Contraseña", "resetPassword", null, null);
			
			message = "Te hemos enviado un correo electrónico con la información para reiniciar tu contraseña";
		    model.addAttribute("successMessage", message);	
		    emailReset.setEmail("");
		}
		else{		
			message = "No hemos encontrado una cuenta asociada a esta dirección de correo";
			model.addAttribute("errorMessage", message);	
			
		}

		return "ForgotPassword";
	}
	
	@ModelAttribute("newEmailReset")
    public EmailReset getEmailResetForm() {
        return new EmailReset();
    }
	
	/*
	 * Reset customer Password
	 */
	@RequestMapping(value = "/Cuenta/RestablecerClave", method = RequestMethod.GET)
    public String resetPassword(Model model, 
    		@RequestParam(value = "address", required = false) String address, 
    		@RequestParam(value = "token", required = false) String token){
		
		logger.info("Reset password - user: " + address);
		
		SessionHelper sh = new SessionHelper();
		Customer customer = sh.findUserByUserName(address);
		
		String message  = "";
		
		if(customer!=null){
			
			if(!customer.getPasswordResetToken().isEmpty() && customer.getPasswordResetToken().equals(token)){
				
				//TODO EXPIRATION DATE
				if(1==1){
					return "ResetPassword";
				}else
				{
					message = "La solicitud para restablecer la contraseña expiró, debes realizar el proceso nuevamente";
					model.addAttribute("errorMessage", message);
					return "ForgotPassword";
				}
					
			}
		}
		
		message = "Este enlace ya no está disponible";
		model.addAttribute("errorMessage", message);
		
		return "ForgotPassword";
	}
	
	
	@ModelAttribute("newCustomer")
    public Customer getLoginForm() {
        return new Customer();
    }

	/*
	 * Costumer Registration Form
	 */
	@RequestMapping(value="/Cuenta/Registrate", method = RequestMethod.GET)
	public String customerRegistration(Model model, Principal user) {
 
		logger.info("Registrate");
		
		if(user!=null)
			return "redirect:/Cuenta/";
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		return "CustomerRegistration";
	}
	
	private static int workload = 12;
	
	/*
	 * Registro de Cliente POST (validacion de formulario)
	 */
    @RequestMapping(value = "/Cuenta/Registrate", method = RequestMethod.POST)
    public String validateForm(
        @ModelAttribute("newCustomer") @Valid Customer newCustomer,
        BindingResult result, Model model) {
    	
    	logger.info("Registrate: Procesando");
    	
    	//Unique Email registration
        if(!newCustomer.getEmail().isEmpty() && !checkUniqueEmail(newCustomer.getEmail())){
        	result.rejectValue("email", "DuplicatedEmail");
        }
        
        if(result.hasErrors()) {
            return "CustomerRegistration";
        }   
        
        SessionHelper sh = new SessionHelper();
        if(sh.findInactiveCustomerByName(newCustomer.getEmail())!= null || sh.findUserByUserName(newCustomer.getEmail())!=null){
        	//Carga del Menú Y Servicio al Cliente
    		StoreController storeController = new StoreController();
    		storeController.loadCostumerService(model);
    		
    		String message = "Estás a un solo paso de activar tu cuenta, por favor dirígete a tu correo electrónico y sigue las instrucciones que te acabamos de enviar";
    	    model.addAttribute("accountConfirmed", message);
    		
    		ImageController imgCont = new ImageController();
    	    model.addAttribute("randomImage", imgCont.getRandomImage());
    	    
            return "InfoMessage";
        }
       
        newCustomer.setUsername(newCustomer.getEmail());
        newCustomer.setActive('N');
        newCustomer.setCreated(new Date());
        
        //Password
        String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(newCustomer.getPassword(), salt);
        newCustomer.setPassword(hashed_password);
        
        sh.attachDirty(newCustomer);

   	 	//newsletter subscription
        if(newCustomer.isNewsletter())
        {
        	Newsletter subscription = sh.findNewsletterSubscription(newCustomer.getEmail());
       	
	       	if(subscription!=null)
	       		subscription.setActive('Y');
	       	else	
		        subscription = new Newsletter( newCustomer.getEmail(), true, new Date());
	       	
	       	sh.attachDirty(subscription);
        }	
 
		//Generates unique token for the account activation.
        String confirmToken = generateConfirmToken(32);
        newCustomer.setConfirmToken(confirmToken);
        sh.attachDirty(newCustomer);
        
		//sends confirmation mail to new user
        Map<String, Object> miniModel = new HashMap<String, Object>(); 
        
        if(newCustomer.getSex().equals(Customer.Sex.FEMALE.getCode()))
        	miniModel.put("greetings", "Estimada");
        else
        	miniModel.put("greetings", "Estimado");
        
        miniModel.put("userName", newCustomer.getName() + " " + newCustomer.getLastname());
		miniModel.put("userMail", newCustomer.getEmail());
		miniModel.put("confirmToken", confirmToken);
	    mailService.sendMail(miniModel, "webstore@beco.com.ve", newCustomer.getEmail(), "Registro Abstracta", "confirmation", null, null);
        
	    
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);
		
		String message = "Estás a un solo paso de activar tu cuenta, por favor dirígete a tu correo electrónico y sigue las instrucciones que te acabamos de enviar";
	    model.addAttribute("accountConfirmed", message);
		
		ImageController imgCont = new ImageController();
	    model.addAttribute("randomImage", imgCont.getRandomImage());
	    
        return "InfoMessage";
    }
	
	private boolean checkUniqueEmail(String email){
		
		SessionHelper sh = new SessionHelper();
		
		if(sh.findUserByUserName(email)!=null)
			return false;
		
		return true;
	}
	
	public String generateConfirmToken(int length)
	{
		String characters = "abcdefghijklmnopqrstuvwxyz1234167890ABCDEFGHIJKLMNOPQRSTUVWXYZ_.$";
		Random random = new Random();
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(random.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	@RequestMapping(value = "/Cuenta/Confirmar", method = RequestMethod.GET)
    public String confirmAccount(Model model, 
    		@RequestParam(value = "address", required = false) String address, 
    		@RequestParam(value = "token", required = false) String token){
		
		logger.info("Confirmar Cuenta - user: " + address);
		
		SessionHelper sh = new SessionHelper();
		Customer customer = sh.findInactiveCustomerByName(address);
		
		if(customer!=null){
			if(customer.getConfirmToken().equals(token)){
				customer.setActive('Y');
				sh.attachDirty(customer);
				String message = "Tu cuenta ha sido activada, ya puedes iniciar sesión";
				model.addAttribute("confirmAccount", message);
				model.addAttribute("attempUsername",customer.getEmail());
			}
		}
		
		return "Login";
	}
	
	@RequestMapping(value="/Cuenta/AgregarAFavoritos", method=RequestMethod.GET)
	public String addItemToWishListSecure(
			HttpServletRequest request, HttpServletResponse response, 
			Model model, Principal principal, @RequestParam("item") String item){
	    
		logger.info("Add Item to Wish List via Product");
		String idAux = item;
		
		Integer id = 0;
		
		if(idAux!=null && !idAux.equals(""))
			id = new Integer(idAux);
		
	    WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabProductinfo product = null;
		
		try {
			product = ws.findProductById(id);
		} catch (RemoteException e) {
			logger.error("Encontrar producto para añadirlo a favoritos: " + e.getMessage());
			return null;
		}
		
	    //Inserting the item on DB
	    SessionHelper sh = new SessionHelper();
	    Customer customer = sh.findUserByUserName(principal.getName());
	    WishList wishList = getWishList(customer, sh);
	    
	    if(wishList==null)
	    	wishList = createNewWishList(customer, sh);
	    
	    WishListLine wishListLine = sh.findWListLineByWList_Product(wishList.getWishListId(), (int)(long)product.getMProductId());
	    
	    String action = "";
	    if(wishListLine==null){
	    	insertItemOnWishList(wishList, product, sh);
	    	action = Action.WISHLIST_ADDED.getCode();
	    }
	    else
	    {
	    	wishListLine.setCreated(new Date());
	    	sh.attachDirty(wishListLine);
	    	action = Action.WISHLIST_UPDATED.getCode();
	    }
	    
	    String redirect = "redirect:" + "/productos/"+ product.getNameFormatted() +"/"+ item + "?action=" + action;
	    
	    return redirect;
	}
	
	@RequestMapping(value="/Cuenta/MoveItemToWishList", method=RequestMethod.GET)
	public String moveItemToWishListGet(
			HttpServletRequest request, HttpServletResponse response, 
			Model model, Principal principal, @RequestParam("cartLine") String cartLineParam){
		
		String action = Action.WISHLIST_ERROR.getCode();
		String redirect = "redirect:" + "/Carrito?action=" + action;
		
		if(principal==null){
			logger.info("Move Item to Wish List (GET) Not Logged!!!!");
			return redirect;
		}
		
		logger.info("Move Item to Wish List (GET)");
        
		String cartLineAux = cartLineParam;
		Integer cartLineID = 0;
		if(cartLineAux!=null)
			cartLineID = new Integer(cartLineAux);

		SessionHelper sh = new SessionHelper();
	    CartLine cartLine = (CartLine) sh.findById(CartLine.class, cartLineID);
	    
	    if(cartLine==null)
	    	return redirect;
	    
	    Cart cart = (Cart) sh.findById(Cart.class, cartLine.getCartId());
	    
	    if(cart==null)
	    	return redirect;
	    
	    Customer customer = sh.findUserByUserName(principal.getName());
	    if(cart.getCustomerId()!=customer.getCustomerId())
	    	return redirect;
	    
	    WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabProductinfo product = null;
		
		try {
			product = ws.findProductById(cartLine.getProductId());
		} catch (RemoteException e) {
			logger.error("Encontrar producto para añadirlo a favoritos: " + e.getMessage());
			return redirect;
		}
		
		//Inserting the item on DB
	    WishList wishList = getWishList(customer, sh);
	    
	    if(wishList==null)
	    	wishList = createNewWishList(customer, sh);
	    
	    WishListLine wishListLine = sh.findWListLineByWList_Product(wishList.getWishListId(), (int)(long)product.getMProductId());
	    if(wishListLine==null){
	    	insertItemOnWishList(wishList, product, sh);
	    	action = Action.WISHLIST_ADDED.getCode();
	    }
	    else
	    {
	    	wishListLine.setCreated(new Date());
	    	sh.attachDirty(wishListLine);
	    	action = Action.WISHLIST_UPDATED.getCode();
	    }
	    
	    //Se borra el detalle del carrito
	    sh.delete(cartLine);
	    
	    CartController cartCont = new CartController();
	    Integer counter = cartCont.getBasketQty(cart.getCartId());
	    
	    Cookie bsktCountCookie = new Cookie("BasketCnt", counter.toString());
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(cookieExp);
	    response.addCookie(bsktCountCookie);
	   
	    redirect = "redirect:" + "/Carrito?action=" + action;
	    
	    return redirect;
	}
	
	/*
	 * Remove Item from wish list
	 */
	@RequestMapping(value="/Cuenta/RemoveItemFromWishList", method=RequestMethod.POST)
	public @ResponseBody String removeItemFromCart(HttpServletRequest request, HttpServletResponse response, 
			Model model, Principal principal){
	    
		logger.info("Remove Item From Wish List");
		String idAux = request.getParameter("itemId");
		Integer id = 0;
		
		//Se verifica que idAux no sea nulo
		if(idAux!=null && !idAux.equals(""))
			id = new Integer(idAux);    
	    
	    //Inserting the item on DB
	    SessionHelper sh = new SessionHelper();
	    Customer customer = sh.findUserByUserName(principal.getName());
	    
	    WishListLine wishListLine = (WishListLine) sh.findById(WishListLine.class, id);
	    
	    if(wishListLine==null)
	    	return null;
	    
	    WishList wishList = (WishList) sh.findById(WishList.class, wishListLine.getWishListId());
	    
	    if(wishList==null || wishList.getCustomerId()!=customer.getCustomerId())
	    	return null;
	    
	    try{
	    	sh.delete(wishListLine);
	    }
	    catch(Exception e){
	    	logger.error("Cant delete: " + e.getMessage());
	    }
	   
	    return "";
	}
	
	@RequestMapping(value="/Cuenta/MoveItemToCart", method=RequestMethod.POST)
	public @ResponseBody String[] moveItemToCart(
			HttpServletRequest request, HttpServletResponse response, Model model, Principal principal){
		
		if(principal==null){
			logger.info("Move Item to Cart - Not Logged!!!!");
			return null;
		}
		
		logger.info("Move Item to Cart");
		
		String wlLineAux = request.getParameter("wlLineID");
		Integer wlLineID = 0;
		
		if(wlLineAux!=null)
			wlLineID = new Integer(wlLineAux);

		SessionHelper sh = new SessionHelper();
	    WishListLine wlLine = (WishListLine) sh.findById(WishListLine.class, wlLineID);
	    
	    if(wlLine==null)
	    	return null;
	    
	    WishList wishList = (WishList) sh.findById(WishList.class, wlLine.getWishListId());
	    
	    if(wishList==null)
	    	return null;
	    
	    Customer customer = sh.findUserByUserName(principal.getName());
	    if(wishList.getCustomerId()!=customer.getCustomerId())
	    	return null;
		
	    WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabProductinfo product = null;
		
		try {
			product = ws.findProductById(wlLine.getProductId());
		} catch (RemoteException e) {
			logger.error("Encontrar producto para añadirlo a favoritos: " + e.getMessage());
			return null;
		}
		
		//Inserting the item on Cart DB
	    Cart cart = getCart(customer, sh);
	    
	    if(cart==null)
	    	cart = createNewCart(customer, sh);
	    
	    String actionMsg = "";
	    insertItemOnCart(customer, cart, product, sh);
	    
	    actionMsg = "Artículo movido a tu carrito";
	    
	    //Se "borra" el detalle de favoritos
	    {	wlLine.setMovedToCart('Y');
	    	wlLine.setDateMovedToCart(new Date());
	    	sh.attachDirty(wlLine);
	    }
	    
	    CartController cartCont = new CartController();
	    Integer counter = cartCont.getBasketQty(cart.getCartId());
	    
	    Cookie bsktCountCookie = new Cookie("BasketCnt", counter.toString());
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(cookieExp);
	    response.addCookie(bsktCountCookie);
	    
	    String[] resp = {actionMsg, counter.toString()};
	    
	    return resp;
	}
	
	@RequestMapping(value="/Cuenta/MoveItemToWishListAjax", method=RequestMethod.POST)
	public @ResponseBody CartTotal moveItemToWishList(
			HttpServletRequest request, HttpServletResponse response, 
			Model model, Principal principal){
		
		if(principal==null){
			logger.info("Move Item to Wish List (Ajax) Not Logged!!!!");
			return null;
		}
		
		logger.info("Move Item to Wish List (Ajax)");
		
		String cartLineAux = request.getParameter("cartLineID");
		Integer cartLineID = 0;
		if(cartLineAux!=null)
			cartLineID = new Integer(cartLineAux);

		SessionHelper sh = new SessionHelper();
	    CartLine cartLine = (CartLine) sh.findById(CartLine.class, cartLineID);
	    
	    if(cartLine==null)
	    	return null;
	    
	    Cart cart = (Cart) sh.findById(Cart.class, cartLine.getCartId());
	    
	    if(cart==null)
	    	return null;
	    
	    Customer customer = sh.findUserByUserName(principal.getName());
	    if(cart.getCustomerId()!=customer.getCustomerId())
	    	return null;
		
	    WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabProductinfo product = null;
		
		try {
			product = ws.findProductById(cartLine.getProductId());
		} catch (RemoteException e) {
			logger.error("Encontrar producto para añadirlo a favoritos: " + e.getMessage());
			return null;
		}
		
		//Inserting the item on DB
	    WishList wishList = getWishList(customer, sh);
	    
	    if(wishList==null)
	    	wishList = createNewWishList(customer, sh);
	    
	    String actionMsg = "";
	    WishListLine wishListLine = sh.findWListLineByWList_Product(wishList.getWishListId(), (int)(long)product.getMProductId());
	    if(wishListLine==null){
	    	insertItemOnWishList(wishList, product, sh);
	    	actionMsg = "Artículo movido a tus favoritos";
	    }
	    else
	    {
	    	wishListLine.setCreated(new Date());
	    	sh.attachDirty(wishListLine);
	    	actionMsg = "Este artículo ya estaba en tus favoritos, lo colocamos al principio de la lista";
	    }
	    
	    //Se borra el detalle del carrito
	    sh.delete(cartLine);
	    
	    CartController cartCont = new CartController();
	    Integer counter = cartCont.getBasketQty(cart.getCartId());
	    	    
	    Cookie bsktCountCookie = new Cookie("BasketCnt", counter.toString());
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(cookieExp);
	    response.addCookie(bsktCountCookie);
	   
		//Get all items in cart
	    CartTotal totals = cartCont.getCartTotal(cart.getCartId(), null, null);
	    totals.setMoveToWishList(actionMsg);
	    
	    return totals;
	}
	
	private WishList getWishList(Customer customer, SessionHelper sh){
		
		return (WishList)sh.findWishListByCustomer(customer);
	}
	
	private WishList createNewWishList(Customer customer, SessionHelper sh){
		
		WishList newWishList = new WishList(new Date(), customer.getCustomerId());
		sh.attachDirty(newWishList);
		return newWishList;
	}
	
	private void insertItemOnWishList(WishList wishList, XxWabProductinfo product, SessionHelper sh){
		
		WishListLine wishListLine = new WishListLine(wishList.getWishListId(), (int) (long) product.getMProductId(), new Date());
		sh.attachDirty(wishListLine);
	}
	
	private Cart getCart(Customer customer, SessionHelper sh){
		
		return (Cart)sh.findCartByCustomer(customer);
	}
	
	private Cart createNewCart(Customer customer, SessionHelper sh){
		
		Cart newCart = new Cart(new Date(), customer.getCustomerId(), customer.getCustomerId(), new Date(), 2);
		sh.attachDirty(newCart);
		return newCart;
	}
	
	private void insertItemOnCart(Customer customer, Cart cart, XxWabProductinfo product, SessionHelper sh){
		
		CartLine cartLine = new CartLine(cart.getCartId(), (int) product.getMProductId(), 
							BigDecimal.valueOf(product.getFinalprice()), new Date(), customer.getCustomerId(), 1);
		sh.attachDirty(cartLine);
	}
	
	/*
	 * Wish List Controller
	 */
	@RequestMapping(value="/Cuenta/Favoritos", method=RequestMethod.GET)
	public String shoppingCart(HttpServletRequest request, HttpServletResponse response, 
						Model model, Principal user,@RequestParam(value = "from", defaultValue = "") String from){
	
		logger.info("Wish List");
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);		
		
		SessionHelper sh = new SessionHelper();
		Customer customer = sh.findUserByUserName(user.getName());
		
		WishList wishList = getWishList(customer, sh);
		
		if(wishList!=null){

			//Get all items in wish list
			ArrayList<WishListLine> wishListItems = getWishListItems(wishList);
			ArrayList<WishListItemDetail> wLItemDetails = new ArrayList<WishListItemDetail>();
			
			if(wishListItems.size()<=0){
				model.addAttribute("noItems", true);
				model.addAttribute("complements", false);
			}
			
			if(wishListItems!=null && wishListItems.size()>0){
				
				WebServiceHelperProxy ws = new WebServiceHelperProxy();
				XxWabProductinfo product = null;
				WishListItemDetail detail = null;
				
				for(int i=0; i<wishListItems.size(); i++){
					try {
						product = ws.findProductById(wishListItems.get(i).getProductId());
						
						//Si el producto es valido se agrega al detalle
						if(product != null){
							detail = new WishListItemDetail();
							detail.setCreated(wishListItems.get(i).getCreated());
							detail.setWishListId(wishListItems.get(i).getWishListId());
							detail.setWishListLineId(wishListItems.get(i).getWishListLineId());
							detail.setProductValue(wishListItems.get(i).getProductId());
							detail.setName(product.getName());
							detail.setNameFormatted(product.getNameFormatted());
							detail.setStockQty(((Long)product.getQty()).intValue());
							detail.setOriginalPrice(BigDecimal.ZERO);
							detail.setRegularPrice(BigDecimal.valueOf(product.getOriginalprice()));
							detail.setFinalPrice(BigDecimal.valueOf(product.getFinalprice()));
							
							wLItemDetails.add(detail);
						}
						
					} catch (RemoteException e) {
						logger.error("Encontrar producto para añadirlo a carrito: " + e.getMessage());
						return null;
					}
				}
			
				model.addAttribute("wishListItems", wLItemDetails);
			}
		}
		else{
			model.addAttribute("noItems", true);
			model.addAttribute("complements", false);
		}
		
		if(from!=null && from.equalsIgnoreCase("Account")){
			model.addAttribute("wishList", true);
			return "Account";
		}
		else
			return "WishList";
	}
	
	/*
	 * Get all the lines of the wish list
	 */
	private ArrayList<WishListLine> getWishListItems(WishList wishList){
		
		ArrayList<WishListLine> items = null;
		
		SessionHelper session = new SessionHelper();
		items = (ArrayList<WishListLine>) session.getWishListLines(wishList.getWishListId());
		
		return items;
	}
	   
}
