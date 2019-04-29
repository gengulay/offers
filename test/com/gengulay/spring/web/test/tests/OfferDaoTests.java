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

import com.gengulay.spring.web.dao.Offer;
import com.gengulay.spring.web.dao.OffersDao;
import com.gengulay.spring.web.dao.User;
import com.gengulay.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/gengulay/spring/web/config/dao-context.xml",
		"classpath:com/gengulay/spring/web/config/security-context.xml",
		"classpath:com/gengulay/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("kaido123", "Beast Kaido", "bfadmin1", true, "ROLE_USER", "kaido@gmail.comm");
	private User user2 = new User("bigmom123", "Big Mom", "bfadmin1", true, "ROLE_ADMIN", "bigmom@gmail.comm");
	private User user3 = new User("shanks123", "Akagami Shanks", "bfadmin1", true, "ROLE_USER", "shanks@gmail.comm");
	private User user4 = new User("blackbeard", "Edward Teach", "bfadmin1", false, "ROLE_USER",
			"blackbeard@gmail.comm");

	private Offer offer = new Offer(user1, "this is a test offer");
	private Offer offer1 = new Offer(user2, "this is a test offer 1");
	private Offer offer2 = new Offer(user3, "this is a test offer 2");
	private Offer offer3 = new Offer(user4, "this is a test offer 3");
	private Offer offer4 = new Offer(user3, "this is a test offer 4");
	private Offer offer5 = new Offer(user2, "this is a test offer 5");
	private Offer offer6 = new Offer(user3, "this is a test offer 6");
	private Offer offer7 = new Offer(user4, "this is a test offer 7");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

	}

	@Test
	public void testGetOfferById() {

		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		offersDao.createOrUpdateOffer(offer);
		offersDao.createOrUpdateOffer(offer1);
		offersDao.createOrUpdateOffer(offer2);
		offersDao.createOrUpdateOffer(offer3);
		offersDao.createOrUpdateOffer(offer4);
		offersDao.createOrUpdateOffer(offer5);
		offersDao.createOrUpdateOffer(offer6);
		offersDao.createOrUpdateOffer(offer7);

		Offer offer = offersDao.getOfferById(offer2.getId());
		assertEquals("Offers should match", offer2, offer);

		Offer offer7retrieved = offersDao.getOfferById(offer7.getId());
		assertNull("Offer 7 should be null", offer7retrieved);

	}

	@Test
	public void testDeleteOffer() {
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		offersDao.createOrUpdateOffer(offer);
		offersDao.createOrUpdateOffer(offer1);
		offersDao.createOrUpdateOffer(offer2);
		offersDao.createOrUpdateOffer(offer3);
		offersDao.createOrUpdateOffer(offer4);
		offersDao.createOrUpdateOffer(offer5);
		offersDao.createOrUpdateOffer(offer6);
		offersDao.createOrUpdateOffer(offer7);

		Offer offer = offersDao.getOfferById(offer2.getId());
		assertNotNull("offer with ID: " + offer.getId() + " should be null or deleted", offer);

		offersDao.deleteOfferById(offer2.getId());
		Offer offer1 = offersDao.getOfferById(offer2.getId());
		assertNull("offer with ID: " + offer.getId() + " should be null or deleted", offer1);

	}

	@Test
	public void testUpdateOffer() {
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		offersDao.createOrUpdateOffer(offer);
		offersDao.createOrUpdateOffer(offer1);
		offersDao.createOrUpdateOffer(offer2);
		offersDao.createOrUpdateOffer(offer3);
		offersDao.createOrUpdateOffer(offer4);
		offersDao.createOrUpdateOffer(offer5);
		offersDao.createOrUpdateOffer(offer6);
		offersDao.createOrUpdateOffer(offer7);

		offer1.setText("asdasdasdasdasdasdas");
		offersDao.createOrUpdateOffer(offer1);

		Offer subject = offersDao.getOfferById(offer1.getId());

		assertEquals("the offer text should be updated", offer1.getText(), subject.getText());

	}

	@Test
	public void testGetOffersByUsername() {
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		offersDao.createOrUpdateOffer(offer);
		offersDao.createOrUpdateOffer(offer1);
		offersDao.createOrUpdateOffer(offer2);
		offersDao.createOrUpdateOffer(offer3);
		offersDao.createOrUpdateOffer(offer4);
		offersDao.createOrUpdateOffer(offer5);
		offersDao.createOrUpdateOffer(offer6);
		offersDao.createOrUpdateOffer(offer7);

		List<Offer> offers = offersDao.getAllOffersByUsername(user3.getUsername());

		assertEquals("should be 3 offers for the user3", 3, offers.size());

		List<Offer> offers2 = offersDao.getAllOffersByUsername(user4.getUsername());

		assertEquals("should be 0 offers for the user4", 0, offers2.size());

		List<Offer> offers3 = offersDao.getAllOffersByUsername(user1.getUsername());

		assertEquals("should be 1 offers for the user1", 1, offers3.size());

	}

	@Test
	public void testCreateOffer() {
		List<Offer> offers;
		usersDao.createUser(user1);
		usersDao.createUser(user2);
		usersDao.createUser(user3);
		usersDao.createUser(user4);

		offersDao.createOrUpdateOffer(offer);

		offers = offersDao.getAllOffers();
		assertEquals("should be 1 offers", 1, offers.size());

		assertEquals("retrive offer should equal inserted offer", offer, offers.get(0));

		offersDao.createOrUpdateOffer(offer);
		offersDao.createOrUpdateOffer(offer1);
		offersDao.createOrUpdateOffer(offer2);
		offersDao.createOrUpdateOffer(offer3);
		offersDao.createOrUpdateOffer(offer4);
		offersDao.createOrUpdateOffer(offer5);
		offersDao.createOrUpdateOffer(offer6);
		offersDao.createOrUpdateOffer(offer7);

		offers = offersDao.getAllOffers();
		assertEquals("should be 6 offers", 6, offers.size());
	}

}
