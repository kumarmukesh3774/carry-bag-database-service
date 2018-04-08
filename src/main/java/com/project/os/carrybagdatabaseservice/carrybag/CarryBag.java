package com.project.os.carrybagdatabaseservice.carrybag;

import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CarryBag {
	
	@Id
	private String userId;
	
	private String offerId;

	private String offerImage;

	private String offerTitle;
	
	private Long offerOriginalPrice;
	
	private Long offerDiscount;

	private String offerValidity;
	
	private String vendorId;

	public CarryBag() {
		super();
	}

	public CarryBag(String userId, String offerId, String offerImage, String offerTitle,
			Long offerOriginalPrice,Long offerDiscount, String offerValidity,
			String vendorId) {
		super();
		this.userId = userId;
		this.offerId = offerId;
		this.offerImage = offerImage;
		this.offerTitle = offerTitle;
		this.offerOriginalPrice=offerOriginalPrice;
		this.offerDiscount=offerDiscount;
		this.offerValidity = offerValidity;
		this.vendorId = vendorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOfferImage() {
		return offerImage;
	}

	public void setOfferImage(String offerImage) {
		this.offerImage = offerImage;
	}

	public String getOfferTitle() {
		return offerTitle;
	}
	
	public Long getOfferOriginalPrice() {
		return offerOriginalPrice;
	}

	public void setOfferOriginalPrice(Long offerOriginalPrice) {
		this.offerOriginalPrice = offerOriginalPrice;
	}

	public Long getOfferDiscount() {
		return offerDiscount;
	}

	public void setOfferDiscount(Long offerDiscount) {
		this.offerDiscount = offerDiscount;
	}

	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}

	public String getOfferValidity() {
		return offerValidity;
	}

	public void setOfferValidity(String offerValidity) {
		this.offerValidity = offerValidity;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}



}
