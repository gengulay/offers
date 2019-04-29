package com.gengulay.spring.web.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.gengulay.spring.web.dao.Offer;
import com.gengulay.spring.web.dao.OffersDao;

@Service("offersService")
public class OfferService {

	private OffersDao offersDao;

	@Autowired
	public void setOffersDao(OffersDao offersDao) {
		this.offersDao = offersDao;
	}

	public List<Offer> getCurrent() {
		return offersDao.getAllOffers();

	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void create(@Valid Offer offer) {
		offersDao.createOrUpdateOffer(offer);

	}

	public boolean hasOffer(String name) {
		if (name == null)
			return false;

		List<Offer> offers = offersDao.getAllOffersByUsername(name);

		if (offers.size() == 0) {
			return false;
		}

		return true;
	}

	public Offer getOfferByUsername(String username) {

		if (username == null)
			return null;

		List<Offer> offers = offersDao.getAllOffersByUsername(username);

		if (offers.size() == 0)
			return null;

		return offers.get(0);
	}

	public void createOrUpdateOffer(@Valid Offer offer) {

		offersDao.createOrUpdateOffer(offer);

	}

	public void deleteById(int id) {
		offersDao.deleteOfferById(id);

	}

}
