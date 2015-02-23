package com.skripsi.apsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skripsi.apsb.entity.User;
import com.skripsi.apsb.service.RoleService;
import com.skripsi.apsb.service.UserService;

@Controller
@RequestMapping("/master")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/users/{page}", method = RequestMethod.GET)
	public String usersMenu(Model model, @PathVariable(value = "page") Integer page) {
		int size = 5;
		Page<User> users = userService.findAllByRolesUserLimit(page, size);

		int current = users.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, users.getTotalPages());

		model.addAttribute("users", users);
		model.addAttribute("current", current);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		return "users";
	}

	@RequestMapping(value = "/users/detail/{id}")
	public String userDetail(Model model, @PathVariable long id) {
		model.addAttribute("detail", userService.findOne(id));
		return "user-detail";
	}

	@RequestMapping(value = "/users/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		userService.delete(id);
		return "redirect:/master/users/1.apsb";
	}
}
