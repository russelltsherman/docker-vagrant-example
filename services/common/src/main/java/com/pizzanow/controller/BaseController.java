package com.pizzanow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {

	@ExceptionHandler(Throwable.class)
    @ResponseBody
    public ApiResponse<String> handleException(HttpServletRequest request, Throwable e) {
		String msg = e.getMessage() == null ? "no error message given" : e.getMessage();
		return new ApiResponse<String>(msg, e);
    }
}
