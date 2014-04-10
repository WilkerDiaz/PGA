package com.abstracta.webstore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abc.ws.bean.XxWabProductinfo;
import com.abc.ws.controller.WebServiceHelperProxy;
import com.abstracta.bean.Cart;
import com.abstracta.bean.CartItemDetail;
import com.abstracta.bean.CartLine;
import com.abstracta.bean.CartTotal;
import com.abstracta.bean.Customer;
import com.abstracta.bean.SessionHelper;
import com.abstracta.enums.Action;

/**
 * Handles cart stuff
 * @author jtrias
 */
@Controller
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	int cookieExp = 5259487;
	
	@RequestMapping(value="/Pago", method = RequestMethod.GET)
	public String payment(Model model, Principal principal){
	
		logger.info("Pago");
		
		String name = principal.getName();
		model.addAttribute("username", name);
		
		return "Payment";
	}
	
	@RequestMapping("/Carrito")
	public String shoppingCart(HttpServletRequest request, HttpServletResponse response, 
						Model model, @RequestParam(value = "action", defaultValue = "") String action){
	
		//TODO all the function
		logger.info("Shopping Cart");
		
		//Carga del Menú Y Servicio al Cliente
		StoreController storeController = new StoreController();
		storeController.loadCostumerService(model);		
		
		Integer cartId = getBasketId(request, response);
		
		BigDecimal merchandise = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal shipping = BigDecimal.ZERO;
		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		
		//Get all items in cart
		ArrayList<CartLine> cartItems = getCartItems(cartId);
		ArrayList<CartItemDetail> caetItemDetails = new ArrayList<CartItemDetail>();
		
		if(cartItems.size()<=0){
			model.addAttribute("noItems", true);
		}
		
		if(cartItems!=null && cartItems.size()>0){
			WebServiceHelperProxy ws = new WebServiceHelperProxy();
			XxWabProductinfo product = null;
			CartItemDetail detail = null;
			for(int i=0; i<cartItems.size(); i++){
				try {
					product = ws.findProductById(cartItems.get(i).getProductId());
					
					//Si el producto es valido se agrega al detalle
					if(product != null){
						detail = new CartItemDetail();
						detail.setCartId(cartItems.get(i).getCartId());
						detail.setCartLineId(cartItems.get(i).getCartLineId());
						detail.setProductId(cartItems.get(i).getProductId());
						detail.setProductValue(product.getValue());
						detail.setName(product.getName());
						detail.setNameFormatted(product.getNameFormatted());
						detail.setDesiredQty(cartItems.get(i).getQty());
						detail.setStockQty(((Long)product.getQty()).intValue());
						detail.setOriginalPrice(cartItems.get(i).getOriginalPrice());
						detail.setRegularPrice(BigDecimal.valueOf(product.getOriginalprice()));
						detail.setFinalPrice(BigDecimal.valueOf(product.getFinalprice()));
						detail.setTotalPrice(detail.getFinalPrice().multiply(BigDecimal.valueOf(detail.getDesiredQty())));
						
						caetItemDetails.add(detail);
						
						BigDecimal priceNoTax = BigDecimal.ZERO;
						BigDecimal taxAmount = BigDecimal.ZERO;
						BigDecimal taxRate = BigDecimal.ONE.add(BigDecimal.valueOf(product.getTaxrate()).divide(BigDecimal.valueOf(100)));
						BigDecimal qty = BigDecimal.valueOf(cartItems.get(i).getQty());
						BigDecimal shippingRate = BigDecimal.valueOf(100);
						BigDecimal finalPrice = detail.getFinalPrice();
						
						priceNoTax = finalPrice.divide(taxRate, RoundingMode.HALF_UP);
						taxAmount = finalPrice.subtract(priceNoTax);
						subtotal = subtotal.add(finalPrice.multiply(qty));
						
						priceNoTax = priceNoTax.multiply(qty);
						taxAmount = taxAmount.multiply(qty);
						
						merchandise = merchandise.add(priceNoTax);
						tax = tax.add(taxAmount);
						shipping = shipping.add(shippingRate.multiply(qty));
					}
					
				} catch (RemoteException e) {
					logger.error("Encontrar producto para añadirlo a carrito: " + e.getMessage());
					return null;
				}
			}
		
			model.addAttribute("cartItems", caetItemDetails);
			model.addAttribute("totalQty", getBasketQty(cartId).toString());
			
			//Total Amounts
			total = subtotal.add(shipping);
			
			model.addAttribute("tax", tax);
			model.addAttribute("merchandise", merchandise);
			model.addAttribute("shipping", shipping);
			model.addAttribute("total", total);
		}
		
		//Wish List
		if(action!=null){
			
			String actionMsg = "";
			
			if(action.equalsIgnoreCase(Action.WISHLIST_ADDED.getCode())){
				actionMsg = "Artículo movido a tus favoritos";
				model.addAttribute("wishList", true);
			}
			else if(action.equalsIgnoreCase(Action.WISHLIST_UPDATED.getCode())){
				actionMsg = "Este artículo ya estaba en tus favoritos, lo colocamos al principio de la lista";
				model.addAttribute("wishList", true);
			}
			
			model.addAttribute("wishListMsg", actionMsg);
		}
		
		return "Cart";
	}
	
	
	/*
	 * Get all the lines of the Shooping Cart
	 */
	private ArrayList<CartLine> getCartItems(Integer cartId){
		
		ArrayList<CartLine> items = null;
		
		SessionHelper session = new SessionHelper();
		items = (ArrayList<CartLine>) session.getCartLines(cartId);
		
		return items;
	}
	
	@RequestMapping(value="/AddItem", method=RequestMethod.POST)
	public @ResponseBody ModelAndView addItemToCart(HttpServletRequest request, HttpServletResponse response, Model model){
	    
		logger.info("Add Item to Cart");
		String idAux = request.getParameter("itemId");
		String qtyAux = request.getParameter("itemQty");
		Integer qty = 1;
		Integer id = 0;
		
		if(qtyAux!=null && !qtyAux.equals(""))
			qty = new Integer(qtyAux);
		
		if(idAux!=null && !idAux.equals(""))
			id = new Integer(idAux);
		
		Integer cartID = getBasketId(request, response);
	    
	    WebServiceHelperProxy ws = new WebServiceHelperProxy();
		XxWabProductinfo product = null;
		try {
			product = ws.findProductById(id);
		} catch (RemoteException e) {
			logger.error("Encontrar producto para añadirlo a carrito: " + e.getMessage());
			return null;
		}
		
		//Se guardan los valores en el modelo
		model.addAttribute("qty", qty);
	    model.addAttribute("itemAdded", product);
		
	    //Inserting the item on DB
	    Integer newCartID = insertItemToCart(cartID, id, product, qty, request);
	    
	    if(cartID==0)
	    	cartID = newCartID;
	    
	    //Setting Cookies
	    Integer count = 0;
	    count = getBasketQty(cartID);
	    
	    Cookie bsktIdCookie = new Cookie("BasketID", cartID.toString());
		bsktIdCookie.setMaxAge(cookieExp);
		bsktIdCookie.setPath("/");
	    response.addCookie(bsktIdCookie);
	    
	    Cookie bsktCountCookie = new Cookie("BasketCnt", count.toString());
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(cookieExp);
	    response.addCookie(bsktCountCookie);
	    
	    //Brief of the cart amount TODO
	    CartTotal cartTotal = getCartTotal(cartID, null, null);
	    model.addAttribute("cartTotal", cartTotal);
	    
	    //view
	    ModelAndView view = new ModelAndView("AddItem");
        view.addObject(model);
	   
	    return view;
	}
	
	public CartTotal getCartTotal(int cartId, Integer id, Integer itemQty){
		
		CartTotal totals = new CartTotal();
		BigDecimal merchandise = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal shipping = BigDecimal.ZERO;
		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal itemPrice = BigDecimal.ZERO;
		Integer totalQty = 0;
		
		//Get all items in cart
		ArrayList<CartLine> cartItems = getCartItems(cartId);
		
		if(cartItems!=null){
			WebServiceHelperProxy ws = new WebServiceHelperProxy();
			XxWabProductinfo product = null;
			for(int i=0; i<cartItems.size(); i++){
				try {
					product = ws.findProductById(cartItems.get(i).getProductId());
					
					if(id!=null && itemQty!=null && cartItems.get(i).getCartLineId().equals(id)){
						itemPrice = BigDecimal.valueOf(itemQty).multiply(BigDecimal.valueOf(product.getFinalprice()));
					}
					
					//Si el producto es valido se agrega al detalle
					if(product != null){
						
						BigDecimal priceNoTax = BigDecimal.ZERO;
						BigDecimal taxAmount = BigDecimal.ZERO;
						BigDecimal taxRate = BigDecimal.ONE.add(BigDecimal.valueOf(product.getTaxrate()).divide(BigDecimal.valueOf(100)));
						BigDecimal finalPrice = BigDecimal.valueOf(product.getFinalprice());
						BigDecimal qty = BigDecimal.valueOf(cartItems.get(i).getQty());
						BigDecimal shippingRate = BigDecimal.valueOf(100);
						
						priceNoTax = finalPrice.divide(taxRate, RoundingMode.HALF_UP);
						taxAmount = finalPrice.subtract(priceNoTax);
						
						priceNoTax = priceNoTax.multiply(qty);
						taxAmount = taxAmount.multiply(qty);
						
						merchandise = merchandise.add(priceNoTax);
						tax = tax.add(taxAmount);
						subtotal = subtotal.add(finalPrice.multiply(qty));
						
						//TODO SHIPPING REAL RATE
						shipping = shipping.add(shippingRate.multiply(qty));
	
						totalQty = totalQty + (qty.intValue());
					}
					
				} catch (RemoteException e) {
					logger.error("Encontrar producto para añadirlo a carrito: " + e.getMessage());
					return null;
				}
			}
		}
		
		//Mercancia + Envio
		totalAmount = subtotal.add(shipping);
		
		totals.setTotalQty(totalQty);
		totals.setItemPrice(itemPrice);
		totals.setMerchandise(merchandise);
		totals.setShipping(shipping);
		totals.setTotal(totalAmount);
		totals.setTax(tax);
		
		return totals;
	}
	

	/*
	 * Remove Item from cart
	 */
	@RequestMapping(value="/RemoveItem", method=RequestMethod.POST)
	public @ResponseBody CartTotal removeItemFromCart(HttpServletRequest request, HttpServletResponse response, Model model){
	    
		//TODO all the function
		logger.info("Remove Item From Cart");
		String idAux = request.getParameter("itemId");
		Integer id = 0;
		
		//Se verifica que idAux no sea nulo
		if(idAux!=null && !idAux.equals(""))
			id = new Integer(idAux);    
		
	    //Delete the item on DB
	    SessionHelper session = new SessionHelper();
	    try{
	    	session.delete(session.findById(CartLine.class, id));
	    }
	    catch(Exception e){
	    	logger.error("Cant delete: " + e.getMessage());
	    }
	    
	    Integer cartId = getBasketId(request, response);
	    Integer counter = getBasketQty(cartId);
	    
	    Cookie bsktCountCookie = new Cookie("BasketCnt", counter.toString());
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(cookieExp);
	    response.addCookie(bsktCountCookie);
	   
		//Get all items in cart
	    CartTotal totals = getCartTotal(cartId, null, null);
		
	    return totals;
	}
	
	@RequestMapping(value="/UpdateItem", method=RequestMethod.POST)
	public @ResponseBody CartTotal updateItemQty(HttpServletRequest request, HttpServletResponse response, Model model){
	    
		//TODO all the function
		logger.info("Update Item Qty");
		String idAux = request.getParameter("itemId");
		String qtyAux = request.getParameter("itemQty");
		Integer id = 0;
		Integer itemQty = 1;
		
		try{
			itemQty = new Integer(qtyAux);
		}
		catch(Exception e)
		{
			logger.error("qty to update on cart item");
		}
		
		if(idAux!=null && !idAux.equals(""))
			id = new Integer(idAux);
		
	    //Update Item on DB
	    SessionHelper session = new SessionHelper();
	    CartLine cartLine = (CartLine)session.findById(CartLine.class, id);
	    cartLine.setQty(itemQty);
	    session.attachDirty(cartLine);
	    
	    Integer cartId = getBasketId(request, response);
	    Integer counter = getBasketQty(cartId);
	    
	    Cookie bsktCountCookie = new Cookie("BasketCnt", counter.toString());
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(cookieExp);
	    response.addCookie(bsktCountCookie);
	   
	    CartTotal totals = getCartTotal(cartId, id, itemQty);
		
	    return totals;
	}
	
	public Integer getBasketId(HttpServletRequest request, HttpServletResponse response){
		
		boolean logged = request.getUserPrincipal()!=null;

		Integer cartID = 0;
		SessionHelper sh = new SessionHelper();
		
		if(!logged)
		{
			Cookie[] cookies = request.getCookies();
			
			if(cookies==null)
				return 0;
			
		    for (Cookie cookie : cookies) {
		        
		    	if(cookie.getName().equals("BasketID")){
		    		
		    		if(cookie.getValue().isEmpty())
						return 0;
		    		
		        	cartID = new Integer(cookie.getValue());
		        }
		    }
		    
		    if(cartID>0)
		    {
		    	Cart cart = (Cart) sh.findById(Cart.class, cartID);
		    	
		    	if(cart!=null){
			    	if(cart.getCustomerId()!=null && cart.getCustomerId()>0)
			    	{
			    		cartID = 0;
			    		deleteCookies(response);
			    	}
		    	}
		    	else
		    		cartID = 0;
		    }
		    	
	    }
		else
		{
			Customer customer = sh.findUserByUserName(request.getUserPrincipal().getName());
			Cart cart = (Cart) sh.findCartByCustomer(customer);
			
			if(cart!=null)
				cartID = cart.getCartId();
		}
		
	    return cartID;
	}
	
	
	private void deleteCookies(HttpServletResponse response){
		
		Cookie bsktIdCookie = new Cookie("BasketID", "0");
		bsktIdCookie.setMaxAge(0);
		bsktIdCookie.setPath("/");
	    response.addCookie(bsktIdCookie);
	    
	    Cookie bsktCountCookie = new Cookie("BasketCnt", "0");
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(0);
	    response.addCookie(bsktCountCookie);
	}
	
	private Integer insertItemToCart(Integer cartId, Integer id, XxWabProductinfo product, Integer qty, HttpServletRequest request){
		
		SessionHelper sh = new SessionHelper();
		Cart tempCart;
		Date now = new Date();
		
		if(cartId==null || cartId==0){
			
		    tempCart = new Cart();
		    
		    boolean logged = request.getUserPrincipal()!=null;
		    
		    if(!logged)
		    {
		    	tempCart.setCustomerId(0);
		    	tempCart.setStatusId(1);
		    	tempCart.setCreatedby(0);
		    	tempCart.setCustomerId(0);
		    }
		    else
		    {
		    	Customer customer = sh.findUserByUserName(request.getUserPrincipal().getName());
		    	tempCart.setCreatedby(customer.getCustomerId());
		    	tempCart.setCustomerId(customer.getCustomerId());
		    	tempCart.setStatusId(2);
		    }
		    
		    tempCart.setCreated(now);
		    tempCart.setUpdated(now);
		    
	    }else{
	    	tempCart = (Cart) sh.findById(Cart.class, cartId);
	    	tempCart.setUpdated(now);
	    }
		
		sh.attachDirty(tempCart);
		
	    //Linea
	    CartLine cartLine = new CartLine();
	    cartLine.setCartId(tempCart.getCartId());
	    cartLine.setCreated(now);
	    cartLine.setCreatedby(0);
	    cartLine.setProductId(id);
	    cartLine.setQty(qty);
	    cartLine.setOriginalPrice(BigDecimal.valueOf(product.getFinalprice()));
	    sh.save(cartLine);
	    
	    return tempCart.getCartId();
	}
	
	public Integer getBasketQty(Integer cartId){
		
		SessionHelper session = new SessionHelper();
		Integer qty = 0;
	    
		qty = session.getCartItemQty(cartId);
		
	    return qty;
	}
}
