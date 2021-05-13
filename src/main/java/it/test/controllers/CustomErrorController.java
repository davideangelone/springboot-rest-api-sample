package it.test.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
	
	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    String error(HttpServletRequest request, HttpServletResponse response) {
    	return "";
    }

	@Override
	public String getErrorPath() {
		return PATH;
	}

}
