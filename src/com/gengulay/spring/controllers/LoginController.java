package com.gengulay.spring.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gengulay.spring.web.dao.FormValidationGroup;
import com.gengulay.spring.web.dao.Message;
import com.gengulay.spring.web.dao.User;
import com.gengulay.spring.web.service.UserService;

@Controller
public class LoginController {

	private UserService userService;

	@Autowired
	private SimpleMailMessage mail;

	@Autowired
	private MailSender mailSender;

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

	@RequestMapping("/messages")
	public String getMessages() {
		return "messages";
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
	public String postCreateAccount(@Validated(FormValidationGroup.class) User user, BindingResult result,
			Model model) {
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

	@ResponseBody
	@RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
	public Map<String, Object> getMessagesByUsername(Principal principal) {

		List<Message> messages = null;

		if (principal == null) {
			messages = new ArrayList<Message>();
		} else {
			String username = principal.getName();
			messages = userService.getMessagesByUsername(username);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());

		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data) {

		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");

		System.out.println(email);

		mail = new SimpleMailMessage();
		mail.setFrom("gengulay@gmail.com");
		mail.setTo(email);
		mail.setSubject("Re: " + name + ", your message");
		mail.setText(text);

		try {
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't send message " + e.getMessage());
		}

		Map<String, Object> rval = new HashMap<String, Object>();
		rval.put("success", true);
		rval.put("target", target);

		return rval;
	}

}
