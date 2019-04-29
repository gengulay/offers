package com.gengulay.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import com.gengulay.spring.web.dao.User;
import com.gengulay.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/gengulay/spring/web/config/dao-context.xml",
		"classpath:com/gengulay/spring/web/config/security-context.xml",
		"classpath:com/gengulay/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("kaido1234", "Beast Kaido", "bfadmin1", true, "ROLE_USER", "kaido@gmail.comm");
	private User user2 = new User("bigmom123", "Big Mom", "bfadmin1", true, "ROLE_ADMIN", "bigmom@gmail.comm");
	private User user3 = new User("shanks123", "Akagami Shanks", "bfadmin1", true, "ROLE_USER", "shanks@gmail.comm");
	private User user4 = new User("blackbeard", "Edward Teach", "bfadmin1", true, "ROLE_USER", "blackbeard@gmail.comm");

	@Before()
	public void init() {

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

	}

	@Test
	public void testCreateRetrieve() {
		usersDao.createUser(user1);
		List<User> yonkos = usersDao.getAllUsers();

		assertEquals("there should be 1 user by now on the database", 1, yonkos.size());
		assertEquals("inserted user should matched retrieved", user1, yonkos.get(0));

		
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		List<User> yonkos2 = usersDao.getAllUsers();

		assertEquals("should retrieved 4 yonkos", 4, yonkos2.size());
	}

	@Test
	public void testExist() {

		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		assertTrue("user should exist", usersDao.exists(user1.getUsername()));
		assertTrue("user should exist", usersDao.exists(user2.getUsername()));
		assertTrue("user should exist", usersDao.exists(user3.getUsername()));
		assertTrue("user should exist", usersDao.exists(user4.getUsername()));
		assertFalse("user should not exist", usersDao.exists("sadasdasd"));

	}

}
