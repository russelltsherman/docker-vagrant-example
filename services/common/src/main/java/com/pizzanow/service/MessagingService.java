package com.pizzanow.service;

public interface MessagingService<T> {
	void push(String destinationQueue, T entity);
}
