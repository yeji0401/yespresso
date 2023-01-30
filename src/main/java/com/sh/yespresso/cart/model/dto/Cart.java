package com.sh.yespresso.cart.model.dto;

public class Cart {

	private int cartNo;
	private String cartMemberId;
	private String cartProductNo;
	private int amount;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartNo, String cartMemberId, String cartProductNo, int amount) {
		super();
		this.cartNo = cartNo;
		this.cartMemberId = cartMemberId;
		this.cartProductNo = cartProductNo;
		this.amount = amount;
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

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", cartMemberId=" + cartMemberId + ", cartProductNo=" + cartProductNo
				+ ", amount=" + amount + "]";
	}

}
