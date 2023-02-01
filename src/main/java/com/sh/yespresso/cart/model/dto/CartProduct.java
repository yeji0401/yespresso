package com.sh.yespresso.cart.model.dto;

public class CartProduct {

	private int cartNo;
	private String cartMemberId;
	private String cartProductNo;
	private int amount;
	private String productName;
	private int productPrice;
	private int totalPrice;

	public CartProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartProduct(int cartNo, String cartMemberId, String cartProductNo, int amount, String productName,
			int productPrice, int totalPrice) {
		super();
		this.cartNo = cartNo;
		this.cartMemberId = cartMemberId;
		this.cartProductNo = cartProductNo;
		this.amount = amount;
		this.productName = productName;
		this.productPrice = productPrice;
		this.totalPrice = totalPrice;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getCartMemberId() {
		return cartMemberId;
	}

	public void setCartMemberId(String cartMemberId) {
		this.cartMemberId = cartMemberId;
	}

	public String getCartProductNo() {
		return cartProductNo;
	}

	public void setCartProductNo(String cartProductNo) {
		this.cartProductNo = cartProductNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartProduct [cartNo=" + cartNo + ", cartMemberId=" + cartMemberId + ", cartProductNo=" + cartProductNo
				+ ", amount=" + amount + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", totalPrice=" + totalPrice + "]";
	}

}
