package com.pizzanow.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.pizzanow.model.Entity;
import com.pizzanow.service.BaseService;

public abstract class BaseCrudController<T extends Entity> extends BaseController {

	protected abstract BaseService<T> getService();
	
	@RequestMapping(value = "/")
	public ApiResponse<T> all() {
		return new ApiResponse<T>("Success", Lists.newArrayList(getService().all()));
	}
	
	@RequestMapping(value = "/{id}")
	public ApiResponse<T> find(@PathVariable(value = "id") Long id) {
		return new ApiResponse<T>("Success", getService().find(id));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ApiResponse<T> create(@RequestBody T entity) {
		return new ApiResponse<T>("Success", getService().create(entity));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ApiResponse<T> update(@PathVariable(value = "id") Long id, @RequestBody T entity) {
		return new ApiResponse<T>("Success", getService().update(id, entity));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ApiResponse<T> delete(@PathVariable(value = "id") Long id) {
		getService().delete(id);
		return new ApiResponse<T>("Success");
	}
}
