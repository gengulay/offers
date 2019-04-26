package com.gengulay.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gengulay.spring.web.dao.User;
import com.gengulay.spring.web.service.UserService;

@Controller
public class LoginController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/admin")
	public String getAdmin(Model model) {

		List<User> users = userService.getAllUsers();

		model.addAttribute("users", users);

		return "admin";

	}

	@RequestMapping("/accessdenied")
	public String getAccessdenied() {
		return "accessdenied";
	}

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@RequestMapping("/logout")
	public String getLogout() {
		return "logout";
	}

	@RequestMapping("/createaccount")
	public String getCreateAccount(Model model) {

		model.addAttribute("user", new User());
		return "createaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String postCreateAccount(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "createaccount";
		}

		user.setEnabled(true);
		user.setAuthority("ROLE_USER");

		if (userService.exist(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "createaccount";
		}

		try {
			userService.createUser(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "createaccount";
		}

		return "accountcreated";
	}

	@RequestMapping("/accountcreated")
	public String getAccountCreated() {
		return "accountcreated";
	}

}
