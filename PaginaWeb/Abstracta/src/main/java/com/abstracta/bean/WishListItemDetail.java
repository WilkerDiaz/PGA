package com.abstracta.bean;

import java.math.BigDecimal;
import java.util.Date;

public class WishListItemDetail {

		private Integer wishListLineId;
		private Integer wishListId;
		private Integer desiredQty;
		private Integer stockQty;
		private Integer productValue;
		private BigDecimal originalPrice;
		private BigDecimal regularPrice;
		private BigDecimal finalPrice;
		private String name;
		private String nameFormatted;
		private Date created;
		
		public WishListItemDetail(){
			
		}

		public WishListItemDetail(Integer wishListLineId, Integer wishListId,
				Integer desiredQty, Integer stockQty, Integer productValue,
				BigDecimal originalPrice, BigDecimal regularPrice,
				BigDecimal finalPrice, String name, String nameFormatted) {
			super();
			this.wishListLineId = wishListLineId;
			this.wishListId = wishListId;
			this.desiredQty = desiredQty;
			this.stockQty = stockQty;
			this.productValue = productValue;
			this.originalPrice = originalPrice;
			this.regularPrice = regularPrice;
			this.finalPrice = finalPrice;
			this.name = name;
			this.nameFormatted = nameFormatted;
		}
		
		public Integer getWishListLineId() {
			return wishListLineId;
		}

		public void setWishListLineId(Integer wishListLineId) {
			this.wishListLineId = wishListLineId;
		}

		public Integer getWishListId() {
			return wishListId;
		}

		public void setWishListId(Integer wishListId) {
			this.wishListId = wishListId;
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

		public Integer getProductValue() {
			return productValue;
		}

		public void setProductValue(Integer productValue) {
			this.productValue = productValue;
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

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}
	}