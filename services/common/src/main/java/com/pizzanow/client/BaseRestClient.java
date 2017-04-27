package com.pizzanow.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pizzanow.controller.ApiResponse;

public abstract class BaseRestClient<T> {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	public abstract RestTemplate getRestTemplate();
	
	public abstract String getBaseUrl();
	
	public T update(Long id, T item) {
		HttpEntity<T> entity = new HttpEntity<T>(item);
		String url = getBaseUrl() + "/" + id;
		log.info("Calling " + url);
		ResponseEntity<ApiResponse<T>> r = getRestTemplate().exchange(url, HttpMethod.PUT, entity, getResponseType());
		return r.getBody().item;
	}
	
	public ParameterizedTypeReference<ApiResponse<T>> getResponseType() {
		return new ParameterizedTypeReference<ApiResponse<T>>(){};
	}
}
