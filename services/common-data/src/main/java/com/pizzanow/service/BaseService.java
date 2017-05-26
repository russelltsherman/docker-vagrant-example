package com.pizzanow.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.google.common.collect.Lists;
import com.pizzanow.model.Entity;

public abstract class BaseService<T extends Entity> {
	
	protected abstract CrudRepository<T, Long> getRepository();
	
	public List<T> all() {
		return Lists.newArrayList(getRepository().findAll());
	}
	
	public T find(Long id) {
		return getRepository().findOne(id);
	}
	
	public T create(T entity) {
		return getRepository().save(entity);
	}
	
	public T update(Long id, T entity) {
		entity.setId(id);
		return getRepository().save(entity);
	}
	
	public void delete(Long id) {
		getRepository().delete(id);
	}
}
