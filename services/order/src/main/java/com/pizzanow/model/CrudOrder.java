package com.pizzanow.model;

import javax.persistence.Entity;

import com.pizzanow.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "orders")
public class CrudOrder extends BaseEntity implements Order {

	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private Double orderTotal;
	private OrderStatus orderStatus = OrderStatus.New;

}
