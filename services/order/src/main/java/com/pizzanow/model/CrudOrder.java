package com.pizzanow.model;

import javax.persistence.Entity;

import com.pizzanow.enums.OrderStatus;

@Entity(name = "orders")
public class Order extends BaseEntity {

	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private Double orderTotal;
	private OrderStatus orderStatus = OrderStatus.New;
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String getDisplayName() {
		return String.format("Order for %s. Total: %.2f (ID=%s)", customerName, orderTotal, id);
	}

}
