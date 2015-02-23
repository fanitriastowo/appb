package com.skripsi.apsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/loginfail")
	public String login(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("success", false);
		return "redirect:/login.apsb";
	}
}
