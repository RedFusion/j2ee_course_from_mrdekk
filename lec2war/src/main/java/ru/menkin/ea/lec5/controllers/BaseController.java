package ru.menkin.ea.lec5.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.menkin.ea.lec5.web.model.responses.ErrorResponse;

public class BaseController {
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorResponse handleError(HttpServletRequest req, Exception exception) 
	{
		ErrorResponse response = new ErrorResponse();
//		response.setMessage(exception.getLocalizedMessage());
		response.setMessage(exception.toString());
		response.setErrorCode(exception.hashCode());
		return response;
	}
}