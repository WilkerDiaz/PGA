package com.abstracta.bean;

import java.util.Date;

public class WishList {

	private int wishListId;
	private Date created;
	private int customerId;
	
	public WishList() {
		
	}
	
	public WishList(int wishListId, Date created, int customerId) {
		this.wishListId = wishListId;
		this.created = created;
		this.customerId = customerId;
	}
	
	public WishList(Date created, int customerId) {
		this.created = created;
		this.customerId = customerId;
	}

	public int getWishListId() {
		return wishListId;
	}

	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
