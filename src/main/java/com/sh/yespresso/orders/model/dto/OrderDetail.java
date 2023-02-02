package com.sh.yespresso.orders.model.dto;

public class OrderDetail {
	private int orderDetailNo;
	private String orderNo; // FK_ORDER_NO
	private String detailProductNo;
	private int orderDetailAmount;
//	awon start
	private String productName;
	private int productPrice;
	// awon end

	public OrderDetail() {
		super();
	}

	// awon start
	public OrderDetail(int orderDetailNo, String orderNo, String detailProductNo, int orderDetailAmount,
			String productName, int productPrice) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.orderNo = orderNo;
		this.detailProductNo = detailProductNo;
		this.orderDetailAmount = orderDetailAmount;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	// awon end

	public OrderDetail(int orderDetailNo, String orderNo, String detailProductNo, int orderDetailAmount) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.orderNo = orderNo;
		this.detailProductNo = detailProductNo;
		this.orderDetailAmount = orderDetailAmount;
	}

	public int getOrderDetailNo() {
		return orderDetailNo;
	}

	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDetailProductNo() {
		return detailProductNo;
	}

	public void setDetailProductNo(String detailProductNo) {
		this.detailProductNo = detailProductNo;
	}

	public int getOrderDetailAmount() {
		return orderDetailAmount;
	}

	public void setOrderDetailAmount(int orderDetailAmount) {
		this.orderDetailAmount = orderDetailAmount;
	}
	
	//awon start

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
	//awon end

	@Override
	public String toString() {
		return "OrderDetail [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", detailProductNo="
				+ detailProductNo + ", orderDetailAmount=" + orderDetailAmount + "]";
	}

}
