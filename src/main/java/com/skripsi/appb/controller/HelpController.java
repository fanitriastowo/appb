package com.skripsi.apsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController {

	@RequestMapping(value = "/help-user")
	public String help() {
		return "help-user";
	}
	
	@RequestMapping(value = "/help-admin")
	public String helpAdmin(){
		return "help-admin";
	}
	
	@RequestMapping(value = "/help-default")
	public String helpDefault(){
		return "help-default";
	}
}
