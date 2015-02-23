package com.skripsi.apsb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.service.UserService;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@ModelAttribute(value = "user")
	public User construct() {
		return new User();
	}

	@RequestMapping
	public String register() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute(value = "user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "register";
		}
		userService.register(user);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/register.apsb";
	}

	@RequestMapping(value = "/available")
	@ResponseBody
	public String available(@RequestParam String username) {
		Boolean available = userService.findOneByUsername(username) == null;
		return available.toString();
	}
}
