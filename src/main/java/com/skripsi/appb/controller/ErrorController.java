package com.skripsi.apsb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

	@RequestMapping
	public String errorPage(Model model, HttpServletRequest request) {

		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		switch (statusCode) {
		case 400:
			model.addAttribute("messageProperty", "Error 400 - Bad Request");
			break;
		case 403:
			model.addAttribute("messageProperty", "Error 403 - Access Denied");
			break;
		case 404:
			model.addAttribute("messageProperty", "Error 404 - Resources Not Found");
			break;
		case 500:
			model.addAttribute("messageProperty", "Error 500 - Internal Server Error");
			break;
		default:
			model.addAttribute("messageProperty", "Unknown Error");
		}

		String requestUrl = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUrl == null) {
			requestUrl = "Unknown";
		}

		model.addAttribute("statusCode", statusCode);
		model.addAttribute("requestUrl", requestUrl);
		return "error";
	}
}
