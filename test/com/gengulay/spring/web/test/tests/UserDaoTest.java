package com.gengulay.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
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

	@Before()
	public void init() {

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");

	}

	@Test
	public void testCreateUser() {

		User user = new User("gengulay", "bfadmin1", true, "ROLE_USER", "gengulay@gmail.com");

		assertTrue("User creattion should return true", usersDao.createUser(user));

		List<User> users = usersDao.getAllUsers();

		assertEquals("number of users should be correct", 1, users.size());

		assertTrue("user should exist", usersDao.exists(user.getUsername()));

		assertEquals("Created User should be identical to retrieve", user, users.get(0));

	}

}
