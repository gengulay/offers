package com.gengulay.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gengulay.spring.web.dao.Message;
import com.gengulay.spring.web.dao.MessagesDao;
import com.gengulay.spring.web.dao.OffersDao;
import com.gengulay.spring.web.dao.User;
import com.gengulay.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/gengulay/spring/web/config/dao-context.xml",
		"classpath:com/gengulay/spring/web/config/security-context.xml",
		"classpath:com/gengulay/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private MessagesDao messagesDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("kaido123", "Beast Kaido", "bfadmin1", true, "ROLE_USER", "kaido@gmail.comm");
	private User user2 = new User("bigmom123", "Big Mom", "bfadmin1", true, "ROLE_ADMIN", "bigmom@gmail.comm");
	private User user3 = new User("shanks123", "Akagami Shanks", "bfadmin1", true, "ROLE_USER", "shanks@gmail.comm");
	private User user4 = new User("blackbeard", "Edward Teach", "bfadmin1", false, "ROLE_USER",
			"blackbeard@gmail.comm");

	private Message message1 = new Message("Test Subject 1", "Test content 1", "Isaac Newton",
			"isaac@caveofprogramming.comm", user1.getUsername());
	private Message message2 = new Message("Test Subject 2", "Test content 2", "Isaac Newton",
			"isaac@caveofprogramming.comm", user1.getUsername());
	private Message message3 = new Message("Test Subject 3", "Test content 3", "Isaac Newton",
			"isaac@caveofprogramming.comm", user2.getUsername());

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testSaveMessage() {

		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		Message m1 = new Message("Test Subject", "Test Content", "Akagami Shanks", "akagamishanks@gmail.comm",
				user1.getUsername());

		messagesDao.createOrUpdateMessage(m1);

	}

	@Test
	public void testDelete() {
		usersDao.createUser(user1);
		usersDao.createUser(user2);

		messagesDao.createOrUpdateMessage(message1);
		messagesDao.createOrUpdateMessage(message2);
		messagesDao.createOrUpdateMessage(message3);

		List<Message> messages = messagesDao.getAllMessagesByUsername(user1.getUsername());

		for (Message message : messages) {
			Message retrieved = messagesDao.getMessageById(message.getId());
			assertEquals(message, retrieved);
		}

		Message toDelete = messages.get(1);

		assertNotNull("This message not deleted yet.", messagesDao.getMessageById(toDelete.getId()));

		messagesDao.deleteMessageById(toDelete.getId());

		assertNull("This message was deleted.", messagesDao.getMessageById(toDelete.getId()));
	}

}
