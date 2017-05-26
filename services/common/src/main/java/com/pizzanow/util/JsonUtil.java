package com.pizzanow.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.pizzanow.model.Order;

public class JsonUtil {

	public static Order parseAsOrder(String orderJson) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new MrBeanModule());
		try {
			Order order = mapper.readValue(orderJson, Order.class);
			return order;
		} catch(Exception e) {
			throw new RuntimeException("Error while deserializing Order JSON", e);
		}
	}

	public static String convertOrder(Order o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			throw new RuntimeException("Error while serializing Order", e);
		}
	}

}
