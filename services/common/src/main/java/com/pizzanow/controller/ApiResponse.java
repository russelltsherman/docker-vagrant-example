package com.pizzanow.controller;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ApiResponse<T> {

	public String message;
	public boolean isError = false;
	public Throwable error;
	public T item;
	public List<T> items;
	
	public ApiResponse() {}
	
	public ApiResponse(String message) {
		this.message = message;
	}
	
	public ApiResponse(String message, Throwable e) {
		this(message);
		this.isError = true;
		this.error = e;
	}

	public ApiResponse(String message, T item) {
		this(message);
		this.item = item;
	}
	
	public ApiResponse(String message, List<T> items) {
		this(message);
		this.items = items;
	}
	
	@JsonGetter("itemType")
	public String itemType() {
		if (item != null) {
			return item.getClass().getSimpleName();
		} else if (!CollectionUtils.isEmpty(items)) {
			return items.get(0).getClass().getSimpleName();
		}
		return null;
	}
}
