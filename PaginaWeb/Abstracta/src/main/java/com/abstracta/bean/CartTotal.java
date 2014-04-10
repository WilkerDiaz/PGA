package com.abstracta.bean;

import java.math.BigDecimal;

public class CartTotal {

	private BigDecimal merchandise;
	private BigDecimal tax;
	private BigDecimal shipping;
	private BigDecimal total;
	private BigDecimal itemPrice;
	private Integer totalQty;
	
	private String moveToWishList;

	public CartTotal() {
		
	}
	
	public BigDecimal getMerchandise() {
		return merchandise;
	}
	public void setMerchandise(BigDecimal merchandise) {
		this.merchandise = merchandise;
	}
	public BigDecimal getShipping() {
		return shipping;
	}
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	public String getMoveToWishList() {
		return moveToWishList;
	}

	public void setMoveToWishList(String moveToWishList) {
		this.moveToWishList = moveToWishList;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
}
