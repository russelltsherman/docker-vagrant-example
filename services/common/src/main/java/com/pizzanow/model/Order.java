package com.pizzanow.model;

import com.pizzanow.enums.OrderStatus;

public interface Order extends Entity {
	public String getCustomerName();
	public void setCustomerName(String customerName);
	public String getCustomerEmail();
	public void setCustomerEmail(String customerEmail);
	public String getCustomerPhone();
	public void setCustomerPhone(String customerPhone);
	public Double getOrderTotal();
	public void setOrderTotal(Double orderTotal);
	public OrderStatus getOrderStatus();
	public void setOrderStatus(OrderStatus orderStatus);
	
	default String getDisplayName() {
		return String.format("Order for %s. Total: %.2f (ID=%s)", getCustomerName(), getOrderTotal(), getId());
	}
}
