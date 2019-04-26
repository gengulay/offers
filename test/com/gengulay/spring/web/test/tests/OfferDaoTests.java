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

import com.gengulay.spring.web.dao.Offer;
import com.gengulay.spring.web.dao.OffersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/gengulay/spring/web/config/dao-context.xml",
		"classpath:com/gengulay/spring/web/config/security-context.xml",
		"classpath:com/gengulay/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from authorities");
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");

	}

	@Test
	public void testCreateUser() {

		Offer offer = new Offer("gengulay", "john@caveofprogramming.com", "This is a test offer.");

		assertTrue("Offer creation should return true", offersDao.createOffer(offer));

		List<Offer> offers = offersDao.getAllOffers();

		assertEquals("Should be one offer in database.", 1, offers.size());

		assertEquals("Retrieved offer should match created offer.", offer, offers.get(0));

		// Get the offer with ID filled in.
		offer = offers.get(0);

		offer.setText("Updated offer text.");
		assertTrue("Offer update should return true", offersDao.updateOffer(offer));

		Offer updated = offersDao.getOfferById(offer.getId());

		assertEquals("Updated offer should match retrieved updated offer", offer, updated);

		offersDao.deleteById(offer.getId());

		List<Offer> empty = offersDao.getAllOffers();

		assertEquals("Offers lists should be empty.", 0, empty.size());
	}

}
