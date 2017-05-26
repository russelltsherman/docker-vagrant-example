package com.pizzanow.service;

public interface QueueHandler<T> {

	public void receive(final String json);
	public Long pendingSize();
	public Long processingSize();
	public T next();
	public void complete(Long id);
}
