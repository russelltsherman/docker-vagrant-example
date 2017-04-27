package com.pizzanow.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pizzanow.model.Order;

@Service
public class OrderRestClient extends BaseRestClient<Order> {
	@Autowired
	protected RestTemplate restTemplate;
	
	@Value("${order.base.url}")
	protected String orderBaseUrl;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public String getBaseUrl() {
		return orderBaseUrl;
	}
}
