package com.abstracta.webstore.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.abstracta.bean.Cart;
import com.abstracta.bean.CartLine;
import com.abstracta.bean.Customer;
import com.abstracta.bean.SessionHelper;
import com.abstracta.webstore.CartController;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	int cookieExp = 5259487;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
    	
    	int cookieCartId = getCookieCart(request, response);
    	
    	SessionHelper sh = new SessionHelper();
    	Customer customer = sh.findUserByUserName(authentication.getName());
    	
    	//Customer Cart Update Only if has cookies with a Temp-Cart
    	if(cookieCartId > 0)
    		cartUpdate(customer, sh, cookieCartId, request, response);
    	
    	//Updates cart's item count
    	updateCartCount(customer, sh, request, response);    	
    	
    	//update last login info
    	String remoteAddress = request.getRemoteAddr();
    	customer.setLastIp(remoteAddress);
    	customer.setLastLogin(new Date());
    	sh.attachDirty(customer);
    	
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
    private void cartUpdate(Customer customer, SessionHelper sh, int cookieCartId, 
    		HttpServletRequest request, HttpServletResponse response) {
		
    	//Buscar Carrito del Cliente
    	Cart customerCart = sh.findCartByCustomer(customer);
    	
    	if(customerCart!=null)
    	{

    		List<CartLine> cartLines = sh.getCartLines(cookieCartId);
    		
    		if(cartLines.size()>0){
    			for(int i = 0; i<cartLines.size() ; i++){
    				cartLines.get(i).setCartId(customerCart.getCartId());
    				sh.attachDirty(cartLines.get(i));
    			}
    			
    			//Cookie Cart
    			Cart cookieCart = (Cart) sh.findById(Cart.class, cookieCartId);
    			sh.delete(cookieCart);
    		}
    	}
    	else
    	{
    		customerCart = (Cart) sh.findById(Cart.class, cookieCartId);
    		
    		if(customerCart!=null)
    		{
    			customerCart.setCustomerId(customer.getCustomerId());
    			customerCart.setStatusId(2);
    			sh.attachDirty(customerCart);
    		}
    	}
    	
    	Cookie bsktIdCookie = new Cookie("BasketID", "0");
		bsktIdCookie.setPath("/");
		bsktIdCookie.setMaxAge(0);
	    response.addCookie(bsktIdCookie);
	}
    
    private int getCookieCart(HttpServletRequest request, HttpServletResponse response){
    	
    	return new CartController().getBasketId(request, response);
    }
    
    /*
     *  Update Cart Counter Cookie
     */
    private void updateCartCount(Customer customer, SessionHelper sh, HttpServletRequest request, HttpServletResponse response){
    	
		Integer counter = 0;
		
		Cart customersCart = sh.findCartByCustomer(customer);
		
		if(customersCart!=null){
		    counter = sh.getCartItemQty(customersCart.getCartId());
	    }
	
	    Cookie bsktCountCookie = new Cookie("BasketCnt", counter.toString());
		bsktCountCookie.setPath("/");
		bsktCountCookie.setMaxAge(-1);
		response.addCookie(bsktCountCookie);
    }
}
