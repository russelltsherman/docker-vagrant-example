package com.pizzanow.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pizzanow.service.QueueHandler;

public abstract class BaseQueueController<T> extends BaseController {
	public abstract QueueHandler<T> getQueueHandler();
	
	@RequestMapping(value = "/pendingCount")
	public ApiResponse<Long> pendingCount() {
		return new ApiResponse<Long>("Pending count", getQueueHandler().pendingSize());
	}
	
	@RequestMapping(value = "/processingCount")
	public ApiResponse<Long> processingCount() {
		return new ApiResponse<Long>("Processing count", getQueueHandler().processingSize());
	}
	
	@RequestMapping(value = "/next")
	public ApiResponse<T> getNext() {
		return new ApiResponse<T>("Next item in queue", getQueueHandler().next());
	}
	
	@RequestMapping(value = "/complete/{id}", method = RequestMethod.PUT)
	public ApiResponse<String> deliver(@PathVariable("id") Long id) {
		getQueueHandler().complete(id);
		return new ApiResponse<String>("Item completed");
	}
}
