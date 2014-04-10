package com.abstracta.bean;

import java.util.Date;

public class WishListLine {
	
	private int wishListLineId;
	private int wishListId;
	private int productId;
	private Date created;
	private char movedToCart;
	private Date dateMovedToCart;
	
	public WishListLine() {
	
	}

	public WishListLine(int wishListId, int productId, Date created) {
	
		this.wishListId = wishListId;
		this.productId = productId;
		this.created = created;
		this.movedToCart = 'N';
	}

	public int getWishListLineId() {
		return wishListLineId;
	}

	public void setWishListLineId(int wishListLineId) {
		this.wishListLineId = wishListLineId;
	}

	public int getWishListId() {
		return wishListId;
	}

	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public char getMovedToCart() {
		return movedToCart;
	}

	public void setMovedToCart(char movedToCart) {
		this.movedToCart = movedToCart;
	}

	public Date getDateMovedToCart() {
		return dateMovedToCart;
	}

	public void setDateMovedToCart(Date dateMovedToCart) {
		this.dateMovedToCart = dateMovedToCart;
	}
	
}
