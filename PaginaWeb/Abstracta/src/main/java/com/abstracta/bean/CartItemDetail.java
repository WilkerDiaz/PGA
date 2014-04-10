package com.abstracta.bean;

import java.math.BigDecimal;

public class CartItemDetail {

	private Integer cartLineId;
	private Integer cartId;
	private Integer desiredQty;
	private Integer stockQty;
	private Integer productId;
	private String productValue;
	private BigDecimal originalPrice;
	private BigDecimal regularPrice;
	private BigDecimal finalPrice;
	private String name;
	private String nameFormatted;
	private BigDecimal totalPrice;
	
	public CartItemDetail(){
		
	}

	public CartItemDetail(Integer cartLineId, Integer cartId,
			Integer desiredQty, Integer stockQty, Integer productId,
			BigDecimal originalPrice, BigDecimal regularPrice,
			BigDecimal finalPrice, String name, String nameFormatted) {
		
		this.cartLineId = cartLineId;
		this.cartId = cartId;
		this.desiredQty = desiredQty;
		this.stockQty = stockQty;
		this.productId = productId;
		this.originalPrice = originalPrice;
		this.regularPrice = regularPrice;
		this.finalPrice = finalPrice;
		this.name = name;
		this.nameFormatted = nameFormatted;
	}

	public Integer getCartLineId() {
		return cartLineId;
	}

	public void setCartLineId(Integer cartLineId) {
		this.cartLineId = cartLineId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getDesiredQty() {
		return desiredQty;
	}

	public void setDesiredQty(Integer desiredQty) {
		this.desiredQty = desiredQty;
	}

	public Integer getStockQty() {
		return stockQty;
	}

	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(BigDecimal regularPrice) {
		this.regularPrice = regularPrice;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFormatted() {
		return nameFormatted;
	}

	public void setNameFormatted(String nameFormatted) {
		this.nameFormatted = nameFormatted;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductValue() {
		return productValue;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}
}
