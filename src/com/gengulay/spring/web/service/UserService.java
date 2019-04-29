package com.gengulay.spring.web.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.gengulay.spring.web.dao.Message;
import com.gengulay.spring.web.dao.MessagesDao;
import com.gengulay.spring.web.dao.User;
import com.gengulay.spring.web.dao.UsersDao;

@Service("usersService")
public class UserService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private MessagesDao messagesDao;

	public void createUser(@Valid User user) {
		usersDao.createUser(user);

	}

	public boolean exist(String username) {
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}

	public void sendMessage(Message message) {
		messagesDao.createOrUpdateMessage(message);
	}

	public User getUserByUsername(String username) {
		return usersDao.getUserByUsername(username);

	}

	public List<Message> getMessagesByUsername(String username) {
		return messagesDao.getAllMessagesByUsername(username);
	}

}
