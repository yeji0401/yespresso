package com.sh.yespresso.orders.model.dto;

import java.sql.Date;

/**
 * OrderDetailList
 */

public class Orders {

	private String orderNo;
	private String orderMemberId;
	private Date orderDate;
	private double totalPrice;
	private OrderState orderState; // b 결제완료 / d 배송중 / f 배송완료

	public Orders() {
		super();
	}

	public Orders(String orderNo, String orderMemberId, Date orderDate, double totalPrice, OrderState orderState) {
		super();
		this.orderNo = orderNo;
		this.orderMemberId = orderMemberId;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.orderState = orderState;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderMemberId() {
		return orderMemberId;
	}

	public void setOrderMemberId(String orderMemberId) {
		this.orderMemberId = orderMemberId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	@Override
	public String toString() {
		return "Orders [orderNo=" + orderNo + ", orderMemberId=" + orderMemberId + ", orderDate=" + orderDate
				+ ", totalPrice=" + totalPrice + ", orderState=" + orderState + "]";
	}

}
